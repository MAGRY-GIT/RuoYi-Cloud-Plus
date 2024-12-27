package com.cdzeroly.wvp.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @author MGAYR
 */
@Data
public class ChannelToGroupByGbDeviceParam {
    private List<Integer> deviceIds;
    private String parentId;
    private String businessGroup;
}
