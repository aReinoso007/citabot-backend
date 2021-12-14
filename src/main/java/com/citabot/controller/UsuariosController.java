package com.citabot.controller;

/*import com.citabot.model.Medico;
import com.citabot.model.Paciente;
import com.citabot.payload.request.LoginRequest;
import com.citabot.payload.response.JwtResponse;
import com.citabot.payload.response.MessageResponse;
import com.citabot.security.jwt.JwtUtils;
import com.citabot.security.services.CustomUserDetails;
import com.citabot.service.MedicoService;
import com.citabot.service.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
//@Slf4j
public class UsuariosController {

    /*@Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    MedicoService medicoService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/paciente_login")
    public ResponseEntity<?> authenticatePaciente(@Valid @RequestBody LoginRequest loginRequest){
        log.info("authentication: "+authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), encoder.encode(loginRequest.getPassword()))));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), encoder.encode(loginRequest.getPassword())));
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

    @PostMapping(value = "/medico_login")
    public ResponseEntity<?> authenticateMedico(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("pimbi", encoder.encode("calabaza23")));
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

    @PostMapping(value = "/medico_registro")
    public ResponseEntity<?> signupMedico(@RequestBody Medico medico){
        if(medicoService.existeUsername(medico.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: nombre de usuario ya esta en uso"));
        }
        if(medicoService.findByEmail(medico.getEmail())!=null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email ya registrado "));
        }
        medicoService.save(medico);
        return new ResponseEntity<>(new MessageResponse("Medico registrado exitosamente"), HttpStatus.CREATED);
    }

    @PostMapping(value = "/paciente_registro")
    public ResponseEntity<?> signupPaciente(@RequestBody Paciente paciente){
        if(pacienteService.existeUsername(paciente.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: nombre de usuario ya esta en uso"));
        }
        if(pacienteService.findByEmail(paciente.getEmail())!=null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email ya registrado "));
        }
        pacienteService.save(paciente);
        return new ResponseEntity<>(new MessageResponse("Paciente registrado exitosamente"), HttpStatus.CREATED);
    }*/

}
