import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UT1_A2_San_Nicolas_Fran {
    public static void main(String[] args){
        System.out.println("Introduzca la ruta del fichero a tratar:");
        Scanner teclado = new Scanner(System.in);

        //Prueba realzada con la ruta absoluta y relativa

        String nombreFichero = teclado.nextLine();
        File fichero = new File(nombreFichero);
        teclado.close();

        if(comprobarFichero(fichero)){
            System.out.println(tratarChars(fichero) + " vocales encontradas.");

        }else{
            System.out.println("El fichero no existe o no es legible.");
        }

    }

    /**
     * Método para comprobar si un fichero existe y no es directorio
     * @param fichero File a comprobar
     * @return si existe y es fichero
     */
    public static boolean comprobarFichero (File fichero){
        return fichero.exists() & fichero.isFile();
    }

    /**
     * Método para contar las vocales entre todos los carácteres de un fichero
     * @param fichero Fichero a tratar
     * @return número de vocales
     */
    public static int tratarChars(File fichero){
        int vocales = 0;
        try(FileReader fr = new FileReader(fichero)){
            int c;
            while((c = fr.read()) != -1){
                char letra = (char) c;
                if (letra == 'a' ||letra == 'e' ||letra == 'i' ||letra == 'o' ||letra == 'u' ||
                    letra == 'A' ||letra == 'E' ||letra == 'I' ||letra == 'O' ||letra == 'U'){
                    vocales ++;
                }
            }
        }catch (IOException e ){
            throw new RuntimeException(e);
        }
        return vocales;
    }
}
