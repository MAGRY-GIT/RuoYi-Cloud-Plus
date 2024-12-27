package com.cdzeroly.wvp.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @author MAGRY
 */
@Data
public class ChannelToRegionParam {

    private String civilCode;
    private List<Long> channelIds;

}
