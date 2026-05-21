package com.banco.sangre.controller;

import com.banco.sangre.dto.ConsentimientoRequest;
import com.banco.sangre.service.ConsentimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consentimientos")
@RequiredArgsConstructor
public class ConsentimientoController {

    private final ConsentimientoService service;

    @PostMapping
    public ResponseEntity<Void> registrarConsentimiento(@Valid @RequestBody ConsentimientoRequest request) {
        service.registrarConsentimiento(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{donanteId}")
    public ResponseEntity<Boolean> verificarConsentimiento(@PathVariable Long donanteId) {
        return ResponseEntity.ok(service.tieneConsentimientoFirmado(donanteId));
    }
}