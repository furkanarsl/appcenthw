package com.furkanarslan.appcenthw;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.service.UserService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppcenthwApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppcenthwApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveUser(new AppUser(null, "abc", "abc", "1234"));
            userService.saveUser(new AppUser(null, "abc", "123", "1234"));

        };
    }
}
