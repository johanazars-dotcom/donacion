package com.banco.sangre.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsentimientoRequest {
    @NotNull
    private Long donanteId;
    @NotBlank
    private String firmaDocumento;
}