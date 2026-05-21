package com.banco.sangre.service;

import com.banco.sangre.dto.ConsentimientoRequest;

public interface ConsentimientoService {
    void registrarConsentimiento(ConsentimientoRequest request);
    boolean tieneConsentimientoFirmado(Long donanteId);
}