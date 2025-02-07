package com.cdzeroly.wvp.storager.mapper;

import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.domain.RecordPlanItem;
import com.cdzeroly.wvp.domain.vo.RecordPlanVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MGARY
 */
@Mapper
public interface RecordPlanItemMapper extends BaseMapperPlus<RecordPlanItem, RecordPlanVo> {

}
