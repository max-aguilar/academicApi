package com.douglas.academicAPI.controller;

import com.douglas.academicAPI.entity.Alumno;
import com.douglas.academicAPI.entity.Maestro;
import com.douglas.academicAPI.service.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maestros")
public class MaestroController {

    @Autowired
    private MaestroService service;

    @GetMapping
    public List<Maestro> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Maestro> maestroOptional = service.findById(id);
        if (maestroOptional.isPresent()) {
            return ResponseEntity.ok(maestroOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Maestro> create(@RequestBody Maestro maestro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(maestro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maestro> update(@PathVariable Long id, @RequestBody Maestro maestro) {
        Optional<Maestro> maestroOptional = service.update(id, maestro);
        if (maestroOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(maestroOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Maestro> maestroOptional = service.delete(id);
        if (maestroOptional.isPresent()) {
            return ResponseEntity.ok(maestroOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }




}
