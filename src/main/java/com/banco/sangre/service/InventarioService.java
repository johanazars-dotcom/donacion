package com.banco.sangre.service;

import com.banco.sangre.dto.InventarioResponse;
import java.util.List;

public interface InventarioService {
    List<InventarioResponse> consultarInventario();
    InventarioResponse consultarPorTipo(String tipoSangre);
    void actualizarInventario(String tipoSangre, Double cantidadMl);
}
