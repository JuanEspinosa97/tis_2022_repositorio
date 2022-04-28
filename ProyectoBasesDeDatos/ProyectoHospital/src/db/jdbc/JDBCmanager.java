package db.jdbc;

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
import pojos.Habitaciones;
//implementa los cuerpos de las funciones de DBmanager.
import pojos.Pacientes;

public class JDBCmanager implements DBmanager{
	
	private static Connection c;
	private static Statement stmt;
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String UBICACION_DB = "./BaseDatos/ProyectoHospital.db";
//	private static final String ficheroInicializacion = "./BaseDatos/ProyectoHospital.sql";
//	private static final String sqlCountElementsFromTable = "SELECT COUNT(*) AS Count FROM ";
//	private static final String sqlAddDoctor = "INSERT INTO Doctores(Nombre,NumColegiado,Edad,Sexo,IdDep) VALUES(?,?,?,?,?);";
//	private static final String sqlAddPaciente = "INSERT INTO Pacientes(Nombre,Edad,Sexo,MotivoIngreso,FechaIngreso,IdDoctor,IdHabitacion VALUES (?,?,?,?,?,?,?);";
//	private static final String sqlAddEnfermero = "INSET INTO Enfermeros(Nombre, Edad) VALUES (?,?);";
//	private static final String sqlAddDepartamento = "INSERT INTO Departamentos(Nombre, NumEmpleados) VALUES(?,?);";
//	private static final String sqlAddHabitacion = "INSERT INTO Habitaciones (NumHabitacion,Estado) VALUES (?,?);";

	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c= DriverManager.getConnection("jdbc:sqlite:" + UBICACION_DB);
			stmt = c.createStatement();

		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.severe("Error al inicializar la base de datos\n" + e.toString());
		}
	}

		
		// TODO Auto-generated method stub
		
	
/*	private void addHabitacion(Habitaciones habitacion) {
		try(PreparedStatement prep = c.prepareStatement(sqlAddHabitacion)){
			prep.setInt(1, habitacion.getNumHabitaciones());
			prep.setBoolean(2, habitacion.isEstado());
		} catch (SQLException e) {
			LOGGER.warning("Error al a�adir habitacion.\n"+ e.toString());
		}
		
	}
	private void addPaciente(Pacientes paciente) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddPaciente)){
			prep.setString(1,paciente.getNombre());
			prep.setInt(2,paciente.getEdad());
			prep.setBoolean(3, paciente.isSexo());
			prep.setString(4, paciente.getMotivoIngreso());
			prep.setDate(5, paciente.getFechaIngreso());
		    prep.setInt(6,1);
		    prep.setInt(7,1);
		}catch (SQLException e) {
			LOGGER.warning("Error al a�adir paciente\n"+ e.toString());
		}		
	}
	private void addEnfermero(Enfermeros enfermero) {
		try(PreparedStatement prep = c.prepareStatement(sqlAddEnfermero)){
			prep.setString(1, enfermero.getNombre());
			prep.setInt(2, enfermero.getEdad());			
		} catch (SQLException e) {
			LOGGER.warning("Error al a�adir enfermero.\n"+ e.toString());
		}
		
	}
	private void addDepartamento(Departamentos departamento) {
		try(PreparedStatement prep = c.prepareStatement(sqlAddDepartamento)){
			prep.setString(1, departamento.getNombre());
			prep.setInt(2, departamento.getNumEmpleados());			
		} catch (SQLException e) {
			LOGGER.warning("Error al a�adir departamento.\n"+ e.toString());
		}	
	}
	private void addDoctor(Doctores doctor) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddDoctor)){
			prep.setString(1,doctor.getNombre());
			prep.setInt(2,doctor.getNumColegiado());
			prep.setInt(3, doctor.getEdad());
			prep.setBoolean(4, doctor.isSexo());
		    prep.setInt(5,1);
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
		
	}*/
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