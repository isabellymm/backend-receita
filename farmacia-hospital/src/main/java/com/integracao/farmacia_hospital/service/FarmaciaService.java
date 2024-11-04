package com.integracao.farmacia_hospital.service;

import com.integracao.farmacia_hospital.model.Farmacia;
import com.integracao.farmacia_hospital.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmaciaService {

    private final FarmaciaRepository farmaciaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FarmaciaService(FarmaciaRepository farmaciaRepository, PasswordEncoder passwordEncoder) {
        this.farmaciaRepository = farmaciaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Farmacia salvarFarmacia(Farmacia farmacia) {
        farmacia.setSenha(passwordEncoder.encode(farmacia.getSenha()));
        return farmaciaRepository.save(farmacia);
    }

    public Optional<Farmacia> buscarFarmaciaPorId(Long id) {
        return farmaciaRepository.findById(id);
    }

    public Optional<Optional<Farmacia>> buscarFarmaciaPorCnpj(String cnpj) {
        return Optional.ofNullable(farmaciaRepository.findByCnpj(cnpj));
    }

    public List<Farmacia> listarTodasFarmacias() {
        return farmaciaRepository.findAll();
    }

    public void deletarFarmacia(Long id) {
        farmaciaRepository.deleteById(id);
    }

}
