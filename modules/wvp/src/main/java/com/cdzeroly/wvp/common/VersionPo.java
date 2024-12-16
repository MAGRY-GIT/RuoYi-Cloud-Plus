package com.cdzeroly.wvp.common;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class VersionPo {
    /**
     * git的全版本号
     */
    @JSONField(name="GIT_Revision")
    private String GIT_Revision;
    /**
     * maven版本
     */
    @JSONField(name = "Create_By")
    private String Create_By;
    /**
     * git的分支
     */
    @JSONField(name = "GIT_BRANCH")
    private String GIT_BRANCH;
    /**
     * git的url
     */
    @JSONField(name = "GIT_URL")
    private String GIT_URL;
    /**
     * 构建日期
     */
    @JSONField(name = "BUILD_DATE")
    private String BUILD_DATE;
    /**
     * 构建日期
     */
    @JSONField(name = "GIT_DATE")
    private String GIT_DATE;
    /**
     * 项目名称 配合pom使用
     */
    @JSONField(name = "artifactId")
    private String artifactId;
    /**
     * git局部版本号
     */
    @JSONField(name = "GIT_Revision_SHORT")
    private String GIT_Revision_SHORT;
    /**
     * 项目的版本如2.0.1.0 配合pom使用
     */
    @JSONField(name = "version")
    private String version;
    /**
     * 子系统名称
     */
    @JSONField(name = "project")
    private String project;
    /**
     * jdk版本
     */
    @JSONField(name="Build_Jdk")
    private String Build_Jdk;

}
