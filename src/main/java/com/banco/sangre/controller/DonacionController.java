package com.banco.sangre.controller;

import com.banco.sangre.dto.DonacionRequest;
import com.banco.sangre.dto.DonacionResponse;
import com.banco.sangre.service.DonacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donaciones")
@RequiredArgsConstructor
public class DonacionController {

    private final DonacionService service;

    @PostMapping
    public ResponseEntity<DonacionResponse> registrarDonacion(@Valid @RequestBody DonacionRequest request) {
        return new ResponseEntity<>(service.registrarDonacion(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DonacionResponse>> listarDonaciones() {
        return ResponseEntity.ok(service.listarDonaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonacionResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}