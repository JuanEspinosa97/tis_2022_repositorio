package Generar;
import java.sql.Date;
import java.util.Random;

import pojos.Departamentos;
import pojos.Doctores;
import pojos.Enfermeros;
import pojos.Habitaciones;
import pojos.Pacientes;

public class GenerarAleatorio {
	private static final String [] NOMBRES = {"Ignacio","Juan","Eduardo","Alberto","Jaime","Carlos","Jose","Pablo","Francisco",
			"Javier","Victor","Miguel","Alvaro","Luis","Manuel","Marcos","Paula","Laura","Sofia","Andrea","Carla","Maria","Teresa",
			"Isabel","Pilar","Sonia","Monica"};
	private static final String[] MOTIVOS={"Dolor Abdominal","Pierna rota", "Esguince", "Brazo roto", "Problemas estomacales", "Dolor de cabeza","Fiebre",
			"Covid-19","Problema respiratorio","Golpe en la cabeza", "Desmayo","Problema Cardiaco"};
	private static final String[] NOMBRESDEPAR= {"Traumatologia","Oncologia","Neurologia","Cirugia","Ginecologia","Urgencias","Cardiologia"};
    
	public static Doctores  generarDoctorAleatorio() {
    	Doctores doctor = new Doctores();
    	doctor.setNombre(randomStringFromArray(NOMBRES));
    	doctor.setEdad(25+randomInt(40));
    	doctor.setNumColegiado(282800000+randomInt(99999));
    	doctor.setSexo(getRandomBoolean());
    	return doctor;
    }
    public static Pacientes generarPacienteAleatorio() {
    	Pacientes paciente = new Pacientes();
    	paciente.setNombre(randomStringFromArray(NOMBRES));
    	paciente.setEdad(randomInt(99));
    	paciente.setSexo(getRandomBoolean());
    	paciente.setFechaIngreso(getRandomDate());
        paciente.setMotivoIngreso(randomStringFromArray(MOTIVOS));
		return paciente;    	
    }
    public static Departamentos generarDepAleatorio() {
    	Departamentos departamento = new Departamentos();
    	departamento.setNombre(randomStringFromArray(NOMBRESDEPAR));
    	departamento.setNumEmpleados(randomInt(100));
		return departamento;
    }
    public static Enfermeros generarEnfermeroAleatorio() {
    	Enfermeros enfermero = new Enfermeros();
    	enfermero.setNombre(randomStringFromArray(NOMBRES));
    	enfermero.setEdad(20+randomInt(45));
		return enfermero;
    }
    public static Habitaciones generarHabitacionAleatorio() {
    	Habitaciones habitacion = new Habitaciones();
    	habitacion.setNumHabitaciones(100+randomInt(99));
    	habitacion.setEstado(getRandomBoolean());
		return habitacion;
    	
    }

	private static Date getRandomDate() {
        Date date = new Date(0);
        long timeInMilliSeconds = date.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
		return date1;
	}
	public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

	private static String randomStringFromArray(String[] array) {
		return array[randomInt(array.length)];
	}
	
	private static int randomInt(int max) {
		return (int) (Math.random() * max);
	}
//numero colegiado
//numero habitacion

}