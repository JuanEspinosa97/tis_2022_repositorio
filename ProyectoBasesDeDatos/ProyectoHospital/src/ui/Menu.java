package ui;

import db.interfaces.DBmanager;
import db.jdbc.JDBCmanager;

public class Menu{
	private static DBmanager dbman = new JDBCmanager();
	private static final String[] Identificacion = {"Salir", "Doctor", "Enfermero", "Paciente"};


	public static void main(String[] args) {
		dbman.connect();
		System.out.println("Bienvenido al men� de nuestro hospital");
		int respuesta=-1;
		do {
			respuesta=mostrarOpciones(Identificacion);
		}
		while (respuesta!=0);
		dbman.disconnect();
		
	}


	private static int mostrarOpciones(String[] opciones) {
		int respuesta=-1;
		do {
			System.out.println("\nElija una opcion:");
			for(int i = 1; i< opciones.length;i++) {
				System.out.println(i +". " + opciones[i]);
			}
		}while(respuesta!=-1);
		
		return 0;
	}



}
