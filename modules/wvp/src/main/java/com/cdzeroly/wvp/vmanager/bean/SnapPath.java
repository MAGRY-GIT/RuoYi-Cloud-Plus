package com.cdzeroly.wvp.vmanager.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
@Schema(description = "截图地址信息")
public class SnapPath {

    @Schema(description = "相对地址")
    private String path;

    @Schema(description = "绝对地址")
    private String absoluteFilePath;

    @Schema(description = "请求地址")
    private String url;


    public static SnapPath getInstance(String path, String absoluteFilePath, String url) {
        SnapPath snapPath = new SnapPath();
        snapPath.setPath(path);
        snapPath.setAbsoluteFilePath(absoluteFilePath);
        snapPath.setUrl(url);
        return snapPath;
    }


}
