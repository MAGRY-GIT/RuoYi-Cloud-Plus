package com.cdzeroly.wvp.gb28181.domian.bo;

import lombok.Data;

import java.util.List;

@Data
public class ChannelToGroupParam {

    private String parentId;
    private String businessGroup;
    private List<Integer> channelIds;

}
