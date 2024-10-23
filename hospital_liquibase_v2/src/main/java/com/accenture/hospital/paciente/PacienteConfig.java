package com.accenture.hospital.paciente;

import com.accenture.hospital.historialmedico.HistorialMedico;
import com.accenture.hospital.historialmedico.HistorialMedicoRepository;
import com.accenture.hospital.vacunas.Vacuna;
import com.accenture.hospital.vacunas.VacunaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PacienteConfig {

    @Bean
    CommandLineRunner commandLineRunner(PacienteRepository pacienteRepository, HistorialMedicoRepository historialMedicoRepository, VacunaRepository vacunaRepository) {
        return args -> {
            Paciente manuel = new Paciente(
                    "12345679S",
                    "Manuel",
                    "manuel@gmail.com",
                    LocalDate.of(1999, Month.JANUARY, 5)
            );

            Paciente anton = new Paciente(
                    "12345638S",
                    "Anton",
                    "anton@gmail.com",
                    LocalDate.of(1989, Month.FEBRUARY, 5)
            );

            pacienteRepository.saveAll(List.of(manuel, anton));

            HistorialMedico historial1 = new HistorialMedico(
                    "Consulta general",
                    LocalDate.now(),
                    manuel
            );

            HistorialMedico historial2 = new HistorialMedico(
                    "Revisión anual",
                    LocalDate.now(),
                    anton
            );

            historialMedicoRepository.saveAll(List.of(historial1, historial2));

            Vacuna vacuna1 = new Vacuna(
                    "Covid-19",
                    LocalDate.now(),
                    manuel
            );

            Vacuna vacuna2 = new Vacuna(
                    "Covid-19",
                    LocalDate.now(),
                    anton
            );

            vacunaRepository.saveAll(List.of(vacuna1, vacuna2));
        };
    }
}
