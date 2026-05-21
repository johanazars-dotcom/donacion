package com.banco.sangre.service;

import com.banco.sangre.dto.DonanteRequest;
import com.banco.sangre.dto.DonanteResponse;
import java.util.List;

public interface DonanteService {
    DonanteResponse registrar(DonanteRequest request);
    List<DonanteResponse> listarTodos();
    DonanteResponse buscarPorId(Long id);
    DonanteResponse actualizar(Long id, DonanteRequest request);
    void eliminar(Long id);
}