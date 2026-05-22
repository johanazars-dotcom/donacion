package com.banco.sangre.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "donors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String tipoSangre;

    @Column(nullable = false)
    private Double peso;

    private String telefono;

    @Column(nullable = false, unique = true)
    private String correo;

    private String direccion;

    private LocalDate fechaUltimaDonacion;

    @Column(nullable = false)
    private Boolean aceptaConsentimiento;
}