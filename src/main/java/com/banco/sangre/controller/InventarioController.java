package com.banco.sangre.controller;

import com.banco.sangre.dto.InventarioResponse;
import com.banco.sangre.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @GetMapping
    public ResponseEntity<List<InventarioResponse>> consultarInventario() {
        return ResponseEntity.ok(service.consultarInventario());
    }

    @GetMapping("/{tipoSangre}")
    public ResponseEntity<InventarioResponse> consultarPorTipo(@PathVariable String tipoSangre) {
        return ResponseEntity.ok(service.consultarPorTipo(tipoSangre));
    }
}