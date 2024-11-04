package com.integracao.farmacia_hospital.service;

import com.integracao.farmacia_hospital.model.Medico;
import com.integracao.farmacia_hospital.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository, PasswordEncoder passwordEncoder) {
        this.medicoRepository = medicoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Medico salvarMedico(Medico medico) {
        medico.setSenha(passwordEncoder.encode(medico.getSenha()));
        return medicoRepository.save(medico);
    }

    public Medico buscarPorUsuario(String usuario) {
        return medicoRepository.findByUsuario(usuario);
    }
}
