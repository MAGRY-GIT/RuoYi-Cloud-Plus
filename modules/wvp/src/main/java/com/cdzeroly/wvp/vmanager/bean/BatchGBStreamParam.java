package com.cdzeroly.wvp.vmanager.bean;

import com.cdzeroly.wvp.gb28181.domian.bean.GbStream;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lin
 */
@Setter
@Getter
@Schema(description = "多个推流信息")
public class BatchGBStreamParam {
    @Schema(description = "推流信息列表")
    private List<GbStream> gbStreams;

}
