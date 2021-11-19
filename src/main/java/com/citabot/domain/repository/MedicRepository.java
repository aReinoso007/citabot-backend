package com.citabot.domain.repository;

import com.citabot.domain.Medic;

import java.util.List;
import java.util.Optional;

public interface MedicRepository {
    List<Medic> getAll();
    Medic save(Medic medic);

    List<Medic> getAllByProfession(String profession);
    Optional<List<Medic>> getBySpeOrSubsp(int specialtyId, int subspecialtyId);

}
