package com.cdzeroly.wvp.gb28181.domian.bo;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class ChannelToRegionParam {

    private String civilCode;
    private List<Integer> channelIds;

}
