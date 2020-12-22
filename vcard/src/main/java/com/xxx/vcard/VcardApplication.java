package com.xxx.vcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication()
// 开启事务管理
@EnableTransactionManagement
@MapperScan(basePackages = "com.xxx.vcard.mapper")
public class VcardApplication {
    public static void main(String[] args) {
        SpringApplication.run(VcardApplication.class, args);
    }
}
