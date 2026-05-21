package com.banco.sangre.repository;

import com.banco.sangre.domain.InventarioSangre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventarioSangreRepository extends JpaRepository<InventarioSangre, Long> {
    Optional<InventarioSangre> findByTipoSangre(String tipoSangre);
}