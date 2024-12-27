package com.cdzeroly.wvp.storager.mapper;

import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.domain.CloudRecord;
import com.cdzeroly.wvp.domain.vo.CloudRecordItemVo;
import org.apache.ibatis.annotations.*;

/**
 * @author MGARY
 */
@Mapper
public interface CloudRecordServiceMapper extends BaseMapperPlus<CloudRecord, CloudRecordItemVo> {

}
