package com.citabot.interfaces;

import com.citabot.model.Cirugia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICirugia extends CrudRepository<Cirugia, Integer> {
}
