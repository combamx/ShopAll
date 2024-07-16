package com.shopAll.ShopAll;

import org.springframework.boot.SpringApplication;

public class TestShopAllApplication {

	public static void main(String[] args) {
		SpringApplication.from(ShopAllApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
