package com.banco.sangre.repository;

import com.banco.sangre.domain.Donante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long> {
    
    boolean existsByDocumento(String documento);
    
    boolean existsByCorreo(String correo);
}