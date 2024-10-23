package com.accenture.hospital.paciente;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteService {

    final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public void anadirPaciente(Paciente paciente) {
        Optional<Paciente> pacienteOptional =
                pacienteRepository.findByDni(paciente.getDni());
        if (pacienteOptional.isPresent()) {
            throw new IllegalStateException("Paciente ya existe");
        }
        pacienteRepository.save(paciente);
    }

    public void borrarPaciente(Long id) {
        boolean existe = pacienteRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    @Transactional
    public void actualizarPaciente(Long id, String nombre, String email) {

        Paciente paciente = pacienteRepository.findById(id).orElseThrow(()->
                new IllegalStateException("Paciente no encontrado"));
        if (nombre!=null && !nombre.isEmpty() && !Objects.equals(paciente.getNombre(), nombre)) {
            paciente.setNombre(nombre);
        }
        if (email!=null && !email.isEmpty() && !Objects.equals(paciente.getEmail(), email)) {
            paciente.setEmail(email);
        }
    }
}
