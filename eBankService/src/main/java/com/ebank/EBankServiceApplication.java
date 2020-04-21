package com.ebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import com.ebank.config.RibbonConfiguration;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="ebankservice",configuration = RibbonConfiguration.class)
public class EBankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankServiceApplication.class, args);
	}

}
