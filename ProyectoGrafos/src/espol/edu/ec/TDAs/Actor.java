/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author pavilion
 */
public class Actor {

    private int id;
    private String nombre;
    private Pelicula pelicula;

    public Actor(int id, String nombre, Pelicula pelicula) {
        this.id = id;
        this.nombre = nombre;
        this.pelicula = pelicula;
    }

    public Actor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.pelicula = null;
    }

    public static LinkedList<Actor> cargarListaInformacion(String archivo) {
        LinkedList<Actor> lista = new LinkedList<>();
        String linea = "";
        try {
            File file = new File(archivo);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((linea = bf.readLine()) != null) {
                String[] cadenas = linea.split("\\|");
                Integer id = Integer.parseInt(cadenas[0]);
                String nombre = cadenas[1];
                Actor a = new Actor(id, nombre);
                lista.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static HashMap<Integer, String> cargarMapaInformacion(String archivo) {
        HashMap<Integer, String> mapa = new HashMap<>();
        String linea = "";
        try {
            File file = new File(archivo);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((linea = bf.readLine()) != null) {
                String[] cadenas = linea.split("\\|");
                Integer id = Integer.parseInt(cadenas[0]);
                String nombre = cadenas[1];
                mapa.put(id, nombre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapa;
    }

    public static boolean estaActor(HashMap<Integer, String> mapaActores, String nombreActor) {
        if (mapaActores.size() > 0) {
            for (Integer id : mapaActores.keySet()) {
                if(mapaActores.get(id).equals(nombreActor)){
                    return true;
                }
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

}
