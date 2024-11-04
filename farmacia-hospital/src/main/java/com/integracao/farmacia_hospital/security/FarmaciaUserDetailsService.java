package com.integracao.farmacia_hospital.security;

import com.integracao.farmacia_hospital.model.Farmacia;
import com.integracao.farmacia_hospital.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
@Service("farmaciaUserDetailsService")
public class FarmaciaUserDetailsService implements UserDetailsService {

    private final FarmaciaRepository farmaciaRepository;

    @Autowired
    public FarmaciaUserDetailsService(FarmaciaRepository farmaciaRepository) {
        this.farmaciaRepository = farmaciaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cnpj) throws UsernameNotFoundException {
        Farmacia farmacia = farmaciaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new UsernameNotFoundException("Farmácia não encontrada"));

        return new User(farmacia.getCnpj(), farmacia.getSenha(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_FARMACIA")));
    }
}
