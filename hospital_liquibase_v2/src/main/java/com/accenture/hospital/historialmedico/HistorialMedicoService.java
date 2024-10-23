package com.accenture.hospital.historialmedico;

import com.accenture.hospital.paciente.Paciente;
import com.accenture.hospital.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HistorialMedicoService {

    private final HistorialMedicoRepository historialMedicoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public HistorialMedicoService(HistorialMedicoRepository historialMedicoRepository, PacienteRepository pacienteRepository) {
        this.historialMedicoRepository = historialMedicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<HistorialMedico> listarHistorialesPorPaciente(Long pacienteId) {
        return historialMedicoRepository.findByPacienteId(pacienteId);
    }

    public void anadirHistorial(Long pacienteId, HistorialMedico historialMedico) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalStateException("Paciente no encontrado"));
        historialMedico.setPaciente(paciente);
        historialMedicoRepository.save(historialMedico);
    }

    public HistorialMedico obtenerHistorialPorId(Long pacienteId, Long historialId) {
        HistorialMedico historialMedico = historialMedicoRepository.findById(historialId)
                .orElseThrow(() -> new IllegalStateException("Historial médico no encontrado"));

        if (Objects.equals(historialMedico.getPaciente().getId(), pacienteId)) {
            return historialMedico;
        }
        throw new IllegalStateException("El historial médico no pertenece al paciente");
    }

    public void borrarHistorial(Long pacienteId, Long historialId) {
        HistorialMedico historialMedico = obtenerHistorialPorId(pacienteId, historialId);
        historialMedicoRepository.delete(historialMedico);
    }

}
