package com.example.myjpa;

import com.example.myjpa.support.VinspierRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = VinspierRepositoryFactoryBean.class) //开启自定义的VinspierRepositoryFactoryBean支持
public class MyJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyJpaApplication.class, args);
	}
}
