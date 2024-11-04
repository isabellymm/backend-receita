package com.integracao.farmacia_hospital.repository;
import com.integracao.farmacia_hospital.model.Dispensacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispensacaoRepository extends JpaRepository<Dispensacao, Long> {
    // Aqui você pode adicionar métodos personalizados de busca, se necessário.
}