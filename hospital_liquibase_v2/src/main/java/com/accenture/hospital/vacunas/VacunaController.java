package com.accenture.hospital.vacunas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/paciente/{pacienteId}/vacunas")
public class VacunaController {

    private final VacunaService vacunaService;

    @Autowired
    public VacunaController(VacunaService vacunaService) {
        this.vacunaService = vacunaService;
    }

    @GetMapping
    public List<Vacuna> listarVacunasPorPaciente(@PathVariable("pacienteId") Long pacienteId) {
        return vacunaService.listarVacunasPorPaciente(pacienteId);
    }

    @PostMapping
    public void crearVacuna(@PathVariable("pacienteId") Long pacienteId,
                            @RequestBody Vacuna vacuna) {
        vacunaService.anadirVacuna(pacienteId, vacuna);
    }

    @GetMapping("/{vacunaId}")
    public Vacuna obtenerVacuna(@PathVariable("pacienteId") Long pacienteId,
                                @PathVariable("vacunaId") Long vacunaId) {
        return vacunaService.obtenerVacunaPorId(pacienteId, vacunaId);
    }

    @DeleteMapping("/{vacunaId}")
    public void borrarVacuna(@PathVariable("pacienteId") Long pacienteId,
                             @PathVariable("vacunaId") Long vacunaId) {
        vacunaService.borrarVacuna(pacienteId, vacunaId);
    }
}
