package com.citabot.domain.repository;

import com.citabot.domain.Subspecialty;

import java.util.List;

public interface SubspecialtyRepository {

    List<Subspecialty> getBySpecialtyId(int id);
    List<Subspecialty> getAll();
}
