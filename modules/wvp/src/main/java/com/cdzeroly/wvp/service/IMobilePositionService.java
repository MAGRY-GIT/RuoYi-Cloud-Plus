package com.cdzeroly.wvp.service;


import com.cdzeroly.wvp.domain.MobilePosition;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.domain.bean.GPSMsgInfo;

import java.util.List;

public interface IMobilePositionService {

    void add(List<MobilePosition> mobilePositionList);

    void add(MobilePosition mobilePosition);

    List<MobilePosition> queryMobilePositions(String deviceId, String channelId, String startTime, String endTime);

    List<Platform> queryEnablePlatformListWithAsMessageChannel();

    MobilePosition queryLatestPosition(String deviceId);

    void updateStreamGPS(List<GPSMsgInfo> gpsMsgInfoList);

}
