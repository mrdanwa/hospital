package com.accenture.hospital.paciente;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PacienteConfig {

    @Bean
    CommandLineRunner commandLineRunner(PacienteRepository repository) {
        return args -> {
            Paciente Manuel = new Paciente(
                    "Manuel",
                    "12345679S",
                    "manuel@gmail.com",
                    LocalDate.of(1999, Month.JANUARY, 5));
            Paciente Anton = new Paciente(
                    "Anton",
                    "12345638S",
                    "anton@gmail.com",
                    LocalDate.of(1989, Month.FEBRUARY, 5));
            repository.saveAll(List.of(Manuel, Anton));
        };
    }
}
