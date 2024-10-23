package com.accenture.hospital.historialmedico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/paciente/{pacienteId}/historialmedico")
public class HistorialMedicoController {

    private final HistorialMedicoService historialMedicoService;

    @Autowired
    public HistorialMedicoController(HistorialMedicoService historialMedicoService) {
        this.historialMedicoService = historialMedicoService;
    }

    @GetMapping
    public List<HistorialMedico> listarHistorialesPorPaciente(@PathVariable("pacienteId") Long pacienteId) {
        return historialMedicoService.listarHistorialesPorPaciente(pacienteId);
    }

    @PostMapping
    public void crearHistorial(@PathVariable("pacienteId") Long pacienteId,
                               @RequestBody HistorialMedico historialMedico) {
        historialMedicoService.anadirHistorial(pacienteId, historialMedico);
    }

    @GetMapping("/{historialId}")
    public HistorialMedico obtenerHistorial(@PathVariable("pacienteId") Long pacienteId,
                                            @PathVariable("historialId") Long historialId) {
        return historialMedicoService.obtenerHistorialPorId(pacienteId, historialId);
    }

    @DeleteMapping("/{historialId}")
    public void borrarHistorial(@PathVariable("pacienteId") Long pacienteId,
                                @PathVariable("historialId") Long historialId) {
        historialMedicoService.borrarHistorial(pacienteId, historialId);
    }

}
