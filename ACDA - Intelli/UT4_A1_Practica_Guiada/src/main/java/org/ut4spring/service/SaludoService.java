package org.ut4spring.service;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {
    public String crearSaludo(String nombre){
        return "Hola " + nombre + " desde Spring";
    }

}
