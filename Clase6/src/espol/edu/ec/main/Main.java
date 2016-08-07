/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.TDAs.*;
import java.util.*;

/**
 *
 * @author eduardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        //creo lista de estudiantes y la imprimo
        LinkedList<Estudiante> Lestudiantes=Estudiante.loadFromFile("estudiantes.txt");
        System.out.println(Lestudiantes);
         //creo lista de materias y la imprimo
        LinkedList<Materia> Lmaterias=Materia.loadFromFile("materias.txt");
        System.out.println(Lmaterias);
        
        
        
        //creo lista de registros y la imprimo
        LinkedList <Registro> lregistros=Registro.loadFromFile(Lestudiantes,Lmaterias,"registros.txt");
        System.out.println(lregistros);
        
        //Cuántos estudiantes tomaron fundamentos
        
        Materia m1=Materia.searchPorNombre(Lmaterias,"Fundamentos de Programacion" );
        Materia m2=Materia.searchPorNombre(Lmaterias, "Estructuras de datos");
        int estud1=m1.getLregistros().size();
        int estud2=m2.getLregistros().size();
        
        //Cuántos estudiantes tomaron estructuras
        System.out.println(m1.getNombre()+ " tiene "+ estud1 + " estudiantes" );
        System.out.println(m2.getNombre()+" tiene "+ estud2 + " estudiantes" );

        
        //Cuántas materias tomó 201409876 y el 201408090
        Estudiante e1=Estudiante.search(Lestudiantes, "201409876");
        Estudiante e2=Estudiante.search(Lestudiantes, "201408090");
        
        int cantMat1=e1.getLregistros().size();
        int cantMat2=e2.getLregistros().size();
        System.out.println("El estudiante con matrícula 201409876 tomó " +cantMat1+" materias");
        System.out.println("El estudiante con matrícula 201408090 tomó " +cantMat2+" materias");
        
        //Cuántas materias tomó 201408090
        
        
        }
        catch (Exception e){
            System.out.println("No se pudo leer el archivo");
        }
    }
    
}
