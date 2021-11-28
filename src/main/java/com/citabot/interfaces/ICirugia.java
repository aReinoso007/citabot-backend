package com.citabot.interfaces;

import com.citabot.model.Cirugia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ICirugia extends CrudRepository<Cirugia, Integer> {

    Optional<Cirugia> findCirugiasByFechaProcedimiento(Date date);

}
