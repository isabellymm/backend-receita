package com.integracao.farmacia_hospital.repository;
import com.integracao.farmacia_hospital.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    Optional<Farmacia> findByCnpj(String cnpj);
}
