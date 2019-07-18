package com.example.redis.dao;

import com.example.redis.RedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RedisApplication.class)
public class RedisApplicationTests {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("tt","dsadadd");
	}

}
