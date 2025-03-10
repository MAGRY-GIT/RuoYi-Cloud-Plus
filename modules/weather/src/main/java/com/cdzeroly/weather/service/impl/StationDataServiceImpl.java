package com.cdzeroly.weather.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.CoordinateTransformUtil;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.weather.domain.vo.StationDataVo1;
import lombok.RequiredArgsConstructor;
import org.meteoinfo.common.Extent;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.GridDataSetting;
import org.meteoinfo.geo.analysis.InterpolationMethods;
import org.meteoinfo.geo.analysis.InterpolationSetting;
import org.meteoinfo.geo.layer.VectorLayer;
import org.meteoinfo.geo.layout.MapLayout;
import org.meteoinfo.geo.mapdata.MapDataManage;
import org.meteoinfo.geo.mapview.MapView;
import org.meteoinfo.geo.meteodata.DrawMeteoData;
import org.meteoinfo.geo.util.GeoMathUtil;
import org.meteoinfo.geometry.legend.LegendScheme;
import org.meteoinfo.geometry.legend.PolygonBreak;
import org.springframework.stereotype.Service;
import com.cdzeroly.weather.domain.bo.StationDataBo;
import com.cdzeroly.weather.domain.vo.StationDataVo;
import com.cdzeroly.weather.domain.StationData;
import com.cdzeroly.weather.mapper.StationDataMapper;
import com.cdzeroly.weather.service.IStationDataService;
import org.springframework.web.multipart.MultipartFile;
import ucar.nc2.dataset.CoordinateTransform;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

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

    /**
     * 查询地面站点数据
     *
     * @param id 主键
     * @return 地面站点数据
     */
    @Override
    public StationDataVo queryById(Long id) throws Exception {
        DateTime parse = DateUtil.parse("2025-01-07 09:00:00");
        List< StationDataVo1> list = baseMapper.findByDataTime(parse, "prs");


        //从数据库查询cimiss数据
         //创建站点格点
        org.meteoinfo.data.StationData stationData = new org.meteoinfo.data.StationData();
        //循环数据将值塞入格点中
        for (StationDataVo1 stationData1 : list) {
            stationData.addData(stationData1.getStationName(),
                stationData1.getLongitude(),
                stationData1.getLatitude(),
                stationData1.getTem());
        }

        String fn = "C:\\Users\\Administrator\\Downloads\\四川省1\\四川省.shp";
        //读取地图图层
        VectorLayer altMap = MapDataManage.readMapFile_ShapeFile(fn);
    //创建网格设置参数
        GridDataSetting gridDataSetting = new GridDataSetting();
//设定数据区域
        gridDataSetting.dataExtent = altMap.getExtent();
//设定格点数
        gridDataSetting.xNum = list.size();
        gridDataSetting.yNum = list.size();
//创建插值设置
        InterpolationSetting interpolationSetting = new InterpolationSetting();
//设定格点配置
        interpolationSetting.setGridDataSetting(gridDataSetting);
//设定插值方法
        interpolationSetting.setInterpolationMethod(InterpolationMethods.KRIGING);
//设定搜索半径
        interpolationSetting.setRadius(10);
//设置最小点数
        interpolationSetting.setMinPointNum(1);
//插值到格点
        GridData gridData = GeoMathUtil.interpolateData(stationData, interpolationSetting);
//        GridData gridData = stationData.interpolateData(interpolationSetting);
        LegendScheme als = readFromLgs("C:\\Users\\Administrator\\Downloads\\色阶\\TEM.lgs");
//绘制图层
        VectorLayer layer = DrawMeteoData.createShadedLayer(gridData,als,"","",true);
//创建视图
        MapView view = new MapView();
        PolygonBreak pb = (PolygonBreak) altMap.getLegendScheme().getLegendBreak(0);
        pb.setDrawFill(false);
        pb.setOutlineColor(Color.GRAY);
        layer = layer.clip(altMap);
//叠加图层
        view.addLayer(layer);
        view.addLayer(altMap);


/**
 * 通用方法,可以抽成工具类
 */
        MapLayout layout  = new MapLayout();
//去除图形边框
        layout.getActiveMapFrame().setDrawNeatLine(false);
//区域边界
        Extent extent = view.getExtent();
//设置矩形的宽和高
        Rectangle bounds = new Rectangle(800, (int) (800 * 1D / extent.getWidth() * extent.getHeight()));
//设置地图边框
        layout.setPageBounds(new Rectangle(0, 0, bounds.width, bounds.height));
//设置页面边框
        layout.getActiveMapFrame().setLayoutBounds(new Rectangle(0, 0, bounds.width, bounds.height));
        layout.getActiveMapFrame().setMapView(view);
//        String imagePath = "C:\\Users\\Administrator\\Desktop\\99.png";
//        String name =   gridData.getBorderYMax() + "-"+  gridData.getBorderXMax() + "," +  gridData.getBorderYMin()+"-"+  gridData.getBorderXMin();
        Double[] doubles1 = CoordinateTransformUtil.WGS84ToGCJ02(gridData.getBorderYMax(), gridData.getBorderXMax());
        Double[] doubles = CoordinateTransformUtil.WGS84ToGCJ02(gridData.getBorderYMin(), gridData.getBorderXMin());
        String name =   doubles1[0] + "-"+  doubles1[1]  + "," +  doubles[0]+"-"+ doubles[1];
        //指定导出图像的路径
        String imagePath = "C:\\Users\\Administrator\\Desktop\\" + name+".png";
        layout.exportToPicture(imagePath);
        transparentProcessing(imagePath);
        return baseMapper.selectVoById(id);
    } /**
     * 获取矢量图层
     * @return
     * @throws Exception
     */
    private static VectorLayer getVectorLayer(String  shapeFilepath) throws Exception {
        if (shapeFilepath == null){
            throw ServiceException.build ("请输入矢量图层路径");
        }
        //读取地图A
        VectorLayer scmap = MapDataManage.readMapFile_ShapeFile(shapeFilepath);
        //描述地图边界线
        PolygonBreak pb = (PolygonBreak) scmap.getLegendScheme().getLegendBreak(0);
        //是否设置填充
        pb.setDrawFill(false);
        //设置轮廓大小
        pb.setOutlineSize(2f);
        //设置轮廓颜色
        pb.setOutlineColor(Color.black);
        return scmap;
    }

    private static void transparentProcessing(String imagePath) throws IOException {
        //读取图片
        BufferedImage bi = ImageIO.read(new File(imagePath));
        //类型转换
        BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.drawImage(bi, null, 0, 0);
        //透明处理
        int alpha = 0;
        for(int i=img.getMinY(); i<img.getHeight(); i++){
            for(int j=img.getMinX(); j<img.getWidth(); j++){
                int rgb = img.getRGB(j, i);
                //透明部分不需要处理
                if(rgb < 0){
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    //将白色剔除
                    Color color = Color.white;
                    if(color.getRed() == R && color.getGreen() == G && color.getBlue() == B){
                        alpha = 0;
                    }
                    else {
                        alpha = 255;
                    }
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);
                    img.setRGB(j, i, rgb);
                }
            }
        }
        //释放资源
        g.dispose();
        ImageIO.write(img, "png", new File(imagePath));
    }

    public static LegendScheme readFromLgs(String path) throws Exception {
        LegendScheme scheme = new LegendScheme();
        scheme.importFromXMLFile(path, false);
        return scheme;
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
    private void validEntityBeforeSave(StationData entity){
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
        if(isValid){
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
            baseMapper.insertBatch(stationData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private StationData parseToGroundInfo(String[] columns) {
        StationData groundInfo = new StationData();

        groundInfo.setStationId(Long.parseLong(columns[0]));
        String dateTiem = columns[1] + "-" +((columns[2].length() == 1 )? "0"+ columns[2]: columns[2]) + "-" + ((columns[3].length() == 1 )? "0"+ columns[3]: columns[3]) + "_" + ((columns[4].length() == 1 )? "0"+ columns[4]: columns[4]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
        DateTime parse = DateUtil.parse(dateTiem, formatter);
        groundInfo.setCreateTime(parse);

        groundInfo.setPrs(Double.parseDouble( columns[5]));
        groundInfo.setPrsSea(Double.parseDouble( columns[6]));
        groundInfo.setPrsMax(Double.parseDouble( columns[7]));
        groundInfo.setPrsMin(Double.parseDouble( columns[8]));
        groundInfo.setWinSMax(Double.parseDouble( columns[9]));
        groundInfo.setWinSInstMax(Double.parseDouble( columns[10]));
        groundInfo.setWinDInstMax(Double.parseDouble( columns[11]));
        groundInfo.setWinDAvg2mi(Double.parseDouble( columns[12]));
        groundInfo.setWinSAvg2mi(Double.parseDouble( columns[13]));
        groundInfo.setWinDSMax(Double.parseDouble( columns[14]));
        groundInfo.setTem(Double.parseDouble( columns[15]));
        groundInfo.setTemMax(Double.parseDouble( columns[16]));
        groundInfo.setTemMin(Double.parseDouble( columns[17]));
        groundInfo.setRhu(Double.parseDouble( columns[18]));
        groundInfo.setRhuMin(Double.parseDouble( columns[19]));
        groundInfo.setPre3h(Double.parseDouble( columns[20]));






        return groundInfo;
    }
}
