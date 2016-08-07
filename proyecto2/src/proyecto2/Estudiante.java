/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Estudiante {
    private String nombres,apellidos,matricula;
    private LinkedList<Double> deberes,lecciones,proyecto;
    private double examen;

    public LinkedList<Double> getDeberes() {
        return deberes;
    }

    public void setDeberes(LinkedList<Double> deberes) {
        this.deberes = deberes;
    }

    public LinkedList<Double> getLecciones() {
        return lecciones;
    }

    public void setLecciones(LinkedList<Double> lecciones) {
        this.lecciones = lecciones;
    }

    public LinkedList<Double> getProyecto() {
        return proyecto;
    }

    public void setProyecto(LinkedList<Double> proyecto) {
        this.proyecto = proyecto;
    }

    public Estudiante(String nombres, String apellidos, String matricula) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.deberes=new LinkedList<>();
        this.lecciones=new LinkedList<>();
        this.proyecto= new LinkedList<>();
        this.examen=0;
    }
    public Estudiante(String nombres, String apellidos, String matricula,LinkedList<Double> deberes,LinkedList<Double> lecciones, LinkedList<Double> proyecto,Double examen) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.deberes=deberes;
        this.lecciones=lecciones;
        this.proyecto= proyecto;
         this.examen=examen;
    }

    public double getExamen() {
        return examen;
    }

    public void setExamen(double examen) {
        this.examen = examen;
    }
    
    
    public double calcularNotaExamen(){
        double nota=0; 
        if(examen<100 && examen>0 ){
            nota=examen/2;
        }
        
        return nota;
    }
    public double calcularNotaDeberes(){
        
        double promedio=0;
        if(this.deberes.size()>0){
        for (Double n: this.deberes){
            promedio+=n;
        }
        promedio=promedio/this.deberes.size();}
        return promedio;
        
    }
    public double calcularNotaLecciones(){
        
        double promedio=0;
        if(this.lecciones.size()>0){
        int size=this.lecciones.size();
        Collections.sort(this.lecciones);
        for(int i=1;i<4;i++){
            Double nota=this.lecciones.get(size-i);
            promedio+=nota*1.5;
        }
        promedio+=promedio/3;
        }
        return promedio;
    }
    public String getNombres() {
        return nombres;
    }
    
    public double calcularNotaProyecto(){
        double promedio=0;
        if(this.proyecto.size()>0)
            promedio=this.proyecto.get(0)*this.proyecto.get(1);
        return promedio;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

   
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombres);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Estudiante){
            Estudiante e=(Estudiante)o;
            if(this.matricula.equals(e.getMatricula()))
                return true;
            return false;
        }
        return false;
    }

    public String toString(){
       String s="Nombre: "+ this.nombres+" , "+"< "+this.matricula+" , "+this.apellidos+" >";
       s+="\n";
       s+="Notas de deberes \n";
       for(Double d:this.deberes){
           s+=d+"\n";
       }
       s+="Notas de lecciones \n";
       for(Double d:this.lecciones){
           s+=d+"\n";
       }
       s+="Nota de proyecto"+this.calcularNotaProyecto()+"\n";
       
       s+="Nota de examen: "+this.examen;
       return s;
    }
    
    
}
