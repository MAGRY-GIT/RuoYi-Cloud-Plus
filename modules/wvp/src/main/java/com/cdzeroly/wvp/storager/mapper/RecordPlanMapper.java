package com.cdzeroly.wvp.storager.mapper;

import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.wvp.service.bean.RecordPlan;
import com.cdzeroly.wvp.service.vo.RecordPlanVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author MGARY
 */
@Mapper
public interface RecordPlanMapper   extends BaseMapperPlus<RecordPlan, RecordPlanVo> {



    @Select(" <script>" +
            " SELECT wrp.*, (select count(1) from wvp_device_channel where record_plan_id = wrp.id) AS channelCount\n" +
            " FROM wvp_record_plan wrp where  1=1" +
            " <if test='query != null'> AND (name LIKE concat('%',#{query},'%') escape '/' )</if> " +
            " </script>")
    List<RecordPlan> query(@Param("query") String query, PageQuery pageQuery);









    @Select(" <script>" +
            " select wdc.id from wvp_device_channel wdc left join wvp_record_plan_item wrpi on wrpi.plan_id = wdc.record_plan_id " +
            " where  wrpi.week_day = #{week} and wrpi.start &lt;= #{index} and stop &gt;= #{index} group by wdc.id" +
            " </script>")
    List<Integer> queryRecordIng(@Param("week") int week, @Param("index") int index);
}
