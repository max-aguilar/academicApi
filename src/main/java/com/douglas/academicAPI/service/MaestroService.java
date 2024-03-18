package com.douglas.academicAPI.service;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Maestro;

import java.util.List;
import java.util.Optional;

public interface MaestroService {
    List<Maestro> findAll();

    Optional<Maestro> findById(Long id);

    Maestro save(Maestro maestro);

    Optional<Maestro> update(Long id, Maestro maestro);

    Optional<Maestro> delete(Long id);

}
