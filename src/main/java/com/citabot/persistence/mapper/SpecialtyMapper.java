package com.citabot.persistence.mapper;

import com.citabot.domain.Specialty;
import com.citabot.persistence.entity.Especialidad;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/*Esto porque voy a pasarme la especialdiad completa */
@Mapper(componentModel = "spring", uses = {SpecialtyMapper.class})
public interface SpecialtyMapper {
    @Mappings({
            @Mapping(source="especialidadId", target="idSpecialty"),
            @Mapping(source="nombre", target="name"),
            @Mapping(source="especialidades", target="specialties"),
            @Mapping(source="especialidad", target="specialty"),
    })
    Specialty toSpecialty(Especialidad especialidad);
    List<Specialty> toSpecialties(List<Especialidad> especialidades);

    @InheritInverseConfiguration
    Especialidad toEspecialidad(Specialty specialty);

    /*@InheritInverseConfiguration
    @Mapping(target = "propiedadQueNoQuiero", ignore=true)
    Especialidad toCategoria(Specialty specialty);*/
}
