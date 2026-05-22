package com.banco.sangre.repository;

import com.banco.sangre.domain.Donante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long> {
    
    boolean existsByDocumento(String documento);
    
    boolean existsByCorreo(String correo);

    // Metodo necesario para implementar el filtro por tipo de sangre
    List<Donante> findByTipoSangre(String tipoSangre);
}