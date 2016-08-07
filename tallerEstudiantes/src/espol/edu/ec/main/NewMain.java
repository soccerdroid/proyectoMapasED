/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.TDAs.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author pavilion
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap<String,Estudiante> infoEstudiantes=obtenerInfoEstudiantes();
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese el promedio mínimo");
        String promedioMinimo=sc.nextLine();
        while(promedioMinimo.equals("")||!isDouble(promedioMinimo)){
                    System.out.println("Ingrese el promedio mínimo correctamente");
                    promedioMinimo=sc.nextLine();
                }
        System.out.println("Ingrese el promedio máximo");
        String promedioMaximo=sc.nextLine();
        while(promedioMaximo.equals("")||!isDouble(promedioMaximo)){
                    System.out.println("Ingrese el promedio máximo correctamente");
                    promedioMaximo=sc.nextLine();
                }
        HashMap<Estudiante,Double> promediosEstudiantes=calcularPromediosEstudiantes(infoEstudiantes);
        LinkedList<String> mejoresMatriculas=obtenerMejoresPromedios(promediosEstudiantes,Double.parseDouble(promedioMinimo),Double.parseDouble(promedioMaximo));
        for(String matricula: mejoresMatriculas){
            Estudiante e=infoEstudiantes.get(matricula);
            System.out.println(e);
            System.out.println("Promedio general: "+promediosEstudiantes.get(e));
        }
    }
    public static HashMap<Estudiante,Double> calcularPromediosEstudiantes(HashMap<String,Estudiante>infoEstudiantes){
        HashMap<Estudiante,Double> promediosEstudiantes= new HashMap<>();
        if(infoEstudiantes.size()>0){
            double promedio=0;
            for(String s:infoEstudiantes.keySet()){
                Estudiante e=infoEstudiantes.get(s);
                LinkedList<Asignatura> materias= e.getlAsignaturas();
                int tamanio=materias.size();
                for(Asignatura m: materias){
                    promedio+=m.getPromedioFinal();
                }
                promedio=promedio/tamanio;
                promediosEstudiantes.put(e, promedio);
                promedio=0;
            }
        }
        return promediosEstudiantes;
    }
    public static LinkedList<String> obtenerMejoresPromedios(HashMap<Estudiante,Double>promediosFinales,Double min, Double max){
        LinkedList<String> matriculas= new LinkedList<>();
        if(promediosFinales.size()>0 &&min<max){
            for(Estudiante e: promediosFinales.keySet()){
                if(promediosFinales.get(e)<=max && promediosFinales.get(e)>=min)
                    matriculas.add(e.getMatricula());
            }
        }
        return matriculas;
    }
    public static HashMap<String,Estudiante>obtenerInfoEstudiantes(){
        HashMap<String,Estudiante> infoEstudiante=new HashMap<>();
        Scanner s=new Scanner(System.in);
        int flag=0;
        while(flag==0){
            int flag1=0;
            String matricula="";
            while(flag1==0){
                System.out.println("Ingrese matrícula");
                matricula=s.nextLine();
                while(matricula.equals("")){
                    System.out.println("Ingrese una matrícula");
                    matricula=s.nextLine();
                }
                if(infoEstudiante.containsKey(matricula))
                    System.out.println("Matrícula ya existe");
                else
                    flag1=1;
            }
            System.out.println("Ingrese nombre");
            String nombre=s.nextLine();
            while(nombre.equals("")){
                    System.out.println("Ingrese nombre");
                    nombre=s.nextLine();
                }
            System.out.println("Ingrese carrera");
            String carrera=s.nextLine();
            while(carrera.equals("")){
                    System.out.println("Ingrese carrera");
                    carrera=s.nextLine();
                }
            Estudiante e=new Estudiante(nombre,carrera,matricula);
            int flag2=0;
            String materia="";
            while(flag2==0){
                System.out.println("Ingrese materia");
                materia=s.nextLine();
                while(materia.equals("")){
                    System.out.println("Ingrese materia");
                    materia=s.nextLine();
                }
                    
                System.out.println("Ingrese promedio final");
                String promedio=s.nextLine();
                while(promedio.equals("") || !isDouble(promedio)){
                    System.out.println("Escriba el promedio correctamente");
                    System.out.println("Ingrese promedio final");
                    promedio=s.nextLine();
                }
                Asignatura a= new Asignatura(materia,Double.parseDouble(promedio));
                e.getlAsignaturas().add(a);
                infoEstudiante.put(e.getMatricula(), e);
                System.out.println("Si desea ingresar otra materia ingrese 0 sino ingrese 1");
                String f2=s.nextLine();
                while(!isInteger(f2)||Integer.parseInt(f2)!=0 && Integer.parseInt(f2)!=1)
                { System.out.println("Ingrese una opción válida");
                    System.out.println("Si desea ingresar otra materia ingrese 0 sino ingrese 1");
                    f2=s.nextLine();
                
                }
                flag2=Integer.parseInt(f2);
            }
            System.out.println("Si desea ingresar otro estudiante ingrese 0 o sino ingrese 1");
            String f1=s.nextLine();
                while(!isInteger(f1)||Integer.parseInt(f1)!=0 && Integer.parseInt(f1)!=1)
                { System.out.println("Ingrese una opción válida");
                    System.out.println("Si desea ingresar otro estudiante ingrese 0 o sino ingrese 1");
                    f1=s.nextLine();
                
                }
                flag=Integer.parseInt(f1);
        }
        return infoEstudiante;
    }
    private static boolean isInteger(String dia) {
        if (!dia.equals("")) {
            char[] arreglo=dia.toCharArray();
            for (char c : arreglo) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }
    private static boolean isDouble(String s){
        if(s.contains(".")){
            String[] cantidad=s.split(".");
            if(cantidad.length>2)
                return false;
            else{
                if(cantidad.length==2){
                    if(isInteger(cantidad[0]) && isInteger(cantidad[1]))
                        return true;
                    return false;
                }
               return isInteger(cantidad[0]);
            }
                
        }
        return isInteger(s);
    }
}
