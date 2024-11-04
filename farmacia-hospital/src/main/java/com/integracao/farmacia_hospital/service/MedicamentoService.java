package com.integracao.farmacia_hospital.service;
import com.integracao.farmacia_hospital.model.Medicamento;
import com.integracao.farmacia_hospital.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // Método para salvar um medicamento
    public Medicamento salvarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    // Método para buscar um medicamento por ID
    public Optional<Medicamento> buscarMedicamentoPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    // Método para listar todos os medicamentos
    public List<Medicamento> listarTodosMedicamentos() {
        return medicamentoRepository.findAll();
    }

    // Método para deletar um medicamento por ID
    public void deletarMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }

    // Método para buscar medicamentos por nome
    public List<Medicamento> buscarMedicamentoPorNome(String nome) {
        return medicamentoRepository.findByNome(nome);
    }
}
