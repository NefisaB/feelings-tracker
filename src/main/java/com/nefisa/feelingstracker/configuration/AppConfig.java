package com.nefisa.feelingstracker.configuration;

import com.nefisa.feelingstracker.entity.Feeling;
import com.nefisa.feelingstracker.repositories.FeelingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner initDatabase(FeelingRepository feelingRepository){
        return args -> {
            feelingRepository.save(new Feeling("Peaceful", LocalDateTime.now()));
            feelingRepository.save(new Feeling("Curious", LocalDateTime.now()));
            feelingRepository.save(new Feeling("Happy", LocalDateTime.now()));
        };
    }
}
