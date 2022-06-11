package ui;

import db.interfaces.DBmanager;
import db.interfaces.UsuariosInterface;

import java.io.BufferedReader;
import java.io.IOException;

import db.jdbc.JDBCmanager;
import db.jpa.JPAmanager;
import pojos.Departamentos;
import pojos.Doctores;
import pojos.Enfermeros;
import pojos.Habitaciones;
import pojos.Pacientes;
import pojos.Rol;
import pojos.Usuario;

import xml.testhospital;

import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import Generar.GenerarAleatorio;

public class Menu{
	private static DBmanager dbman = new JDBCmanager();
	private static UsuariosInterface userman = new JPAmanager();
//	private static final String[] Identificacion = {"Salir", "Jefe", "Doctor", "Enfermero", "Paciente"};
	private static final String []MENUDOCTOR= {"Salir","Ver Pacientes","Modificar Historial Pacientes","Ver Enfermeros"};
//	private static final String []MENUENFERMERO= {};
	//private static final String []MENUPACIENTE= {};
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String[] MENUJEFE = {"Salir", "Introducir un elemento","Introducir Muchos elemento","Modificar Informacion","Borrar elementos","Asignar Enfermeros a Paciente","VIsualizar elementos","Crear XML hospital","Leer XML"};
	private static final String[] ELEMENTO = {"Salir","Doctor","Enfermero","Paciente"};
	private static final DateTimeFormatter formatterFecha =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private static final String[] MENU_INICIO ={"Salir del programa", "Registrarse", "Login"};
	static Usuario usuario;
	
	
	public static void main(String[] args) {
		dbman.connect();
		userman.connect();
		System.out.println("Bienvenido al men� de nuestro hospital");
		int respuesta=-1;
		do {
			respuesta = mostrarOpciones(MENU_INICIO);
			LOGGER.fine("El usuario ha seleccionado la opción " + respuesta + " en el menú principal");
			switch(respuesta) {
				case 1 :{registrarse();}
				case 2 :{ login();}
			}
		}while (respuesta!=0);
		dbman.disconnect();
		userman.disconnect();
	}


	private static void registrarse() {
		try {
			System.out.println("Indique su email:");
			String email = reader.readLine();
			System.out.println("Indique su contraseña:");
			String pass = reader.readLine();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] hash = md.digest();
			System.out.println(userman.getRoles());
			System.out.println("Indique el id del rol:");
			int rolId = Integer.parseInt(reader.readLine());
			Rol rol = userman.getRolById(rolId);
			Usuario usuario = new Usuario(email, hash, rol);
			rol.addUsuario(usuario);
			userman.addUsuario(usuario);
			
			switch(usuario.getRol().getNombre()) {
				case "paciente":{añadirPacienteDb();
				break;}
				case "doctor" :{añadirDoctorDb();
								dbman.actualizarDoctoresDepartamentos();
				break;}
				case "enfermero" :{añadirEnfermeroDb();
				break;}
				case "jefe" :{
				break;}
			}
	}catch(IOException | NoSuchAlgorithmException e) {
		LOGGER.warning("Error en el registro\n" + e);
	}
	}



	private static void login() {
		try {
			System.out.println("Indique su email:");
			String email = reader.readLine();
			System.out.println("Indique su contraseña:");
			String pass = reader.readLine();
			usuario = userman.checkLogin(email, pass);
			if (usuario == null) {
				System.out.println("Email o contraseña incorrectos");
			} else {
				switch(usuario.getRol().getNombre()) {
					case "paciente":{mostrarMenuPaciente();
					break;}
					case "doctor" :{mostrarMenuDoctor();
					break;}
					case "enfermero" :{ mostarMenuEnfermero();
					break;}
					case "jefe" :{ mostrarMenuJefe();
					break;}
					}
				}
			}
		 catch(IOException e) {
			LOGGER.warning("Error en el registro\n" + e);
		}
	}

	private static void mostrarMenuJefe() {
		System.out.println("Bienvenido al menu jefe");
		int respuesta=-1;
		do {
			respuesta=mostrarOpciones(MENUJEFE);
			switch(respuesta) {
			case 1:{añadirTrabajador();
					break;}
			case 2:{añadirMuchosTrabajadores();
					break;}
			case 3:{ModificarInformacion();
					break;}
			case 4:{borrarElemento();
				break;				
			}
			case 5:{asignarEnfermerosPaciente();
				break;				
			}
			case 6:{visualizarElementos();
				break;
			}
			case 7:{crearXMLHospital();
				break;
			}
			case 8:{
				leerXML();
			}
			}
		}while(respuesta!=0);
	}
		
	




	private static void leerXML() {
		try {
			testhospital.leerhospitalxml();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private static void crearXMLHospital() {
		try {
			testhospital.hospitalxml();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private static void visualizarElementos() {
			int respuesta=-1;
			do {
				System.out.println("\nQue tipo de elemento desea visualizar:");
				respuesta=mostrarOpciones(ELEMENTO);
				switch(respuesta) {
				case 1:{verDoctorDb();
						break;}
				case 2:{verEnfermeroDb();
						break;}
				case 3:{verPacienteDb();
						break;}
				}
			}while(respuesta!=0);
			
		}
		
	private static void verPacienteDb() {
		System.out.println("Indique el nombre del paciente del que quiere visualizar datos:\nSi quiere verlos todos pulse ENTER.");
		try {
			String busq=reader.readLine();
			ArrayList<Pacientes> paciente = dbman.buscarPacienteNombre(busq);
			mostrarArrayList(paciente);
		}catch (IOException e) {
				e.printStackTrace();
			}
	}


	private static void verEnfermeroDb() {
		System.out.println("Indique el nombre del enfermero del que quiere visualizar datos:\nSi quiere verlos todos pulse ENTER.");
		try {
			String busq=reader.readLine();
			ArrayList<Enfermeros> enfermero =dbman.buscarEnfermeroNombre(busq);
			mostrarArrayList(enfermero);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	private static void verDoctorDb() {
		System.out.println("Indique el nombre del doctor del que quiere visualizar datos:\nSi quiere verlos todos pulse ENTER.");
		try {
		String busq=reader.readLine();
		ArrayList<Doctores> doctor =dbman.buscarDoctorNombre(busq);
		mostrarArrayList(doctor);
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	


	private static void asignarEnfermerosPaciente() {
		System.out.println("Indique el nombre del enfermero al que quiere asignar pacientes:\nSi quiere verlos todos pulse ENTER.");
		try {
			String busq=reader.readLine();
			ArrayList<Enfermeros> enfermero =dbman.buscarEnfermeroNombre(busq);
			mostrarArrayList(enfermero);
			System.out.println("Seleccione el id del enfermero:");
			int id = Integer.parseInt(reader.readLine());
			Enfermeros enf= dbman.buscarEnfId(id);
			int id2=-1;
			do {
				System.out.println("Seleccione el id del paciente:\n Si no deesea añadir mas pulse 0");
				id2 = Integer.parseInt(reader.readLine());
				if (id2!=0) {
					Pacientes p = dbman.buscarPacId(id2);
					System.out.println(p);
					enf.addPaciente(p);}
			}while(id2!=0); 
			
			dbman.addEnfPac(enf);
		}catch (IOException e) {
				e.printStackTrace();
		}
	}


	private static void borrarElemento() {
		int respuesta=-1;
		do {
			System.out.println("Que tipo de elemento desea borrar:");
			respuesta=mostrarOpciones(ELEMENTO);
			switch(respuesta) {
			case 1:{borrarDoctorDb();
			dbman.actualizarDoctoresDepartamentos();
					break;}
			case 2:{borrarEnfermeroDb();
					break;}
			case 3:{borrarPacienteDb();
					break;}
			}
		}while(respuesta!=0);		
	}


	private static void borrarPacienteDb() {
		
		try {
			System.out.println("Indique el nombre del paciente del que quiere modificar datos:\nSi quiere verlos todos pulse ENTER.");
			String busq=reader.readLine();
			ArrayList<Pacientes> paciente = dbman.buscarPacienteNombre(busq);
			mostrarArrayList(paciente);
			System.out.println("Seleccione el id del paciente deseado:\nSi no desea borrar introduzca 0.");
			int id = Integer.parseInt(reader.readLine());
			boolean exito = dbman.deletePaciente(id);
			if (exito) {
				System.out.println("Enfermero borrado con éxito");
			} else {
				System.out.println("No hay ningún enfermero con id " + id);
			}
			}
			 catch (IOException e) {
				e.printStackTrace();
			}	
		}


	private static void borrarEnfermeroDb() {
		try {
			System.out.println("Indique el nombre del enfermero que quiere eliminar:");
			String busq;
			busq = reader.readLine();
			ArrayList<Enfermeros> enf =dbman.buscarEnfermeroNombre(busq);
			mostrarArrayList(enf);

			System.out.println("Seleccione el id del enfermero deseado:\nSi no desea borrar introduzca 0.");
			int id = Integer.parseInt(reader.readLine());
			boolean exito = dbman.deleteEnfermero(id);
			if (exito) {
				System.out.println("Enfermero borrado con éxito");
			} else {
				System.out.println("No hay ningún enfermero con id " + id);
			}
			}
			 catch (IOException e) {
				e.printStackTrace();
			}
	}


	private static void borrarDoctorDb() {
		try {
		System.out.println("Indique el nombre del doctor que quiere eliminar:");
		String busq;
		busq = reader.readLine();
		ArrayList<Doctores> doctor =dbman.buscarDoctorNombre(busq);
		mostrarArrayList(doctor);

		System.out.println("Seleccione el id del doctor deseado:\nSi no desea borrar introduzca 0.");
		int id = Integer.parseInt(reader.readLine());
		boolean exito = dbman.deleteDoctor(id);
		if (exito) {
			System.out.println("Doctor borrado con éxito");
		} else {
			System.out.println("No hay ningún doctor con id " + id);
		}
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void ModificarInformacion() {
		int respuesta=-1;
		do {
			System.out.println("Que tipo de elemento desea modificar:");
			respuesta=mostrarOpciones(ELEMENTO);
			switch(respuesta) {
			case 1:{modificarDoctorDb();
			dbman.actualizarDoctoresDepartamentos();
					break;}
			case 2:{modificarEnfermeroDb();
					break;}
			case 3:{modificarPacienteDb();
					break;}
			}
		}while(respuesta!=0);
		
	}


	private static void modificarPacienteDb() {
		System.out.println("Indique el nombre del paciente del que quiere modificar datos:\nSi quiere verlos todos pulse ENTER.");
		try {
			String busq=reader.readLine();
			ArrayList<Pacientes> paciente = dbman.buscarPacienteNombre(busq);
			mostrarArrayList(paciente);
			boolean exito=false;
			
			System.out.println("\nSeleccione el id del paciente deseado:\nSi no desea modificar introduzca 0.");
			int id = Integer.parseInt(reader.readLine());
			
			if (id!=0) {
			System.out.println("Indique el nombre del paciente:");
			String nombre = reader.readLine();
			
			System.out.println("Indique edad:");
			int edad = Integer.parseInt(reader.readLine());
			
			System.out.println("Indique el sexo: True para Hombre, False para Mujer");
			boolean sexo =Boolean.parseBoolean(reader.readLine());
			
			System.out.println("Indique el motivo de ingreso:");
			String motivo = reader.readLine();
			
			System.out.println("Indique la fecha (DD-MM-AAAA HH:MM):");
			Date fecha = Date.valueOf(LocalDate.parse(reader.readLine(), formatterFecha));
			
	        System.out.println("Indique el id del doctor que lo trata:");
	        int idDoctor= Integer.parseInt(reader.readLine());
	        Doctores doctor = new Doctores();
	        doctor.setId(idDoctor);
	        
	        System.out.println("Indique el id de la habitacion en la que se encuentra:");
	        int idHabitacion= Integer.parseInt(reader.readLine());
	        Habitaciones habitacion = new Habitaciones();
	        habitacion.setId(idHabitacion);
	        exito= dbman.updatePaciente(new Pacientes(id,nombre,edad,sexo,motivo,fecha,doctor,habitacion));
			}
			
			if (exito) {
				System.out.println("Paciente modificado con éxito");
			} else {
				System.out.println("No hay ningún doctor con id " + id);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void modificarEnfermeroDb() {
		System.out.println("Indique el nombre del enfermero del que quiere modificar datos:\nSi quiere verlos todos pulse ENTER.");
		try {
			String busq=reader.readLine();
			ArrayList<Enfermeros> enfermero =dbman.buscarEnfermeroNombre(busq);
			mostrarArrayList(enfermero);
			boolean exito=false;
			System.out.println("Seleccione el id del enfermero/a deseado:\nSi no desea modificar introduzca 0.");
			int id = Integer.parseInt(reader.readLine());
			if (id !=0) {
			System.out.println("Indique el nombre del enfermero/a:");
			String nombre = reader.readLine();
			
			System.out.println("Indique la edad del enfermero/a:");
			int edad = Integer.parseInt(reader.readLine());
			
			Enfermeros e = new Enfermeros(id,nombre,edad);
			exito = dbman.updateEnfermero(e);}
			if (exito) {
				System.out.println("Enfermero modificado con éxito");
			} else {
				System.out.println("No hay ningún doctor con id " + id);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private static void modificarDoctorDb() {
		System.out.println("Indique el nombre del doctor del que quiere modificar datos:\nSi quiere verlos todos pulse ENTER.");
		try {
			String busq=reader.readLine();
			ArrayList<Doctores> doctor =dbman.buscarDoctorNombre(busq);
			mostrarArrayList(doctor);
			boolean exito=false;
			System.out.println("Seleccione el id del doctor deseado:\nSi no desea modificar introduzca 0.");
			int id = Integer.parseInt(reader.readLine());
			
			if (id!=0) {
			System.out.println("Indique el nombre del Doctor:");
			String nombre = reader.readLine();			
			System.out.println("Indique el numero de colegiado (Deberia ser algo como 2828XXXXX):");
			int numColegiado = Integer.parseInt(reader.readLine());			
			System.out.println("Indique edad:");
			int edad = Integer.parseInt(reader.readLine());			
			System.out.println("Indique el sexo: True para Hombre, False para Mujer");
			boolean sexo =Boolean.parseBoolean(reader.readLine());			
			System.out.println("Indique id del departamento");
			int idDepartamento = Integer.parseInt(reader.readLine());
			Departamentos depar = new Departamentos();
			depar.setId(idDepartamento);
			Doctores d = new Doctores(id,nombre,numColegiado, edad, sexo, depar);
			exito = dbman.updateDoctor(d);}
			
			if (exito) {
				System.out.println("Doctor modificado con éxito");
			} else {
				System.out.println("No hay ningún doctor con id " + id);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	private static void mostrarArrayList(ArrayList arrayList) {
		for(Object object: arrayList) {
			System.out.println(object);
		}
	}
	private static void añadirMuchosTrabajadores() {
		try {
			int respuesta=-1;
			do {
			int numero=0;
			System.out.println("Que tipo de elemento desea añadir:");
				respuesta=mostrarOpciones(ELEMENTO);
				switch(respuesta) {
				case 1:{System.out.println("Indique el numero de elementos que quiere añadir:");
					numero=Integer.parseInt(reader.readLine());
					añadirMultipleDoctorDb(numero);
					dbman.actualizarDoctoresDepartamentos();
					break;}
				case 2:{System.out.println("Indique el numero de elementos que quiere añadir:");
					numero=Integer.parseInt(reader.readLine());
					añadirMultipleEnfermeroDb(numero);
					break;}
				case 3:{System.out.println("Indique el numero de elementos que quiere añadir:");
					numero=Integer.parseInt(reader.readLine());
					añadirMultiplePacienteDb(numero);
					break;}
				}
			}while(respuesta!=0);
	    } catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void añadirMultiplePacienteDb(int numero) {
		for (int i=0;i<numero;i++) {
			dbman.addPaciente(GenerarAleatorio.generarPacienteAleatorio());
		}
	}


	private static void añadirMultipleEnfermeroDb(int numero) {
		for (int i=0;i<numero;i++) {
			dbman.addEnfermero(GenerarAleatorio.generarEnfermeroAleatorio());
		}
		
	}


	private static void añadirMultipleDoctorDb(int numero) {
		for (int i =0;i<numero;i++) {
			dbman.addDoctor(GenerarAleatorio.generarDoctorAleatorio());
		}
		
	}


	private static void añadirTrabajador() {
		System.out.println("Que tipo de elemento desea añadir:");
		int respuesta=-1;
		do {
			respuesta=mostrarOpciones(ELEMENTO);
			switch(respuesta) {
			case 1:{añadirDoctorDb();
					dbman.actualizarDoctoresDepartamentos();
					break;}
			case 2:{añadirEnfermeroDb();
					break;}
			case 3:{añadirPacienteDb();
					break;}
			}
		}while(respuesta!=0);
		
	}


	private static void mostrarMenuPaciente() {
		// TODO Auto-generated method stub
		
	}


	private static void mostarMenuEnfermero() {
		// TODO Auto-generated method stub
	}


	private static void mostrarMenuDoctor() {
		System.out.println("Bienvenido al menu de doctores.");
		int respuesta=-1;
		do {
			respuesta=mostrarOpciones(MENUDOCTOR);
			switch(respuesta) {
			case 1:{verPacienteDb();
					break;}
			case 2:{modificarPacienteDb();
					break;}
			case 3:{verEnfermeroDb();
					break;}
			}
		}while(respuesta!=0);
	}





	private static void añadirPacienteDb() {
		try {
			System.out.println("Indique el nombre del paciente:");
			String nombre = reader.readLine();
			System.out.println("Indique edad:");
			int edad = Integer.parseInt(reader.readLine());
			System.out.println("Indique el sexo: True para Hombre, False para Mujer");
			boolean sexo =Boolean.parseBoolean(reader.readLine());
			System.out.println("Indique el motivo de ingreso:");
			String motivo = reader.readLine();//comprobar que no sea NULL
			System.out.println("Indique la fecha (DD-MM-AAAA HH:MM):");
			Date fecha = Date.valueOf(LocalDate.parse(reader.readLine(), formatterFecha));
	        System.out.println("Indique el id del doctor que lo trata:");
	        int idDoctor= Integer.parseInt(reader.readLine());
	        Doctores doctor = new Doctores();
	        doctor.setId(idDoctor);
	        System.out.println("Indique el id de la habitacion en la que se encuentra:");
	        int idHabitacion= Integer.parseInt(reader.readLine());
	        Habitaciones habitacion = new Habitaciones();
	        habitacion.setId(idHabitacion);
	        Pacientes paciente = new Pacientes(nombre,edad,sexo,motivo,fecha,doctor,habitacion);
	        dbman.addPaciente(paciente);
		}catch(IOException e) {
			LOGGER.severe("Error al leer una linea\n" + e.getMessage());
		}
	}


	private static void añadirEnfermeroDb() {
		try {
			System.out.println("Indique el nombre del enfermero:");
			String nombre = reader.readLine();
			System.out.println("Indique edad:");
			int edad = Integer.parseInt(reader.readLine());
			Enfermeros enfermero = new Enfermeros(nombre, edad);
			dbman.addEnfermero(enfermero);
		}catch(IOException e) {
			LOGGER.severe("Error al leer una linea\n" + e.getMessage());
		}
		
	}


	private static void añadirDoctorDb() {
		try {
			System.out.println("Indique el nombre del Doctor:");
			String nombre = reader.readLine();
			System.out.println("Indique el numero de colegiado (Deberia ser algo como 2828XXXXX):");
			int numColegiado = Integer.parseInt(reader.readLine());
			System.out.println("Indique edad:");
			int edad = Integer.parseInt(reader.readLine());
			System.out.println("Indique el sexo: True para Hombre, False para Mujer");
			boolean sexo =Boolean.parseBoolean(reader.readLine());
			System.out.println("Indique id del departamento");
			int idDepartamento = Integer.parseInt(reader.readLine());
			Departamentos depar = new Departamentos();
			depar.setId(idDepartamento);
			Doctores doctor = new Doctores(nombre,numColegiado, edad, sexo, depar);
			dbman.addDoctor(doctor);
			System.out.println("Doctor añadido con exito");
		} catch (IOException e) {
			LOGGER.severe("Error al leer una linea\n" + e.getMessage());
		}
		
	}





	private static int mostrarOpciones(String[] opciones) {
		int respuesta=-1;
		do {
			System.out.println("\nElija una opcion:");
			for(int i = 1; i< opciones.length;i++) {
				System.out.println(i +". " + opciones[i]);
			}
			System.out.println("0. " + opciones[0]);
			try {
				respuesta = Integer.parseInt(reader.readLine());
			} catch (IOException e) {
				LOGGER.severe("Error al leer una l�nea\n" + e.getMessage());
			} catch (NumberFormatException e) {
				
			}
		}while(respuesta<0 ||respuesta>= opciones.length);
		return respuesta;
	}



}
