package com.github.jwt.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jwt.secret}")
    private String value;

    @GetMapping("/")
    public TestDto index() {
        Random random = new SecureRandom();
        byte[] key = new byte[64];
        random.nextBytes(key);
        String secretKey = Base64.getEncoder().encodeToString(key);
        TestDto testDto = new TestDto();
        testDto.setPassword(passwordEncoder.encode("qwe"));
        testDto.setSecretKey(value);

        return testDto;
    }

    @GetMapping("/v1/test")
    public String test() {
        return "Hello World";
    }
}
