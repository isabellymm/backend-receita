package com.integracao.farmacia_hospital.repository;
import com.integracao.farmacia_hospital.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByNome(String nome);
}
