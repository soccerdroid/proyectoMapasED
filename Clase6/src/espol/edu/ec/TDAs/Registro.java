/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

import java.io.*;

import java.util.*;

/**
 *
 * @author eduardo
 */
public class Registro {
    Estudiante estudiante;
    Materia materia;

    public Registro(Estudiante e, Materia m) {
        this.estudiante = e;
        this.materia = m;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    public static LinkedList<Registro> loadFromFile(LinkedList<Estudiante> le,LinkedList<Materia> lm,String archivo) throws Exception{
        LinkedList<Registro> lr= new LinkedList<>();
        
        try{
            File file=new File(archivo);
            Scanner sc= new Scanner(file);
            while(sc.hasNext()){
                String linea=sc.nextLine();
                String[] cadena=linea.split("\\|");
                Estudiante e=Estudiante.search(le, cadena[0]);
                Materia m=Materia.search(lm, cadena[1]);
                Registro r=new Registro(e,m);
                lr.add(r);
                e.addRegistro(r); //añado este registro a la lista de registros de la clase estudiante (lista será estática)
                m.addRegistro(r);//añado este registro a la lista de registros de la clase materia (lista será estática)
            }
            sc.close();
        }
            
        catch(Exception e){
            return lr;
        }
        return lr;
    }
    
    public String toString(){
        return this.estudiante.getMatricula()+" | "+this.materia.getCodigo();
    }
}
