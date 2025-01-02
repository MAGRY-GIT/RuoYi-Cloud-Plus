package com.cdzeroly.wvp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.domain.StreamProxy;
import com.cdzeroly.wvp.domain.vo.StreamProxyVo;
import com.cdzeroly.wvp.mapper.provider.StreamProxyProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MGARY
 */
@Mapper
@Repository
public interface StreamProxyMapper extends BaseMapperPlus<StreamProxy, StreamProxyVo> {


    int update(StreamProxy streamProxyDto);


    @SelectProvider(type = StreamProxyProvider.class, method = "selectAll")
    List<StreamProxyVo> selectAll(@Param("build") Page<StreamProxy> build, @Param("query") String query, @Param("pulling") Boolean pulling, @Param("mediaServerId") String mediaServerId);

    @SelectProvider(type = StreamProxyProvider.class, method = "selectOneByAppAndStream")
    StreamProxyVo selectOneByAppAndStream(@Param("app") String app, @Param("stream") String stream);

    @SelectProvider(type = StreamProxyProvider.class, method = "selectForPushingInMediaServer")
    List<StreamProxyVo> selectForPushingInMediaServer(@Param("mediaServerId") String mediaServerId, @Param("enable") boolean enable);


    int getOnline();


    int online(@Param("id") Long id);

    int offline(@Param("id") Long id);

    @SelectProvider(type = StreamProxyProvider.class, method = "select")
    StreamProxyVo select(@Param("id") Long id);

    void removeStream(@Param("id") int id);

}
