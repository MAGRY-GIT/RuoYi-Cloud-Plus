package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.TimeTable;
import com.cdzeroly.wvp.i1.temp.domain.PhotoTimeTable;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 拍照时间设置业务对象 photo_time_table
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PhotoTimeTable.class, reverseConvertGenerate = false)
public class PhotoTimeTableBo extends BaseEntity {


    String monitoringDeviceId;

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 通道号
     */
    @NotNull(message = "通道号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long channelNo;

    private String timeTables;
    /**
     * 时间表
     */
    @NotBlank(message = "时间表不能为空")
    private List<TimeTable> timeTableList;

    public List<TimeTable> getTimeTableList() {
        return JsonUtils.parseObject(timeTables, new TypeReference<>() {
        });
    }
}
