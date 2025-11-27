package org.ut4.gymmanager.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.service.EjercicioService;

import java.util.List;

@RestController
@RequestMapping("api/ejercicios")
public class EjercicioRestController {
    private final EjercicioService ejercicioService;

    public EjercicioRestController(EjercicioService ejercicioService){
        this.ejercicioService = ejercicioService;
    }

    @GetMapping
    public ResponseEntity<List<Ejercicio>> printarEjercicios(@RequestParam(defaultValue = "") String grupo){ //si no le doy un valor por defecto salta error
        List<Ejercicio> ejercicios = ejercicioService.listarTodos();
        if(ejercicios.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ejercicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> printarPorIdPath(@PathVariable long id){
        Ejercicio ejercicioPorId = ejercicioService.buscarPorId(id);
        if(ejercicioPorId == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ejercicioPorId);
    }

    @PostMapping
    public ResponseEntity<Ejercicio> crearEjercicio(@Valid @RequestBody Ejercicio ejercicio){
        return ResponseEntity.status(HttpStatus.CREATED).body(ejercicioService.guardar(ejercicio));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ejercicio> actualizarEjercicio(@PathVariable long id, @Valid @RequestBody Ejercicio ejercicio){
        ejercicio.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(ejercicioService.guardar(ejercicio));
    }
    @DeleteMapping("/{id}")
    public void eliminarEjercicio(@PathVariable long id){
        for(Ejercicio ej : ejercicioService.listarTodos()){
            if(ej.getId() == id){
                ejercicioService.eliminar(id);
            }
        }
    }
}
