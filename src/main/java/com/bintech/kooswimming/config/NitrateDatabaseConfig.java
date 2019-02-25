package com.bintech.kooswimming.config;

import com.bintech.kooswimming.entriy.SignUp;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NitrateDatabaseConfig {

    @Bean
    public Nitrite getNitrateDb() {
        return Nitrite
                .builder()
                .compressed()
                .filePath("db/main.db")
                .openOrCreate();

    }

    @Bean
    public ObjectRepository<SignUp> signUpRepository() {
        return getNitrateDb().getRepository(SignUp.class);
    }


}
