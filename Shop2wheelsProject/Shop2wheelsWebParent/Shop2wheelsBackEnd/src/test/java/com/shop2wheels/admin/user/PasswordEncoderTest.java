package com.shop2wheels.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

	@Test
	public void testEncodePassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "sonny2k";
		String encodedPassword = passwordEncoder.encode(rawPassword);

		System.out.println(encodedPassword);

		boolean matched = passwordEncoder.matches(rawPassword, encodedPassword);

		assertThat(matched).isTrue();
	}
}
