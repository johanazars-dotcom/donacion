package com.banco.sangre.service.impl;

import com.banco.sangre.domain.Donacion;
import com.banco.sangre.domain.Donante;
import com.banco.sangre.dto.DonacionRequest;
import com.banco.sangre.dto.DonacionResponse;
import com.banco.sangre.exception.ConsentimientoNoFirmadoException;
import com.banco.sangre.exception.DonanteNoAptoException;
import com.banco.sangre.exception.ResourceNotFoundException;
import com.banco.sangre.mapper.DonacionMapper;
import com.banco.sangre.repository.DonacionRepository;
import com.banco.sangre.repository.DonanteRepository;
import com.banco.sangre.service.ConsentimientoService;
import com.banco.sangre.service.DonacionService;
import com.banco.sangre.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonacionServiceImpl implements DonacionService {

    private final DonacionRepository donacionRepository;
    private final DonanteRepository donanteRepository;
    private final ConsentimientoService consentimientoService;
    private final InventarioService inventarioService;
    private final DonacionMapper mapper;

    @Override
    @Transactional
    public DonacionResponse registrarDonacion(DonacionRequest request) {
        Donante donante = donanteRepository.findById(request.getDonanteId())
            .orElseThrow(() -> new ResourceNotFoundException("Donante no encontrado"));

        validarReglasMedicas(donante);

        Donacion donacion = mapper.toEntity(request);
        donacion.setDonante(donante);
        donacion.setFechaDonacion(LocalDateTime.now());
        donacion.setCodigoDonacion(UUID.randomUUID().toString());

        donacion = donacionRepository.save(donacion);

        donante.setFechaUltimaDonacion(LocalDate.now());
        donanteRepository.save(donante);

        inventarioService.actualizarInventario(donante.getTipoSangre(), request.getCantidadMl());

        return mapper.toResponse(donacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonacionResponse> listarDonaciones() {
        return donacionRepository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DonacionResponse buscarPorId(Long id) {
        Donacion donacion = donacionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Donacion no encontrada"));
        return mapper.toResponse(donacion);
    }

    private void validarReglasMedicas(Donante donante) {
        int edad = Period.between(donante.getFechaNacimiento(), LocalDate.now()).getYears();
        if (edad < 18) {
            throw new DonanteNoAptoException("El donante es menor de 18 anos");
        }

        if (donante.getPeso() < 50.0) {
            throw new DonanteNoAptoException("El donante pesa menos de 50 kg");
        }

        if (donante.getFechaUltimaDonacion() != null) {
            long mesesTranscurridos = ChronoUnit.MONTHS.between(donante.getFechaUltimaDonacion(), LocalDate.now());
            if (mesesTranscurridos < 3) {
                throw new DonanteNoAptoException("Han pasado menos de 3 meses desde la ultima donacion");
            }
        }

        if (!Boolean.TRUE.equals(donante.getAceptaConsentimiento())) {
            throw new ConsentimientoNoFirmadoException("El donante no ha aceptado el consentimiento");
        }

        if (!consentimientoService.tieneConsentimientoFirmado(donante.getId())) {
            throw new ConsentimientoNoFirmadoException("El donante no tiene firma registrada en el sistema");
        }
    }
}