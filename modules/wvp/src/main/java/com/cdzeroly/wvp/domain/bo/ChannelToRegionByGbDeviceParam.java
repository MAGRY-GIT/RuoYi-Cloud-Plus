package com.cdzeroly.wvp.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @author MAGRY
 */
@Data
public class ChannelToRegionByGbDeviceParam {
    private List<Integer> deviceIds;
    private String civilCode;
}
