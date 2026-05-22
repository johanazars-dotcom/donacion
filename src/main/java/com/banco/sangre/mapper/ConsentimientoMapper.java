package com.banco.sangre.mapper;

import com.banco.sangre.domain.Consentimiento;
import com.banco.sangre.dto.ConsentimientoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper para convertir entre Consentimiento (Entidad) y ConsentimientoRequest (DTO).
 * Usamos unmappedTargetPolicy = ReportingPolicy.IGNORE para evitar errores 
 * de compilación si existen propiedades en la entidad que no están en el DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConsentimientoMapper {

    @Mapping(target = "donante.id", source = "donanteId")
    @Mapping(target = "id", ignore = true) // Ignoramos el ID porque es autogenerado por MySQL
    Consentimiento toEntity(ConsentimientoRequest request);

    @Mapping(target = "donanteId", source = "donante.id")
    ConsentimientoRequest toDto(Consentimiento consentimiento);
}