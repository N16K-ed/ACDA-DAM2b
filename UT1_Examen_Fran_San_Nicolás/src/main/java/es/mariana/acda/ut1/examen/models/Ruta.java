package es.mariana.acda.ut1.examen.models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Ruta {
    private List<Checkpoint> checkpoints = new ArrayList<>();

    // Constructor y getter
    public Ruta(JSONArray ruta) {
        // TODO START parsea la ruta de un JSONArray a una lista de checkpoints

        for (int i = 0; i < ruta.length(); i++){

            JSONArray checkpoints = ruta.getJSONArray(i);

            double longitud = checkpoints.getDouble(0);
            double latitud = checkpoints.getDouble(1);
            String timestamp = checkpoints.getString(2);
            double elevacion = checkpoints.getDouble(3);

            this.checkpoints.add(new Checkpoint(latitud,longitud,timestamp,elevacion));
        }

        // TODO END
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}

