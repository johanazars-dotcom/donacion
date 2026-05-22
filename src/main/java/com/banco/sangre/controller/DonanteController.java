package com.banco.sangre.controller;

import com.banco.sangre.dto.DonanteRequest;
import com.banco.sangre.dto.DonanteResponse;
import com.banco.sangre.service.DonanteService;
import com.banco.sangre.service.FileStorageService;
import com.banco.sangre.service.PdfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/donantes")
@RequiredArgsConstructor
public class DonanteController {

    private final DonanteService service;
    private final PdfService pdfService;
    private final FileStorageService fileStorageService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<DonanteResponse> registrar(
            @RequestParam("datos") String datosJson,
            @RequestParam("firma") MultipartFile firma) throws Exception {

        DonanteRequest request = objectMapper.readValue(datosJson, DonanteRequest.class);

        String nombreArchivo = fileStorageService.guardarFirma(firma);
        request.setFirmaConsentimiento(nombreArchivo);

        return new ResponseEntity<>(service.registrar(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DonanteResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<DonanteResponse>> listarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(service.listarPorTipoSangre(tipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonanteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonanteResponse> actualizar(
            @PathVariable Long id,
            @RequestParam("datos") String datosJson,
            @RequestParam(value = "firma", required = false) MultipartFile firma) throws Exception {

        DonanteRequest request = objectMapper.readValue(datosJson, DonanteRequest.class);

        if (firma != null && !firma.isEmpty()) {
            String nombreArchivo = fileStorageService.guardarFirma(firma);
            request.setFirmaConsentimiento(nombreArchivo);
        }

        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exportar-pdf")
    public ResponseEntity<byte[]> exportarPdf() throws Exception {
        List<DonanteResponse> donantes = service.listarTodos();
        byte[] pdf = pdfService.exportarDonantesPdf(donantes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=donantes.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}