package com.citabot.controller;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping("/pacientes")
    public String listar(Model model){
    List<Paciente> pacienteList = service.listar();
    model.addAttribute("pacienteList", pacienteList);
    return "pacienteList";
    }

    //@GetMapping("/patients/{id}")

    @PostMapping("pacientes/")
    public String save(@Validated Paciente p, Model model){
        service.save(p);
        return "redirect:/pacientes";
    }


}
