package com.integracao.farmacia_hospital.repository;
import com.integracao.farmacia_hospital.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findByUsuario(String usuario);
}