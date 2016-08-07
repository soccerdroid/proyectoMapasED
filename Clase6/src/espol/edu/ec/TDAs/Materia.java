/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;
import java.io.File;
import java.util.*;
/**
 *
 * @author eduardo
 */
public class Materia {
    private String codigo, nombre;
    private int creditos;
    private LinkedList<Registro> Lregistros;

    public Materia(String codigo, String nombre, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.Lregistros = new LinkedList<Registro>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    public void addRegistro(Registro r)
    {
        this.Lregistros.add(r);
    }
    
    public static Materia search(LinkedList<Materia> L, String codigo)
    {
        ListIterator<Materia> i = L.listIterator();
        while(i.hasNext())
        {
            Materia m = i.next();
            if(m.getCodigo().equals(codigo))
                return m;
        }
        return null;
    }
    public static Materia searchPorNombre(LinkedList<Materia> L, String nombre)
    {
        ListIterator<Materia> i = L.listIterator();
        while(i.hasNext())
        {
            Materia m = i.next();
            if(m.getNombre().equals(nombre))
                return m;
        }
        return null;
    }

    public LinkedList<Registro> getLregistros() {
        return Lregistros;
    }

    public void setLregistros(LinkedList<Registro> Lregistros) {
        this.Lregistros = Lregistros;
    }
    
    public static LinkedList<Materia> loadFromFile(String archivo)
    {
        LinkedList<Materia> L = new LinkedList<Materia>();
        try
        {
            File file = new File(archivo);
            Scanner input = new Scanner(file);
            while (input.hasNext())
            {
                String line = input.nextLine();
                String cadenas[] = line.split("\\|");
                Materia m = new Materia(cadenas[0],cadenas[1],Integer.parseInt(cadenas[2]));
                
                L.add(m);
            }
            input.close();
        }
        catch(Exception e)
        {
            return L;
        }
        return L;
    }
    @Override
     public String toString(){
     
     return this.nombre +" , código  " +this.codigo + " < "+ this.creditos +" créditos >";}
}
