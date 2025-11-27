package org.ut4spring;

import org.springframework.stereotype.Service;

/**
 * Servicio de negocio encargado de construir mensajes de saludo.
 * <p>
 * Esta clase está anotada con {@link Service}, por lo que Spring
 * la detecta automáticamente como un componente de la capa de servicio.
 */
@Service
public class SaludoService {

    /**
     * Construye un mensaje de saludo a partir de un nombre.
     *
     * @param nombre nombre de la persona a saludar
     * @return cadena con el saludo personalizado
     */
    public String crearSaludo(String nombre) {
        return "Hola " + nombre + " desde Spring";
    }
}
