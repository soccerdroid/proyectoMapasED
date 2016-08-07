
package espol.edu.ec.TDAs;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.naming.ldap.LdapReferralException;

public class Reserva {
	
	//Atributos
	
	private  int  numerounico;
	private Usuario usuario; 
	private Vuelo vuelo; 
	private String asientosr; 
	private Double ctotal;
	private String fecha;
	private String estado;
	
	//Constructor
	
	public Reserva(int numerounico, Usuario usuario, Vuelo vuelo, String asientosr, Double ctotal, String fecha,String estado) {
		
		this.numerounico = numerounico;
		this.usuario = usuario;
		this.vuelo = vuelo;
		this.asientosr = asientosr;
		this.ctotal = ctotal;
		this.fecha = fecha;
		this.estado = estado;
	}
	
	
	//Getters and Setters
	
	
	public int getNumerounico() {
		return numerounico;
	}



	public void setNumerounico(int numerounico) {
		this.numerounico = numerounico;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Vuelo getVuelo() {
		return vuelo;
	}



	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}



	public String getAsientosr() {
		return asientosr;
	}



	public void setAsientosr(String asientosr) {
		this.asientosr = asientosr;
	}



	public Double getCtotal() {
		return ctotal;
	}



	public void setCtotal(Double ctotal) {
		this.ctotal = ctotal;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}

	//Metodos

	public static LinkedList<Reserva> loadFromFile(LinkedList<Usuario> LUsuarios,LinkedList<Vuelo> LVuelos, String archivo,LinkedList<Integer> LNumero){
		
		LinkedList<Reserva> LReser = new LinkedList<>();
		try{
		File file = new File(archivo);
        Scanner input = new Scanner(file);
        
        while (input.hasNext())
        {
            String line = input.nextLine(); //Lee linea del archivo , toda la linea
            String cadenas[] = line.split("\\|");
            Usuario u = Usuario.buscarUsuario(LUsuarios, cadenas[1]);
            Vuelo v = Vuelo.buscarVuelo(LVuelos, cadenas[2]);
            Reserva r = new Reserva(Integer.parseInt(cadenas[0]),u,v,cadenas[3],Double.parseDouble(cadenas[4]),cadenas[5],cadenas[6]);
            u.getLResevas().add(r);
            v.getReservas().add(r);
            
            LReser.add(r);
            LNumero.add(1);
            
        }
        
        input.close();
        
        return LReser;
		
		}
		catch(FileNotFoundException e)
		{
			return LReser;
		}
		}
		
		
		
	
	public static Reserva crearReserva(LinkedList<Reserva> LReservas,LinkedList<Integer> LNumero, Usuario usuario, Vuelo vuelo, String asientosr, Double ctotal, String fecha,String estado){
		Reserva reserva = new Reserva(LNumero.size(), usuario, vuelo, asientosr, ctotal, fecha, estado);
		LNumero.add(1);
		return reserva;
	}
	
	public void agregarReserva(LinkedList<Reserva> LReservas, String archivo){
		
		LReservas.add(this);
		this.usuario.getLResevas().add(this);
		this.vuelo.getReservas().add(this);
		
		File f;
		f = new File(archivo);
		try{
		FileWriter w = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(w);
		for (int i=0;i < LReservas.size();i++){
			bw.write(LReservas.get(i).getNumerounico()+"|"+LReservas.get(i).getUsuario().getCedula()+"|"+LReservas.get(i).getVuelo().getNumeroVuelo()+"|"+LReservas.get(i).getAsientosr()+"|"+LReservas.get(i).getCtotal()+"|"+LReservas.get(i).getFecha()+"|"+LReservas.get(i).getEstado());
			
		}
		
		bw.close();
		}
		catch(IOException e){}
	}
	
	public Reserva buscarReserva(LinkedList<Reserva> LReservas){
		
		for (int i=0;i < LReservas.size();i++){
			if(LReservas.get(i).getNumerounico()==this.getNumerounico()){
				return LReservas.get(i);
			}
		}
		return null;
	}
	
	public void mmodificarEstado(LinkedList<Reserva> LReservas,String estado,String archivo){
		Reserva reserva=this.buscarReserva(LReservas);
		reserva.setEstado(estado);
		reserva=this.buscarReserva(this.usuario.getLResevas());
		reserva.setEstado(estado);
		reserva=this.buscarReserva(this.vuelo.getReservas());
		reserva.setEstado(estado);
		File f;
		f = new File(archivo);
		try{
		FileWriter w = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(w);
		  
		for (int i=0;i < LReservas.size();i++){
			bw.write(LReservas.get(i).getNumerounico()+"|"+LReservas.get(i).getUsuario().getCedula()+"|"+LReservas.get(i).getVuelo().getNumeroVuelo()+"|"+LReservas.get(i).getAsientosr()+"|"+LReservas.get(i).getCtotal()+"|"+LReservas.get(i).getFecha()+"|"+LReservas.get(i).getEstado());
			
		}
		
	
		bw.close();
		}
		catch(IOException e){}
		
	}
	
	public static boolean existeReserva(int numero,LinkedList<Reserva> LReservas){
		for (int i=0;i < LReservas.size();i++){
			if (LReservas.get(i).getNumerounico()==numero){
					return true;
				}
			}
			return false;
	}
	
	public static Reserva buscarReservaPorN(int numero,LinkedList<Reserva> LReservas){
		
		for (int i=0;i < LReservas.size();i++){
			if (LReservas.get(i).getNumerounico()==numero){
				return LReservas.get(i); 
			}
		}
		return null;
	}
	
    public static LinkedList<Reserva> buscarPorFecha(LinkedList<Reserva> LReservas,int anioinicio,int aniofin, int mesinicio, int mesfin, int diainicio, int diafin ){
    	LinkedList<Reserva> LReservasF = new LinkedList<>();
    	for (int i=0;i < LReservas.size();i++){
    		
    		String cadenas[] = LReservas.get(i).getFecha().split("/");
    		if (Integer.parseInt(cadenas[2])>=anioinicio && Integer.parseInt(cadenas[2])<=aniofin){
    			if (Integer.parseInt(cadenas[1])>= mesinicio && Integer.parseInt(cadenas[1])<=mesfin){
    				if (Integer.parseInt(cadenas[0])>= diainicio && Integer.parseInt(cadenas[0])<=diafin){
            			LReservasF.add(LReservas.get(i));
            		}
        		}
    		}
    		
    	}
    	return LReservasF;
    }
	
	
	public String toString(){
		return "Usuario: " + this.usuario + " Costo Total: " + this.ctotal + " Fecha: "+ this.fecha + " Numero de Asientos: "+ this.asientosr + " Estado: "+ this.estado;
	}
	

}
