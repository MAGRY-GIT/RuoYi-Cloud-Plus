package com.cdzeroly.wvp.storager.mapper;

import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.service.domian.bean.RecordPlanItem;
import com.cdzeroly.wvp.service.domian.vo.RecordPlanVo;
import org.apache.ibatis.annotations.*;

/**
 * @author MGARY
 */
@Mapper
public interface RecordPlanItemMapper extends BaseMapperPlus<RecordPlanItem, RecordPlanVo> {

}
