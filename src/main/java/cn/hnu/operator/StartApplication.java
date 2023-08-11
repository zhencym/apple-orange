package cn.hnu.operator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 博客启动类
 *
 */

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        // app-orange ，启动！
        new Starter().start();
        SpringApplication.run(StartApplication.class, args);
    }


}
