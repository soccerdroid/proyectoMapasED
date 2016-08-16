/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.TDAs.*;

import java.io.*;

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author pavilion
 */
public class elNumeroDeBacon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        LinkedList<Actor> listaActores;
        LinkedList<Pelicula> listaPeliculas;
        HashMap<Integer, LinkedList<Integer>> mapaPelisActores;
        HashMap<Integer, String> mapaActores;
        HashMap<Integer, String> mapaPeliculas;
        
        
        long startTime = System.nanoTime();
        listaActores = Actor.cargarListaInformacion("actores-test.txt");
        listaPeliculas = Pelicula.cargarListaInformacion("peliculas-test.txt");
        crearRelaciones("pelicula-actores-test.txt", listaActores, listaPeliculas);
        long endTime = System.nanoTime();

        System.out.println("Tiempo de demora del primer método de carga(con listas)" + (endTime - startTime) / 1000000.0 + " milisegundos");

        long startTimeMaps = System.nanoTime();
        mapaActores = Actor.cargarMapaInformacion("actores-test.txt");
        mapaPeliculas = Pelicula.cargarMapaInformacion("peliculas-test.txt");
        mapaPelisActores = asociarPeliculasActores("pelicula-actores-test.txt");
        long endTimeMaps = System.nanoTime();
        System.out.println("Tiempo de demora del segundo método(con mapas)" + (endTimeMaps - startTimeMaps) / 1000000.0 + " milisegundos");
        
        //menu
        while (true) {
            String opcion = JOptionPane.showInputDialog(null, "¿Qué método de búsqueda desea utilizar?\n 1.Dijkstra \n 2.BFS");
            while (!opcion.equals("1") && !opcion.equals("2")) {
                JOptionPane.showMessageDialog(null, "Opción ingresada no válida");
                opcion = JOptionPane.showInputDialog(null, "¿Qué método de búsqueda desea utilizar?\n 1.Dijkstra \n 2.BFS");

            }
            if (opcion.equals("1")) {
                String nombreActor = JOptionPane.showInputDialog(null, "Ingrese el nombre del actor/actriz");
                if (!Actor.estaActor(mapaActores, nombreActor)) {
                    JOptionPane.showMessageDialog(null, "El actor o actriz no se encuentra registrado en la base de datos");
                }
                else{
                //hacer dijkstra
                }
                
            }
            if (opcion.equals("2")) {
                String nombreActor = JOptionPane.showInputDialog(null, "Ingrese el nombre del actor/actriz");
                if (!Actor.estaActor(mapaActores, nombreActor)) {
                    JOptionPane.showMessageDialog(null, "El actor o actriz no se encuentra registrado en la base de datos");
                }
                else{
                //hacer bfs
                }
                
            }
        }
    }

    private static boolean crearRelaciones(String archivo, LinkedList<Actor> lActores, LinkedList<Pelicula> lPeliculas) {

        String linea = "";
        try {
            File file = new File(archivo);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((linea = bf.readLine()) != null) {
                String[] cadenas = linea.split("\\|");
                Integer id = Integer.parseInt(cadenas[0]);
                Integer idActor = Integer.parseInt(cadenas[1]);
                Pelicula p = null;
                Actor a = null;
                for (Actor ac : lActores) {
                    if (ac.getId() == idActor) {
                        a = ac;
                    }
                }
                for (Pelicula pe : lPeliculas) {
                    if (pe.getId() == id) {
                        p = pe;

                    }

                }
                if (p != null && a != null) {
                    p.getReparto().add(a);
                    a.setPelicula(p);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static HashMap<Integer, LinkedList<Integer>> asociarPeliculasActores(String archivo) {
        HashMap<Integer, LinkedList<Integer>> resultado = new HashMap<>();

        try {
            String linea;
            File file = new File(archivo);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((linea = bf.readLine()) != null) {
                String[] cadenas = linea.split("\\|");
                Integer id = Integer.parseInt(cadenas[0]);
                if (!resultado.keySet().contains(id)) {
                    LinkedList<Integer> idsActores = new LinkedList<>();

                    idsActores.add(Integer.parseInt(cadenas[1]));
                    resultado.put(id, idsActores);
                } else {
                    resultado.get(id).add(Integer.parseInt(cadenas[1]));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
