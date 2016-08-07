/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.util.HashMap;

/**
 *
 * @author pavilion
 */
public class Curso {
    private HashMap<String, Estudiante> LEstudiantes;
    private String nombre;

    public Curso(HashMap<String, Estudiante> LEstudiantes, String nombre) {
        this.LEstudiantes = LEstudiantes;
        this.nombre = nombre;
    }
    
    

    public HashMap<String, Estudiante> getLEstudiantes() {
        return LEstudiantes;
    }

    public void setLEstudiantes(HashMap<String, Estudiante> LEstudiantes) {
        this.LEstudiantes = LEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

