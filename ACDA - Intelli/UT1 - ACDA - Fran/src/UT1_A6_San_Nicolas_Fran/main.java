package UT1_A6_San_Nicolas_Fran;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class main {
    public static void main(String[] args) {
        List<Coche> coches = new ArrayList<>();
        coches.add(new Coche("Toyota", "Corolla", 132));
        coches.add(new Coche("Ford", "Mustang", 450));
        coches.add(new Coche("Tesla", "Model 3", 283));

        crearDocumentoXML(coches);
    }

    public static void crearDocumentoXML(List<Coche> coches){
        try{
            // 1. Crear el objeto Document vacío
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element root = documento.createElement("coches");
            documento.appendChild(root);

            for(Coche coche: coches){

                Element cocheNuevo = documento.createElement("coche");
                root.appendChild(cocheNuevo);

                Element marca = documento.createElement("marca");
                marca.appendChild(documento.createTextNode(coche.getMarca()));
                cocheNuevo.appendChild(marca);

                Element modelo = documento.createElement("modelo");
                modelo.appendChild(documento.createTextNode(coche.getModelo()));
                cocheNuevo.appendChild(modelo);

                Element caballos = documento.createElement("caballos");
                caballos.appendChild(documento.createTextNode(String.valueOf(coche.getCaballos())));
                cocheNuevo.appendChild(caballos);
            }

            //Guardar xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File("coches.xml"));

            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente: coches.xml");

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());;
        }
    }
}
