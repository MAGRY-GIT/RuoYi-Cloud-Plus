package com.cdzeroly.wvp.dubbo;


import cn.hutool.core.io.FileUtil;

class RemoteScreenshotServiceImplTest {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String rootPath = FileUtil.getAbsolutePath(userDir);
        System.out.println("项目根路径：" + rootPath);
    }
}
