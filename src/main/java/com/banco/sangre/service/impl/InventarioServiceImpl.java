package com.banco.sangre.service.impl;

import com.banco.sangre.domain.InventarioSangre;
import com.banco.sangre.dto.InventarioResponse;
import com.banco.sangre.exception.ResourceNotFoundException;
import com.banco.sangre.mapper.InventarioMapper;
import com.banco.sangre.repository.InventarioSangreRepository;
import com.banco.sangre.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioSangreRepository repository;
    private final InventarioMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponse> consultarInventario() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioResponse consultarPorTipo(String tipoSangre) {
        InventarioSangre inventario = repository.findByTipoSangre(tipoSangre)
            .orElseThrow(() -> new ResourceNotFoundException("Tipo de sangre no encontrado"));
        return mapper.toResponse(inventario);
    }

    @Override
    @Transactional
    public void actualizarInventario(String tipoSangre, Double cantidadMl) {
        InventarioSangre inventario = repository.findByTipoSangre(tipoSangre)
            .orElseThrow(() -> new ResourceNotFoundException("Tipo de sangre no encontrado"));
        inventario.setCantidadMl(inventario.getCantidadMl() + cantidadMl);
        repository.save(inventario);
    }
}