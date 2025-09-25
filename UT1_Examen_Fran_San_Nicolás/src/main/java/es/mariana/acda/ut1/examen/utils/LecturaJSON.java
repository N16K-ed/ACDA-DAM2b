package es.mariana.acda.ut1.examen.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LecturaJSON {

    public static JSONObject lecturaJSON(String rutaFichero) throws IOException {
        String contenidoFichero = lecturaFichero(rutaFichero);

        // TODO START cambiar null por el parseo del String a JSONObject
        JSONObject jsonObject = new JSONObject(contenidoFichero);
        // TODO END
        return jsonObject;
    }

    private static String lecturaFichero(String rutaFichero) throws IOException {
        StringBuilder contenidoFichero = new StringBuilder();

        // TODO START lee el fichero que recibe por parámetro, eliminando las líneas con comentarios (empiezan por //)
        String contenido = new String(Files.readAllBytes(Paths.get(rutaFichero)));
        contenidoFichero.append(contenido);
        // TODO END

        return contenidoFichero.toString();
    }
}
