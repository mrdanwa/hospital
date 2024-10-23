package com.accenture.hospital.historialmedico;

import com.accenture.hospital.paciente.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "historial_medico")
public class HistorialMedico {

    @Id
    @SequenceGenerator(
            name = "historial_medico_sequence",
            sequenceName = "historial_medico_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "historial_medico_sequence"
    )
    private Long id;

    private String descripcion;
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnore
    private Paciente paciente;

    // Constructores
    public HistorialMedico() {
    }

    public HistorialMedico(String descripcion, LocalDate fecha, Paciente paciente) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.paciente = paciente;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "HistorialMedico{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
