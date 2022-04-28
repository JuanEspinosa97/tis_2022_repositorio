package ui;

import db.interfaces.DBmanager;
import java.io.BufferedReader;
import java.io.IOException;

import db.jdbc.JDBCmanager;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Menu{
	private static DBmanager dbman = new JDBCmanager();
	private static final String[] Identificacion = {"Salir", "Doctor", "Enfermero", "Paciente"};
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) {
		dbman.connect();
		System.out.println("Bienvenido al men� de nuestro hospital");
		int respuesta=-1;
		do {
			respuesta=mostrarOpciones(Identificacion);
			switch(respuesta) {
				case( 1) -> mostrarMenuDoctor();
				case 2 -> mostarMenuEnfermero();
				case 3
			}
		}
		while (respuesta!=0);
		dbman.disconnect();
		
	}


	private static Object mostarMenuEnfermero() {
		// TODO Auto-generated method stub
		return null;
	}


	private static Object mostrarMenuDoctor() {
		// TODO Auto-generated method stub
		return null;
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
