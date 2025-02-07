package com.cdzeroly.wvp.storager.mapper;

import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.vo.MediaServerVo;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author MGARY
 */
@Mapper
public interface MediaServerMapper extends BaseMapperPlus<MediaServer, MediaServerVo> {



}
