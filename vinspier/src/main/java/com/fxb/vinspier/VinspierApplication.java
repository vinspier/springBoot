package com.fxb.vinspier;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class VinspierApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(VinspierApplication.class);
				app.setBannerMode(Banner.Mode.OFF);
				app.run(args);
	//	SpringApplication.run(VinspierApplication.class, args);
	}
}
