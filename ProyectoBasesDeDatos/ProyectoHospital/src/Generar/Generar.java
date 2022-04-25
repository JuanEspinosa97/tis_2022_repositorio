package Generar;
import java.sql.Date;
import java.util.Random;

import pojos.Doctores;
import pojos.Pacientes;

public class Generar {
	private static final String [] NOMBRES = {"Ignacio","Juan","Eduardo","Alberto","Jaime","Carlos","Jose","Pablo","Francisco",
			"Javier","Victor","Miguel","Alvaro","Luis","Manuel","Marcos","Paula","Laura","Sofia","Andrea","Carla","Maria","Teresa",
			"Isabel","Pilar","Sonia","Monica"};
	private static final String[] MOTIVOS={};
	
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
    	paciente.setSexo(getRandomBoolean());
    	paciente.setFechaIngreso(getRandomDate());
		return null;
    	
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
