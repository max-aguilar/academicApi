package com.douglas.academicAPI.controller;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Materia;
import com.douglas.academicAPI.service.AlumnoService;
import com.douglas.academicAPI.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<Materia> list() {
        return materiaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Materia> materiaOptional = materiaService.findById(id);
        if (materiaOptional.isPresent()) {
            return ResponseEntity.ok(materiaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Materia> create(@RequestBody Materia materia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.save(materia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> update(@PathVariable Long id, @RequestBody Materia materia) {
        Optional<Materia> materiaOptional = materiaService.update(id, materia);
        if (materiaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(materiaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Materia> materiaOptional = materiaService.delete(id);
        if (materiaOptional.isPresent()) {
            return ResponseEntity.ok(materiaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/alumnos")
    public List<Alumno> findAlumnosByMateriaId(@PathVariable Long id) {
        Optional<Materia> materiaOptional = materiaService.findById(id);
        if (materiaOptional.isPresent()) {
            Materia materia = materiaOptional.get();
            return alumnoService.findByMaterias(materia);
        }
        return Collections.emptyList();
    }

    @PostMapping("/{id}/alumnos")
    public ResponseEntity<Alumno> addAlumnoToMateria(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Materia> materiaOptional = materiaService.findById(id);
        if (materiaOptional.isPresent()) {
            Optional<Alumno> alumnoOptional = alumnoService.addMateria(alumno.getId(), id);
            if (alumnoOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(alumnoOptional.orElseThrow());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/alumnos/{alumnoId}")
    public ResponseEntity<?> removeAlumnoFromMateria(@PathVariable Long id, @PathVariable Long alumnoId) {
        Optional<Materia> materiaOptional = materiaService.findById(id);
        Optional<Alumno> alumnoOptional = alumnoService.findById(alumnoId);
        if (materiaOptional.isPresent() && alumnoOptional.isPresent()) {
            Optional<Materia> materiaUpdatedOptional = materiaService.removeAlumno(id, alumnoId);
            if (materiaUpdatedOptional.isPresent()) {
                return ResponseEntity.ok(materiaUpdatedOptional.orElseThrow());
            }
        }
        return ResponseEntity.notFound().build();
    }


}
