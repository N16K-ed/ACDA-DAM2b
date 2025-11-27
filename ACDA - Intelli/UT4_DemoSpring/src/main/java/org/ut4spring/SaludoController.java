package org.ut4spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que expone un endpoint sencillo de saludo.
 * <p>
 * Demuestra el uso de {@link RestController}, la definición de rutas HTTP
 * con {@link GetMapping} y la inyección de dependencias de un servicio.
 */
@RestController
public class SaludoController {

    /**
     * Servicio encargado de construir el mensaje de saludo.
     * <p>
     * Esta dependencia es inyectada por el contenedor de Spring
     * (Inversión de Control / Inyección de Dependencias).
     */
    private final SaludoService saludoService;

    /**
     * Constructor con inyección de dependencias.
     * <p>
     * Spring detecta este constructor y proporciona automáticamente una
     * instancia de {@link SaludoService} cuando crea el controlador.
     *
     * @param saludoService servicio de negocio que genera el saludo
     */
    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    /**
     * Endpoint HTTP GET que devuelve un saludo en texto plano.
     * <p>
     * Ejemplos de uso:
     * <ul>
     *     <li><code>GET /api/saludo</code></li>
     *     <li><code>GET /api/saludo?nombre=Dani</code></li>
     * </ul>
     *
     * @param nombre nombre de la persona a saludar; si no se indica,
     *               se usa el valor por defecto {@code "mundo"}.
     * @return mensaje de saludo generado por {@link SaludoService}
     */
    @GetMapping("/api/saludo")
    public String saludar(@RequestParam(defaultValue = "mundo") String nombre) {
        return saludoService.crearSaludo(nombre);
    }
}
