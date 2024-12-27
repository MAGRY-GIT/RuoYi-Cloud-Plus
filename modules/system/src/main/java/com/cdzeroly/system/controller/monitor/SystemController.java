package com.cdzeroly.system.controller.monitor;

import com.cdzeroly.system.domain.bean.SystemAllInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2024/12/27 15:40
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/cache")
public class SystemController {


    @GetMapping(value = "/system/info")
    @ResponseBody
    @Operation(summary = "获取系统信息")
    public SystemAllInfo getSystemInfo() {
        // SystemAllInfo systemAllInfo = redisCatchStorage.getSystemInfo();
        // String cpuKey = VideoManagerConstants.SYSTEM_INFO_CPU_PREFIX + userSetting.getServerId();
        // String memKey = VideoManagerConstants.SYSTEM_INFO_MEM_PREFIX + userSetting.getServerId();
        // String netKey = VideoManagerConstants.SYSTEM_INFO_NET_PREFIX + userSetting.getServerId();
        // String diskKey = VideoManagerConstants.SYSTEM_INFO_DISK_PREFIX + userSetting.getServerId();
        // SystemAllInfo systemAllInfo = new SystemAllInfo();
        // systemAllInfo.setCpu(redisTemplate.opsForList().range(cpuKey, 0, -1));
        // systemAllInfo.setMem(redisTemplate.opsForList().range(memKey, 0, -1));
        // systemAllInfo.setNet(redisTemplate.opsForList().range(netKey, 0, -1));
        //
        // systemAllInfo.setDisk(redisTemplate.opsForValue().get(diskKey));
        // systemAllInfo.setNetTotal(SystemInfoUtils.getNetworkTotal());
        return null;
    }
}
