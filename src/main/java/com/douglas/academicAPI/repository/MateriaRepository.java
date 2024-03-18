package com.douglas.academicAPI.repository;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Materia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MateriaRepository extends CrudRepository<Materia, Long> {

    List<Materia> findByAlumnos(Alumno alumno);
}
