package com.accenture.hospital.vacunas;

import com.accenture.hospital.paciente.Paciente;
import com.accenture.hospital.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VacunaService {

    private final VacunaRepository vacunaRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public VacunaService(VacunaRepository vacunaRepository, PacienteRepository pacienteRepository) {
        this.vacunaRepository = vacunaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<Vacuna> listarVacunasPorPaciente(Long pacienteId) {
        return vacunaRepository.findByPacienteId(pacienteId);
    }

    public void anadirVacuna(Long pacienteId, Vacuna vacuna) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalStateException("Paciente no encontrado"));
        vacuna.setPaciente(paciente);
        vacunaRepository.save(vacuna);
    }

    public Vacuna obtenerVacunaPorId(Long pacienteId, Long vacunaId) {
        Vacuna vacuna = vacunaRepository.findById(vacunaId)
                .orElseThrow(() -> new IllegalStateException("Vacuna no encontrada"));

        if (Objects.equals(vacuna.getPaciente().getId(), pacienteId)) {
            return vacuna;
        }
        throw new IllegalStateException("La vacuna no pertenece al paciente");
    }

    public void borrarVacuna(Long pacienteId, Long vacunaId) {
        Vacuna vacuna = obtenerVacunaPorId(pacienteId, vacunaId);
        vacunaRepository.delete(vacuna);
    }
}
