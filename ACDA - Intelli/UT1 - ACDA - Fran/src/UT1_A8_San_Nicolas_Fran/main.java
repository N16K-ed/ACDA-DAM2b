package UT1_A8_San_Nicolas_Fran;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);


        System.out.println("Introduzca la ruta donde se va a guardar la lista de álbums");
        String ruta = teclado.nextLine();

        guardarAlbumes(ruta,crearAlbums());
        teclado.close();
        leerBinario(ruta);
    }

    private static void guardarAlbumes(String ruta, List<Album> albums){
        String rutaCompleta = ruta + "\\Albums.bin";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaCompleta))){
            for(Album album : albums){
                oos.writeObject(album);
            }
            System.out.println("Álbums guardados en " + rutaCompleta);
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void leerBinario(String ruta){
        String rutaCompleta = ruta + "\\Albums.bin";
        try(ObjectInputStream ois = (new ObjectInputStream(new FileInputStream(rutaCompleta)))){
            ArrayList<Album> albums = new ArrayList<>();
            boolean fin = false;
            while (!fin){
                Album album = (Album) ois.readObject();
                if(album != null){
                    albums.add(album);
                }else{
                    fin = true;
                }
            }
            System.out.println("Albumes en el fichero: ");
            for (Album album : albums){
                System.out.println(album);
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Crea Albumes, dado en el enunciado del ejercicio
    private static List<Album> crearAlbums() {
        // Crear tres álbums con cinco canciones cada uno
        Album album1 = new Album("The Beatles", LocalDate.of(1969, 9, 26));
        album1.agregarCancion(new Cancion("Come Together", 4.20, 9.5));
        album1.agregarCancion(new Cancion("Something", 3.03, 8.1));
        album1.agregarCancion(new Cancion("Maxwell's Silver Hammer", 3.27, 8.5));
        album1.agregarCancion(new Cancion("Oh! Darling", 3.27, 8.6));
        album1.agregarCancion(new Cancion("Octopus's Garden", 2.51, 7.0));

        Album album2 = new Album("Pink Floyd", LocalDate.of(1973, 3, 1));
        album2.agregarCancion(new Cancion("Speak to Me", 1.30, 3.5));
        album2.agregarCancion(new Cancion("Breathe", 2.43, 5.0));
        album2.agregarCancion(new Cancion("Time", 6.53, 11.3));
        album2.agregarCancion(new Cancion("Money", 6.22, 10.7));
        album2.agregarCancion(new Cancion("Us and Them", 7.51, 12.0));

        Album album3 = new Album("Queen", LocalDate.of(1975, 11, 21));
        album3.agregarCancion(new Cancion("Bohemian Rhapsody", 5.55, 12.5));
        album3.agregarCancion(new Cancion("Love of My Life", 3.39, 7.8));
        album3.agregarCancion(new Cancion("You're My Best Friend", 2.50, 7.0));
        album3.agregarCancion(new Cancion("God Save the Queen", 1.15, 3.2));
        album3.agregarCancion(new Cancion("I'm In Love With My Car", 3.05, 7.4));

        // Crear una lista de álbumes
        List<Album> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);
        albums.add(album3);
        return albums;
    }

}
