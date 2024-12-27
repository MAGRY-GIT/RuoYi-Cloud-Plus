package com.cdzeroly.wvp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用于存储上级平台
 * @author MGARY
 */
@Mapper
@Repository
public interface PlatformMapper  extends BaseMapper<Platform> {

    /**
     * 查询列表
     */
    List<Platform> queryList(@Param("page") Page<Platform> page, @Param("query") String query);

    List<Platform> getByEnable(boolean enable);

    List<Platform> queryByEnableAndAsMessageChannel(boolean enable,boolean asMessageChannel);

    /**
     * 按服务器 GB ID 获取
     */
    Platform getByServerGBId(String platformGbId);



    /**
     * 更新状态
     * @return
     */
    int updateStatus(@Param("platformGbId") String platformGbId, @Param("online") boolean online);



}
