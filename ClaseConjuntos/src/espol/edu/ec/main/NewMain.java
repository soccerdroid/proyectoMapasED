/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.TDAs.*;

/**
 *
 * @author Administrador
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //OPERACIONES BÁSICAS DE CONJUNTOS
        SetVB conjunto1=new SetVB();
        SetVB conjunto2=new SetVB();
        for(int i=0;i<5;i++) //agrego elementos al setbv conjunto1
            conjunto1.add(i);
        System.out.println(conjunto1); //to String de setvb
        if(conjunto1.search(1))
            System.out.println("el 1 está en el set");
        else
            System.out.println("el 1 no está en el set");
        
        SetVB union=conjunto1.union(conjunto2); //union del conjunto 1 con el cojunto 2 vacío
        System.out.println(union); //imprimo el resultado de unión
        SetVB interseccion=conjunto1.interseccion(conjunto2);
        System.out.println(interseccion);
         SetVB diferencia=conjunto1.diferencia(conjunto2);
        System.out.println(diferencia);
        
        for(int i=0;i<5;i++) //agrego elementos al setbv conjunto2
            conjunto2.add(i+1);
        
        System.out.println(conjunto2);
        SetVB conjunto3=new SetVB(); //conjunto vacio
        
        SetVB unionvacio=conjunto1.union(conjunto3);
        System.out.println(unionvacio);
         SetVB interseccionvacio=conjunto1.interseccion(conjunto3);
        System.out.println(interseccionvacio);
         SetVB diferenciavacio=conjunto1.diferencia(conjunto3);
        System.out.println(diferenciavacio);
        
         SetVB union2=conjunto1.union(conjunto2); //union del conjunto 1 con el cojunto 2 
        System.out.println(union2); //imprimo el resultado de unión
        SetVB interseccion2=conjunto1.interseccion(conjunto2);
        System.out.println(interseccion2);
         SetVB diferencia2=conjunto1.diferencia(conjunto2);
        System.out.println(diferencia2);
        SetVB diferencia3=conjunto2.diferencia(conjunto1);
        System.out.println(diferencia3);
        //CONJUNTOS CON IMPLEMENTACIÓN DE LISTAS ORDENADAS
        SetLO l1 = new SetLO();
        SetLO l2=new SetLO();
        for (int i=0;i<3;i++)
            l2.add(i+2);
        
        SetLO l3=new SetLO(); //vacio
        l1.add(new Integer(3));
        l1.add(new Integer(2));
        l1.add(new Integer(9));
        l1.add(new Integer(10));
        l1.add(new Integer(5));
        l1.add(new Integer(4));
        
        
        SetLO unionlo=l1.union(l2);
        SetLO unionlovacio=l1.union(l3);
        SetLO interseccionlo=l1.interseccion(l2);
        SetLO intersecvacio=l1.interseccion(l3);
        SetLO diferencialo=l1.diferencia(l2);
        SetLO difervacio=l1.diferencia(l3);
        
        
        
         System.out.println(l1);
         l1.remove(5);
         if(l1.search(5))
             System.out.println("El 5 está en la lista1");
         else
             System.out.println("El 5 no está en la lista");
         System.out.println(l1);
         System.out.println(l2);
         System.out.println(unionlo);
         System.out.println(unionlovacio);
         System.out.println(interseccionlo);
         System.out.println(intersecvacio);
         System.out.println(diferencialo);
         System.out.println(difervacio);
         
    }

}
