import java.io.*;
import java.util.Scanner;

public class UT1_A3_San_Nicolas_Fran {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        //Pide la ruta del fichero

        System.out.println("Introduzca la ruta del archivo:");
        String ruta = teclado.nextLine();

        File fichero = new File(ruta);

        //Trata el fichero si es apto

        if(comprobarFichero(fichero)){

            escribirRespuesta(contarPalabrasYLineas(fichero), ruta);
            /* Para probar por consola.
            int[] respuesta =  contarPalabrasYLineas(fichero);
            System.out.println("Nº Lineas: " + respuesta[0] + "\nNº Palabras: " + respuesta[1]);
            */
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

    /**
     * Método para contar palabras y líneas
     * @param file Fichero a tratar
     * @return Array de enteros con los valores de la respuesta [0] = lineas y [1] = palabras totales.
     */
    private static int[] contarPalabrasYLineas(File file) {
        //Variables para guardar los contadores

        int lineas = 0;
        int palabras = 0;

        //Aquí se guarda la linea del BufferedReader para poder tratarla

        String linea;
        try(BufferedReader br = new BufferedReader(new FileReader(file)) ){
           while((linea = br.readLine()) != null){
               //Suma la longitud (cantidad) de palabras en la linea
               palabras += linea.split(" ").length;
               lineas++;
           }
        }catch (IOException e ){
            throw new RuntimeException(e);
        }
        return new int[]{lineas,palabras};
    }

    /**
     * Método para escribir la solución del programa en un txt en la carpeta "src"
     * @param valores El array con las respuestas que hay que imprimir
     */
    private static void escribirRespuesta(int[] valores, String ruta){
        //Extrae la ruta donde se encuentra y guarda el nombre en rutaAntigua[rutaAntigua.length -1]
        String[] rutaAntigua = ruta.split("\\\\"); //Para hacer split() con "\", hace falta escapar 2 veces con "\\\\"
        String rutaNueva = "";
        /*
        Recorre el array con la ruta del fichero original completa
        y la vuelve a montar sin el nombre para poder crear el nuevo fichero en la misma ruta que el original.
        */
        for(int i = 0; i <= rutaAntigua.length - 2; i++){
            rutaNueva += rutaAntigua[i];
            rutaNueva += "\\";
        }
        rutaNueva += "ConteoPalabras.txt";
        //Crea si puede el archivo en la ruta especificada
        try(FileWriter fileWriter = new FileWriter(rutaNueva)){

            //Escribe en el nuevo fichero las respuestas.
            fileWriter.write("Archivo: " + rutaAntigua[rutaAntigua.length - 1] + "\nNº Palabras: " + valores[0] + "\nNº Lineas: " + valores[1]);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
