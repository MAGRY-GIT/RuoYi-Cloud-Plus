package com.cdzeroly.wvp.streamProxy.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.streamProxy.bean.StreamProxy;
import com.cdzeroly.wvp.streamProxy.mapper.provider.StreamProxyProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StreamProxyMapper extends BaseMapperPlus<StreamProxy,StreamProxy> {




    int update(StreamProxy streamProxyDto);



    @SelectProvider(type = StreamProxyProvider.class, method = "selectAll")
    List<StreamProxy> selectAll(@Param("build") Page<StreamProxy> build, @Param("query") String query, @Param("pulling") Boolean pulling, @Param("mediaServerId") String mediaServerId);

    @SelectProvider(type = StreamProxyProvider.class, method = "selectOneByAppAndStream")
    StreamProxy selectOneByAppAndStream(@Param("app") String app, @Param("stream") String stream);

    @SelectProvider(type = StreamProxyProvider.class, method = "selectForPushingInMediaServer")
    List<StreamProxy> selectForPushingInMediaServer(@Param("mediaServerId")  String mediaServerId, @Param("enable") boolean enable);



    int getOnline();




    void deleteByList(List<StreamProxy> streamProxiesForRemove);


    int online(@Param("id") int id);

    int offline(@Param("id") int id);

    @SelectProvider(type = StreamProxyProvider.class, method = "select")
    StreamProxy select(@Param("id") int id);


}
