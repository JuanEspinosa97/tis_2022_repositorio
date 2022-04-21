package db.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

import db.interfaces.DBmanager;

//implementa los cuerpos de las funciones de DBmanager.

public class JDBCmanager implements DBmanager{
	
	private static Connection c;
	private static Statement stmt;
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String UBICACION_DB = "./BaseDatos/ProyectoHospital.db";
	private static final String ficheroInicializacion = "./BaseDatos/ProyectoHospital.sql";

	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c= DriverManager.getConnection("jdbc:sqlite:" + UBICACION_DB);
			stmt = c.createStatement();
			createTablas();
			inicializarTablas();
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.severe("Error al inicializar la base de datos\n" + e.toString());
		}
	}
	private void inicializarTablas() {
		if(countElementsFromTable("Doctores")==0) {
			addDoctor();
		}
		// TODO Auto-generated method stub
		
	}
	private void createTablas() throws SQLException {
		File file = new File(ficheroInicializacion);
	    try (Scanner scanner = new Scanner(file)){
	    	String sqlInicializacion = "";
	    	while (scanner.hasNextLine()) {
	    		sqlInicializacion += scanner.nextLine();
	    	}
	    	stmt.executeUpdate(sqlInicializacion);
		} catch (FileNotFoundException e) {
			LOGGER.severe("Error al leer fichero sql\n" + e.toString());
		}
		
	}
	@Override
	public void disconnect() {
		try {
			stmt.close();
			c.close();
			LOGGER.info("Se ha desconectado la base de datos con exito.");
		} catch (SQLException e) {
			LOGGER.warning("Error al desconectar la base de datos.");
		}
		
		
	}
}