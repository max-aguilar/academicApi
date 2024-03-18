package com.douglas.academicAPI.service;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Materia;

import java.util.List;
import java.util.Optional;

public interface MateriaService {

    List<Materia> findAll();

    Optional<Materia> findById(Long id);

    Materia save(Materia materia);

    Optional<Materia> update(Long id, Materia materia);

    Optional<Materia> delete(Long id);

    List<Materia> findByAlumnos(Alumno alumno);

    Optional<Materia> addAlumno(Long materiaId, Long alumnoId);

    Optional<Materia> removeAlumno(Long materiaId, Long alumnoId);
}
