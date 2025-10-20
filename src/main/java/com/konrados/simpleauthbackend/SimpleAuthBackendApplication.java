package com.konrados.simpleauthbackend;

import com.konrados.simpleauthbackend.user.User;
import com.konrados.simpleauthbackend.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleAuthBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAuthBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository repo) {
        return args -> {
            repo.save(new User("test", "1234"));
        };
    }

}

