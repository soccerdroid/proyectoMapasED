
package espol.edu.ec.main;

import espol.edu.ec.TDAs.Aerolinea;
import espol.edu.ec.TDAs.Reserva;
import espol.edu.ec.TDAs.Usuario;
import espol.edu.ec.TDAs.Vuelo;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

    	LinkedList<Integer> LNumero = new LinkedList<>(); //Lista Numeros unicos

    	//Lista Usuarios
    	LinkedList<Usuario> LUsuarios = Usuario.loadFromFile("Usuarios.txt");
    	System.out.println("USUARIOS");
    	for (int i = 0 ; i < LUsuarios.size();i++){
    		System.out.println(LUsuarios.get(i).toString());
    	}
    	System.out.println("");

    	//Lista Aerolineas
    	LinkedList<Aerolinea> listaAerolineas = Aerolinea.cargarPorArchivo("Aerolineas.txt");
    	System.out.println("Aerolineas");
    	for (int i = 0 ; i < listaAerolineas.size();i++){
    		System.out.println(listaAerolineas.get(i).toString());
    	}
    	System.out.println("");

    	//Lista de Vuelos
        LinkedList<Vuelo> listaVuelos = Vuelo.cargarPorArchivo("Vuelos.txt");
        System.out.println("Vuelos");
    	for (int i = 0 ; i < listaVuelos.size();i++){
    		System.out.println(listaVuelos.get(i).toString());
    	}
    	System.out.println("");

    	//Lista Reservas
        LinkedList<Reserva> LReservas = Reserva.loadFromFile(LUsuarios, listaVuelos, "Reservas.txt", LNumero);
    	System.out.println("Reservas");
    	for (int i = 0 ; i < listaVuelos.size();i++){
    		System.out.println(listaVuelos.get(i).toString());
    	}
    	System.out.println("");



        boolean descuento = false;


        while (true) {

        	String su = JOptionPane.showInputDialog(null, "1.Aerolinea \n 2.Vuelo\n 3.Reserva \n 4.Usuario \n 5.Salir", "Escoja una opci�n", JOptionPane.PLAIN_MESSAGE);
            while (su.equals("") || !isInteger(su) || Integer.parseInt(su) < 1 || Integer.parseInt(su) > 5) {
                JOptionPane.showMessageDialog(null, "Opci�n incorrecta");
                su = JOptionPane.showInputDialog("1.Aerolinea \n 2.Vuelo\n 3.Reserva \n 4.Usuario \n 5.Salir");

            }

//................................................................

            if(su.equals("3")){ //MENU CASO RESERVA
            	 String sa = JOptionPane.showInputDialog("1.Crear Reserva \n 2. Buscar Reserva \n 3.Confirmar Reserva \n 4.Cancelar Reserva \n 5.Salir");
                 while (!isInteger(sa) || Integer.parseInt(sa) < 0 || Integer.parseInt(sa) > 5) {
                     JOptionPane.showMessageDialog(null, "N�mero ingresado incorrecto");
                     sa = JOptionPane.showInputDialog("1.Crear Reserva \n 2. Buscar Reserva \n 3.Confirmar Reserva \n 4.Cancelar Reserva \n 5.Salir");
                 }

                 if(sa.equals("1")){
                	int flag1=0;
                	String cedula="000000000000";
                	while(flag1==0){
                	cedula = JOptionPane.showInputDialog("Ingrese Cedula de Usuario");
                	char [] arreglopalabra = cedula.toCharArray();
        			for (int i=0;i < arreglopalabra.length;i++ ){
                		if (Character.isDigit(arreglopalabra[i])){

                		}
                		else{
                			break;
                		}
                		flag1=2;
        				}
        				if (Usuario.buscarUsuario(LUsuarios, cedula)==null){
        					flag1=0;
        				}
                	}
                	Usuario usuario = Usuario.buscarUsuario(LUsuarios, cedula); //busco usuario

                  //Busco vuelo
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

                    while (nuevoDia.equals("") || comienzaCero(nuevoDia)||!isInteger(nuevoDia) || Integer.parseInt(nuevoDia) > 31 || Integer.parseInt(nuevoDia) < 0) { //valido que el día no sea un número negativo,mayor a 31 o que no sea un número
                        JOptionPane.showMessageDialog(null, "Día ingresado incorrecto");
                        nuevoDia = JOptionPane.showInputDialog("Ingrese el día");
                    }
                    if(Integer.parseInt(nuevoDia)<10) //agrego un cero al día si es menor a 10
                      nuevoDia="0"+nuevoDia;

                    //Mes
                    String nuevoMes = JOptionPane.showInputDialog("Ingrese el mes");//Pido el mes

                    while (nuevoMes.equals("") || comienzaCero(nuevoMes) || Integer.parseInt(nuevoMes) > 12 || Integer.parseInt(nuevoMes) < 0 || !isInteger(nuevoMes)) {
                        JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                        nuevoMes = JOptionPane.showInputDialog("Ingrese el mes");
                    }
                    if(Integer.parseInt(nuevoMes)<10)
                      nuevoMes="0"+nuevoMes;    //agrego un cero si el mes es menor a 10
                    //Año
                    String nuevoAnio = JOptionPane.showInputDialog("Ingrese el año"); //Año

                    while (nuevoAnio.equals("") || comienzaCero(nuevoAnio) || Integer.parseInt(nuevoAnio) > 2017 || Integer.parseInt(nuevoAnio) < 2016 || !isInteger(nuevoAnio)) { //Valido que el año ingresado sea razonable y que sea un número
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
                        while (!isInteger(diaRegreso) || comienzaCero(diaRegreso)||Integer.parseInt(diaRegreso) > 31 || Integer.parseInt(diaRegreso) < 0) { //valido que el día no sea un número negativo,mayor a 31 o que no sea un número
                            JOptionPane.showMessageDialog(null, "Día ingresado incorrecto");
                            diaRegreso = JOptionPane.showInputDialog("Ingrese el día");
                        }
                        if(Integer.parseInt(diaRegreso)<10)
                            diaRegreso="0"+diaRegreso;

                        //Mes
                        String mesRegreso = JOptionPane.showInputDialog(null, "Ingrese el mes", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE);//Pido el mes

                        while (!isInteger(mesRegreso)||comienzaCero(mesRegreso)||Integer.parseInt(mesRegreso) > 12 || Integer.parseInt(mesRegreso) < 0 ) {
                            JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                            mesRegreso = JOptionPane.showInputDialog("Ingrese el mes");
                            
                        }
                        if(Integer.parseInt(mesRegreso)<10)
                            mesRegreso="0"+mesRegreso;
                        //Año
                        String anioRegreso = JOptionPane.showInputDialog(null, "Ingrese el año", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE); //Año de retorno

                        while (!isInteger(anioRegreso)||comienzaCero(anioRegreso)||Integer.parseInt(anioRegreso) > 2017 || Integer.parseInt(anioRegreso) < 2016 ) { //Valido que el año ingresado sea razonable y que sea un número
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

                    if (!diaRegreso.equals("")&& vuelosEncontradosIda.size()!=0 && vuelosEncontradosVuelta.size() != 0) {// si hay vuelos de ida y de regreso encontrados

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

                    if(vuelosEncontradosIda.size()!=0){ //si hay vuelos de ida que coincidan
                    String vueloString= JOptionPane.showInputDialog(null,"Ingrese el número de vuelo de ida que desee reservar"); //pido el número del vuelo que desea reservar
                    while(vueloString.equals("") ||! Vuelo.vueloEnLista(vuelosEncontradosIda,vueloString)){ //valido el número de vuelo que haya ingresado el usuario
                        JOptionPane.showMessageDialog(null,"El número de vuelo no está entre los vuelos encontrados o no existe");
                        vueloString= JOptionPane.showInputDialog(null,"Ingrese el número de vuelo de ida que desee reservar");

                    }
                    Vuelo vuelo= Vuelo.buscarVuelo(vuelosEncontradosIda,vueloString); //obtengo el vuelo de la lista de vuelos de ida encontrados
                    String asientosr="0000";

                  	int flag3=0;
                  	while(flag3==0){
                  	asientosr =JOptionPane.showInputDialog("Ingrese Numero de Asientos que desea reservar:");
                  	char [] arreglopalabra = asientosr.toCharArray();
          			for (int i=0;i < arreglopalabra.length;i++ ){
                  		if (Character.isDigit(arreglopalabra[i])){

                  		}
                  		else{
                  			break;
                  		}
                  		flag3=1;
                  	}

                  	}

                  	double ctotal ;
                  	if (descuento){
                  		ctotal =  Integer.parseInt(asientosr) * vuelo.getPrecio()-(((Integer.parseInt(asientosr) * vuelo.getPrecio())*30)/100);
                  	}
                  	else{
                  	ctotal = Integer.parseInt(asientosr) * vuelo.getPrecio();
                  	}

                  	

                        //creo reserva

                  	Reserva reserva = Reserva.crearReserva(LReservas, LNumero, usuario ,vuelo , asientosr, ctotal, fechaSalida, "RESERVADO");
                  	System.out.println(reserva);
                  	reserva.agregarReserva(LReservas, "Reservas.txt");
                  	System.out.println(LReservas);

                  }


                  if(vuelosEncontradosVuelta.size()!=0){ //si hay vuelos de vuelta que coincidan
                  String vueloStringRegreso= JOptionPane.showInputDialog(null,"Ingrese el número de vuelo de regreso que desee reservar"); //pido el número del vuelo que desea reservar
                  while(vueloStringRegreso.equals("") ||! Vuelo.vueloEnLista(vuelosEncontradosVuelta,vueloStringRegreso)){ //valido el número de vuelo que haya ingresado el usuario
                      JOptionPane.showMessageDialog(null,"El número de vuelo no está entre los vuelos encontrados o no existe");
                      vueloStringRegreso= JOptionPane.showInputDialog(null,"Ingrese el número de vuelo de regreso que desee reservar");

                  }
                  Vuelo vueloRegreso= Vuelo.buscarVuelo(vuelosEncontradosVuelta,vueloStringRegreso); //obtengo el vuelo de la lista de vuelos de regreso encontrados
                  String asientosr="0000";

                  int flag3=0;
                  while(flag3==0){
                  asientosr =JOptionPane.showInputDialog("Ingrese Numero de Asientos que desea reservar:");
                  char [] arreglopalabra = asientosr.toCharArray();
              for (int i=0;i < arreglopalabra.length;i++ ){
                    if (Character.isDigit(arreglopalabra[i])){

                    }
                    else{
                      break;
                    }
                    flag3=1;
                  }

                  }

                  double ctotal ;
                  if (descuento){
                    ctotal =  Integer.parseInt(asientosr) * vueloRegreso.getPrecio()-(((Integer.parseInt(asientosr) * vueloRegreso.getPrecio())*30)/100);
                  }
                  else{
                  ctotal = Integer.parseInt(asientosr) * vueloRegreso.getPrecio();
                  }

                  //creo reserva

                  Reserva reserva = Reserva.crearReserva(LReservas, LNumero, usuario ,vueloRegreso , asientosr, ctotal, fechaRegreso, "RESERVADO");
                  System.out.println(reserva);
                  reserva.agregarReserva(LReservas, "Reservas.txt");
                  System.out.println(LReservas);

                }



                 }
                 else if(sa.equals("2")){
                	 String nunico = JOptionPane.showInputDialog("Ingrese Numero Unico de Reserva: ");
                	 while(!Reserva.existeReserva(Integer.parseInt(nunico), LReservas)){
                	 nunico = JOptionPane.showInputDialog("Ingrese Numero Unico de Reserva: ");
                	 }
                	 System.out.println(Reserva.buscarReservaPorN(Integer.parseInt(nunico), LReservas).toString());
                 }

                 else if (sa.equals("3")){
                	 String nunico = JOptionPane.showInputDialog("Ingrese Numero Unico de Reserva: ");
                	 while(!Reserva.existeReserva(Integer.parseInt(nunico), LReservas)){
                	 nunico = JOptionPane.showInputDialog("Ingrese Numero Unico de Reserva: ");
                	 }
                	 Reserva.buscarReservaPorN(Integer.parseInt(nunico), LReservas).mmodificarEstado(LReservas, "CONFIRMADO", "Reservas.txt");
                 }
                 else if (sa.equals("4")){
                	 String nunico = JOptionPane.showInputDialog("Ingrese Numero Unico de Reserva: ");
                	 while(!Reserva.existeReserva(Integer.parseInt(nunico), LReservas)){
                	 nunico = JOptionPane.showInputDialog("Ingrese Numero Unico de Reserva: ");
                	 }
                	 Reserva.buscarReservaPorN(Integer.parseInt(nunico), LReservas).mmodificarEstado(LReservas, "CANCELADO", "Reservas.txt");
                 }
                 else{
                	 System.exit(0);
                 }


            }

//................................................................

            if (su.equals("4")){
            	String seu = JOptionPane.showInputDialog("1.Crear Usuario \n 2. Buscar Usuario \n 3.Modificar Usuario \n 4.Salir");
                while (!isInteger(su) || Integer.parseInt(seu) < 0 || Integer.parseInt(seu) > 4) {
                    JOptionPane.showMessageDialog(null, "N�mero ingresado incorrecto");
                    seu = JOptionPane.showInputDialog("1.Crear Usuario \n 2. Buscar Usuario \n 3.Modificar Usuario \n 4.Salir");
                }
                if (seu=="1"){
                	String cedula = JOptionPane.showInputDialog("Ingrese Numero de Cedula: ");
                	String idtwitter = JOptionPane.showInputDialog("Ingrese Id Twitter: ");
                	String nombres = JOptionPane.showInputDialog("Ingrese sus Nombres: ");
                	LinkedList<Reserva> lReservas =new LinkedList<>();
                	Usuario usuario = new Usuario(cedula, idtwitter, nombres, lReservas);
                	LUsuarios.add(usuario);

                }
                else if (seu=="2"){
                	String cedula = JOptionPane.showInputDialog("Ingrese Numero de Cedula: ");
                	System.out.println(Usuario.buscarUsuario(LUsuarios, cedula ));
                }
                else if (seu=="3"){
                	String cedula = JOptionPane.showInputDialog("Ingrese Numero de Cedula: ");
                	Usuario u ;
                	u = Usuario.buscarUsuario(LUsuarios, cedula);
                	String cedula1 = JOptionPane.showInputDialog("Ingrese Nuevo Numero de Cedula: ");
                	String idtwitter = JOptionPane.showInputDialog("Ingrese Id Twitter: ");
                	String nombres = JOptionPane.showInputDialog("Ingrese Nommbres: ");
                	u.modificarUsuario(cedula1, idtwitter, nombres);
                }
                	else{
                		System.exit(0);
                	}

                }


//.................
            if (su.equals("2")) { //MEN� CASO VUELO
                System.out.println();
                String sv = JOptionPane.showInputDialog("1.Crear vuelo \n 2. Modificar datos de vuelo \n 3.Buscar vuelo \n 4.Cancelar vuelo \n 5.Men� principal");
                while (!isInteger(sv) || Integer.parseInt(sv) < 0 || Integer.parseInt(sv) > 5) {
                    JOptionPane.showMessageDialog(null, "N�mero ingresado incorrecto");
                    sv = JOptionPane.showInputDialog("1.Crear vuelo \n 2. Modificar datos de vuelo \n 3.Buscar vuelo \n 4.Cancelar vuelo \n 5.Men� principal");
                }
                if (sv.equals("1")) { //Caso crear un vuelo
                    //Pido el n�mero de vuelo
                    String numVuelo = JOptionPane.showInputDialog("Ingrese el n�mero de vuelo");
                    while (Vuelo.vueloEnLista(listaVuelos, numVuelo)) {//eval�o que lo ingresado sea v�lido o que el n�mero de vuelo no exista
                        JOptionPane.showMessageDialog(null, "El n�mero ingresado es incorrecto o ya est� en uso");
                        numVuelo = JOptionPane.showInputDialog("Ingrese el n�mero de vuelo");
                    }

                    //Pido el lugar de origen
                    String origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (c�digo)");
                    while (origen.length() < 2 || isInteger(origen)) {
                        JOptionPane.showMessageDialog(null, "El origen ingresado es incorrecto");
                        origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (c�digo)");
                    }
                    //Pido el lugar de destino
                    String destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (c�digo)");
                    while (isInteger(destino) || destino.length() < 2) {
                        JOptionPane.showMessageDialog(null, "El destino ingresado es incorrecto");
                        destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (c�digo)");

                    }
                    //Pido la fecha por parte
                    //D�a
                    String dia = JOptionPane.showInputDialog(null, "Ingrese el d�a", "Ingrese la fecha", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(dia) || comienzaCero(dia) || Integer.parseInt(dia) > 31 || Integer.parseInt(dia) < 0) { //valido que el d�a no sea un n�mero negativo,mayor a 31 o que no sea un n�mero
                        JOptionPane.showMessageDialog(null, "D�a ingresado incorrecto");
                        dia = JOptionPane.showInputDialog("Ingrese el d�a");
                    }
                    if(Integer.parseInt(dia)<10)
                        dia="0"+dia; //si el d�a es menor a 10 le agrego un cero al principio
                    //Mes
                    String mes = JOptionPane.showInputDialog(null, "Ingrese el mes", "Ingrese la fecha", JOptionPane.QUESTION_MESSAGE);

                    while (!isInteger(mes) || comienzaCero(mes)|| Integer.parseInt(mes) > 12 || Integer.parseInt(mes) < 1) {
                        JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                        mes = JOptionPane.showInputDialog("Ingrese el mes");

                    }

                    if(Integer.parseInt(mes)<10)
                        mes="0"+mes; //le antepongo un cero al n�mero del mes, si el mes es menor a 10

                    System.out.println("Ingrese el a�o"); //A�o
                    String anio = JOptionPane.showInputDialog(null, "Ingrese el a�o", "Ingrese la fecha", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(anio) || comienzaCero(anio)||Integer.parseInt(anio) > 2017 || Integer.parseInt(anio) < 2016) { //Valido que el a�o ingresado sea razonable y que sea un n�mero
                        JOptionPane.showMessageDialog(null, "A�o ingresado incorrecto");
                        JOptionPane.showInputDialog("Ingrese el a�o");
                    }
                    String fecha = dia + "/" + mes + "/" + anio;  //Uno el d�a,mes y a�o con "/"

                    //Pido la hora por partes
                    //Horas
                    String hora = JOptionPane.showInputDialog(null, "Ingrese la hora", "Ingrese la hora de vuelo", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(hora) || comienzaCero(hora)||Integer.parseInt(hora) < 0 || Integer.parseInt(hora) > 24) {
                        JOptionPane.showMessageDialog(null, "Hora ingresada incorrecta");
                        hora = JOptionPane.showInputDialog("Ingrese la hora");
                    }
                    //Minutos
                    String minutos = JOptionPane.showInputDialog(null, "Ingrese los minutos", "Ingrese la hora", JOptionPane.QUESTION_MESSAGE);
                    while (!isInteger(minutos) || comienzaCero(minutos)||Integer.parseInt(minutos) < 0 || Integer.parseInt(minutos) > 60) {
                        JOptionPane.showMessageDialog(null, "Minuto ingresado incorrecto");
                        minutos = JOptionPane.showInputDialog("Ingrese los minutos");
                    }
                    if (Integer.parseInt(minutos) < 10) {
                        minutos = "0" + minutos;
                    }
                    String horaFinal = hora + ":" + minutos;

                    //Pido el n�mero de asientos disponibles
                    String asientosDisponibles = JOptionPane.showInputDialog("Ingrese el n�mero de asientos disponibles");
                    while (!isInteger(asientosDisponibles) || Integer.parseInt(asientosDisponibles) < 0) { //valido la entrada
                        JOptionPane.showMessageDialog(null, "N�mero ingresado incorrecto");
                        asientosDisponibles = JOptionPane.showInputDialog("Ingrese el n�mero de asientos disponibles");
                    }
                    //Pido el precio del vuelo
                    String precio = JOptionPane.showInputDialog("Ingrese el precio");
                    while (!isInteger(precio)) {
                        JOptionPane.showMessageDialog(null, "Precio ingresado incorrecto");
                        precio = JOptionPane.showInputDialog("Ingrese el precio");
                    }
                    //Pido n�mero de escalas
                    String numeroEscalas = JOptionPane.showInputDialog("Ingrese el n�mero de escalas");
                    while (!isInteger(numeroEscalas) || Integer.parseInt(numeroEscalas) < 0 || Integer.parseInt(numeroEscalas) > 3) {
                        JOptionPane.showMessageDialog(null, "N�mero ingresado incorrecto");
                        numeroEscalas = JOptionPane.showInputDialog("Ingrese el n�mero de escalas");
                    }
                    //Pido RUC
                    String rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerolinea");
                    while (rucAerolinea.length() < 13) {
                        JOptionPane.showMessageDialog(null, "RUC ingresado incorrecto");
                        rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerolinea");
                    }
                    //Creo un nuevo objeto vuelo con los datos ingresados
                    Vuelo nuevoVuelo = new Vuelo(numVuelo, origen, destino, fecha, horaFinal, Integer.parseInt(asientosDisponibles), Double.parseDouble(precio), Integer.parseInt(numeroEscalas), rucAerolinea);
                    //Lo agrego a la lista de vuelos
                    listaVuelos.add(nuevoVuelo);
                    //Refresco el archivo de vuelos
                    if (Vuelo.cargarAlArchivo(listaVuelos, "vuelos.txt")) {

                        JOptionPane.showMessageDialog(null, nuevoVuelo.aString() + "\n Ingreso de nuevo vuelo exitoso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Houston,tenemos un problema. Algo ocurri� y su vuelo no se pudo ingresar :(");
                    }
                    System.out.println(listaVuelos);

                } else if (sv.equals("2")) { //CASO MODIFICAR DATOS DE UN VUELO
                    JOptionPane.showMessageDialog(null, "A continuaci�n, ingrese los valores que desee modificar \n caso contrario, presione enter para saltarse al siguiente campo"); //Pido el n�mero de vuelo

                    //Pido el n�mero de vuelo para buscarlo
                    String numVuelo = JOptionPane.showInputDialog("Ingrese el n�mero de vuelo a modificar");
                    while (!Vuelo.vueloEnLista(listaVuelos, numVuelo)) {//eval�o que lo ingresado sea v�lido o que el n�mero de vuelo no exista
                        JOptionPane.showMessageDialog(null, "El n�mero ingresado es incorrecto o no existe el vuelo");
                        numVuelo = JOptionPane.showInputDialog("Ingrese el n�mero de vuelo nuevo");
                    }
                    Vuelo vueloAmodificar = Vuelo.buscarVuelo(listaVuelos, numVuelo); //vuelo obtenido con el n�mero ingresado por el usuario,sobre este har� modificaciones
                    System.out.println(vueloAmodificar);

                    numVuelo = JOptionPane.showInputDialog("Ingrese el n�mero de vuelo nuevo");
                    if (!numVuelo.equals("")) { //Si no presion� enter,modifico
                        while (Vuelo.vueloEnLista(listaVuelos, numVuelo)) {//eval�o que lo ingresado sea v�lido o que el n�mero de vuelo no exista
                            JOptionPane.showMessageDialog(null, "El n�mero ingresado es incorrecto o ya existe");
                            numVuelo = JOptionPane.showInputDialog("Ingrese el nuevo n�mero de vuelo");
                        }
                        vueloAmodificar.setNumeroVuelo(numVuelo);

                    }
                    //Obtengo el vuelo con el n�mero de vuelo ingresado
                    //Pido el lugar de origen nuevo
                    String origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (c�digo) nuevo");
                    if (!origen.equals("")) { //SI NO ME INGRESA VAC�O
                        while (origen.length() < 2) {
                            JOptionPane.showMessageDialog(null, "El origen ingresado es incorrecto");
                            origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (c�digo) nuevo");
                        }
                        vueloAmodificar.setOrigen(origen);//modifico su origen
                    }

                    //Pido el lugar de destino nuevo
                    String destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (c�digo) nuevo");
                    if (!destino.equals("")) { //Si me ingres� algo diferente de vac�o
                        while (destino.length() < 2) {
                            JOptionPane.showMessageDialog(null, "El destino ingresado es incorrecto");
                            destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (c�digo) nuevo");
                        }

                        vueloAmodificar.setDestino(destino); //Modifico el destino del vuelo
                    }

                    //Pido la fecha nueva por parte
                    //D�a
                    String nuevoDia = JOptionPane.showInputDialog(null, "Ingrese el d�a nuevo", "Ingrese la nueva fecha", JOptionPane.QUESTION_MESSAGE); //Pido el d�a nuevo
                    if (!nuevoDia.equals("")) {
                        while (!isInteger(nuevoDia) ||comienzaCero(nuevoDia)|| Integer.parseInt(nuevoDia) > 31 || Integer.parseInt(nuevoDia) < 0) { //valido que el d�a no sea un n�mero negativo,mayor a 31 o que no sea un n�mero
                            JOptionPane.showMessageDialog(null, "D�a ingresado incorrecto");
                            nuevoDia = JOptionPane.showInputDialog("Ingrese el d�a nuevo");
                        }
                        if(Integer.parseInt(nuevoDia)<10)
                            nuevoDia="0"+nuevoDia;  //si el nuevo d�a es menor a 10 le agrego un cero

                        //Mes
                        String nuevoMes = JOptionPane.showInputDialog("Ingrese el nuevo mes");//Pido el nuevo mes

                        while (!isInteger(nuevoMes) || comienzaCero(nuevoMes)||Integer.parseInt(nuevoMes) > 12 || Integer.parseInt(nuevoMes) < 0) { //valido lo que ingresa
                            JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                            nuevoMes = JOptionPane.showInputDialog("Ingrese el nuevo mes");
                        }
                        if (Integer.parseInt(nuevoMes)<10)
                            nuevoMes="0"+nuevoMes;  //le agrego un cero al mes si es menor a 10
                        //A�o
                        String nuevoAnio = JOptionPane.showInputDialog("Ingrese el a�o"); //Nuevo A�o

                        while ( !isInteger(nuevoAnio) ||comienzaCero(nuevoAnio)|| Integer.parseInt(nuevoAnio) > 2017 || Integer.parseInt(nuevoAnio) < 2016) { //Valido que el a�o ingresado sea razonable y que sea un n�mero
                            JOptionPane.showMessageDialog(null, "A�o ingresado incorrecto");
                            nuevoAnio = JOptionPane.showInputDialog("Ingrese el a�o");
                        }

                        String fecha = nuevoDia + "/" + nuevoMes + "/" + nuevoAnio;  //Uno el d�a,mes y a�o con "/"
                        vueloAmodificar.setFechaString(fecha); //MOODIFICO LA FECHA

                    }

                    //Pido la hora por partes
                    //Horas
                    String hora = JOptionPane.showInputDialog(null, "Ingrese las horas", "Ingrese la nueva hora", JOptionPane.QUESTION_MESSAGE);
                    if (!hora.equals("")) {//Si no me ingresa algo vac�o
                        while (!isInteger(hora) || comienzaCero(hora)||Integer.parseInt(hora) < 0 || Integer.parseInt(hora) > 24) {
                            JOptionPane.showMessageDialog(null, "Hora ingresada incorrecta");

                            hora = JOptionPane.showInputDialog("Ingrese las horas");
                        }
                        //Minutos
                        String minutos = JOptionPane.showInputDialog("Ingrese los minutos");
                        while (!isInteger(minutos) || comienzaCero(minutos)||Integer.parseInt(minutos) < 0 || Integer.parseInt(minutos) > 60) {
                            JOptionPane.showMessageDialog(null, "Minuto ingresado incorrecto");

                            minutos = JOptionPane.showInputDialog("Ingrese los minutos");
                        }
                        if (Integer.parseInt(minutos) < 10) {
                            minutos = "0" + minutos;
                        }
                        String horaFinal = hora + ":" + minutos; //Nueva hora
                        vueloAmodificar.setHora(horaFinal); //MODIFICO LA HORA

                    }
                    //Pido el n�mero de asientos disponibles
                    String asientosDisponibles = JOptionPane.showInputDialog("Ingrese el n�mero de asientos disponibles");
                    if (!asientosDisponibles.equals("")) {
                        while (!isInteger(asientosDisponibles) || comienzaCero(asientosDisponibles) || Integer.parseInt(asientosDisponibles) < 0) { //valido la entrada
                            JOptionPane.showMessageDialog(null, "N�mero ingresado incorrecto");
                            asientosDisponibles = JOptionPane.showInputDialog("Ingrese el n�mero de asientos disponibles");
                        }
                        vueloAmodificar.setAsientosDisponibles(Integer.parseInt(asientosDisponibles)); //Modificar los asientos disponibles

                    }
                    //*********************
                    //Pido el precio del vuelo
                    String precio = JOptionPane.showInputDialog("Ingrese el precio"); //Ingresa el precio
                    if (!precio.equals("")) {
                        while (precio.equals("") ||comienzaCero(precio)|| !isInteger(precio)) { //valido
                            JOptionPane.showMessageDialog(null, "Precio ingresado incorrecto");

                            precio = JOptionPane.showInputDialog("Ingrese el precio");
                        }
                        vueloAmodificar.setPrecio(Double.parseDouble(precio)); //modifico su precio
                    }
                    //Pido RUC
                    String rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerol�nea");
                    if (!rucAerolinea.equals("")) { //si no presion� enter el usuario
                        while (rucAerolinea.equals("") || rucAerolinea.length() < 13) { //valido
                            JOptionPane.showMessageDialog(null, "RUC ingresado incorrecto");
                            rucAerolinea = JOptionPane.showInputDialog("Ingrese el RUC de la aerol�nea");
                        }
                        vueloAmodificar.setRucAerolinea(rucAerolinea); //modifico el ruc de la aerol�nea
                    }

                    JOptionPane.showMessageDialog(null, vueloAmodificar.aString(), "Vuelo con nuevos datos \n Vuelo modificado ", JOptionPane.PLAIN_MESSAGE); //imprimo por pantalla el vuelo con los datos modificados
                    Vuelo.cargarAlArchivo(listaVuelos, "vuelos.txt"); //escribo en el archivo de vuelos la lista de vuelos con el vuelo ya modificado
                    System.out.println(listaVuelos);

                } else if (sv.equals("3")) { //CASO buscar vuelo
                    //Pido el lugar de origen nuevo
                    JOptionPane.showMessageDialog(null, "Ingrese los par�metros de b�squeda");
                    String origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (c�digo) nuevo"); //pido origen

                    while (origen.equals("") || origen.length() < 2) { //valido el origen
                        JOptionPane.showMessageDialog(null, "El origen ingresado es incorrecto");
                        origen = JOptionPane.showInputDialog("Ingrese el lugar de origen (c�digo) nuevo");
                    }

                    //Pido el lugar de destino
                    String destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (c�digo) nuevo");

                    while (destino.equals("") || destino.length() < 2) { //valido lo ingresado
                        JOptionPane.showMessageDialog(null, "El destino ingresado es incorrecto");
                        destino = JOptionPane.showInputDialog("Ingrese el lugar de destino (c�digo) nuevo");
                    }

                    //Pido la fecha por parte
                    //D�a
                    String nuevoDia = JOptionPane.showInputDialog(null, "Ingrese el d�a", "Ingrese la fecha de salida", JOptionPane.QUESTION_MESSAGE); //Pido el d�a

                    while (nuevoDia.equals("") || !isInteger(nuevoDia) || comienzaCero(nuevoDia)||Integer.parseInt(nuevoDia) > 31 || Integer.parseInt(nuevoDia) < 0) { //valido que el d�a no sea un n�mero negativo,mayor a 31 o que no sea un n�mero
                        JOptionPane.showMessageDialog(null, "D�a ingresado incorrecto");
                        nuevoDia = JOptionPane.showInputDialog("Ingrese el d�a");
                    }
                    if(Integer.parseInt(nuevoDia)<10)
                    nuevoDia="0"+nuevoDia;

                    //Mes
                    String nuevoMes = JOptionPane.showInputDialog("Ingrese el mes");//Pido el mes

                    while (nuevoMes.equals("") || comienzaCero(nuevoMes)||!isInteger(nuevoMes)||Integer.parseInt(nuevoMes) > 12 || Integer.parseInt(nuevoMes) < 0) {
                        JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                        nuevoMes = JOptionPane.showInputDialog("Ingrese el mes");
                    }
                    if(Integer.parseInt(nuevoMes)<10)
                      nuevoMes="0"+nuevoMes;
                    //A�o
                    String nuevoAnio = JOptionPane.showInputDialog("Ingrese el a�o"); //A�o

                    while (nuevoAnio.equals("") || comienzaCero(nuevoAnio)||!isInteger(nuevoAnio)||Integer.parseInt(nuevoAnio) > 2017 || Integer.parseInt(nuevoAnio) < 2016 ) { //Valido que el a�o ingresado sea razonable y que sea un n�mero
                        JOptionPane.showMessageDialog(null, "A�o ingresado incorrecto");
                        nuevoAnio = JOptionPane.showInputDialog("Ingrese el a�o");
                    }

                    String fechaSalida = nuevoDia + "/" + nuevoMes + "/" + nuevoAnio;  //Uno el d�a,mes y a�o con "/"
                    String fechaRegreso = null;

                    //PREGUNTO SI VA A INGRESAR FECHA DE REGRESO PARA LA B�SQUEDA
                    JOptionPane.showMessageDialog(null, "Si desea, ingrese la fecha de regreso.Caso contrario,presione enter");
                    //Pido la fecha nueva por parte
                    //D�a
                    String diaRegreso = JOptionPane.showInputDialog(null, "Ingrese el d�a", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE); //Pido el d�a
                    if (!diaRegreso.equals("")) {
                        while (!isInteger(diaRegreso) || comienzaCero(diaRegreso)||Integer.parseInt(diaRegreso) > 31 || Integer.parseInt(diaRegreso) < 0) { //valido que el d�a no sea un n�mero negativo,mayor a 31 o que no sea un n�mero
                            JOptionPane.showMessageDialog(null, "D�a ingresado incorrecto");
                            diaRegreso = JOptionPane.showInputDialog("Ingrese el d�a");
                        }
                        if(Integer.parseInt(diaRegreso)<10)
                            diaRegreso="0"+diaRegreso;

                        //Mes
                        String mesRegreso = JOptionPane.showInputDialog(null, "Ingrese el mes", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE);//Pido el mes

                        while ( !isInteger(mesRegreso)||Integer.parseInt(mesRegreso) > 12 || comienzaCero(mesRegreso)||Integer.parseInt(mesRegreso) < 0 ) {
                            JOptionPane.showMessageDialog(null, "Mes ingresado incorrecto");
                            mesRegreso = JOptionPane.showInputDialog("Ingrese el mes");
                        }
                        if(Integer.parseInt(mesRegreso)<10)
                          mesRegreso="0"+mesRegreso;
                        //A�o
                        String anioRegreso = JOptionPane.showInputDialog(null, "Ingrese el a�o", "Ingrese la fecha de regreso", JOptionPane.QUESTION_MESSAGE); //A�o de retorno

                        while (!isInteger(anioRegreso)||comienzaCero(anioRegreso)||Integer.parseInt(anioRegreso) > 2017 || Integer.parseInt(anioRegreso) < 2016 ) { //Valido que el a�o ingresado sea razonable y que sea un n�mero
                            JOptionPane.showMessageDialog(null, "A�o ingresado incorrecto");
                            anioRegreso = JOptionPane.showInputDialog("Ingrese el a�o");
                        }

                        fechaRegreso = diaRegreso + "/" + mesRegreso + "/" + anioRegreso;  //Uno el d�a,mes y a�o con "/"

                    }

                    //Pido n�mero de escalas
                    String numeroEscalas = JOptionPane.showInputDialog("Ingrese el n�mero de escalas");

                    while (numeroEscalas.equals("") || !isInteger(numeroEscalas) || Integer.parseInt(numeroEscalas) < 0 || Integer.parseInt(numeroEscalas) > 4) {
                        JOptionPane.showMessageDialog(null, "N�mero ingresado incorrecto");
                        numeroEscalas = JOptionPane.showInputDialog("Ingrese el n�mero de escalas");
                    }
                    //BUSCO VUELO
                    LinkedList<Vuelo> vuelosEncontradosIda = Vuelo.buscarVuelosDisponibles(listaVuelos, origen, destino, fechaSalida, Integer.parseInt(numeroEscalas)); //busco los vuelos de ida
                    LinkedList<Vuelo> vuelosEncontradosVuelta=new LinkedList<>();

                    if(fechaRegreso!=null){ //si el usuario me ingres� una fecha de regreso
                        vuelosEncontradosVuelta=Vuelo.buscarVuelosDisponiblesVuelta(listaVuelos, origen, destino, fechaRegreso, Integer.parseInt(numeroEscalas),vuelosEncontradosIda); //busco los vuelos de regreso
                    }
                        String vuelosEnString=""; //string donde estar�n todos los vuelos encontrados

                    if (!diaRegreso.equals("") && vuelosEncontradosVuelta.size() != 0) {// si hay vuelos de ida y de regreso encontrados

                        descuento = true;    //valor booleano que indica que el cliente tiene 30% de dscto
                        LinkedList<Vuelo> vuelosEncontrados= new LinkedList<>(); //lista que contendr� a los vuelos de ida y regreso
                        vuelosEncontrados.addAll(vuelosEncontradosIda); //agrego los vuelos encontrados con la fecha de salida
                        vuelosEncontrados.addAll(vuelosEncontradosVuelta); //agrego los vuelos encontrados con la fecha de regreso
                        for (Vuelo v:vuelosEncontrados)
                        vuelosEnString+=v+"\n"; //agrego al string de los vuelos que mostrar� por pantalla cada vuelo encontrado
                        JOptionPane.showMessageDialog(null, vuelosEnString,"Vuelos encontrados",JOptionPane.PLAIN_MESSAGE); //vuelos encontrados mostrados por pantalla
                    }
                    else { // si s�lo hay vuelos de ida
                        descuento = false;//cliente no tiene el descuento
                        for (Vuelo v:vuelosEncontradosIda)
                        vuelosEnString+=v+"\n"; //agrego al string de los vuelos que mostrar� por pantalla cada vuelo encontrado

                        JOptionPane.showMessageDialog(null, vuelosEnString, "Vuelos encontrados (s�lo de ida)", JOptionPane.PLAIN_MESSAGE); //Imprimo por pantalla la lista con vuelos disponibles
                    }





                } else if (sv.equals("4")) { //CASO CANCELAR VUELO
                    String vueloAeliminar = JOptionPane.showInputDialog("Ingrese el n�mero de vuelo a cancelar");

                    while (vueloAeliminar.equals("")) {//eval�o que lo ingresado sea v�lido o que el n�mero de vuelo no exista
                        JOptionPane.showMessageDialog(null, "El n�mero ingresado es incorrecto");
                        vueloAeliminar = JOptionPane.showInputDialog("Ingrese el n�mero de vuelo a cancelar");

                    }
                    if (!Vuelo.vueloEnLista(listaVuelos, vueloAeliminar)) { //si el n�mero de vuelo no coincide con niguno de la lista de vuelos
                        JOptionPane.showMessageDialog(null, "No existe ning�n vuelo con ese n�mero,int�ntelo nuevamente");
                    } else {
                        Vuelo vueloEliminado = Vuelo.buscarVuelo(listaVuelos, vueloAeliminar); //busco el vuelo por el n�mero ingresado
                        //M�TODO EN LA CLASE RESERVA QUE DADAS LAS LISTAS DE VUELOS,USUARIOS Y RESERVAS, LOS DEJA SIN REFERENCIAR Y LOS ELIMINA

                        listaVuelos.remove(vueloEliminado);   //ELIMINO DE LA LISTA EL VUELO CON EL N�MERO DE VUELO INGRESADO

                        if (Vuelo.cargarAlArchivo(listaVuelos, "vuelos.txt")) { //refrescar datos en el archivo
                            JOptionPane.showMessageDialog(null, vueloEliminado); //imprimo los datos del vuelo eliminado
                            JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo concretar la remoci�n del vuelo");
                        }
                    }
                }

            } else if (su.equals("1")) { //CASO MEN� AEROL�NEA
                String sA = JOptionPane.showInputDialog("1.Crear aerolinea \n 2. Modificar datos de aerolinea \n 3.Buscar aerolinea \n 4.Menu principal");
                while (!isInteger(su) || Integer.parseInt(su) < 0 || Integer.parseInt(su) > 5) {
                    JOptionPane.showMessageDialog(null, "Numero ingresado incorrecto");
                    su = JOptionPane.showInputDialog("1.Crear aerolinea \n 2. Modificar datos de aerolinea \n 3.Buscar aerolinea \n 4.Menu principal");
                }
                if (sA.equals("1")) { //CASO CREAR AEROL�NEA
                    //pido el RUC
                    String ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerolinea");
                    while (ruc.length() < 13 || !isInteger(ruc)) {
                        JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto");
                        ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerolinea");
                    }
                    //Pido la raz�n social
                    String razonSocial = JOptionPane.showInputDialog("Ingrese la razon social)");
                    while (razonSocial.equals("")) {
                        JOptionPane.showMessageDialog(null, "La raz�n social ingresada es incorrecta");
                        razonSocial = JOptionPane.showInputDialog("Ingrese la razon social");

                    }
                    //PIDO EL NOMBRE COOMERCIAL
                    String nombreComercial = JOptionPane.showInputDialog("Ingrese el nombre comercial");
                    while (nombreComercial.equals("")) {
                        JOptionPane.showMessageDialog(null, "El nombre comercial es incorrecto");
                        nombreComercial = JOptionPane.showInputDialog("Ingrese el nombre comercial");

                    }
                    Aerolinea aerolinea = new Aerolinea(ruc, razonSocial, nombreComercial); //CREO UNA NUEVA AEROL�NEA CON LOS DATOS INGRESADOS
                    listaAerolineas.add(aerolinea); //AGREGO A LA LISTA
                    if (Aerolinea.cargarAlArchivo(listaAerolineas, "aerolineas.txt")) {
                        JOptionPane.showMessageDialog(null, "Aerol�nea a agregar:");
                        JOptionPane.showMessageDialog(null, aerolinea); //Imprimo datos de la nueva aerol�nea
                        JOptionPane.showMessageDialog(null, "Se ha agregado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo escribir en el archivo de aerol�neas");
                    }
                } else if (sA.equals("2")) { //CASO MODIFICAR DATOS DE AEROL�NEA
                    //pido el ruc para obtener la aerolinea
                    String ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerol�nea");
                    while (ruc.length() < 13 || !isInteger(ruc) || !Aerolinea.aerolineaEnLista(listaAerolineas, ruc)) { //valido el ruc ingresado, y si existe una aerolinea con ese ruc
                        JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto");
                        ruc = JOptionPane.showInputDialog("Ingrese el nuevo RUC de la aerol�nea ");
                    }
                    Aerolinea aerolineaModificar = Aerolinea.buscarPorRuc(listaAerolineas, ruc);
                    JOptionPane.showMessageDialog(null, "Ingrese los nuevos datos. Si no los desea modificar, presione enter");
                    //pido el nuevo ruc
                    String rucNuevo = JOptionPane.showInputDialog("Ingrese el nuevo RUC de la aerolinea");
                    if (!rucNuevo.equals("")) {
                        while (rucNuevo.length() < 13 || !isInteger(rucNuevo) || Aerolinea.aerolineaEnLista(listaAerolineas, rucNuevo)) { //valido el ruc ingresado, y si existe una aerolinea con ese ruc
                            JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto o ya existe ");
                            rucNuevo = JOptionPane.showInputDialog("Ingrese el nuevo RUC de la aerol�nea ");
                        }
                        aerolineaModificar.setRuc(rucNuevo); //modifico el ruc
                    }
                    //PIDO EL NOMBRE COOMERCIAL
                    String nombreComercial = JOptionPane.showInputDialog("Ingrese el nuevo nombre comercial");
                    if (!nombreComercial.equals("")) { //si no ingres� enter
                        while (nombreComercial.equals("")) {
                            JOptionPane.showMessageDialog(null, "El nombre comercial es incorrecto");
                            nombreComercial = JOptionPane.showInputDialog("Ingrese el nuevo nombre comercial");

                        }
                        aerolineaModificar.setNombreComercial(nombreComercial); //moidifico el nombre comercial
                    }
                    //Pido la raz�n social nueva
                    String razonSocial = JOptionPane.showInputDialog("Ingrese la nueva razon social)");
                    if (!razonSocial.equals("")) { //si no ingres� enter
                        while (razonSocial.equals("")) {
                            JOptionPane.showMessageDialog(null, "La raz�n social ingresada es incorrecta");
                            razonSocial = JOptionPane.showInputDialog("Ingrese la razon social");

                        }
                        aerolineaModificar.setRazonSocial(razonSocial); //modifico la raz�n social
                    }
                    JOptionPane.showMessageDialog(null, "Estado de la aerolinea");
                    JOptionPane.showMessageDialog(null, aerolineaModificar); //Imprimo la aerol�nea con los datos actuales
                    if (Aerolinea.cargarAlArchivo(listaAerolineas, "aerolineas.txt")) //refresco los datos en el archivo
                    {
                        JOptionPane.showMessageDialog(null, "Datos ingresados exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudieron guardar los datos");
                    }

                } else if (sA.equals("3")) { //CASO BUSCAR AEROL�NEA
                    //PIDO EL RUC DE LA AEROL�NEA
                    String ruc = JOptionPane.showInputDialog("Ingrese el RUC de la aerolinea a buscar");
                    while (ruc.length() < 13 || !isInteger(ruc)) {
                        JOptionPane.showMessageDialog(null, "El RUC ingresado es incorrecto ");
                        ruc = JOptionPane.showInputDialog("\"Ingrese el RUC de la aerolinea a buscar");
                    }
                    if (!Aerolinea.aerolineaEnLista(listaAerolineas, ruc)) { //SI EL RUC INGRESADO NO EXISTE, SALE DEL MEN�
                        JOptionPane.showMessageDialog(null, "no hay aerolinea con ese ruc");
                    } else {
                        Aerolinea aerolineaEncontrada = Aerolinea.buscarPorRuc(listaAerolineas, ruc); //busco la aerol�nea por ruc
                        JOptionPane.showMessageDialog(null, aerolineaEncontrada); //imprimo los datos de la aerol�nea
                    }
                }
            }
        }
    }

    //funci�n que eval�a si una expresi�n es un n�mero
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
