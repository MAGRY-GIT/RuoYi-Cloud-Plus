package com.cdzeroly.wvp.domain.bo;

import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author : MGARY
 *  报警信息管理
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlarmBo extends BaseEntity {

    /**
     * ID
     */
    private Long id;
    /**
     * 多个设备id
     */
    private List<String> deviceIds;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;


}
