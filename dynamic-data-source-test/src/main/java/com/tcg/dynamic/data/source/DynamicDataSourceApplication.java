package com.tcg.dynamic.data.source;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class, MongoAutoConfiguration.class})
@MapperScan(annotationClass = Repository.class, basePackages = {"com.tcg.dynamic.data.source.mapper"})
public class DynamicDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDataSourceApplication.class, args);
	}

}
