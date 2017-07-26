package com.simpleuser;

import com.simpleuser.model.User;
import com.simpleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Adds some sample data to the db on startup
 */
@Component
public class SampleDataGenerator implements CommandLineRunner {
    @Value("${data.generate.sample}")
    private boolean generateSample;

    @Autowired
    private UserService service;

    @Override
    public void run(String... strings) throws Exception {
        if (generateSample) {
            for (int i = 1; i <= 5; i++) {
                service.createUser(new User("Sample Data " + i, "email" + i + "@test.ca"));
            }
        }
    }
}
