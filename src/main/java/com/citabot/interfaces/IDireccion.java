package com.citabot.interfaces;

import com.citabot.model.Direccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDireccion extends CrudRepository<Direccion, Integer> {


}
