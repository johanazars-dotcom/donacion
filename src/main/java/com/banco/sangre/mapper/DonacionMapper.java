package com.banco.sangre.mapper;

import com.banco.sangre.domain.Donacion;
import com.banco.sangre.dto.DonacionRequest;
import com.banco.sangre.dto.DonacionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonacionMapper {
    @Mapping(target = "donante.id", source = "donanteId")
    Donacion toEntity(DonacionRequest request);
    
    @Mapping(target = "donanteId", source = "donante.id")
    DonacionResponse toResponse(Donacion donacion);
}