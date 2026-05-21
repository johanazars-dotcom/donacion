package com.banco.sangre.mapper;

import com.banco.sangre.domain.InventarioSangre;
import com.banco.sangre.dto.InventarioResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventarioMapper {
    InventarioResponse toResponse(InventarioSangre inventario);
}