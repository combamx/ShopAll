package com.shopAll.ShopAll;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ShopAllApplicationTests {

	@Test
	void contextLoads() {
	}

}
