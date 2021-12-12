package com.citabot.security.services;

import com.citabot.model.Medico;
import com.citabot.model.Paciente;
import com.citabot.service.MedicoService;
import com.citabot.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PacienteService pacienteData;
    @Autowired
    private MedicoService medicoData;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Medico medico = medicoData.findByUsername(username);
        if(medico!=null){
            return new CustomUserDetails(medico.getUsuarioId(), medico.getUsername(), medico.getPassword(), medico.getRol());
        }else{
            Paciente paciente = pacienteData.buscarPorUsername(username);
            if(paciente!=null){
                return new CustomUserDetails(medico.getUsuarioId(), medico.getUsername(),medico.getPassword(), medico.getRol());
            }
        }

        throw new UsernameNotFoundException("Usuario: "+username+"'not found'");
    }
}
