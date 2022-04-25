package Generar;

import java.util.Random;

import pojos.Doctores;

public class Generar {
	private static final String [] NOMBRES = {"Ignacio","Juan","Eduardo","Alberto","Jaime","Carlos","Jose","Pablo","Francisco",
			"Javier","Victor","Miguel","Alvaro","Luis","Manuel","Marcos","Paula","Laura","Sofia","Andrea","Carla","Maria","Teresa",
			"Isabel","Pilar","Sonia","Monica"};
	
    public static Doctores  generarDoctorAleatorio() {
    	Doctores doctor = new Doctores();
    	doctor.setNombre(randomStringFromArray(NOMBRES));
    	doctor.setEdad(25+randomInt(40));
    	doctor.setNumColegiado(282800000+randomInt(99999));
    	doctor.setSexo(getRandomBoolean());
    	return doctor;
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
