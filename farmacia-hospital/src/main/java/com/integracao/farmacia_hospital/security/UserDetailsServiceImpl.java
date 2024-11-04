package com.integracao.farmacia_hospital.security;

import com.integracao.farmacia_hospital.model.Medico;
import com.integracao.farmacia_hospital.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MedicoRepository medicoRepository;

    @Autowired
    public UserDetailsServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medico medico = medicoRepository.findByUsuario(username);
        if (medico == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new User(medico.getUsuario(), medico.getSenha(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEDICO")));
    }
}
