import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UT1_A7_San_Nicolas_Fran {
    public static void main(String[] args) {
        try{
            //Lee el json
            String contenido = new String(Files.readAllBytes(Paths.get("src\\A7_Recursos.json")));

            JSONObject contenidos = new JSONObject(contenido);
            JSONArray empleados = contenidos.getJSONArray("empleados");
            JSONArray activos = new JSONArray();
            JSONArray sinSalarioOEdad = new JSONArray();

            int salarioTotal = 0;
            int empleadosSalario = 0;

            for(int i = 0; i < empleados.length(); i++){
                JSONObject empleado = empleados.getJSONObject(i);
                if(empleado.getBoolean("activo")){
                    activos.put(empleado);
                    if(empleado.get("salario") != null){
                        salarioTotal += empleado.optInt("salario");
                        empleadosSalario ++;
                    }
                }
                if(empleado.get("salario") == null || empleado.get("edad") == null){
                    sinSalarioOEdad.put(empleado);
                }
            }
            double salarioMedio = 0;
            if (empleadosSalario != 0){
                salarioMedio = (double) salarioTotal / empleadosSalario;
            }
            JSONObject reporte = new JSONObject();
            reporte.put("empleados_activos", activos);
            reporte.put("salario_medio", salarioMedio);
            reporte.put("empleados_datos_incompletos", sinSalarioOEdad);
            String reporteJSON = reporte.toString();
            System.out.println(reporteJSON);
            BufferedWriter bw = new BufferedWriter(new FileWriter("reporte.json"));
            bw.write(reporteJSON);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
