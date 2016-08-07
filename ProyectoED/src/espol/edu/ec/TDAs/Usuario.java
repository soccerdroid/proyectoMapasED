package espol.edu.ec.TDAs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Usuario {
	
	//Atributos
	
	private String cedula;
	private String idtwitter;
	private String nombres;
	private LinkedList<Reserva> LResevas;
	
	
	//Getters and Setters 
	
	
	public String getCedula() {
		return cedula;
	}



	public void setCedula(String cedula) {
		this.cedula = cedula;
	}



	public String getIdtwitter() {
		return idtwitter;
	}



	public void setIdtwitter(String idtwitter) {
		this.idtwitter = idtwitter;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public LinkedList<Reserva> getLResevas() {
		return LResevas;
	}



	public void setLResevas(LinkedList<Reserva> lResevas) {
		LResevas = lResevas;
	}

	//Constructor

	public Usuario(String cedula, String idtwitter, String nombres, LinkedList<Reserva> lResevas) {
		this.cedula = cedula;
		this.idtwitter = idtwitter;
		this.nombres = nombres;
		LResevas = lResevas;
	}
	
	
public static LinkedList<Usuario> loadFromFile( String archivo){
		
		
		
		LinkedList<Usuario> Lusu = new LinkedList<>();
        try {
        	File file = new File(archivo);
			Scanner input = new Scanner(file);
			
			while (input.hasNext())
	        {
	            String line = input.nextLine(); //Lee linea del archivo , toda la linea
	            String cadenas[] = line.split("\\|");
	            LinkedList<Reserva> LReservasu = new LinkedList<>();
	            Usuario u =  new Usuario(cadenas[0], cadenas[1], cadenas[2],LReservasu );
	            Lusu.add(u);
	            
	            
	            
	        }
	        input.close();
	        return Lusu;
		} 
        catch (FileNotFoundException e) {
			
			return Lusu;
		}
        
    }
	
public static boolean  existeUsuario(LinkedList<Usuario> LUsuarios, String cedula){
	
	for (int i=0;i < LUsuarios.size();i++){
		 if(LUsuarios.get(i).cedula==cedula){
			 return true;
		 }
		
	}
	return false;
	
}



public void agregarUsuario(LinkedList<Usuario> LUsuarios,String archivo){
	LUsuarios.add(this);
	File f;
	f = new File(archivo);
	try{
		FileWriter w = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(w);
		PrintWriter wr = new PrintWriter(bw);  
		for (int i=0;i < LUsuarios.size();i++){
			wr.println(LUsuarios.get(i).cedula+"|"+LUsuarios.get(i).idtwitter+"|"+LUsuarios.get(i).nombres);
			
		}
		wr.close();
		bw.close();
		}
		catch(IOException e){}
	}
	
public static Usuario buscarUsuario(LinkedList<Usuario> LUsuarios,String cedula){
	
	for (int i=0;i < LUsuarios.size();i++){
		 if(LUsuarios.get(i).cedula.equals(cedula)){
			 return LUsuarios.get(i);
		 }
		}
	return null;
	}


public void modificarUsuario(String cedula, String idtwitter, String nombres){ 
	if (cedula!=""){
		this.cedula=cedula;
	}
	if (idtwitter!=""){
		this.idtwitter=idtwitter;
	}
	if (nombres!=""){
		this.nombres=nombres;
	}
	
}


public String toString(){
	return "Nombre:" + this.nombres + " " + "Id Twitter: " + this.idtwitter + " " + "Cedula: " +  this.cedula; 
}




}
