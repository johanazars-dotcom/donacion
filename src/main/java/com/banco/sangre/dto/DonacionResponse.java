package com.banco.sangre.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonacionResponse {
    private Long id;
    private Long donanteId;
    private String codigoDonacion;
    private Double cantidadMl;
    private LocalDateTime fechaDonacion;
    private String observaciones;
}