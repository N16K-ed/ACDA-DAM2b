package es.mariana.acda.ut1.examen;

import es.mariana.acda.ut1.examen.models.Persona;
import es.mariana.acda.ut1.examen.utils.EscrituraBinario;
import es.mariana.acda.ut1.examen.utils.LecturaJSON;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // TODO START define la ruta relativa al fichero datos.json contenido en el directorio src/main/resources
            //Uso una copia arreglada para no atascarme con leerFichero de LecturaJSON
        String rutaJSON = "src/main/resources/datos-arreglado.json";
        // TODO END

        // TODO START maneja el error avisando de qué en la lectura mediante un Sistem.out
        //          y termina la ejecución del programa

        try{
            JSONObject  jsonObject = LecturaJSON.lecturaJSON(rutaJSON);

        // TODO END

        // TODO START transforma el jsonObject a la lista de Personas
            List<Persona> personas = new ArrayList<>();
            JSONArray jsonPersonas = jsonObject.getJSONArray("personas");
            for (int i = 0; i < jsonPersonas.length(); i++){
                JSONObject persona = jsonPersonas.getJSONObject(i);
                int id = persona.getInt("id");
                String nombre = persona.getString("nombre");
                int edad = persona.getInt("edad");
                personas.add(new Persona(id,nombre,edad, persona.getJSONArray("rutas")));
            }

        // TODO END

        // TODO START maneja el error avisando de de qué en la escritura mediante un Sistem.out

            EscrituraBinario.escribirBinarios(personas);
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        // TODO END
    }
}
