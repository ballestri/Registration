package com.tech.Registration;

import com.tech.Registration.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RegistrationApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		assertThat(userService).isNotNull();
	}
}

