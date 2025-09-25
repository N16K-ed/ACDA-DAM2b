import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UT1_A5_San_Nicolas_Fran {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        //Pide la ruta del fichero

        System.out.println("Introduzca la ruta del archivo:");
        String ruta = teclado.nextLine();

        File fichero = new File(ruta);
        if(comprobarFichero(fichero)){
            procesarXML(fichero);
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
     * Método para tratar un fichero.xml
     * @param file el fichero a tratar
     */
    private static void procesarXML(File file){
        try{
            //Prepara el objeto Document para tratar
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(file);

            documento.getDocumentElement().normalize();

            //Obtiene la lista de los nodos del xml
            NodeList lista = documento.getElementsByTagName("videojuego");

            //Recorre los nodos
            for(int i = 0; i< lista.getLength(); i++){
                Node nodo = lista.item(i);

                //Comprueba que el nodo sea un elemento para poder tratarlo
                if (nodo.getNodeType() == Node.ELEMENT_NODE){
                    //Obtiene el elemento
                    Element elemento = (Element) nodo;
                    //obtiene los datos del elemento
                    String popularidad = elemento.getAttribute("popularidad");
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    //Los elementos con varios elementos dentro los convierte en listas
                    NodeList plataformas = elemento.getElementsByTagName("plataformas");
                    List <String> plat = new ArrayList<>();
                    for(int a = 0; a < plataformas.getLength(); a++ ){
                        plat.add(plataformas.item(a).getTextContent());
                    }

                    NodeList generos = elemento.getElementsByTagName("generos");
                    List <String> genero = new ArrayList<>();
                    for (int b = 0; b < generos.getLength(); b++){
                        genero.add(generos.item(b).getTextContent());
                    }

                    String anio = elemento.getElementsByTagName("anio").item(0).getTextContent();
                    String beneficio = elemento.getElementsByTagName("beneficios").item(0).getTextContent();

                    //Imprime los datos
                    System.out.println("-----------------------------------------");
                    System.out.println("Videojuego: " + nombre);
                    System.out.println("Popularidad: " + popularidad);
                    System.out.println("Beneficio: " + beneficio);
                    System.out.println("Año: " + anio);
                    System.out.print("Plataformas:");
                    for(String plats : plat){
                        System.out.println(plats);
                    }
                    System.out.print("Generos:");
                    for(String gens : genero){
                        System.out.println(gens);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
