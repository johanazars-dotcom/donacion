package com.banco.sangre.mapper;

import com.banco.sangre.domain.Donante;
import com.banco.sangre.dto.DonanteRequest;
import com.banco.sangre.dto.DonanteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DonanteMapper {
    Donante toEntity(DonanteRequest request);
    DonanteResponse toResponse(Donante donante);
    void updateEntity(@MappingTarget Donante donante, DonanteRequest request);
}