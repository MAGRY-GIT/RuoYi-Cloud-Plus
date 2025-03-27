package com.cdzeroly.weather.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.weather.domain.vo.GroundStationVo;
import com.cdzeroly.weather.mapper.GroundStationMapper;
import com.cdzeroly.weather.utils.MapImagesUtil;
import com.cdzeroly.weather.utils.WeatherUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.resource.api.RemoteFileService;
import com.cdzeroly.weather.domain.vo.StationDataVo1;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jetbrains.annotations.NotNull;
import org.meteoinfo.common.Extent;
import org.meteoinfo.data.GridData;
import org.meteoinfo.geo.layer.VectorLayer;
import org.meteoinfo.geo.layout.MapLayout;
import org.meteoinfo.geo.mapview.MapView;
import org.meteoinfo.geo.meteodata.DrawMeteoData;
import org.meteoinfo.geometry.legend.LegendScheme;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.cdzeroly.weather.domain.bo.StationDataBo;
import com.cdzeroly.weather.domain.vo.StationDataVo;
import com.cdzeroly.weather.domain.StationData;
import com.cdzeroly.weather.mapper.StationDataMapper;
import com.cdzeroly.weather.service.IStationDataService;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 地面站点数据Service业务层处理
 *
 * @author MGARY
 * @date 2025-03-07
 */
@RequiredArgsConstructor
@Service
public class StationDataServiceImpl implements IStationDataService {

    private final StationDataMapper baseMapper;
    private final GroundStationMapper groundStationMapper;


    @DubboReference
    RemoteFileService remoteFileService;

    /**
     * 查询地面站点数据
     *
     * @param id 主键
     * @return 地面站点数据
     */
    @Override
    public StationDataVo queryById(Long id) throws Exception {
        DateTime parse = DateUtil.parse("2025-03-11 03:00:00");

        ArrayList<String> strings = Lists.newArrayList("prs", "prs_sea", "prs_max", "prs_min", "win_s_max", "win_s_inst_max", "win_d_inst_max", "win_d_avg2mi", "win_s_avg2mi", "win_d_s_max", "tem", "tem_max", "tem_min", "Rhu", "rhu_min", "pre3h");
        final Map<String, String> staticMap = getTypePathStringMap();
        List< StationDataVo1> list = baseMapper.findByDataTime(parse, "prs");
        staticMap.forEach((name,path)->{
            try {
                processStationData(list,name,path,parse);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return baseMapper.selectVoById(id);
    }

    @NotNull
    private Map<String, String> getTypePathStringMap() {
        String path = getClass().getClassLoader().getResource("color.scale/TEM.lgs").getFile();
        String rainfall = getClass().getClassLoader().getResource("color.scale/3rainfall.lgs").getFile();
        String dimianQiya = getClass().getClassLoader().getResource("color.scale/dimian-qiya.lgs").getFile();


        final Map<String, String> staticMap = Maps.newHashMap();
        staticMap.put("prs", dimianQiya);
        staticMap.put("prsSea", dimianQiya);
        staticMap.put("prsMax", dimianQiya);
        staticMap.put("prsMin", dimianQiya);
        staticMap.put("tem", path);
        staticMap.put("temMax", path);
        staticMap.put("temMin", path);
        staticMap.put("rhu", "");
        staticMap.put("rhuMin", "");
        staticMap.put("pre3h", rainfall);
        return staticMap;
    }

    private void processStationData(List<StationDataVo1> list, String name, String path, DateTime parse) throws Exception {
        org.meteoinfo.data.StationData stationData = MapImagesUtil.getStationData(list, name);
        ClassPathResource resource = new ClassPathResource("shp/sichuan.shp");
        String fn = resource.getFile().getAbsolutePath();
        //读取地图图层
        VectorLayer altMap = MapImagesUtil.getVectorLayer(fn);
        GridData gridData =  MapImagesUtil.getGridData(list, altMap, stationData);
        VectorLayer layer;
        //绘制图层
        if (StrUtil.isNotBlank(path)) {
            LegendScheme als = MapImagesUtil.readFromLgs(path);
            layer = DrawMeteoData.createShadedLayer(gridData, als, "", "", true);
        } else {
            layer = DrawMeteoData.createShadedLayer(gridData, "", "", true);
        }

        //创建视图
        MapView view = new MapView();
        layer = layer.clip(altMap);
        //叠加图层
        view.addLayer(layer);
        view.addLayer(altMap);

        MapLayout mapLayout = MapImagesUtil.getMapLayout(view);
        name = name + "_" + parse.getTime() + "_" + gridData.getBorderYMax() + "-" + gridData.getBorderXMax() + "," + gridData.getBorderYMin() + "_"  + gridData.getBorderXMin();
        //指定导出图像的路径
        String imagePath = FileUtil.getTmpDir().getAbsolutePath() + "\\" + name + ".png";
        //导出图片
        mapLayout.exportToPicture(imagePath);
        //转换为透明图片
        WeatherUtils.transparentProcessing(imagePath);
        remoteFileService.upload(name, name + ".png", "png", FileUtil.readBytes(imagePath));
    }


    private GridData processStationData(List<StationDataVo1> list, String name, Extent extent) throws Exception {
        org.meteoinfo.data.StationData stationData = MapImagesUtil.getStationData(list, name);
        ClassPathResource resource = new ClassPathResource("shp/sichuan.shp");
        String fn = resource.getFile().getAbsolutePath();
        //读取地图图层
        VectorLayer altMap = MapImagesUtil.getVectorLayer(fn);
        if (extent != null){
            altMap.setExtent(extent);
        }
        return MapImagesUtil.getGridData(list, altMap, stationData);
    }



    /**
     * 分页查询地面站点数据列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 地面站点数据分页列表
     */
    @Override
    public TableDataInfo<StationDataVo> queryPageList(StationDataBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<StationData> lqw = buildQueryWrapper(bo);
        Page<StationDataVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的地面站点数据列表
     *
     * @param bo 查询条件
     * @return 地面站点数据列表
     */
    @Override
    public List<StationDataVo> queryList(StationDataBo bo) {
        if(ObjectUtil.isNull(bo.getCreateTime())){
            DateTime parse = DateUtil.parse("2025-03-11 03:00:00");
            bo.setCreateTime(parse);
        }
        ArrayList<String> wind = Lists.newArrayList( "win_s_max", "win_s_inst_max", "win_d_inst_max", "win_d_avg2mi", "win_s_avg2mi", "win_d_s_max");
        if (!wind.contains(bo.getType())) {
            throw new RuntimeException("类型错误");
        }
        LambdaQueryWrapper<StationData> lqw = buildQueryWrapper(bo);

        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<StationData> buildQueryWrapper(StationDataBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<StationData> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getStationId() != null, StationData::getStationId, bo.getStationId());
        lqw.eq(bo.getPrs() != null, StationData::getPrs, bo.getPrs());
        lqw.eq(bo.getPrsSea() != null, StationData::getPrsSea, bo.getPrsSea());
        lqw.eq(bo.getPrsMax() != null, StationData::getPrsMax, bo.getPrsMax());
        lqw.eq(bo.getPrsMin() != null, StationData::getPrsMin, bo.getPrsMin());
        lqw.eq(bo.getWinSMax() != null, StationData::getWinSMax, bo.getWinSMax());
        lqw.eq(bo.getWinSInstMax() != null, StationData::getWinSInstMax, bo.getWinSInstMax());
        lqw.eq(bo.getWinDInstMax() != null, StationData::getWinDInstMax, bo.getWinDInstMax());
        lqw.eq(bo.getWinDAvg2mi() != null, StationData::getWinDAvg2mi, bo.getWinDAvg2mi());
        lqw.eq(bo.getWinSAvg2mi() != null, StationData::getWinSAvg2mi, bo.getWinSAvg2mi());
        lqw.eq(bo.getWinDSMax() != null, StationData::getWinDSMax, bo.getWinDSMax());
        lqw.eq(bo.getTem() != null, StationData::getTem, bo.getTem());
        lqw.eq(bo.getTemMax() != null, StationData::getTemMax, bo.getTemMax());
        lqw.eq(bo.getTemMin() != null, StationData::getTemMin, bo.getTemMin());
        lqw.eq(bo.getRhu() != null, StationData::getRhu, bo.getRhu());
        lqw.eq(bo.getRhuMin() != null, StationData::getRhuMin, bo.getRhuMin());
        lqw.eq(bo.getPre3h() != null, StationData::getPre3h, bo.getPre3h());
        return lqw;
    }

    /**
     * 新增地面站点数据
     *
     * @param bo 地面站点数据
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StationDataBo bo) {
        StationData add = MapstructUtils.convert(bo, StationData.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地面站点数据
     *
     * @param bo 地面站点数据
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StationDataBo bo) {
        StationData update = MapstructUtils.convert(bo, StationData.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(StationData entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除地面站点数据信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    @Override
    public void importData(MultipartFile file) {

        // 读取文件内容
        List<StationData> stationData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    // 跳过表头
                    continue;
                }
                if (line.trim().isEmpty()) {
                    // 跳过空行
                    continue;
                }
                // 按空格分割
                String[] columns = line.split("\\s+");
                StationData groundInfo = parseToGroundInfo(columns);
                stationData.add(groundInfo);
            }
            //存储数据
            List<Long> stationIds = stationData.stream().map(StationData::getStationId).toList();
            Map<Long, GroundStationVo> groundStationVoMap = groundStationMapper.selectVoByIds(stationIds).stream().collect(Collectors.toMap(GroundStationVo::getId, groundStation -> groundStation));

            stationData.stream().map(e -> {
                StationDataVo1 stationDataVo1 = new StationDataVo1();
                BeanUtil.copyProperties(e, stationDataVo1);
                GroundStationVo groundStationVo = groundStationVoMap.get(e.getStationId());
                stationDataVo1.setLatitude(groundStationVo.getLatitude());
                stationDataVo1.setLongitude(groundStationVo.getLongitude());
                return stationDataVo1;
            }).collect(Collectors.groupingBy(StationDataVo1::getCreateTime)).forEach((createTime, list) -> {
                getTypePathStringMap().forEach((name, path) -> {
                    try {
                        processStationData(list, name, path, DateTime.of(createTime));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });


            baseMapper.insertBatch(stationData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public GridData queryListGridDataVo(StationDataBo bo) throws Exception {

        ArrayList<String> types = Lists.newArrayList("prs", "prs_sea", "prs_max", "prs_min", "tem", "tem_max", "tem_min", "Rhu", "rhu_min", "pre3h");
        if (!types.contains(bo.getType())) {
            throw new RuntimeException("类型错误");
        }
        if(ObjectUtil.isNull(bo.getCreateTime())){
            DateTime parse = DateUtil.parse("2025-03-11 03:00:00");
            bo.setCreateTime(parse);
        }
        List< StationDataVo1> stationDataVos = baseMapper.findByDataTime(bo.getCreateTime(), "prs");


        return processStationData(stationDataVos, bo.getType(),bo.getExtent());


    }


    private StationData parseToGroundInfo(String[] columns) {
        StationData groundInfo = new StationData();

        groundInfo.setStationId(Long.parseLong(columns[0]));
        String dateTiem = columns[1] + "-" + ((columns[2].length() == 1) ? "0" + columns[2] : columns[2]) + "-" + ((columns[3].length() == 1) ? "0" + columns[3] : columns[3]) + "_" + ((columns[4].length() == 1) ? "0" + columns[4] : columns[4]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
        DateTime parse = DateUtil.parse(dateTiem, formatter);
        groundInfo.setCreateTime(parse);

        groundInfo.setPrs(Double.parseDouble(columns[5]));
        groundInfo.setPrsSea(Double.parseDouble(columns[6]));
        groundInfo.setPrsMax(Double.parseDouble(columns[7]));
        groundInfo.setPrsMin(Double.parseDouble(columns[8]));
        groundInfo.setWinSMax(Double.parseDouble(columns[9]));
        groundInfo.setWinSInstMax(Double.parseDouble(columns[10]));
        groundInfo.setWinDInstMax(Double.parseDouble(columns[11]));
        groundInfo.setWinDAvg2mi(Double.parseDouble(columns[12]));
        groundInfo.setWinSAvg2mi(Double.parseDouble(columns[13]));
        groundInfo.setWinDSMax(Double.parseDouble(columns[14]));
        groundInfo.setTem(Double.parseDouble(columns[15]));
        groundInfo.setTemMax(Double.parseDouble(columns[16]));
        groundInfo.setTemMin(Double.parseDouble(columns[17]));
        groundInfo.setRhu(Double.parseDouble(columns[18]));
        groundInfo.setRhuMin(Double.parseDouble(columns[19]));
        groundInfo.setPre3h(Double.parseDouble(columns[20]));


        return groundInfo;
    }
}
