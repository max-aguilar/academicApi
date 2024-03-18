package com.douglas.academicAPI.service;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Materia;
import com.douglas.academicAPI.repository.AlumnoRepository;
import com.douglas.academicAPI.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements MateriaService{


    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Materia> findAll() {
        return (List<Materia>) materiaRepository.findAll();
    }

    @Override
    public Optional<Materia> findById(Long id) {
        return materiaRepository.findById(id);
    }

    @Override
    public Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public Optional<Materia> update(Long id, Materia materia) {
        Optional<Materia> materiaOptional = materiaRepository.findById(id);

        if (materiaOptional.isPresent()) {
            Materia materiaDb = materiaOptional.orElseThrow();

            materiaDb.setDescription(materia.getDescription());
            materiaDb.setPuntos(materia.getPuntos());

            return Optional.of(materiaRepository.save(materiaDb));
        }

        return materiaOptional;
    }

    @Override
    public Optional<Materia> delete(Long id) {
        Optional<Materia> materiaOptional = materiaRepository.findById(id);
        materiaOptional.ifPresent(materiaDb -> {
            materiaRepository.delete(materiaDb);
        });
        return materiaOptional;
    }

    @Override
    public List<Materia> findByAlumnos(Alumno alumno) {
        return materiaRepository.findByAlumnos(alumno);
    }

    @Override
    public Optional<Materia> addAlumno(Long materiaId, Long alumnoId) {
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaId);
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(alumnoId);

        if (materiaOptional.isPresent() && alumnoOptional.isPresent()) {
            Materia materia = materiaOptional.get();
            Alumno alumno = alumnoOptional.get();

            materia.getAlumnos().add(alumno);
            alumno.getMaterias().add(materia);

            materiaRepository.save(materia);
            alumnoRepository.save(alumno);

            return Optional.of(materia);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Materia> removeAlumno(Long materiaId, Long alumnoId) {
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaId);
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(alumnoId);

        if (materiaOptional.isPresent() && alumnoOptional.isPresent()) {
            Materia materia = materiaOptional.get();
            Alumno alumno = alumnoOptional.get();

            materia.getAlumnos().remove(alumno);
            alumno.getMaterias().remove(materia);

            materiaRepository.save(materia);
            alumnoRepository.save(alumno);

            return Optional.of(materia);
        }

        return Optional.empty();
    }
}
