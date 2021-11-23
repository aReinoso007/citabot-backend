package com.citabot.interfaceService;

import com.citabot.model.DireccionClinica;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IDireccionClinicaService {

    public List<DireccionClinica>  listar();
    public DireccionClinica save(int clinica_id, int direccion_id);
    public Optional<DireccionClinica> listarById(int id);
    public DireccionClinica update(DireccionClinica direccionClinica);
    public String delete(int id);


}
