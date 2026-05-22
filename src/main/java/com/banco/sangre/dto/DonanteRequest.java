package com.banco.sangre.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonanteRequest {
    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String documento;

    @NotNull
    @Past
    private LocalDate fechaNacimiento;

    @NotBlank
    private String tipoSangre;

    @NotNull
    @Positive
    private Double peso;

    private String telefono;

    @NotBlank
    @Email
    private String correo;

    private String direccion;

    private LocalDate fechaUltimaDonacion;

    @NotNull
    private Boolean aceptaConsentimiento;

    @NotBlank
    private String firmaConsentimiento;
}