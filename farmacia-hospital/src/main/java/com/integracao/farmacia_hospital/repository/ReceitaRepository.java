package com.integracao.farmacia_hospital.repository;
import com.integracao.farmacia_hospital.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    Receita findByAssinaturaDigital(String assinaturaDigital);
}