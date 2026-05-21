package com.banco.sangre.mapper;

import com.banco.sangre.domain.Consentimiento;
import com.banco.sangre.dto.ConsentimientoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsentimientoMapper {
    @Mapping(target = "donante.id", source = "donanteId")
    Consentimiento toEntity(ConsentimientoRequest request);
}