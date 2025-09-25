import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UT1_A4_San_Nicolas_Fran {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        //Pide la ruta del fichero

        System.out.println("Introduzca la ruta del archivo:");
        String ruta = teclado.nextLine();

        File fichero = new File(ruta);
        if(comprobarFichero(fichero)){

        }else{
            System.out.println("No se ha encontrado el archivo o no es legible.");
        }

    }

    /**
     * Método para comprobar si un fichero existe y no es directorio
     * @param fichero File a comprobar
     * @return si existe y es fichero
     */
    private static boolean comprobarFichero (File fichero){
        return fichero.exists() & fichero.isFile();
    }

    private static int[] tratarCSV(File file){
        int campos = 0;
        int edad = 0;
        int posEdad = 0;
        boolean primera = true;
        String linea;
        try(BufferedReader br = new BufferedReader(new FileReader(file)) ){
            while ((linea = br.readLine()) != null){
                String[] datos = linea.split(";");
                if(primera){
                    campos = datos.length;
                    for(int i = 0; i <= datos.length; i++){
                        if (datos[i].equals("edad")){
                            posEdad = i;
                        }
                    }
                    primera = false;
                }else{
                    for(int i = 0; i <= datos.length; i++){
                        if (i == posEdad){

                        }
                    }
                }
            }
        }catch (IOException e ){
            throw new RuntimeException(e);
        }
        return new int[] {campos,edad};
    }
}
