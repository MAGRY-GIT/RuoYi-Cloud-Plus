package com.cdzeroly.wvp.storager.mapper;

import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.wvp.domain.RecordPlan;
import com.cdzeroly.wvp.domain.vo.RecordPlanVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MGARY
 */
@Mapper
public interface RecordPlanMapper   extends BaseMapperPlus<RecordPlan, RecordPlanVo> {


    List<RecordPlan> query( @Param("page") PageQuery pageQuery,@Param("query") String query);


    List<Long> queryRecordIng(@Param("week") int week, @Param("index") int index);
}
