/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.TDAs.Aerolinea;
import espol.edu.ec.TDAs.Vuelo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        LinkedList<Aerolinea> listaAerolineas = Aerolinea.cargarPorArchivo("aerolineas.txt"); //lista aerolineas
        System.out.println(listaAerolineas);
        LinkedList<Vuelo> listaVuelos = Vuelo.cargarPorArchivo("vuelos.txt"); //lista de vuelos
        System.out.println(listaVuelos);
        boolean descuento;
        int flag = 1;
        
        while (flag == 1) {
            String su = JOptionPane.showInputDialog(null, "1.Aerolinea \n 2.Vuelo\n 3.Reserva \n 4.Usuario \n 5.Salir", "Escoja una opción", JOptionPane.PLAIN_MESSAGE);
            while (su.equals("") || !isInteger(su) || Integer.parseInt(su) < 1 || Integer.parseInt(su) > 5) {
                JOptionPane.showMessageDialog(null, "Opción incorrecta");
                su = JOptionPane.showInputDialog("1.Aerolinea \n 2.Vuelo\n 3.Reserva \n 4.Usuario \n 5.Salir");
                
            }
            
            if (su.equals("2")) { //MENÚ CASO VUELO
                System.out.println();
                String sv = JOptionPane.showInputDialog("1.Crear vuelo \n 2. Modificar datos de vuelo \n 3.Buscar vuelo \n 4.Cancelar vuelo \n 5.Menú principal");
                while (!isInteger(sv) || Integer.parseInt(sv) < 0 || Integer.parseInt(sv) > 5) {
                    JOptionPane.showMessageDialog(null, "Número ingresado incorrecto");
                    sv = JOptionPane.showInputDialog("1.Crear vuelo \n 2. Modificar datos de vuelo \n 3.Buscar vuelo \n 4.Cancelar vuelo \n 5.Menú principal");
                }
                if (sv.equals("1")) { //Caso crear un vuelo
                    //Pido el número de vuelo
                    String numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo");
                    while (Vuelo.vueloEnLista(listaVuelos, numVuelo)) {//evalúo que lo ingresado sea válido o que el número de vuelo no exista
                        JOptionPane.showMessageDialog(null, "El número ingresado es incorrecto o ya está en uso");
                        numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo");
                    }

                    //Pido el lugar de origen
                    String origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (código)");
                    while (origen.length() < 2 || isInteger(origen)) {
                        JOptionPane.showMessageDialog(null, "El origen ingresado es incorrecto");
                        origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (código)");
                    }
                    //Pido el lugar de destino
                    String destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (código)");
                    while (isInteger(destino) || destino.length() < 2) {
                        JOptionPane.showMessageDialog(null, "El destino ingresado es incorrecto");
                        destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (código)");
                        
                    }
                    //Pido la fecha por parte
                    //Día
                    String dia = JOptionPane.showInputDialog(null, "Ingrese el día", "Ingrese la fecha", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(dia) || Integer.parseInt(dia) > 31 || Integer.parseInt(dia) < 0) { //valido que el día no sea un número negativo,mayor a 31 o que no sea un número
                        JOptionPane.showMessageDialog(null, "Día ingresado incorrecto");
                        dia = JOptionPane.showInputDialog("Ingrese el día");
                    }
                    if(Integer.parseInt(dia)<10)
                        dia="0"+dia; //si el día es menor a 10 le agrego un cero al principio
                    //Mes
                    String mes = JOptionPane.showInputDialog(null, "Ingrese el mes", "Ingrese la fecha", JOptionPane.QUESTION_MESSAGE);
                    char [] arreglo=mes.toCharArray(); //descompongo el string mes en sus caracteres
                    while (!isInteger(mes) || arreglo[0]==0 || Integer.parseInt(mes) > 12 || Integer.parseInt(mes) < 1) {
                        JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                        mes = JOptionPane.showInputDialog("Ingrese el mes");
                        
                    }
                    
                    if(Integer.parseInt(mes)<10)
                        mes="0"+mes; //le antepongo un cero al número del mes, si el mes es menor a 10
                        
                    System.out.println("Ingrese el año"); //Año
                    String anio = JOptionPane.showInputDialog(null, "Ingrese el año", "Ingrese la fecha", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(anio) || Integer.parseInt(anio) > 2017 || Integer.parseInt(anio) < 2016) { //Valido que el año ingresado sea razonable y que sea un número
                        JOptionPane.showMessageDialog(null, "Año ingresado incorrecto");
                        JOptionPane.showInputDialog("Ingrese el año");
                    }
                    String fecha = dia + "/" + mes + "/" + anio;  //Uno el día,mes y año con "/"

                    //Pido la hora por partes
                    //Horas
                    String hora = JOptionPane.showInputDialog(null, "Ingrese la hora", "Ingrese la hora de vuelo", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(hora) || Integer.parseInt(hora) < 0 || Integer.parseInt(hora) > 24) {
                        JOptionPane.showMessageDialog(null, "Hora ingresada incorrecta");
                        hora = JOptionPane.showInputDialog("Ingrese la hora");
                    }
                    //Minutos
                    String minutos = JOptionPane.showInputDialog(null, "Ingrese los minutos", "Ingrese la hora", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(minutos) || Integer.parseInt(minutos) < 0 || Integer.parseInt(minutos) > 60) {
                        JOptionPane.showMessageDialog(null, "Minuto ingresado incorrecto");
                        minutos = JOptionPane.showInputDialog("Ingrese los minutos");
                    }
                    if (Integer.parseInt(minutos) < 10) {
                        minutos = "0" + minutos;
                    }
                    String horaFinal = hora + ":" + minutos;

                    //Pido el número de asientos disponibles
                    String asientosDisponibles = JOptionPane.showInputDialog("Ingrese el número de asientos disponibles");
                    while (!isInteger(asientosDisponibles) || Integer.parseInt(asientosDisponibles) < 0) { //valido la entrada
                        JOptionPane.showMessageDialog(null, "Número ingresado incorrecto");
                        asientosDisponibles = JOptionPane.showInputDialog("Ingrese el número de asientos disponibles");
                    }
                    //Pido el precio del vuelo
                    String precio = JOptionPane.showInputDialog("Ingrese el precio");
                    while (!isInteger(precio)) {
                        JOptionPane.showMessageDialog(null, "Precio ingresado incorrecto");
                        precio = JOptionPane.showInputDialog("Ingrese el precio");
                    }
                    //Pido número de escalas
                    String numeroEscalas = JOptionPane.showInputDialog("Ingrese el número de escalas");
                    while (!isInteger(numeroEscalas) || Integer.parseInt(numeroEscalas) < 0 || Integer.parseInt(numeroEscalas) > 3) {
                        JOptionPane.showMessageDialog(null, "Número ingresado incorrecto");
                        numeroEscalas = JOptionPane.showInputDialog("Ingrese el número de escalas");
                    }
                    //Pido RUC
                    String rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea");
                    while (rucAerolinea.length() < 13) {
                        JOptionPane.showMessageDialog(null, "RUC ingresado incorrecto");
                        rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea");
                    }
                    //Creo un nuevo objeto vuelo con los datos ingresados
                    Vuelo nuevoVuelo = new Vuelo(numVuelo, origen, destino, fecha, horaFinal, Integer.parseInt(asientosDisponibles), Double.parseDouble(precio), Integer.parseInt(numeroEscalas), rucAerolinea);
                    //Lo agrego a la lista de vuelos
                    listaVuelos.add(nuevoVuelo);
                    //Refresco el archivo de vuelos
                    if (Vuelo.cargarAlArchivo(listaVuelos, "vuelos.txt")) {
                        
                        JOptionPane.showMessageDialog(null, nuevoVuelo.aString() + "\n Ingreso de nuevo vuelo exitoso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Houston,tenemos un problema. Algo ocurrió y su vuelo no se pudo ingresar :(");
                    }
                    System.out.println(listaVuelos);
                    
                } else if (sv.equals("2")) { //CASO MODIFICAR DATOS DE UN VUELO
                    JOptionPane.showMessageDialog(null, "A continuación, ingrese los valores que desee modificar \n caso contrario, presione enter para saltarse al siguiente campo"); //Pido el número de vuelo

                    //Pido el número de vuelo para buscarlo
                    String numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo a modificar");
                    while (!Vuelo.vueloEnLista(listaVuelos, numVuelo)) {//evalúo que lo ingresado sea válido o que el número de vuelo no exista
                        JOptionPane.showMessageDialog(null, "El número ingresado es incorrecto o no existe el vuelo");                        
                        numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo nuevo");
                    }
                    Vuelo vueloAmodificar = Vuelo.buscarVuelo(listaVuelos, numVuelo); //vuelo obtenido con el número ingresado por el usuario,sobre este haré modificaciones
                    System.out.println(vueloAmodificar);
                    
                    numVuelo = JOptionPane.showInputDialog("Ingrese el número de vuelo nuevo");
                    if (!numVuelo.equals("")) { //Si no presionó enter,modifico
                        while (Vuelo.vueloEnLista(listaVuelos, numVuelo)) {//evalúo que lo ingresado sea válido o que el número de vuelo no exista
                            JOptionPane.showMessageDialog(null, "El número ingresado es incorrecto o ya existe");
                            numVuelo = JOptionPane.showInputDialog("Ingrese el nuevo número de vuelo");
                        }
                        vueloAmodificar.setNumeroVuelo(numVuelo);
                        
                    }
                    //Obtengo el vuelo con el número de vuelo ingresado
                    //Pido el lugar de origen nuevo
                    String origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (código) nuevo");
                    if (!origen.equals("")) { //SI NO ME INGRESA VACÍO
                        while (origen.length() < 2) {
                            JOptionPane.showMessageDialog(null, "El origen ingresado es incorrecto");
                            origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (código) nuevo");
                        }
                        vueloAmodificar.setOrigen(origen);//modifico su origen
                    }

                    //Pido el lugar de destino nuevo
                    String destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (código) nuevo");
                    if (!destino.equals("")) { //Si me ingresó algo diferente de vacío
                        while (destino.length() < 2) {
                            JOptionPane.showMessageDialog(null, "El destino ingresado es incorrecto");
                            destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (código) nuevo");
                        }
                        
                        vueloAmodificar.setDestino(destino); //Modifico el destino del vuelo
                    }

                    //Pido la fecha nueva por parte
                    //Día
                    String nuevoDia = JOptionPane.showInputDialog(null, "Ingrese el día nuevo", "Ingrese la nueva fecha", JOptionPane.QUESTION_MESSAGE); //Pido el día nuevo
                    if (!nuevoDia.equals("")) {
                        while (!isInteger(nuevoDia) || Integer.parseInt(nuevoDia) > 31 || Integer.parseInt(nuevoDia) < 0) { //valido que el día no sea un número negativo,mayor a 31 o que no sea un número
                            JOptionPane.showMessageDialog(null, "Día ingresado incorrecto");
                            nuevoDia = JOptionPane.showInputDialog("Ingrese el día nuevo");
                        }
                        if(Integer.parseInt(nuevoDia)<10)
                            nuevoDia="0"+nuevoDia;  //si el nuevo día es menor a 10 le agrego un cero

                        //Mes
                        String nuevoMes = JOptionPane.showInputDialog("Ingrese el nuevo mes");//Pido el nuevo mes

                        while (!isInteger(nuevoMes) || Integer.parseInt(nuevoMes) > 12 || Integer.parseInt(nuevoMes) < 0) { //valido lo que ingresa
                            JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                            nuevoMes = JOptionPane.showInputDialog("Ingrese el nuevo mes");
                        }
                        if (Integer.parseInt(nuevoMes)<10)
                            nuevoMes="0"+nuevoMes;  //le agrego un cero al mes si es menor a 10
                        //Año
                        String nuevoAnio = JOptionPane.showInputDialog("Ingrese el año"); //Nuevo Año

                        while ( !isInteger(nuevoAnio) || Integer.parseInt(nuevoAnio) > 2017 || Integer.parseInt(nuevoAnio) < 2016) { //Valido que el año ingresado sea razonable y que sea un número
                            JOptionPane.showMessageDialog(null, "Año ingresado incorrecto");
                            nuevoAnio = JOptionPane.showInputDialog("Ingrese el año");
                        }
                        
                        String fecha = nuevoDia + "/" + nuevoMes + "/" + nuevoAnio;  //Uno el día,mes y año con "/"
                        vueloAmodificar.setFecha(fecha); //MOODIFICO LA FECHA

                    }

                    //Pido la hora por partes
                    //Horas
                    String hora = JOptionPane.showInputDialog(null, "Ingrese las horas", "Ingrese la nueva hora", JOptionPane.QUESTION_MESSAGE);
                    if (!hora.equals("")) {//Si no me ingresa algo vacío
                        while (!isInteger(hora) || Integer.parseInt(hora) < 0 || Integer.parseInt(hora) > 24) {
                            JOptionPane.showMessageDialog(null, "Hora ingresada incorrecta");
                            
                            hora = JOptionPane.showInputDialog("Ingrese las horas");
                        }
                        //Minutos
                        String minutos = JOptionPane.showInputDialog("Ingrese los minutos");
                        while (!isInteger(minutos) || Integer.parseInt(minutos) < 0 || Integer.parseInt(minutos) > 60) {
                            JOptionPane.showMessageDialog(null, "Minuto ingresado incorrecto");
                            
                            minutos = JOptionPane.showInputDialog("Ingrese los minutos");
                        }
                        if (Integer.parseInt(minutos) < 10) {
                            minutos = "0" + minutos;
                        }
                        String horaFinal = hora + ":" + minutos; //Nueva hora
                        vueloAmodificar.setHora(horaFinal); //MODIFICO LA HORA

                    }
                    //Pido el número de asientos disponibles
                    String asientosDisponibles = JOptionPane.showInputDialog("Ingrese el número de asientos disponibles");
                    if (!asientosDisponibles.equals("")) {
                        while (!isInteger(asientosDisponibles) || Integer.parseInt(asientosDisponibles) < 0) { //valido la entrada
                            JOptionPane.showMessageDialog(null, "Número ingresado incorrecto");
                            asientosDisponibles = JOptionPane.showInputDialog("Ingrese el número de asientos disponibles");
                        }
                        vueloAmodificar.setAsientosDisponibles(Integer.parseInt(asientosDisponibles)); //Modificar los asientos disponibles

                    }
                    //*********************
                    //Pido el precio del vuelo
                    String precio = JOptionPane.showInputDialog("Ingrese el precio"); //Ingresa el precio
                    if (!precio.equals("")) {
                        while (precio.equals("") || !isInteger(precio)) { //valido
                            JOptionPane.showMessageDialog(null, "Precio ingresado incorrecto");
                            
                            precio = JOptionPane.showInputDialog("Ingrese el precio");
                        }
                        vueloAmodificar.setPrecio(Double.parseDouble(precio)); //modifico su precio
                    }
                    //Pido RUC
                    String rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea");
                    if (!rucAerolinea.equals("")) { //si no presionó enter el usuario
                        while (rucAerolinea.equals("") || rucAerolinea.length() < 13) { //valido 
                            JOptionPane.showMessageDialog(null, "RUC ingresado incorrecto");
                            rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea");
                        }
                        vueloAmodificar.setRucAerolinea(rucAerolinea); //modifico el ruc de la aerolínea
                    }
                    
                    JOptionPane.showMessageDialog(null, vueloAmodificar.aString(), "Vuelo con nuevos datos \n Vuelo modificado ", JOptionPane.PLAIN_MESSAGE); //imprimo por pantalla el vuelo con los datos modificados
                    Vuelo.cargarAlArchivo(listaVuelos, "vuelos.txt"); //escribo en el archivo de vuelos la lista de vuelos con el vuelo ya modificado
                    System.out.println(listaVuelos);
                    
                } else if (sv.equals("3")) { //CASO buscar vuelo 
                    //Pido el lugar de origen nuevo
                    JOptionPane.showMessageDialog(null, "Ingrese los parámetros de búsqueda");
                    String origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (código) nuevo"); //pido origen

                    while (origen.equals("") || origen.length() < 2) { //valido el origen
                        JOptionPane.showMessageDialog(null, "El origen ingresado es incorrecto");
                        origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (código) nuevo");
                    }

                    //Pido el lugar de destino 
                    String destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (código) nuevo");
                    
                    while (destino.equals("") || destino.length() < 2) { //valido lo ingresado
                        JOptionPane.showMessageDialog(null, "El destino ingresado es incorrecto");
                        destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (código) nuevo");
                    }

                    //Pido la fecha por parte
                    //Día
                    String nuevoDia = JOptionPane.showInputDialog(null, "Ingrese el día", "Ingrese la fecha de salida", JOptionPane.QUESTION_MESSAGE); //Pido el día

                    while (nuevoDia.equals("") || !isInteger(nuevoDia) || Integer.parseInt(nuevoDia) > 31 || Integer.parseInt(nuevoDia) < 0) { //valido que el día no sea un número negativo,mayor a 31 o que no sea un número
                        JOptionPane.showMessageDialog(null, "Día ingresado incorrecto");
                        nuevoDia = JOptionPane.showInputDialog("Ingrese el día");
                    }

                    //Mes
                    String nuevoMes = JOptionPane.showInputDialog("Ingrese el mes");//Pido el mes

                    while (nuevoMes.equals("") || Integer.parseInt(nuevoMes) > 12 || Integer.parseInt(nuevoMes) < 0 || !isInteger(nuevoMes)) {
                        JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                        nuevoMes = JOptionPane.showInputDialog("Ingrese el mes");
                    }
                    //Año
                    String nuevoAnio = JOptionPane.showInputDialog("Ingrese el año"); //Año

                    while (nuevoAnio.equals("") || Integer.parseInt(nuevoAnio) > 2017 || Integer.parseInt(nuevoAnio) < 2016 || !isInteger(nuevoAnio)) { //Valido que el año ingresado sea razonable y que sea un número
                        JOptionPane.showMessageDialog(null, "Año ingresado incorrecto");
                        nuevoAnio = JOptionPane.showInputDialog("Ingrese el año");
                    }
                    
                    String fechaSalida = nuevoDia + "/" + nuevoMes + "/" + nuevoAnio;  //Uno el día,mes y año con "/"
                    String fechaRegreso = null;

                    //PREGUNTO SI VA A INGRESAR FECHA DE REGRESO PARA LA BÚSQUEDA
                    JOptionPane.showMessageDialog(null, "Si desea, ingrese la fecha de regreso.Caso contrario,presione enter");
                    //Pido la fecha nueva por parte
                    //Día
                    String diaRegreso = JOptionPane.showInputDialog(null, "Ingrese el día", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE); //Pido el día 
                    if (!diaRegreso.equals("")) {
                        while (!isInteger(diaRegreso) || Integer.parseInt(diaRegreso) > 31 || Integer.parseInt(diaRegreso) < 0) { //valido que el día no sea un número negativo,mayor a 31 o que no sea un número
                            JOptionPane.showMessageDialog(null, "Día ingresado incorrecto");
                            diaRegreso = JOptionPane.showInputDialog("Ingrese el día");
                        }

                        //Mes
                        String mesRegreso = JOptionPane.showInputDialog(null, "Ingrese el mes", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE);//Pido el mes

                        while (Integer.parseInt(mesRegreso) > 12 || Integer.parseInt(mesRegreso) < 0 || !isInteger(mesRegreso)) {
                            JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                            mesRegreso = JOptionPane.showInputDialog("Ingrese el mes");
                        }
                        //Año
                        String anioRegreso = JOptionPane.showInputDialog(null, "Ingrese el año", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE); //Año de retorno

                        while (Integer.parseInt(anioRegreso) > 2017 || Integer.parseInt(anioRegreso) < 2016 || !isInteger(anioRegreso)) { //Valido que el año ingresado sea razonable y que sea un número
                            JOptionPane.showMessageDialog(null, "Año ingresado incorrecto");
                            anioRegreso = JOptionPane.showInputDialog("Ingrese el año");
                        }
                        
                        fechaRegreso = diaRegreso + "/" + mesRegreso + "/" + anioRegreso;  //Uno el día,mes y año con "/"

                    }

                    //Pido número de escalas
                    String numeroEscalas = JOptionPane.showInputDialog("Ingrese el número de escalas");
                    
                    while (numeroEscalas.equals("") || !isInteger(numeroEscalas) || Integer.parseInt(numeroEscalas) < 0 || Integer.parseInt(numeroEscalas) > 4) {
                        JOptionPane.showMessageDialog(null, "Número ingresado incorrecto");
                        numeroEscalas = JOptionPane.showInputDialog("Ingrese el número de escalas");
                    }
                    //BUSCO VUELO
                    LinkedList<Vuelo> vuelosEncontradosIda = Vuelo.buscarVuelosDisponibles(listaVuelos, origen, destino, fechaSalida, Integer.parseInt(numeroEscalas)); //busco los vuelos de ida
                    LinkedList<Vuelo> vuelosEncontradosVuelta=new LinkedList<>();
                    
                    if(fechaRegreso!=null){ //si el usuario me ingresó una fecha de regreso
                        vuelosEncontradosVuelta=Vuelo.buscarVuelosDisponiblesVuelta(listaVuelos, origen, destino, fechaRegreso, Integer.parseInt(numeroEscalas),vuelosEncontradosIda); //busco los vuelos de regreso
                    }
                        String vuelosEnString=""; //string donde estarán todos los vuelos encontrados
                     
                    if (!diaRegreso.equals("") && vuelosEncontradosVuelta.size() != 0) {// si hay vuelos de ida y de regreso encontrados
                        
                        descuento = true;    //valor booleano que indica que el cliente tiene 30% de dscto
                        LinkedList<Vuelo> vuelosEncontrados= new LinkedList<>(); //lista que contendrá a los vuelos de ida y regreso
                        vuelosEncontrados.addAll(vuelosEncontradosIda); //agrego los vuelos encontrados con la fecha de salida
                        vuelosEncontrados.addAll(vuelosEncontradosVuelta); //agrego los vuelos encontrados con la fecha de regreso
                        for (Vuelo v:vuelosEncontrados)
                        vuelosEnString+=v+"\n"; //agrego al string de los vuelos que mostraré por pantalla cada vuelo encontrado
                        JOptionPane.showMessageDialog(null, vuelosEnString,"Vuelos encontrados",JOptionPane.PLAIN_MESSAGE); //vuelos encontrados mostrados por pantalla
                    } 
                    else { // si sólo hay vuelos de ida
                        descuento = false;//cliente no tiene el descuento
                        for (Vuelo v:vuelosEncontradosIda)
                        vuelosEnString+=v+"\n"; //agrego al string de los vuelos que mostraré por pantalla cada vuelo encontrado
                 
                        JOptionPane.showMessageDialog(null, vuelosEnString, "Vuelos encontrados (sólo de ida)", JOptionPane.PLAIN_MESSAGE); //Imprimo por pantalla la lista con vuelos disponibles
                    } 
                    
                    
                   
                    
                    
                } else if (sv.equals("4")) { //CASO CANCELAR VUELO
                    String vueloAeliminar = JOptionPane.showInputDialog("Ingrese el número de vuelo a cancelar");
                    
                    while (vueloAeliminar.equals("")) {//evalúo que lo ingresado sea válido o que el número de vuelo no exista
                        JOptionPane.showMessageDialog(null, "El número ingresado es incorrecto");
                        vueloAeliminar = JOptionPane.showInputDialog("Ingrese el número de vuelo a cancelar");
                        
                    }
                    if (!Vuelo.vueloEnLista(listaVuelos, vueloAeliminar)) { //si el número de vuelo no coincide con niguno de la lista de vuelos
                        JOptionPane.showMessageDialog(null, "No existe ningún vuelo con ese número,inténtelo nuevamente");
                    } else {
                        Vuelo vueloEliminado = Vuelo.buscarVuelo(listaVuelos, vueloAeliminar); //busco el vuelo por el número ingresado
                        //MÉTODO EN LA CLASE RESERVA QUE DADAS LAS LISTAS DE VUELOS,USUARIOS Y RESERVAS, LOS DEJA SIN REFERENCIAR Y LOS ELIMINA
                        
                        listaVuelos.remove(vueloEliminado);   //ELIMINO DE LA LISTA EL VUELO CON EL NÚMERO DE VUELO INGRESADO

                        if (Vuelo.cargarAlArchivo(listaVuelos, "vuelos.txt")) { //refrescar datos en el archivo
                            JOptionPane.showMessageDialog(null, vueloEliminado); //imprimo los datos del vuelo eliminado
                            JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo concretar la remoción del vuelo");
                        }
                    }
                }
                
            } else if (su.equals("1")) { //CASO MENÚ AEROLÍNEA
                String sA = JOptionPane.showInputDialog("1.Crear aerolínea \n 2. Modificar datos de aerolínea \n 3.Buscar aerolínea \n 4.Menú principal");
                while (!isInteger(su) || Integer.parseInt(su) < 0 || Integer.parseInt(su) > 5) {
                    JOptionPane.showMessageDialog(null, "Número ingresado incorrecto");
                    su = JOptionPane.showInputDialog("1.Crear aerolínea \n 2. Modificar datos de aerolínea \n 3.Buscar aerolínea \n 4.Menú principal");
                }
                if (sA.equals("1")) { //CASO CREAR AEROLÍNEA
                    //pido el RUC
                    String ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea");
                    while (ruc.length() < 13 || !isInteger(ruc)) {
                        JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto");
                        ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea");
                    }
                    //Pido la razón social
                    String razonSocial = JOptionPane.showInputDialog("Ingrese la razón social)");
                    while (razonSocial.equals("")) {
                        JOptionPane.showMessageDialog(null, "La razón social ingresada es incorrecta");
                        razonSocial = JOptionPane.showInputDialog("Ingrese la razón social");
                        
                    }
                    //PIDO EL NOMBRE COOMERCIAL
                    String nombreComercial = JOptionPane.showInputDialog("Ingrese el nombre comercial");
                    while (nombreComercial.equals("")) {
                        JOptionPane.showMessageDialog(null, "El nombre comercial es incorrecto");
                        nombreComercial = JOptionPane.showInputDialog("Ingrese el nombre comercial");
                        
                    }
                    Aerolinea aerolinea = new Aerolinea(ruc, razonSocial, nombreComercial); //CREO UNA NUEVA AEROLÍNEA CON LOS DATOS INGRESADOS
                    listaAerolineas.add(aerolinea); //AGREGO A LA LISTA
                    if (Aerolinea.cargarAlArchivo(listaAerolineas, "aerolineas.txt")) {
                        JOptionPane.showMessageDialog(null, "Aerolínea a agregar:");
                        JOptionPane.showMessageDialog(null, aerolinea); //Imprimo datos de la nueva aerolínea
                        JOptionPane.showMessageDialog(null, "Se ha agregado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo escribir en el archivo de aerolíneas");
                    }
                } else if (sA.equals("2")) { //CASO MODIFICAR DATOS DE AEROLÍNEA
                    //pido el ruc para obtener la aerolinea
                    String ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea");
                    while (ruc.length() < 13 || !isInteger(ruc) || !Aerolinea.aerolineaEnLista(listaAerolineas, ruc)) { //valido el ruc ingresado, y si existe una aerolinea con ese ruc
                        JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto");
                        ruc = JOptionPane.showInputDialog("Ingrese el nuevo RUC de la aerolínea ");
                    }
                    Aerolinea aerolineaModificar = Aerolinea.buscarPorRuc(listaAerolineas, ruc);
                    JOptionPane.showMessageDialog(null, "Ingrese los nuevos datos. Si no los desea modificar, presione enter");
                    //pido el nuevo ruc
                    String rucNuevo = JOptionPane.showInputDialog("Ingrese el nuevo RUC de la aerolínea");
                    if (!rucNuevo.equals("")) {
                        while (rucNuevo.length() < 13 || !isInteger(rucNuevo) || Aerolinea.aerolineaEnLista(listaAerolineas, rucNuevo)) { //valido el ruc ingresado, y si existe una aerolinea con ese ruc
                            JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto o ya existe ");
                            rucNuevo = JOptionPane.showInputDialog("Ingrese el nuevo RUC de la aerolínea ");
                        }
                        aerolineaModificar.setRuc(rucNuevo); //modifico el ruc
                    }
                    //PIDO EL NOMBRE COOMERCIAL
                    String nombreComercial = JOptionPane.showInputDialog("Ingrese el nuevo nombre comercial");
                    if (!nombreComercial.equals("")) { //si no ingresó enter
                        while (nombreComercial.equals("")) {
                            JOptionPane.showMessageDialog(null, "El nombre comercial es incorrecto");
                            nombreComercial = JOptionPane.showInputDialog("Ingrese el nuevo nombre comercial");
                            
                        }
                        aerolineaModificar.setNombreComercial(nombreComercial); //moidifico el nombre comercial
                    }
                    //Pido la razón social nueva
                    String razonSocial = JOptionPane.showInputDialog("Ingrese la nueva razón social)");
                    if (!razonSocial.equals("")) { //si no ingresó enter
                        while (razonSocial.equals("")) {
                            JOptionPane.showMessageDialog(null, "La razón social ingresada es incorrecta");
                            razonSocial = JOptionPane.showInputDialog("Ingrese la razón social");
                            
                        }
                        aerolineaModificar.setRazonSocial(razonSocial); //modifico la razón social
                    }
                    JOptionPane.showMessageDialog(null, "Estado de la aerolínea");
                    JOptionPane.showMessageDialog(null, aerolineaModificar); //Imprimo la aerolínea con los datos actuales
                    if (Aerolinea.cargarAlArchivo(listaAerolineas, "aerolineas.txt")) //refresco los datos en el archivo 
                    {
                        JOptionPane.showMessageDialog(null, "Datos ingresados exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudieron guardar los datos");
                    }
                    
                } else if (sA.equals("3")) { //CASO BUSCAR AEROLÍNEA
                    //PIDO EL RUC DE LA AEROLÍNEA
                    String ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerolínea a buscar");
                    while (ruc.length() < 13 || !isInteger(ruc)) {
                        JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto ");
                        ruc = JOptionPane.showInputDialog("\"Ingrese el RUC de la aerolínea a buscar");
                    }
                    if (!Aerolinea.aerolineaEnLista(listaAerolineas, ruc)) { //SI EL RUC INGRESADO NO EXISTE, SALE DEL MENÚ
                        JOptionPane.showMessageDialog(null, "no hay aerolínea con ese ruc");
                    } else {
                        Aerolinea aerolineaEncontrada = Aerolinea.buscarPorRuc(listaAerolineas, ruc); //busco la aerolínea por ruc
                        JOptionPane.showMessageDialog(null, aerolineaEncontrada); //imprimo los datos de la aerolínea
                    }
                }
            }
        }
    }
     //función que evalúa si una expresión es un número
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
    //método que valida si un string comienza con el número cero
    private static boolean comienzaCero(String s){
        if(!s.equals("")){
            char[]arreglo=s.toCharArray();
            if(Character.isDigit(arreglo[0]))
                if(arreglo[0]==0)
                    return true;
                else
                    return false;
            return false;
        }
        return false;
    }
}
