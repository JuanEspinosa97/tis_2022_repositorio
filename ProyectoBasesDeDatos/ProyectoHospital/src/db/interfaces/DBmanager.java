package db.interfaces;

import java.util.ArrayList;

import pojos.Departamentos;
import pojos.Doctores;
import pojos.Enfermeros;
import pojos.Pacientes;

//llamadas a las funciones que tienen que ver con la DB

public interface DBmanager{

	void connect();
	void addEnfermero(Enfermeros en);
	void addPaciente(Pacientes p);
	void addDoctor(Doctores d);
	void addDoctorConID (Doctores doctor);
	ArrayList<Doctores> buscarDoctorNombre(String s);
	ArrayList<Doctores> buscarDoctores();
	ArrayList<Doctores> buscarDoctoresDepartamento(int i);
	ArrayList<Pacientes> buscarPacienteNombre(String paciente);
	ArrayList<Enfermeros> buscarEnfermeroNombre(String enfermero);
	ArrayList<Departamentos> buscarDepartamentos();
	boolean updateDoctor(Doctores d);
	boolean updateEnfermero(Enfermeros e);
	boolean updatePaciente(Pacientes p);	
	boolean deleteDoctor(int id);
	boolean deleteEnfermero(int id);
	boolean deletePaciente(int id);
	void disconnect();
	Enfermeros buscarEnfId(int id);
	Pacientes buscarPacId(int id2);
	void addEnfPac(Enfermeros enf);
	void actualizarDoctoresDepartamentos();
	
}