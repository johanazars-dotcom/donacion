package com.banco.sangre.service;

import com.banco.sangre.dto.DonanteRequest;
import com.banco.sangre.dto.DonanteResponse;
import java.util.List;

public interface DonanteService {

    DonanteResponse registrar(DonanteRequest request);

    List<DonanteResponse> listarTodos();

    // Metodo para el filtro por tipo de sangre
    List<DonanteResponse> listarPorTipoSangre(String tipoSangre);

    DonanteResponse buscarPorId(Long id);

    DonanteResponse actualizar(Long id, DonanteRequest request);

    void eliminar(Long id);
}