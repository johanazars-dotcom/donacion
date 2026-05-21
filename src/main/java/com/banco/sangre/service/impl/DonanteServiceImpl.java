package com.banco.sangre.service.impl;

import com.banco.sangre.domain.Donante;
import com.banco.sangre.dto.DonanteRequest;
import com.banco.sangre.dto.DonanteResponse;
import com.banco.sangre.exception.BusinessException;
import com.banco.sangre.exception.ResourceNotFoundException;
import com.banco.sangre.mapper.DonanteMapper;
import com.banco.sangre.repository.DonanteRepository;
import com.banco.sangre.service.DonanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonanteServiceImpl implements DonanteService {

    private final DonanteRepository repository;
    private final DonanteMapper mapper;

    @Override
    @Transactional
    public DonanteResponse registrar(DonanteRequest request) {
        if (repository.existsByDocumento(request.getDocumento())) {
            throw new BusinessException("El documento ya esta registrado");
        }
        if (repository.existsByCorreo(request.getCorreo())) {
            throw new BusinessException("El correo ya esta registrado");
        }
        Donante donante = mapper.toEntity(request);
        return mapper.toResponse(repository.save(donante));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonanteResponse> listarTodos() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DonanteResponse buscarPorId(Long id) {
        return mapper.toResponse(obtenerDonante(id));
    }

    @Override
    @Transactional
    public DonanteResponse actualizar(Long id, DonanteRequest request) {
        Donante donante = obtenerDonante(id);
        mapper.updateEntity(donante, request);
        return mapper.toResponse(repository.save(donante));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Donante donante = obtenerDonante(id);
        repository.delete(donante);
    }

    private Donante obtenerDonante(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Donante no encontrado"));
    }
}