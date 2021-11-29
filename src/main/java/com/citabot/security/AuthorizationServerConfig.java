package com.citabot.security;

import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaceService.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IMedicoService medicoService;

    /*Aqui se agregan los clientes
    * la primer vez se manda solo con la contrasena*/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
                .withClient("pacienteApp")
                .secret(bCryptPasswordEncoder.encode(aplicaciones.PACIENTE_APP))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read","write")
                .accessTokenValiditySeconds(1*60)
                .refreshTokenValiditySeconds(2*500000);
        clients.inMemory()
                .withClient("medicoApp")
                .secret(bCryptPasswordEncoder.encode(aplicaciones.MEDICO_APP))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read","write")
                .accessTokenValiditySeconds(2*50000)
                .refreshTokenValiditySeconds(2*500000);
        clients.inMemory()
                .withClient("rasaServer")
                .secret(bCryptPasswordEncoder.encode(aplicaciones.RASA_APP))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read","write")
                .accessTokenValiditySeconds(2*50000)
                .refreshTokenValiditySeconds(2*500000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.authenticationManager(authenticationManager).userDetailsService((UserDetailsService) pacienteService);
        endpoints.authenticationManager(authenticationManager).userDetailsService((UserDetailsService) medicoService);
    }

}
