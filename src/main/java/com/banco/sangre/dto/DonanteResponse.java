package com.banco.sangre.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonanteResponse {
    private Long id;
    private String nombres;
    private String apellidos;
    private String documento;
    private LocalDate fechaNacimiento;
    private String tipoSangre;
    private Double peso;
    private String telefono;
    private String correo;
    private String direccion;
    private LocalDate fechaUltimaDonacion;
    private Boolean aceptaConsentimiento;
    
    // Este campo permite enviar la firma al frontend cuando consultes el donante
    private String firmaConsentimiento;
}