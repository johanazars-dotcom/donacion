package com.banco.sangre.controller;

import com.banco.sangre.dto.DonanteRequest;
import com.banco.sangre.dto.DonanteResponse;
import com.banco.sangre.service.DonanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donantes")
@RequiredArgsConstructor
public class DonanteController {

    private final DonanteService service;

    @PostMapping
    public ResponseEntity<DonanteResponse> registrar(@Valid @RequestBody DonanteRequest request) {
        return new ResponseEntity<>(service.registrar(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DonanteResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonanteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonanteResponse> actualizar(@PathVariable Long id, @Valid @RequestBody DonanteRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}