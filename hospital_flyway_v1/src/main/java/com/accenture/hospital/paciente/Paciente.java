package com.accenture.hospital.paciente;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table

public class Paciente {
    @Id
    @SequenceGenerator(
            name = "PacienteSequence",
            sequenceName = "paciente_sequence",
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
