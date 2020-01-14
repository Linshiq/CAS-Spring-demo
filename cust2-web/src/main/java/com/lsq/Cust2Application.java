package com.lsq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // 需要连接数据库就把这个注释去掉
public class Cust2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cust2Application.class, args);
	}

}
