package org.ut4spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ut4spring.service.SaludoService;

@RestController
public class SaludoController {

    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService){
        this.saludoService = saludoService;
    }

    @GetMapping("/api/saludo")
    public String saludar(){
        return saludoService.crearSaludo("DAM");
    }

    @GetMapping("/api/saludo-param")
    public String saludarParam(@RequestParam(defaultValue = "mundo") String nombre){
        return saludoService.crearSaludo(nombre);
    }

    @GetMapping("/api/saludo/{nombre}")
    public String saludoPath(@PathVariable String nombre){
        return saludoService.crearSaludo(nombre);
    }
}
