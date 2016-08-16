/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap<String, HashMap<String, Estudiante>> mapaCursos = new HashMap<>();  //mapa con el nombre del curso como clave, y con la lista de estudiantes como valor
        String sdirectorio = "C:\\Users/pavilion/Documents/NetBeansProjects/proyecto2/paralelos";
        try {
            File f = new File(sdirectorio);
            File[] archivos = f.listFiles();
            for (File a : archivos) {
                HashMap<String, Estudiante> mapaEstudiantes = cargarCalificaciones(a.getAbsolutePath());
                System.out.println(a.getName().substring(0, a.getName().indexOf(".")));
                mapaCursos.put(a.getName().substring(0, a.getName().indexOf(".")), mapaEstudiantes);
            }
            System.out.println(mapaCursos);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = "<matricula>,<nombres>,<apellidos>,[<deb1>,<deb2>,<deb3>],[<lec1>,<lec2>,<lec3>],[<proy>,<sust>],<examen>";

    }

    private static HashMap<String, Estudiante> obtenerInformacionEstudiante() { //usarla para crear curso(?)
        HashMap<String, Estudiante> informacionEstudiantes = new HashMap<>();
        int flag1 = 0;
        //Pedir matrícula
        while (flag1 == 0) {
            String matricula = JOptionPane.showInputDialog(null, "Ingrese el número de matrícula del estudiante");
            while (matricula.equals("") || informacionEstudiantes.keySet().contains(matricula)) {
                JOptionPane.showMessageDialog(null, "La matrícula ingresada es incorrecta o ya existe");
                matricula = JOptionPane.showInputDialog(null, "Ingrese el número de matrícula del estudiante");
            }
            //Pedir nombes
            String nombres = JOptionPane.showInputDialog(null, "Ingrese los nombres del estudiante");
            while (nombres.equals("")) { //*****
                JOptionPane.showMessageDialog(null, "No puede dejar este campo vacío");
                nombres = JOptionPane.showInputDialog(null, "Ingrese los nombres del estudiante");
            }
            //Pedir apellidos
            String apellidos = JOptionPane.showInputDialog(null, "Ingrese los apellidos del estudiante");
            while (apellidos.equals("")) { //*****
                JOptionPane.showMessageDialog(null, "No puede dejar este campo vacío");
                apellidos = JOptionPane.showInputDialog(null, "Ingrese los apellidos del estudiante");
            }

            //Pedir deberes
            String notaDeber = JOptionPane.showInputDialog("Ingrese las notas de deberes del estudiante");
            while (notaDeber.equals("") || !notaDeber.contains(",")) {//*****
                JOptionPane.showMessageDialog(null, "Notas ingresadas incorrectas");
                notaDeber = JOptionPane.showInputDialog("Ingrese las notas de deberes del estudiante");
            }
            String[] deberes = notaDeber.split(",");
            boolean proyectoCorrecto = false; //boolean para saber si las notas ingresadas del proyecto están correctas
            boolean leccionesCorrectas = false;//boolean para saber si las notas ingresadas de lecciones están correctas
            boolean deberesCorrectos = false; //boolean para saber si las notas ingresadas de deberes están correctas
            boolean examenCorrecto = false;

            //Listas donde se van a guardar las notas 
            LinkedList<Double> notaFinalD = new LinkedList<>();
            LinkedList<Double> notaFinalL = new LinkedList<>();
            LinkedList<Double> notaFinalP = new LinkedList<>();
            for (String deber : deberes) {
                if (deber.equals("") || !isInteger(deber) || comienzaCero(deber) || Double.parseDouble(deber) > 10 || Double.parseDouble(deber) < 0) { //si una de las notas ingresadas por comas está mal (usar isDigit)
                    JOptionPane.showMessageDialog(null, "Notas ingresadas incorrectas.Una nota debe estar en el rango de 0-10");
                    break;
                } else {
                    notaFinalD.add(Double.parseDouble(deber));
                    deberesCorrectos = true;
                }
            }
            //si escribió bien todas las notas, se puede pedir por las lecciones
            if (deberesCorrectos) {
                String notaLeccion = JOptionPane.showInputDialog("Ingrese las notas de lecciones del estudiante");
                while (notaDeber.equals("") || !notaDeber.contains(",")) {//*****
                    JOptionPane.showMessageDialog(null, "Notas ingresadas incorrectas");
                    notaLeccion = JOptionPane.showInputDialog("Ingrese las notas de lecciones del estudiante");
                }
                String[] lecciones = notaDeber.split(",");

                for (String l : lecciones) {
                    if (l.equals("") || comienzaCero(l) || !isInteger(l) || Double.parseDouble(l) > 10 || Double.parseDouble(l) < 0) { //si una de las notas ingresadas por comas está mal (usar isDigit)
                        JOptionPane.showMessageDialog(null, "Notas ingresadas incorrectas.Una nota debe estar en el rango de 0-10");
                        break;
                    } else {
                        notaFinalL.add(Double.parseDouble(l));
                        leccionesCorrectas = true;
                    }
                }
            }
            //si escribió bien las lecciones, se puede pedir por el proyecto
            if (leccionesCorrectas) {
                String notaProyecto = JOptionPane.showInputDialog("Ingrese las notas de proyecto y sustentación del estudiante");
                while (notaDeber.equals("") || !notaDeber.contains(",")) {//*****
                    JOptionPane.showMessageDialog(null, "Notas ingresadas incorrectas");
                    notaProyecto = JOptionPane.showInputDialog("Ingrese las notas e proyecto y sustentación del estudiante");
                }
                String[] proyecto = notaDeber.split(",");

                if (proyecto.length == 2) {
                    for (String p : proyecto) {
                        if (p.equals("") || !isInteger(p) || comienzaCero(p) || Double.parseDouble(p) > 25 || Double.parseDouble(p) < 0) { //si una de las notas ingresadas por comas está mal (usar isDigit)
                            JOptionPane.showMessageDialog(null, "Notas ingresadas incorrectas.Una nota debe estar en el rango de 0-10");
                            break;
                        } else {
                            notaFinalP.add(Double.parseDouble(p));
                            proyectoCorrecto = true;

                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar 2 notas");
                }
            }
            //si escribió bien el proyecto se puede pedir la nota del examen
            if (proyectoCorrecto) {
                String stringexamen = JOptionPane.showInputDialog(null, "Ingrese la nota del examen");
                while (stringexamen.equals("") || !isInteger(stringexamen) || comienzaCero(stringexamen) || Double.parseDouble(stringexamen) > 100 || Double.parseDouble(stringexamen) < 0) {
                    JOptionPane.showMessageDialog(null, "Nota ingresada incorrecta");
                    stringexamen = JOptionPane.showInputDialog(null, "Ingrese la nota del examen");

                }
                Double examen = Double.parseDouble(stringexamen);
                examenCorrecto = true;

                //si escribió bien el examen, se puede crear el estudiante y guardarlo
                if (examenCorrecto) {

                    Estudiante estudiante = new Estudiante(nombres, apellidos, matricula, notaFinalD, notaFinalL, notaFinalP, examen); //creo un estudiante con sus notas
                    informacionEstudiantes.put(matricula, estudiante); //lo agrego al mapa
                    String opcionParaSalir = JOptionPane.showInputDialog("Ingrese CERO si quiere seguir agregando estudiantes, o UNO si desea terminar");
                    while (!isInteger(opcionParaSalir) || Double.parseDouble(notaDeber) < 0 || Double.parseDouble(notaDeber) < 1) {//*****
                        JOptionPane.showMessageDialog(null, "Número ingresado incorrecto");
                        notaDeber = JOptionPane.showInputDialog("Ingrese CERO si quiere seguir agregando estudiantes, o UNO si desea terminar");
                    }
                    flag1 = Integer.parseInt(notaDeber);

                }
            }

        }
        return informacionEstudiantes;
    }

    private static HashMap<String, Estudiante> cargarCalificaciones(String archivo) {
        HashMap<String, Estudiante> LEstudiantes = new HashMap<>();
        try {
            LinkedList<Double> notasDeberes = new LinkedList<>();
            LinkedList<Double> notasLecciones = new LinkedList<>();
            LinkedList<Double> notasProyecto = new LinkedList<>();

            String linea = "";
            File file = new File(archivo);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((linea = bf.readLine()) != null) {
                
                String[] cadenas = linea.split(",");
                Estudiante estudiante = new Estudiante(cadenas[0], cadenas[1], cadenas[2]);
                System.out.println(estudiante);
                Double notaExamen = Double.parseDouble(cadenas[cadenas.length - 1]);
                int subdeberes1 = linea.indexOf("[");
                int subdeberes2 = linea.indexOf("]");
                String substringdeberes = linea.substring(subdeberes1 + 1, subdeberes2 - 1); //obtengo el primer substring de las notas de deberes
                System.out.println(substringdeberes);
                String[] deberes = substringdeberes.split(",");
                for (String s : deberes) {
                    notasDeberes.add(Double.parseDouble(s));
                }
                String substring = linea.substring(subdeberes2 + 2); //obtengo el substring desde las notas de lecciones
                
                int lecciones2 = substring.indexOf("]");
                String substringLecciones = substring.substring(1, lecciones2 );
                System.out.println(substringLecciones);
                String[] lecciones = substringLecciones.split(",");
                for (String s : lecciones) {
                    notasLecciones.add(Double.parseDouble(s));
                }
                String substring2 = linea.substring(subdeberes2+lecciones2 + 4); //obtengo el substring de las notas de proyecto
                
                int proyecto2 = substring2.indexOf("]");
                String substringProyecto = substring2.substring(1, proyecto2);
                System.out.println(substringProyecto);
                String[] proyecto = substringProyecto.split(",");
                for (String s : proyecto) {
                    notasProyecto.add(Double.parseDouble(s));
                }
                estudiante.setDeberes(notasDeberes);
                estudiante.setLecciones(notasLecciones);
                estudiante.setProyecto(notasProyecto);
                estudiante.setExamen(notaExamen);
                LEstudiantes.put(estudiante.getMatricula(), estudiante);

            }
            bf.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo encontrar el archivo");

        }
        return LEstudiantes;
    }

    private static boolean guardarCalificaciones(HashMap<String, Estudiante> LEstudiantes, String archivo) {
        try {
            File file = new File(archivo);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String s = "";
            for (String m : LEstudiantes.keySet()) {
                Estudiante e = LEstudiantes.get(m);
                s += m + ",";
                s += e.getNombres() + ",";
                s += e.getApellidos() + ",";
                s += e.getDeberes() + ",";
                s += e.getLecciones() + ",";
                s += e.getProyecto() + ",";
                s += e.getDeberes();
                bw.write(s);
                bw.write("\n");

            }
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
            return false;
        }
    }

    private static void mostrarResultados(String matricula, HashMap<String, Estudiante> LEstudiantes) { //muestra los datos de un estudiante por matrícula
        Estudiante e = LEstudiantes.get(matricula);
        System.out.println(e);
    }

    private static boolean isInteger(String dia) {
        if (!dia.equals("")) {
            char[] arreglo = dia.toCharArray();
            for (char c : arreglo) {
                System.out.println(c);
                String x = ".";
                if (Character.compare(c, x.charAt(0)) != 0 && !Character.isDigit(c)) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    private static boolean comienzaCero(String s) {
        if (!s.equals("")) {
            char[] arreglo = s.toCharArray();
            if (Character.isDigit(arreglo[0])) {
                if (arreglo[0] == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    //copiar funcion isDigit para validar que ingrese una nota
    //hacer funcion contieneLetras para validar que la matricula no contenga letras
}
