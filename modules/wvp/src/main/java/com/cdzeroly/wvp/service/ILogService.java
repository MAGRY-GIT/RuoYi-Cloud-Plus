package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.service.domian.bean.LogFileInfo;

import java.io.File;
import java.util.List;

public interface ILogService {
    List<LogFileInfo> queryList(String query, String startTime, String endTime);

    File getFileByName(String fileName);
}
