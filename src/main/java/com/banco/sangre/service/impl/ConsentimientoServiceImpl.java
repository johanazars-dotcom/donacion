package com.banco.sangre.service.impl;

import com.banco.sangre.domain.Consentimiento;
import com.banco.sangre.domain.Donante;
import com.banco.sangre.dto.ConsentimientoRequest;
import com.banco.sangre.exception.ResourceNotFoundException;
import com.banco.sangre.mapper.ConsentimientoMapper;
import com.banco.sangre.repository.ConsentimientoRepository;
import com.banco.sangre.repository.DonanteRepository;
import com.banco.sangre.service.ConsentimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsentimientoServiceImpl implements ConsentimientoService {

    private final ConsentimientoRepository consentimientoRepository;
    private final DonanteRepository donanteRepository;
    private final ConsentimientoMapper mapper;

    @Override
    @Transactional
    public void registrarConsentimiento(ConsentimientoRequest request) {
        Donante donante = donanteRepository.findById(request.getDonanteId())
            .orElseThrow(() -> new ResourceNotFoundException("Donante no encontrado"));
        
        Consentimiento consentimiento = mapper.toEntity(request);
        consentimiento.setDonante(donante);
        consentimientoRepository.save(consentimiento);

        donante.setAceptaConsentimiento(true);
        donanteRepository.save(donante);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean tieneConsentimientoFirmado(Long donanteId) {
        return consentimientoRepository.findByDonanteId(donanteId).isPresent();
    }
}