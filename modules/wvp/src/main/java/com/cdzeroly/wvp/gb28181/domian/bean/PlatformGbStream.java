package com.cdzeroly.wvp.gb28181.domian.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author MGARY
 */
@Data
public class PlatformGbStream {

    @Schema(description = "ID")
    private Integer gbStreamId;

    @Schema(description = "平台ID")
    private String platformId;

    @Schema(description = "目录ID")
    private String catalogId;

}
