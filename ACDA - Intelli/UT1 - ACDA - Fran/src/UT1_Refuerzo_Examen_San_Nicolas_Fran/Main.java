package UT1_Refuerzo_Examen_San_Nicolas_Fran;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Leer XML
        //Guardar y leer en JSON
        //Guardar y leer en binario

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca la ruta del fichero (XML) a leer:");
        String ruta = teclado.nextLine();

        File fichero = new File(ruta);

        if (comprobarFichero(fichero)){
            List<Producto> productos = leerXML(fichero);

            System.out.println("Elige opción a realizar:");
            System.out.println("****************************************");
            System.out.println("1 - Guardar y Leer en JSON.");
            System.out.println("2 - Guardar y Leer en binario.");

            int opcion = teclado.nextInt();
            while (opcion < 1 ||  opcion > 2){
                opcion = teclado.nextInt();;
            }
            switch (opcion){
                case 1:
                    teclado.nextLine();
                    System.out.println("Introduzca la ruta del directorio del nuevo JSON: ");
                    String rutaJSON = teclado.nextLine();
                    File directorio = new File(rutaJSON);
                    if(comprobarDirectotio(directorio)){
                        guardarJSON(productos, rutaJSON);
                        //leerJSON(rutaJSON + "\\productos.json");
                    }

                    break;
                case 2:
                    guardarBinario(productos);
                    break;
            }

        }else{
            System.out.println("La ruta no apunta a un fichero legible.");
        }
    }

    public static boolean comprobarFichero(File fichero){
        return fichero.exists() && fichero.isFile();
    }

    public static boolean comprobarDirectotio(File file){
        return file.exists() && file.isDirectory();
    }

    public static List<Producto> leerXML(File file){
        ArrayList<Producto> lista = new ArrayList<>();

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(file);
            documento.getDocumentElement().normalize();

            NodeList productos = documento.getElementsByTagName("producto");

            for (int i = 0; i < productos.getLength(); i++){
                Node nodo = productos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element producto = (Element) nodo;

                    int id = Integer.parseInt(producto.getAttribute("id"));
                    String nombreProducto = producto.getElementsByTagName("nombre").item(0).getTextContent();
                    String categoria = producto.getElementsByTagName("categoria").item(0).getTextContent();
                    double precio = Double.parseDouble(producto.getElementsByTagName("precio").item(0).getTextContent());
                    int stock = Integer.parseInt(producto.getElementsByTagName("stock").item(0).getTextContent());

                    lista.add(new Producto(id,nombreProducto,categoria,precio,stock));
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public static void leerJSON (String ruta){
        try{
            String contenidoJSON = new String(Files.readAllBytes(Paths.get(ruta)));

            JSONArray listaProductos = new JSONArray(contenidoJSON);

            System.out.println("****************************************");

            for (int i = 0; i < listaProductos.length(); i++){
                JSONObject producto = listaProductos.getJSONObject(i);

                int id = producto.getInt("id");
                String nombre = producto.getString("nombre");
                String categoria = producto.getString("categoria");
                double precio = producto.getDouble("precio");
                int stock = producto.getInt("stock");

                System.out.println(new Producto(id,nombre,categoria,precio,stock));
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void leerBinario (){

    }

    public static void guardarJSON(List<Producto> lista, String ruta){
       try{
            JSONArray listaProductos = new JSONArray();

            for(Producto producto: lista){
                JSONObject objetoJSON = new JSONObject();
                objetoJSON.put("id", producto.getId());
                objetoJSON.put("nombre", producto.getNombreProducto());
                objetoJSON.put("categoría",producto.getCategoria());
                objetoJSON.put("precio", producto.getPrecio());
                objetoJSON.put("stock", producto.getStock());
                listaProductos.put(objetoJSON);
            }

            String productos = listaProductos.toString();
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta + "\\productos.json"));
            bw.write(productos);

        }catch (IOException e){
           System.out.println("Error: " + e.getMessage());
        }
    }

    public static void guardarBinario(List<Producto> lista){

    }

}

