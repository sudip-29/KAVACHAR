package com.bytekoders.KavachAR

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Disabled("JWT_SECRET is provided through environment variables")
class KavachArApplicationTests {

	@Test
	fun contextLoads() {
	}
}