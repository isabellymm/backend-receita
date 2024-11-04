package com.integracao.farmacia_hospital.service;
import com.integracao.farmacia_hospital.model.Paciente;
import com.integracao.farmacia_hospital.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente salvarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> listarTodosPacientes() {
        return pacienteRepository.findAll();
    }

    public void deletarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> buscarPacientePorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }
}
