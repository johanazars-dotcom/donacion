package com.banco.sangre.dto;

import jakarta.validation.constraints.*;
import lombok.*;

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
}