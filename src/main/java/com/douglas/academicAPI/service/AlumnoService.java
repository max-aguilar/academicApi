package com.douglas.academicAPI.service;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Maestro;
import com.douglas.academicAPI.entity.Materia;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    List<Alumno> findAll();

    Optional<Alumno> findById(Long id);

    Alumno save(Alumno alumno);

    Optional<Alumno> update(Long id, Alumno alumno);

    Optional<Alumno> delete(Long id);

    List<Alumno> findByMaestro(Maestro maestro);

    List<Alumno> findByMaterias(Materia materia);

    Optional<Alumno> addMateria(Long alumnoId, Long materiaId);

    Optional<Alumno> removeMateria(Long alumnoId, Long materiaId);


}
