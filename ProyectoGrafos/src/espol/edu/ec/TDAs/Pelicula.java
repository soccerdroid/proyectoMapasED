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
public class Pelicula {

    private int id;
    private String nombre;
    private LinkedList<Actor> reparto;

    public Pelicula(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.reparto = new LinkedList<>();
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

    public static LinkedList<Pelicula> cargarListaInformacion(String archivo) {
        LinkedList<Pelicula> lista = new LinkedList<>();
        String linea = "";
        try {
            File file = new File(archivo);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((linea = bf.readLine()) != null) {
                String[] cadenas = linea.split("\\|");
                Integer id = Integer.parseInt(cadenas[0]);
                String nombre = cadenas[1];
                Pelicula p = new Pelicula(id, nombre);
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
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

    public LinkedList<Actor> getReparto() {
        return reparto;
    }

    public void setReparto(LinkedList<Actor> reparto) {
        this.reparto = reparto;
    }

}
