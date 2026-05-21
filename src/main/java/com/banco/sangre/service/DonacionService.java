package com.banco.sangre.service;

import com.banco.sangre.dto.DonacionRequest;
import com.banco.sangre.dto.DonacionResponse;
import java.util.List;

public interface DonacionService {
    DonacionResponse registrarDonacion(DonacionRequest request);
    List<DonacionResponse> listarDonaciones();
    DonacionResponse buscarPorId(Long id);
}