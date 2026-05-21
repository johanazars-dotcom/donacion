package com.banco.sangre.repository;

import com.banco.sangre.domain.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long> {
    List<Donacion> findByDonanteId(Long donanteId);
    boolean existsByCodigoDonacion(String codigoDonacion);
}