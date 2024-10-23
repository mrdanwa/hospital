package com.accenture.hospital.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/paciente")

public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @PostMapping
    public void postPaciente(@RequestBody Paciente paciente) {
        pacienteService.anadirPaciente(paciente);
    }

    @DeleteMapping(path = "{PacienteId}")
    public void borrarPaciente(@PathVariable("PacienteId") Long id) {
        pacienteService.borrarPaciente(id);
    }

    @PutMapping(path = "{PacienteId}")
    public void actualizarPaciente(@PathVariable("PacienteId") Long id,
                                   @RequestParam(required = false) String nombre,
                                   @RequestParam(required = false) String email) {
        pacienteService.actualizarPaciente(id, nombre, email);
    }

}
