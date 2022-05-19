package db.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import Generar.GenerarAleatorio;

import db.interfaces.DBmanager;
import pojos.Departamentos;
import pojos.Doctores;
import pojos.Enfermeros;

import pojos.Habitaciones;
import pojos.Pacientes;

public class JDBCmanager implements DBmanager {

	private static Connection c;
	private static Statement stmt;
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String UBICACION_DB = "./BaseDatos/ProyectoHospital.db";
	private static final String ficheroInicializacion = "./BaseDatos/ProyectoHospital.sql";
	private static final String sqlCountElementsFromTable = "SELECT COUNT(*) AS Count FROM ";
	private static final int numeroDepartamentosIniciales = 10;
	private static final int numeroHabitacionesIniciales = 100;
	private static final String sqlAddDoctor = "INSERT INTO Doctores(Nombre,NumColegiado,Edad,Sexo,IdDep) VALUES(?,?,?,?,?);";
	private static final String sqlAddPaciente = "INSERT INTO Pacientes(Nombre,Edad,Sexo,MotivoIngreso,FechaIngreso,IdDoctor,IdHabitacion) VALUES (?,?,?,?,?,?,?);";
	private static final String sqlAddEnfermero = "INSERT INTO Enfermeros(Nombre, Edad) VALUES (?,?);";
	private static final String sqlAddDepartamento = "INSERT INTO Departamentos(Nombre, NumEmpleados) VALUES(?,?);";
	private static final String sqlAddHabitacion = "INSERT INTO Habitaciones (NumHabitacion,Estado) VALUES (?,?);";
	private static final String sqlBuscarDoctoresNombre = "SELECT * FROM Doctores WHERE Nombre LIKE ?;";
	private static final String sqlBuscarEnfermerosNombre = "SELECT * FROM Enfermeros WHERE Nombre LIKE ?;";
	private static final String sqlBuscarPacientesNombre = "SELECT * FROM Pacientes WHERE Nombre LIKE ?;";
	private static final String sqlUpdateDoctor = "UPDATE Doctores SET Nombre =?,NumColegiado=?,Edad=?,Sexo=?,IdDep=? WHERE Id=?;";
	private static final String sqlUpdateEnfermero = "UPDATE Enfermeros SET Nombre=?,Edad=? WHERE Id=?;";
	private static final String sqlUpdatePaciente = "UPDATE Pacientes SET Nombre=?,Edad=?,Sexo=?,MotivoIngreso=?,FechaIngreso=?,IdDoctor=?,IdHabitacion=? WHERE Id=?;";
	private static final DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private static final String sqlDeleteDoctores = "DELETE FROM Doctores WHERE Id =?;";
	private static final String sqlDeleteEnfermeros = "DELETE FROM Enfermeros WHERE Id =?;";
	private static final String sqlDeletePacientes = "DELETE FROM Pacientes WHERE Id =?;";

	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + UBICACION_DB);
			stmt = c.createStatement();
			createTablas();
			inicializarHabitaciones();
			inicializarDepartamentos();
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.severe("Error al inicializar la base de datos\n" + e.toString());
		}
	}

	private void inicializarDepartamentos() {
		if (countElementsFromTable("Departamentos") == 0) {
			for (int i = 0; i < numeroDepartamentosIniciales; i++) {
				addDepartamento(GenerarAleatorio.generarDepAleatorio(i));
			}
		}
	}

	private void inicializarHabitaciones() {
		if (countElementsFromTable("Habitaciones") == 0) {
			for (int i = 0; i < numeroHabitacionesIniciales; i++) {
				addHabitacion(GenerarAleatorio.generarHabitacionAleatorio(i));
			}
		}
	}

	private void addHabitacion(Habitaciones habitacion) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddHabitacion)) {
			prep.setInt(1, habitacion.getNumHabitaciones());
			prep.setBoolean(2, habitacion.isEstado());
			prep.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning("Error al aniadir habitacion.\n" + e.toString());
		}

	}

	public void addPaciente(Pacientes paciente) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddPaciente)) {
			prep.setString(1, paciente.getNombre());
			prep.setInt(2, paciente.getEdad());
			prep.setBoolean(3, paciente.isSexo());
			prep.setString(4, paciente.getMotivoIngreso());
			prep.setDate(5, paciente.getFechaIngreso());
			prep.setInt(6, paciente.getDoctor().getId());
			prep.setInt(7, paciente.getHabitacion().getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning("Error al aniadir paciente\n" + e.toString());
		}
	}

	public boolean updatePaciente(Pacientes p) {
		boolean exito = false;
		try (PreparedStatement prep = c.prepareStatement(sqlUpdatePaciente)) {
			prep.setString(1, p.getNombre());
			prep.setInt(2, p.getEdad());
			prep.setBoolean(3, p.isSexo());
			prep.setString(4, p.getMotivoIngreso());
			prep.setDate(5, p.getFechaIngreso());
			prep.setInt(6, p.getDoctor().getId());
			prep.setInt(7, p.getHabitacion().getId());
			prep.setInt(8, p.getId());
			int resultado = prep.executeUpdate();
			if (resultado == 1) {
				exito = true;
			}
		} catch (SQLException e) {
			LOGGER.warning("Error al aniadir paciente\n" + e.toString());
		}
		return exito;
	}

	public boolean deletePaciente(int id) {
		boolean exito = false;
		try (PreparedStatement prep = c.prepareStatement(sqlDeletePacientes)) {
			prep.setInt(1, id);
			int resultado = prep.executeUpdate();
			if (resultado == 1) {
				exito = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exito;
	}

	public void addEnfermero(Enfermeros enfermero) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddEnfermero)) {
			prep.setString(1, enfermero.getNombre());
			prep.setInt(2, enfermero.getEdad());
			prep.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning("Error al aniadir enfermero.\n" + e.toString());
		}
	}

	public boolean updateEnfermero(Enfermeros e) {
		boolean exito = false;
		try (PreparedStatement prep = c.prepareStatement(sqlUpdateEnfermero)) {
			prep.setString(1, e.getNombre());
			prep.setInt(2, e.getEdad());
			prep.setInt(3, e.getId());
			int resultado = prep.executeUpdate();
			if (resultado == 1) {
				exito = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return exito;
	}

	public boolean deleteEnfermero(int id) {
		boolean exito = false;
		try (PreparedStatement prep = c.prepareStatement(sqlDeleteEnfermeros)) {
			prep.setInt(1, id);
			int resultado = prep.executeUpdate();
			if (resultado == 1) {
				exito = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exito;
	}

	private void addDepartamento(Departamentos departamento) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddDepartamento)) {
			prep.setString(1, departamento.getNombre());
			prep.setInt(2, departamento.getNumEmpleados());
			prep.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning("Error al aniadir departamento.\n" + e.toString());
		}
	}

	public void addDoctor(Doctores doctor) {
		try (PreparedStatement prep = c.prepareStatement(sqlAddDoctor)) {
			prep.setString(1, doctor.getNombre());
			prep.setInt(2, doctor.getNumColegiado());
			prep.setInt(3, doctor.getEdad());
			prep.setBoolean(4, doctor.isSexo());
			prep.setInt(5, doctor.getDepartamento().getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning("Error al aniadir doctor\n" + e.toString());
		}
	}

	@Override
	public boolean updateDoctor(Doctores d) {
		boolean exito = false;
		try (PreparedStatement prep = c.prepareStatement(sqlUpdateDoctor)) {
			prep.setString(1, d.getNombre());
			prep.setInt(2, d.getNumColegiado());
			prep.setInt(3, d.getEdad());
			prep.setBoolean(4, d.isSexo());
			prep.setInt(5, d.getDepartamento().getId());
			prep.setInt(6, d.getId());
			int resultado = prep.executeUpdate();
			if (resultado == 1) {
				exito = true;
			}
		} catch (SQLException e) {
			LOGGER.warning("Error al aniadir doctor\n" + e.toString());
		}
		return exito;
	}

	public boolean deleteDoctor(int id) {
		boolean exito = false;
		try (PreparedStatement prep = c.prepareStatement(sqlDeleteDoctores)) {
			prep.setInt(1, id);
			int resultado = prep.executeUpdate();
			if (resultado == 1) {
				exito = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exito;
	}

	public ArrayList<Doctores> buscarDoctorNombre(String dato) {
		ArrayList<Doctores> doctor = new ArrayList<Doctores>();
		try (PreparedStatement prep = c.prepareStatement(sqlBuscarDoctoresNombre)) {
			prep.setString(1, "%" + dato + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id");
				String Nombre = rs.getString("Nombre");
				int NumColegiado = rs.getInt("NumColegiado");
				int edad = rs.getInt("Edad");
				boolean sexo = rs.getBoolean("Sexo");
				int idDepartamento = rs.getInt("IdDep");
				Departamentos depar = new Departamentos();
				depar.setId(idDepartamento);
				doctor.add(new Doctores(id, Nombre, NumColegiado, edad, sexo, depar));
			}
			rs.close();
		} catch (SQLException e) {
			LOGGER.warning("Error al buscar doctor por nombre\n" + e.toString());
			e.printStackTrace();
		}
		return doctor;

	}

	public ArrayList<Enfermeros> buscarEnfermeroNombre(String enfermero) {
		ArrayList<Enfermeros> enf = new ArrayList<Enfermeros>();
		try (PreparedStatement prep = c.prepareStatement(sqlBuscarEnfermerosNombre)) {
			prep.setString(1, "%" + enfermero + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id");
				String nombre = rs.getString("Nombre");
				int edad = rs.getInt("Edad");
				enf.add(new Enfermeros(id, nombre, edad));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enf;
	}

	public ArrayList<Pacientes> buscarPacienteNombre(String paciente) {
		ArrayList<Pacientes> pac = new ArrayList<Pacientes>();
		try (PreparedStatement prep = c.prepareStatement(sqlBuscarPacientesNombre)) {
			prep.setString(1, "%" + paciente + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id");
				String nombre = rs.getString("Nombre");
				int edad = rs.getInt("Edad");
				boolean sexo = rs.getBoolean("Sexo");
				String motivo = rs.getString("MotivoIngreso");
				long fecha1 = rs.getLong("FechaIngreso");
				Date fecha = new Date(fecha1);
				int idDoc = rs.getInt("IdDoctor");
				Doctores doc = new Doctores();
				doc.setId(idDoc);
				int idHab = rs.getInt("IdHabitacion");
				Habitaciones hab = new Habitaciones();
				hab.setId(idHab);
				pac.add(new Pacientes(id, nombre, edad, sexo, motivo, fecha, doc, hab));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pac;
	}

	private int countElementsFromTable(String tabla) {
		int contador = 0;
		try (ResultSet rs = stmt.executeQuery(sqlCountElementsFromTable + tabla + ";");) {
			contador = rs.getInt("Count");
			rs.close();
		} catch (SQLException e) {
			LOGGER.warning("Error al buscar elementos de la tabla " + tabla + "\n" + e.toString());
		}
		return contador;
	}

	private void createTablas() throws SQLException {
		File file = new File(ficheroInicializacion);
		try (Scanner scanner = new Scanner(file)) {
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