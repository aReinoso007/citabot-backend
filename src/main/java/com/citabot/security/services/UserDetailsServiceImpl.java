package com.citabot.security.services;

/*import com.citabot.model.Medico;
import com.citabot.model.Paciente;
import com.citabot.service.MedicoService;
import com.citabot.service.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j*/
public class UserDetailsServiceImpl/* implements UserDetailsService */{

    /*@Autowired
    private PacienteService pacienteData;
    @Autowired
    private MedicoService medicoData;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Medico medico = medicoData.findByUsername(username);
        log.info("Medico de impl: "+ medico.getEmail());
        if(medico!=null){
            log.info("build de medico: "+CustomUserDetails.buildMedico(medico).getPassword());
            return CustomUserDetails.buildMedico(medico);
        }else{
            Paciente paciente = pacienteData.buscarPorUsername(username);
            if(paciente!=null){
                return CustomUserDetails.buiddPaciente(paciente);
            }
        }

        throw new UsernameNotFoundException("Usuario: "+username+"'not found'");
    }*/
}
