package com.banco.sangre.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventarioResponse {
    private String tipoSangre;
    private Double cantidadMl;
}