package com.cdzeroly.wvp.service;


import com.cdzeroly.wvp.gb28181.domian.MobilePosition;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.service.domian.bean.GPSMsgInfo;

import java.util.List;

public interface IMobilePositionService {

    void add(List<MobilePosition> mobilePositionList);

    void add(MobilePosition mobilePosition);

    List<MobilePosition> queryMobilePositions(String deviceId, String channelId, String startTime, String endTime);

    List<Platform> queryEnablePlatformListWithAsMessageChannel();

    MobilePosition queryLatestPosition(String deviceId);

    void updateStreamGPS(List<GPSMsgInfo> gpsMsgInfoList);

}
