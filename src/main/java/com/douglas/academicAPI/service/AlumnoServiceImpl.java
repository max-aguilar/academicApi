package com.douglas.academicAPI.service;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Maestro;
import com.douglas.academicAPI.entity.Materia;
import com.douglas.academicAPI.repository.AlumnoRepository;
import com.douglas.academicAPI.repository.MaestroRepository;
import com.douglas.academicAPI.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Alumno> findAll() {
        return (List<Alumno>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        return repository.save(alumno);
    }

    @Override
    @Transactional
    public Optional<Alumno> update(Long id, Alumno alumno) {

        Optional<Alumno> alumnoOptional = repository.findById(id);

        if (alumnoOptional.isPresent()) {

            Alumno alumnoDb = alumnoOptional.orElseThrow();

            alumnoDb.setNombre(alumno.getNombre());
            alumnoDb.setApellido(alumno.getApellido());
            alumnoDb.setMaestro(alumno.getMaestro());

            return Optional.of(repository.save(alumnoDb));

        }

        return alumnoOptional;
    }

    @Transactional
    @Override
    public Optional<Alumno> delete(Long id) {
        Optional<Alumno> alumnoOptional = repository.findById(id);
        alumnoOptional.ifPresent(alumnoDb -> {
            repository.delete(alumnoDb);
        });
        return alumnoOptional;
    }

    @Override
    public List<Alumno> findByMaestro(Maestro maestro) {
        return repository.findByMaestro(maestro);
    }


    //_____________________________________________________________________________

    @Override
    public Optional<Alumno> addMateria(Long alumnoId, Long materiaId) {
        Optional<Alumno> alumnoOptional = repository.findById(alumnoId);
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaId);

        if (alumnoOptional.isPresent() && materiaOptional.isPresent()) {
            Alumno alumno = alumnoOptional.get();
            Materia materia = materiaOptional.get();

            alumno.getMaterias().add(materia);
            materia.getAlumnos().add(alumno);

            repository.save(alumno);
            materiaRepository.save(materia);

            return Optional.of(alumno);
        }

        return Optional.empty();
    }

    @Override
    public List<Alumno> findByMaterias(Materia materia) {
        return repository.findByMaterias(materia);
    }

    @Override
    public Optional<Alumno> removeMateria(Long alumnoId, Long materiaId) {
        Optional<Alumno> alumnoOptional = repository.findById(alumnoId);
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaId);

        if (alumnoOptional.isPresent() && materiaOptional.isPresent()) {
            Alumno alumno = alumnoOptional.get();
            Materia materia = materiaOptional.get();

            alumno.getMaterias().remove(materia);
            materia.getAlumnos().remove(alumno);

            repository.save(alumno);
            materiaRepository.save(materia);

            return Optional.of(alumno);
        }

        return Optional.empty();
    }


}
