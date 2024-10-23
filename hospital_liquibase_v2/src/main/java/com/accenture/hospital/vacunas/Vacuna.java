package com.accenture.hospital.vacunas;

import com.accenture.hospital.paciente.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vacuna")
public class Vacuna {

    @Id
    @SequenceGenerator(name = "vacuna_sequence", sequenceName = "vacuna_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacuna_sequence")
    private Long id;

    private String vacuna;
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnore
    private Paciente paciente;

    public Vacuna() {
    }

    public Vacuna(String vacuna, LocalDate fecha, Paciente paciente) {
        this.vacuna = vacuna;
        this.fecha = fecha;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
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
        return "Vacuna{"
                + "id=" + id
                + ", vacuna='"
                + vacuna + '\''
                + ", fecha="
                + fecha
                + '}';
    }
}
