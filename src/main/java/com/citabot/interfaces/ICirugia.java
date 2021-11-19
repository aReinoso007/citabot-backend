package com.citabot.interfaces;

import com.citabot.model.Cirugia;
import org.springframework.data.repository.CrudRepository;

public interface ICirugia extends CrudRepository<Cirugia, Integer> {
}
