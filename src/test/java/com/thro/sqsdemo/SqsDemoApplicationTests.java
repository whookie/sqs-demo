package com.thro.sqsdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SqsDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	void AlwaysSuccessful() {
		assertTrue(true);
	}

}