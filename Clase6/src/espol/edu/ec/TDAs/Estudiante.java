/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;
import java.util.*;
import java.io.*;
/**
 *
 * @author eduardo
 */
public class Estudiante {
    private String matricula, nombres, apellidos;
    private LinkedList<Registro> Lregistros;

    public Estudiante(String matricula, String nombres, String apellidos) {
        this.matricula = matricula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.Lregistros = new LinkedList<Registro>();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombres() {
        return nombres;
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
    
    public static Estudiante search(LinkedList<Estudiante> L, String matricula)
    {
        ListIterator<Estudiante> i = L.listIterator();
        while(i.hasNext())
        {
            Estudiante e = i.next();
            if(e.getMatricula().equals(matricula))
                return e;
        }
        return null;
    }
    
    public static LinkedList<Estudiante> loadFromFile(String archivo)
    {
        LinkedList<Estudiante> L = new LinkedList<Estudiante>();
        try
        {
            File file = new File(archivo);
            Scanner input = new Scanner(file);
            while (input.hasNext())
            {
                String line = input.nextLine();
                String cadenas[] = line.split("\\|");
                System.out.println(cadenas[0]);
                Estudiante e = new Estudiante(cadenas[0],cadenas[1],cadenas[2]);
                L.add(e);
            }
            input.close();
        }
        catch(Exception e)
        {
            return L;
        }
        return L;
    }

    public LinkedList<Registro> getLregistros() {
        return Lregistros;
    }

    public void setLregistros(LinkedList<Registro> Lregistros) {
        this.Lregistros = Lregistros;
    }
     public void addRegistro(Registro r)
    {
        this.Lregistros.add(r);
    }
     
    @Override
     public String toString(){
     
     return this.nombres +" " +this.apellidos + " < "+ this.matricula +" >";}
     
     /*public Estudiante getEstudiante(int i){
         return this.Lregistros.get(i).getEstudiante();
     }*/
}
