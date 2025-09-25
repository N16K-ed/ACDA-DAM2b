package es.mariana.acda.ut1.examen.models;

public class Checkpoint {
    private final double latitud;
    private final double longitud;
    private final String timestamp;
    private final double elevacion;

    // Constructor y getters
    public Checkpoint(double latitud, double longitud, String timestamp, double elevacion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.timestamp = timestamp;
        this.elevacion = elevacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getElevacion() {
        return elevacion;
    }
}
