package com.douglas.academicAPI.service;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Maestro;
import com.douglas.academicAPI.repository.AlumnoRepository;
import com.douglas.academicAPI.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MaestroServiceImpl implements MaestroService{

    @Autowired
    private MaestroRepository repository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Maestro> findAll() {
        return (List<Maestro>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Maestro> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Maestro save(Maestro maestro) {
        return repository.save(maestro);
    }

    @Override
    @Transactional
    public Optional<Maestro> update(Long id, Maestro maestro) {

        Optional<Maestro> maestroOptional = repository.findById(id);

        if (maestroOptional.isPresent()) {

            Maestro maestroDb = maestroOptional.orElseThrow();

            maestroDb.setNombre(maestro.getNombre());
            maestroDb.setApellido(maestro.getApellido());
            maestroDb.setTitulo(maestro.getTitulo());

            return Optional.of(repository.save(maestroDb));

        }

        return maestroOptional;
    }

    @Transactional
    @Override
    public Optional<Maestro> delete(Long id) {
        Optional<Maestro> maestroOptional = repository.findById(id);
        maestroOptional.ifPresent(maestroDb -> {
            repository.delete(maestroDb);
        });
        return maestroOptional;
    }

}
