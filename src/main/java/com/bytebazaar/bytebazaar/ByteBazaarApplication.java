package com.bytebazaar.bytebazaar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ByteBazaarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteBazaarApplication.class, args);
    }

}
