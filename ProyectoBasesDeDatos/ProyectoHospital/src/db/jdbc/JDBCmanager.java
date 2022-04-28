package db.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

import Generar.GenerarAleatorio;

import db.interfaces.DBmanager;
import pojos.Departamentos;
import pojos.Doctores;
import pojos.Enfermeros;
//implementa los cuerpos de las funciones de DBmanager.
import pojos.Pacientes;

public class JDBCmanager implements DBmanager{
	
	private static Connection c;
	private static Statement stmt;
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String UBICACION_DB = "./BaseDatos/ProyectoHospital.db";
	private static final String ficheroInicializacion = "./BaseDatos/ProyectoHospital.sql";
	private static final String sqlCountElementsFromTable = "SELECT COUNT(*) AS Count FROM ";
	private static final String sqlAddDoctor = "INSERT INTO Doctores(Nombre,NumColegiado,Edad,Sexo,IdDep) VALUES(?,?,?,?,?);";
	private static final String sqlAddPaciente = "INSERT INTO Pacientes(Nombre,Edad,Sexo,MotivoIngreso,FechaIngreso,IdDoctor,IdHabitacion VALUES (?,?,?,?,?,?,?);";

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
			addDoctor(GenerarAleatorio.generarDoctorAleatorio());
		}
	   if(countElementsFromTable("Departamentos")==0) {
			addDepartamento(GenerarAleatorio.generarDepAleatorio());
		}
	   if(countElementsFromTable("Doctores")==0) {
			addEnfermero(GenerarAleatorio.generarEnfermeroAleatorio());
		}
	   if(countElementsFromTable("Doctores")==0) {
			addPaciente(GenerarAleatorio.generarPacienteAleatorio());
		}
		// TODO Auto-generated method stub
		
	}
	private void addPaciente(Pacientes paciente) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddPaciente)){
			prep.setString(1,paciente.getNombre());
			prep.setInt(2,paciente.getEdad());
			prep.setBoolean(3, paciente.isSexo());
			prep.setString(4, paciente.getMotivoIngreso());
			prep.setDate(5, paciente.getFechaIngreso());
		//Faltaria incluir doctores y habitacion.
		}catch (SQLException e) {
			LOGGER.warning("Error al a�adir doctor\n"+ e.toString());
		}		
	}
	private void addEnfermero(Enfermeros enfermero) {
		// TODO Auto-generated method stub
		
	}
	private void addDepartamento(Departamentos departamento) {
		// TODO Auto-generated method stub
		
	}
	private void addDoctor(Doctores doctor) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddDoctor)){
			prep.setString(1,doctor.getNombre());
			prep.setInt(2,doctor.getNumColegiado());
			prep.setInt(3, doctor.getEdad());
			prep.setBoolean(4, doctor.isSexo());
		//Faltaria incluir departamentos.
		}catch (SQLException e) {
			LOGGER.warning("Error al a�adir doctor\n"+ e.toString());
		}
		
	}
	private int countElementsFromTable(String tabla) {
		int contador = 0;
		try(ResultSet rs= stmt.executeQuery(sqlCountElementsFromTable + tabla+ ";");){
			contador=rs.getInt("Count");
			rs.close();
		}catch(SQLException e) {
			LOGGER.warning("Error al buscar elementos de la tabla " + tabla + "\n" + e.toString());	
		}
		return contador;
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