
package espol.edu.ec.TDAs;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vuelo {

    LinkedList<Reserva> reservas;
    String numeroVuelo, origen, destino, hora, rucAerolinea;
    int numeroEscalas, asientosDisponibles;
    double precio;
    Date fecha;

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }
    //CONSTRUCTOR

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha, String hora, int asientosDisponibles, double precio, int numeroEscalas, String rucAerolinea) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        try {
            this.fecha = formatter.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Vuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.hora = hora;
        this.rucAerolinea = rucAerolinea;
        this.numeroEscalas = numeroEscalas;
        this.precio = precio;
        this.asientosDisponibles = asientosDisponibles;
        this.reservas = new LinkedList<>();
    }

    //Cargar una lista de vuelos dado un archivo
    public static LinkedList<Vuelo> cargarPorArchivo(String archivo) {
        LinkedList<Vuelo> listaVuelos = new LinkedList<>();
        try {
            File file = new File(archivo);

            BufferedReader bf = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = bf.readLine()) != null) {

                String[] cadenas = linea.split("\\|");
                Vuelo vuelo = new Vuelo(cadenas[0], cadenas[1], cadenas[2], cadenas[3], cadenas[4], Integer.parseInt(cadenas[5]), Double.parseDouble(cadenas[6]), Integer.parseInt(cadenas[7]), cadenas[8]);
                listaVuelos.add(vuelo);
            }
            bf.close();

        } catch (IOException | NumberFormatException e) {
            System.out.println("No se pudo encontrar el archivo");
            return listaVuelos;
        }
        return listaVuelos;

    }

    //Busca vuelo por su numero de vuelo	
    public static Vuelo buscarVuelo(LinkedList<Vuelo> listaVuelos, String numVuelo) {
        ListIterator<Vuelo> i = listaVuelos.listIterator();
        while (i.hasNext()) {
            Vuelo v = i.next();
            if (v.getNumeroVuelo().equals(numVuelo)) {
                return v;
            }

        }
        return null;
    }
    //M�todo que busca vuelos de acuerdo a los par�metros ingresados, retorna una lista enlaza de los vuelos coincidentes

    public static LinkedList<Vuelo> buscarVuelosDisponibles(LinkedList<Vuelo> listaVuelos, String origen, String destino, String fechaSalida, int numEscalas) {
        LinkedList<Vuelo> disponibles = new LinkedList<>();
        if (!listaVuelos.isEmpty()) {

            System.out.println(fechaSalida);
            ListIterator<Vuelo> i = listaVuelos.listIterator();
            LinkedList<String> rucsAerolineas = new LinkedList<>(); //lista de los rucs de las aerolíneas existentes

            while (i.hasNext()) { //itero sobre la lista de vuelos
                Vuelo v = i.next();
                //obttengo el atributo vuelo de la reserva
                if (v.getAsientosDisponibles() != 0) { // Si aún quedan asientos disponibles en el vuelo
                    if (v.getOrigen().equals(origen) && v.getDestino().equals(destino) && v.getFecha().equals(fechaSalida) && v.getNumeroEscalas() == numEscalas) {
                        disponibles.add(v);
                        rucsAerolineas.add(v.getRucAerolinea());

                    }

                }

            }

        }
        return disponibles; //retorno las coincidencias de vuelos de ida

    }
    
    
//método que busca, con los parámetros ingresados por el usuario+la fecha de regreso, los vuelos cuyos datos sean iguales a los ingresado + que sean de las mismas aerolíneas que los vuelos de ida
    
    //M�todo que valida si un vuelo ya existe, por el n�mero de vuelo
    public static boolean vueloEnLista(LinkedList<Vuelo> lvuelos, String numeroVuelo) {
        if (!lvuelos.isEmpty() && numeroVuelo != null) {
            for (Vuelo v : lvuelos) {
                if (v.getNumeroVuelo().equals(numeroVuelo)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public String aString() {
        return "< " + this.numeroVuelo + " | " + this.origen + " | " + this.destino + " | " + this.hora + " | " + "$" + this.precio + " >";
    }
    //M�todo para guardar en el archivo los datos de los vuelos contenidos en la lista vuelos

    public static boolean cargarAlArchivo(LinkedList<Vuelo> listaVuelos, String archivo) {

        try {
            //M�todo para escribir en un archivo (reescribe)
            File file = new File(archivo);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            ListIterator<Vuelo> i = listaVuelos.listIterator();
            while (i.hasNext()) {
                Vuelo v = i.next();
                bw.write(v.toString());
                bw.write("\n");
            }
            bw.close();
            return true;

        } catch (Exception ex) {
            System.out.println("No se pudo escribir en el archivo");
            Logger.getLogger(Vuelo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    //m�todo equals
    public boolean equals(Object o1) {
        if (o1 instanceof Vuelo) {
            Vuelo vuelo = (Vuelo) o1;
            return this.numeroVuelo.equals(vuelo.getNumeroVuelo());

        } else {
            return false;
        }
    }
    //Getters y setters

    public void setReservas(LinkedList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFechaString(String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        try {
            this.fecha = formatter.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Vuelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRucAerolinea() {
        return rucAerolinea;
    }

    public void setRucAerolinea(String rucAerolinea) {
        this.rucAerolinea = rucAerolinea;
    }

    public int getNumeroEscalas() {
        return numeroEscalas;
    }

    public void setNumeroEscalas(int numeroEscalas) {
        this.numeroEscalas = numeroEscalas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return this.getNumeroVuelo() + "|" + this.origen + "|" + this.destino + "|" + formatter.format(this.fecha) + "|" + this.hora + "|" + this.asientosDisponibles + "|" + this.precio + "|" + this.numeroEscalas + "|" + this.rucAerolinea;
    }

    public static LinkedList<Vuelo> buscarVuelosDisponiblesVuelta(LinkedList<Vuelo> listaVuelos, String origen, String destino, String fechaRegreso, int numEscalas, LinkedList<Vuelo> listaVuelosIda) {
        LinkedList<Vuelo> disponibles = new LinkedList<>();
        if (!listaVuelos.isEmpty()) {

            ListIterator<Vuelo> i = listaVuelos.listIterator(); 
            LinkedList<String> rucsAerolineas = new LinkedList<>(); //lista de los rucs de las aerol�neas existentes
            for (Vuelo v : listaVuelosIda) {
                rucsAerolineas.add(v.getRucAerolinea());
            }

            if (fechaRegreso != null) {  //si me ingres� una fecha de regreso

                i = listaVuelos.listIterator();
                while (i.hasNext()) {
                    Vuelo v = i.next();
                    if (rucsAerolineas.contains(v.getRucAerolinea()) && v.getOrigen().equals(origen) && v.getDestino().equals(destino) && v.getFecha().equals(fechaRegreso) && v.getNumeroEscalas() == numEscalas) { //valido si los datos del vuelo son iguales a los que ingres� el usuario + que el vuelo sea de la misma aerol�nea
                        disponibles.add(v);
                    }
                }
            }

        }
        return disponibles; //retorno las coincidencias de vuelos de regreso

    }

}

