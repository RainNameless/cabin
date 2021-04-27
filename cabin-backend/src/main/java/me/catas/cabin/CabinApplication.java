package me.catas.cabin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("me.catas.cabin.mapper")
@EnableScheduling
@SpringBootApplication
public class CabinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CabinApplication.class, args);
    }

}
