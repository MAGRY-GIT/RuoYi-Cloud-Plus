package com.cdzeroly.wvp.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @author MGARY
 */
@Data
public class ChannelToGroupParam {

    private String parentId;
    private String businessGroup;
    private List<Long> channelIds;

}
