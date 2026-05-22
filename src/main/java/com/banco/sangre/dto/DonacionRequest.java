package com.banco.sangre.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime; // Asegúrate de importar esto

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonacionRequest {
    @NotNull
    private Long donanteId;
    
    @NotNull
    @Positive
    private Double cantidadMl;
    
    private String observaciones;
    
    // Agrega estos campos que faltaban
    private String codigoDonacion;
    private LocalDateTime fechaDonacion; 
}