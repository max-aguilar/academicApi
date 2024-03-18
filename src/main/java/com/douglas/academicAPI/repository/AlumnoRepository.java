package com.douglas.academicAPI.repository;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Maestro;
import com.douglas.academicAPI.entity.Materia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
    List<Alumno> findByMaestro(Maestro maestro);

    List<Alumno> findByMaterias(Materia materia);
}
