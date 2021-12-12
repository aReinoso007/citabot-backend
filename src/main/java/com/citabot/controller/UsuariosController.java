package com.citabot.controller;

import com.citabot.model.Medico;
import com.citabot.payload.response.JwtResponse;
import com.citabot.security.jwt.JwtUtils;
import com.citabot.security.services.CustomUserDetails;
import com.citabot.service.MedicoService;
import com.citabot.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UsuariosController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PacienteService pacienteService;
    @Autowired
    MedicoService medicoService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/paciente/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> authenticatePaciente(@RequestParam("username") String username, @RequestParam("password") String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                                                    userDetails.getUsername(),
                                                    userDetails.getEmail(),
                                                    roles));

    }

    @PostMapping(value = "medico/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> authenticateMedico(@RequestParam("username") String username, @RequestParam("password") String password){
        Authentication authentication2 = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication2);
        String jwt = jwtUtils.generateJwtToken(authentication2);
        CustomUserDetails userDetails = (CustomUserDetails) authentication2.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                                                    userDetails.getUsername(),
                                                    userDetails.getEmail(),
                                                    roles));
    }

    /*@PostMapping(value = "/medio/registro")
    public ResponseEntity<?> signupMedico(@RequestBody Medico medico){

    }*/

}
