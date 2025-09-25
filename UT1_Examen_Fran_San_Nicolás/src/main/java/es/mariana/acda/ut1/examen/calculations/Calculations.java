package es.mariana.acda.ut1.examen.calculations;

import es.mariana.acda.ut1.examen.models.Checkpoint;

import java.time.Duration;
import java.time.Instant;

public class Calculations {
    final static int RADIO_TIERRA_KM = 6371;  // Radio de la Tierra en kilómetros
    final static double SEGUNDOS_EN_HORA = 3600;

    public static double getDistanciaEntreCheckpoints(Checkpoint checkpoint1, Checkpoint checkpoint2) {
        // Convertir latitudes y longitudes de grados a radianes
        double lat1 = Math.toRadians(checkpoint1.getLatitud());
        double lon1 = Math.toRadians(checkpoint1.getLongitud());
        double lat2 = Math.toRadians(checkpoint2.getLatitud());
        double lon2 = Math.toRadians(checkpoint2.getLongitud());

        // Diferencias de latitud y longitud
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // Fórmula de Haversine para el cálculo de distancias
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                + Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia final
        return RADIO_TIERRA_KM * c;
    }

    // Función que calcula la diferencia de tiempo transcurrido entre dos timestamps y la devuelve en horas (con decimales)
    public static double getDiferenciaTimestampsEnHoras(String timestamp1, String timestamp2) {
        // Leer los timestamps
        Instant instant1 = Instant.parse(timestamp1);
        Instant instant2 = Instant.parse(timestamp2);

        // Calcular la diferencia entre los dos timestamps
        Duration duracion = Duration.between(instant1, instant2);
        return ((double) duracion.toSeconds()) / SEGUNDOS_EN_HORA;
    }
}
