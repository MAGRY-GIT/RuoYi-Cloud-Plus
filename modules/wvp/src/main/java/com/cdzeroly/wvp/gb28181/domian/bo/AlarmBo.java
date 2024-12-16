package com.cdzeroly.wvp.gb28181.domian.bo;

import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author : MGARY
 * @description : 报警信息管理
 * @createDate : 2024/12/12 11:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlarmBo extends BaseEntity {

    /**
     * ID
     */
    private Integer id;
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
