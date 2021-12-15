package com.citabot.controller;

import com.citabot.Constants;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Medico;
import com.citabot.model.Paciente;
import com.citabot.model.formulario.FMedico;
import com.citabot.model.formulario.FPaciente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
/*Parte publica de los usuarios para login y registro */
public class UsuariosController {
    @Autowired
    IMedicoService medicoService;
    @Autowired
    IPacienteService pacienteService;

    @PostMapping(value = "/medico_login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> loginMedico(@RequestParam("email") String email, @RequestParam("password") String password){
        Medico medicodb = medicoService.findByEmailAndContrasena(email, password);
        if(medicodb!=null){
            return new ResponseEntity<>(generateJWTTokenMedico(medicodb), HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/paciente_login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> loginPaciente(@RequestParam("email") String email, @RequestParam("password") String password){
        Paciente pacienteDb = pacienteService.buscarPorEmailYContrasena(email, password);
        if(pacienteDb!=null){
            return new ResponseEntity<>(generateJWTTokenPaciente(pacienteDb), HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/medico_registro")
    public ResponseEntity<?> signUpMedico(@RequestBody FMedico fMedico){
        Medico medicodb = medicoService.findByEmail(fMedico.getEmail());
        String message = "Paciente ya registrado con email: "+ fMedico.getEmail();
        if(medicodb==null){
            medicoService.save(fMedico);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

    }



    /*Formato de fecha: anio-mes-dia */
    @PostMapping(value = "/paciente_registro")
    public ResponseEntity<?> signUpPaciente(@RequestBody FPaciente form){
        Paciente pacientedb = pacienteService.findByEmail(form.getEmail());
        String message = "Paciente ya registrado con email: "+ form.getEmail();
        if(pacientedb==null){
            pacienteService.save(form);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    private long currentTm(){
        return System.currentTimeMillis();
    }

    private Map<String, String> generateJWTTokenMedico(Medico medico){
        /*Set a timeout for both medico and paciente
        * Date expiration = getExpirationDate()-> currentDate + 3 months or 6
        * then add to builder
        * .setExpiration(expiration) */
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(currentTm()))
                .claim("userId", medico.getUsuarioId())
                .claim("email", medico.getEmail())
                .claim("nombre", medico.getNombre())
                .claim("apellido",medico.getApellido())
                .claim("profesion",medico.getProfesion())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    private Map<String, String> generateJWTTokenPaciente(Paciente paciente){
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(currentTm()))
                .claim("userId", paciente.getUsuarioId())
                .claim("email", paciente.getEmail())
                .claim("nombre", paciente.getNombre())
                .claim("apellido", paciente.getApellido())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
