package com.cdzeroly.weather.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.weather.domain.StationData;
import com.cdzeroly.weather.domain.vo.GroundStationVo;
import com.cdzeroly.weather.mapper.GroundStationMapper;
import lombok.RequiredArgsConstructor;
import org.meteoinfo.data.meteodata.MeteoDataInfo;
import org.springframework.stereotype.Service;
import com.cdzeroly.weather.domain.bo.GroundInfoBo;
import com.cdzeroly.weather.domain.vo.GroundInfoVo;
import com.cdzeroly.weather.domain.GroundInfo;
import com.cdzeroly.weather.mapper.GroundInfoMapper;
import com.cdzeroly.weather.service.IGroundInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 气象数据Service业务层处理
 *
 * @author MGARY
 * @date 2025-03-07
 */
@RequiredArgsConstructor
@Service
public class GroundInfoServiceImpl implements IGroundInfoService {

    private final GroundInfoMapper baseMapper;
    private final GroundStationMapper groundStationMapper;

    /**
     * 查询气象数据
     *
     * @param id 主键
     * @return 气象数据
     */
    @Override
    public GroundInfoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询气象数据列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 气象数据分页列表
     */
    @Override
    public TableDataInfo<GroundInfoVo> queryPageList(GroundInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GroundInfo> lqw = buildQueryWrapper(bo);
        Page<GroundInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的气象数据列表
     *
     * @param bo 查询条件
     * @return 气象数据列表
     */
    @Override
    public List<GroundInfoVo> queryList(GroundInfoBo bo) {
        LambdaQueryWrapper<GroundInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<GroundInfo> buildQueryWrapper(GroundInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<GroundInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getWea()), GroundInfo::getWea, bo.getWea());
        lqw.eq(StringUtils.isNotBlank(bo.getWeaImg()), GroundInfo::getWeaImg, bo.getWeaImg());
        lqw.eq(StringUtils.isNotBlank(bo.getTem()), GroundInfo::getTem, bo.getTem());
        lqw.eq(StringUtils.isNotBlank(bo.getTem1()), GroundInfo::getTem1, bo.getTem1());
        lqw.eq(StringUtils.isNotBlank(bo.getTem2()), GroundInfo::getTem2, bo.getTem2());
        lqw.eq(StringUtils.isNotBlank(bo.getWin()), GroundInfo::getWin, bo.getWin());
        lqw.eq(StringUtils.isNotBlank(bo.getWinSpeed()), GroundInfo::getWinSpeed, bo.getWinSpeed());
        lqw.eq(StringUtils.isNotBlank(bo.getWinMeter()), GroundInfo::getWinMeter, bo.getWinMeter());
        lqw.eq(StringUtils.isNotBlank(bo.getHumidity()), GroundInfo::getHumidity, bo.getHumidity());
        lqw.eq(StringUtils.isNotBlank(bo.getVisibility()), GroundInfo::getVisibility, bo.getVisibility());
        lqw.eq(StringUtils.isNotBlank(bo.getPressure()), GroundInfo::getPressure, bo.getPressure());
        lqw.eq(StringUtils.isNotBlank(bo.getRainPcpn()), GroundInfo::getRainPcpn, bo.getRainPcpn());
        lqw.eq(StringUtils.isNotBlank(bo.getAir()), GroundInfo::getAir, bo.getAir());
        lqw.eq(StringUtils.isNotBlank(bo.getAirLevel()), GroundInfo::getAirLevel, bo.getAirLevel());
        lqw.eq(StringUtils.isNotBlank(bo.getAirTips()), GroundInfo::getAirTips, bo.getAirTips());
        return lqw;
    }

    /**
     * 新增气象数据
     *
     * @param bo 气象数据
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GroundInfoBo bo) {
        GroundInfo add = MapstructUtils.convert(bo, GroundInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改气象数据
     *
     * @param bo 气象数据
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GroundInfoBo bo) {
        GroundInfo update = MapstructUtils.convert(bo, GroundInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GroundInfo entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除气象数据信息
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
        List<String> lines = new ArrayList<>();
        List<StationData> groundInfoList = new ArrayList<>();



        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // 跳过表头
                }

                if (line.trim().isEmpty()) {
                    continue; // 跳过空行
                }
                // 按空格分割
                String[] columns = line.split("\\s+");
                StationData groundInfo = parseToGroundInfo(columns);
                groundInfoList.add(groundInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private StationData parseToGroundInfo(String[] columns) {
        StationData groundInfo = new StationData();

        groundInfo.setStationId(Long.parseLong(columns[0]));
        String dateTiem = columns[1] + "-" + columns[2] + "_" + columns[3] + "_" + columns[4];
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
