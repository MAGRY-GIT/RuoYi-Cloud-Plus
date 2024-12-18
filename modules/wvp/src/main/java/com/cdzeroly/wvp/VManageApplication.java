package com.cdzeroly.wvp;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author MGARY
 */
@EnableDubbo
@SpringBootApplication
@EnableScheduling
public class VManageApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(VManageApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  WVP模块启动成功   ლ(´ڡ`ლ)ﾞ  ");

	}

}
