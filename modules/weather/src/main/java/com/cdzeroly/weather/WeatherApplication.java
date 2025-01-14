package com.cdzeroly.weather;

import com.cdzeroly.common.core.domain.GeoPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * @author MGARY
 */
@SpringBootApplication
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WeatherApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  气象模块启动成功   ლ(´ڡ`ლ)ﾞ  ");

    }

}
