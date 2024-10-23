package com.accenture.hospital.paciente;

import com.accenture.hospital.historialmedico.HistorialMedico;
import com.accenture.hospital.vacunas.Vacuna;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table

public class Paciente {
    @Id
    @SequenceGenerator(
            name = "PacienteSequence",
            sequenceName = "PacienteSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PacienteSequence"
    )
    private long id;
    private String dni;
    private String nombre;
    private String email;
    private LocalDate fechaNacimiento;
    @Transient
    private Integer edad;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialMedico> historialMedico = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacuna> vacunas = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(int id, String dni, String nombre, String email, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Paciente(String dni, String nombre, String email, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<HistorialMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(List<HistorialMedico> historialMedico) {
        this.historialMedico = historialMedico;
    }

    public void addHistorialMedico(HistorialMedico historial) {
        historialMedico.add(historial);
        historial.setPaciente(this);
    }

    public void removeHistorialMedico(HistorialMedico historial) {
        historialMedico.remove(historial);
        historial.setPaciente(null);
    }

    public List<Vacuna> getVacuna() {
        return vacunas;
    }

    public void setVacuna(List<Vacuna> vacuna) {
        this.vacunas = vacuna;
    }

    public void addVacuna(Vacuna vacuna) {
        vacunas.add(vacuna);
        vacuna.setPaciente(this);
    }

    public void removeVacuna(Vacuna vacuna) {
        vacunas.remove(vacuna);
        vacuna.setPaciente(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                '}';
    }

}
