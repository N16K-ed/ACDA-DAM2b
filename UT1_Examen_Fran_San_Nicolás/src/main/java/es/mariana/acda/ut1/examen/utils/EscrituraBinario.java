package es.mariana.acda.ut1.examen.utils;

import es.mariana.acda.ut1.examen.models.Persona;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class EscrituraBinario {

    public static void escribirBinarios(List<Persona> personas) throws IOException {
        // TODO START crea un fichero binario para cada persona con el contenido adecuado "id.bin"
        int contadorpersonas = 0;

        for(Persona persona : personas){
            contadorpersonas++;
            String nombre = persona.getNombre();
            int edad = persona.getEdad();
            double altitudMAX = persona.getAltitudMaxima();
            double altitudMIN = persona.getAltitudMinima();
            double tiempoTotal = persona.getTiempoTotalEnHoras();
            double distTotal = persona.getDistanciaTotal();
            // suponiendo Calculations da la distTotal en metros y sabiendo que el tiempo está en horas
            // si no la conversión seria suponiendo distTotal en KM y quitando los multiplicadores
            double velocidadMedia = distTotal/tiempoTotal * 60 * 60;

            String mensaje = "Nombre de la persona: "+ nombre + "\n" +
                    "Edad: "+ edad  + "\n" +
                    "Altitud máxima: " + altitudMAX  + "\n" +
                    "Altitud mínima: " + altitudMIN  + "\n" +
                    "Tiempo total: "+ tiempoTotal  + "\n" +
                    "Distancia total: "+ distTotal  + "\n" +
                    "Velocidad media: "+ velocidadMedia;

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/resources/out/" + contadorpersonas + ".bin"))) {
                // Escribir un entero
                dos.writeUTF(mensaje);

            } catch (IOException e) {
                System.err.println("Error al escribir en el fichero: " + e.getMessage());
            }
        }
        // TODO END
    }
}
