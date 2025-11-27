package org.ut4spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación de ejemplo de Spring Boot para la UT4.
 * <p>
 * La anotación {@link SpringBootApplication} indica a Spring Boot que
 * esta es la clase de arranque: configura el contexto de Spring, realiza
 * el escaneo de componentes y levanta el servidor web embebido (Tomcat).
 */
@SpringBootApplication
public class Ut4DemoApplication {

    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Llama a {@link SpringApplication#run(Class, String...)} para iniciar
     * el contenedor de Spring y el servidor web embebido.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SpringApplication.run(Ut4DemoApplication.class, args);
    }
}
