import java.io.File;

public class UT1_A1_San_Nicolas_Fran {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("No hay argumentos.");
            return;
        }

        File fichero = new File(args[0]);

        if (fichero.exists()){
            if(fichero.isFile()){
                mostrarTamanoArchivo(fichero);
                mostrarPermisosArchivo(fichero);
            }else if (fichero.isDirectory()){
                mostrarContenidoDirectorio(fichero, 0);
            }else{
                System.out.println("El fichero [" + args[0] +"] no es un archivo ni un directorio.");
            }
        }else{
            System.out.println("El fichero [" + args[0] +"] no existe.");
        }
    }

    //Método para mostrar el tamaño del fichero en bytes
    public static void mostrarTamanoArchivo(File fichero){
        System.out.println("Tamaño del fichero: " + fichero.length() + " bytes.");
    }

    //Método para mostrar los permisos de lectura, escritura y ejecución del fichero
    public static void mostrarPermisosArchivo(File fichero){
        System.out.println("Permisos: \n\tLectura: "
                + fichero.canRead() + "\n\tEscritura: "
                + fichero.canWrite() + "\n\tEjecución: " + fichero.canExecute());
    }

    //CORRECCIÓN: Usar las esxpresiones booleanas para cambiar las respuestas: (fichero.canExecute()) ? "Si" : "No";

    /*
    Método para mostrar el contenido del directorio
    El parámetro tab se usa para la tabulación en la impresión
    */
    public static void mostrarContenidoDirectorio(File directorio, int tab) {
        String tabulacion = "\t".repeat(tab);
        long tamano = calcularTamanoDirectorio(directorio);
        File[] ficheros = directorio.listFiles(); //en vez de list(), este método devuelve un array de File

        System.out.println(tabulacion + directorio.getName() + " [Directorio] - " + tamano + " bytes"); // getName() devuelve el nombre de un File

        if (ficheros == null || ficheros.length == 0) {
            System.out.println(tabulacion + "(El directorio está vacío)");
            return;
        }

        for (File fichero : ficheros) {
            if (fichero.isDirectory()) {
                mostrarContenidoDirectorio(fichero, tab + 1); // recursividad
            } else {
                System.out.println("\t".repeat(tab + 1) + fichero.getName() + " - " + fichero.length() + " bytes");
            }
        }
    }
    //Método para calcular el tamaño total del directorio sumando los tamaños de sus archivos y subdirectorios
    public static long calcularTamanoDirectorio(File directorio) {
        long tamano = 0;
        File[] ficheros = directorio.listFiles();
        if (ficheros != null) {
            for (File fichero : ficheros) {
                if (fichero.isFile()) {
                    tamano += fichero.length();
                } else if (fichero.isDirectory()) {
                    tamano += calcularTamanoDirectorio(fichero); // llamada recursiva como antes
                }
            }
        }
        return tamano;
    }

}
