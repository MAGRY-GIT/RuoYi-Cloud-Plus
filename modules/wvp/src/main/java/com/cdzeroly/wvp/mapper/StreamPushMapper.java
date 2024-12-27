package com.cdzeroly.wvp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.common.enums.ChannelDataType;
import com.cdzeroly.wvp.domain.StreamPush;
import com.cdzeroly.wvp.domain.vo.StreamPushVo;
import com.cdzeroly.wvp.domain.bean.StreamPushItemFromRedis;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author MGARY
 */
@Mapper
@Repository
public interface StreamPushMapper extends BaseMapperPlus<StreamPush, StreamPushVo> {
    Integer dataType = ChannelDataType.GB28181.value;

    List<StreamPushVo> selectPage(@Param("page") Page<StreamPushVo> page, @Param("query") String query, @Param("pushing") Boolean pushing, @Param("mediaServerId") String mediaServerId);


    List<StreamPushVo> selectAll(@Param("query") String query, @Param("pushing") Boolean pushing, @Param("mediaServerId") String mediaServerId);

    StreamPushVo selectByAppAndStream(@Param("app") String app, @Param("stream") String stream);

    List<StreamPushVo> selectAllByMediaServerId(String mediaServerId);

    /**
     * 按媒体服务器 ID 选择全部，但不包含 GB ID
     * @param mediaServerId
     * @return
     */
    List<StreamPushVo> selectAllByMediaServerIdWithOutGbID(String mediaServerId);


    int updatePushStatus(@Param("id") Long id, @Param("pushing") boolean pushing);



    List<StreamPushVo> getListFromRedis(List<StreamPushItemFromRedis> offlineStreams);


    List<String> getAllAppAndStream();



    int getAllPushing(Boolean usePushingAsStatus);

    @MapKey("uniqueKey")
    Map<String, StreamPushVo> getAllAppAndStreamMap();

    @MapKey("gbDeviceId")
    Map<String, StreamPushVo> getAllGBId();

    StreamPushVo queryOne(@Param("id") Long id);


    List<StreamPushVo> selectInSet(Set<Long> ids);


    int batchUpdate(List<StreamPushVo> streamPushVoItemForUpdate);
}
