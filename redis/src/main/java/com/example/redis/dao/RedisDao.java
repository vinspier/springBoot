package com.example.redis.dao;

import com.example.redis.domain.Person;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * redis 操作层
 * 将来自不同的服务器的客户端请求的session
 * 进行验证并保存到redis中 实现多客户端session共享
 * */
@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    public Person getPerson(String name){
        System.out.println("========= 从redis中获取用户信息 =============");
        Person person = (Person)redisTemplate.opsForValue().get(name);
        return person;
    }

    public Person delPerson(String name){
        Person person = (Person)redisTemplate.opsForValue().get(name);
        if(person != null){
            System.out.println("========= 从redis中删除用户信息 =============");
            redisTemplate.delete(name);
        }
        return person;
    }


    public void setPerson(String name,Person person){
        System.out.println("========= 将用户信息放入缓存 =============");
        redisTemplate.opsForValue().set(name,person);
       // redisTemplate.expire(name,10, TimeUnit.SECONDS);
    }
}
