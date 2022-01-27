/**
 * Copyright (c) 2016-2019 浩浩商城 All rights reserved.
 *
 * https://www.slowcom.com
 *
 * 版权所有，侵权必究！
 */

package com.uu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AdminApplication{

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		log.info("============= SpringBoot Admin Project Start Success =============");
	}

}
