package com.douglas.academicAPI.controller;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Maestro;
import com.douglas.academicAPI.service.AlumnoService;
import com.douglas.academicAPI.service.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService service;

    @Autowired
    private MaestroService maestroService;

    @GetMapping
    public List<Alumno> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Alumno> alumnoOptional = service.findById(id);
        if (alumnoOptional.isPresent()) {
            return ResponseEntity.ok(alumnoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Alumno> create(@RequestBody Alumno alumno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> update(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Alumno> alumnoOptional = service.update(id, alumno);
        if (alumnoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Alumno> alumnoOptional = service.delete(id);
        if (alumnoOptional.isPresent()) {
            return ResponseEntity.ok(alumnoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/maestro/{id}")
    public List<Alumno> findByMaestro(@PathVariable Long id) {
        Optional<Maestro> maestroOptional = maestroService.findById(id);
        if (maestroOptional.isPresent()) {
            return service.findByMaestro(maestroOptional.orElseThrow());
        }
        return Collections.emptyList();
    }
}
