package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import logging.MyLogger;

import pojos.*;

public class TestXML {
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	//private static String[] empleadosNombres = {"Juan", "Alfonso", "Elisa", "Sergio", "Adolfo", "German", "Carlos", "Alejandro"};
	//private static String[] empleadosApellidos = {"Gonzalez", "Vico", "Garcia", "Palomo", "Coello", "Colmenarejo", "Lopez"};
	private static int contador = 0;
	public static void main(String[] args) throws JAXBException, IOException {
		MyLogger.setupFromFile();
		//Empleado empleado = randomEmpleado();
		//Empresa empresa = generarEmpresa();
		//marshalling(empresa);
		unMarshalling();
	}
	
	/*private static Departamentos generarDepatamentos() {
		Departamentos dep = new Departamentos();
		ArrayList<Doctores> doc = new ArrayList<>();
		for(int d = 0; d < 5; d++) {
			Doctores doctores = new Doctores();
			doc.setNombre("Doc-" + d);
			doctores.add(doc);
			dep.addDepartamento(doc);
		}
		
		ArrayList<Pacientes> pacientes = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			Pacientes p = randomPaciente();
			pacientes.add(e);
			int randDoc = (int)(Math.random() * 5);
			Doctores doc = doctores.get(randDoc);
			dep.addEmpleado(e);
			e.setDepartamento(dep);
		}
		LOGGER.info("Se ha generado un departamento con " + doctores.size() + 
				" doctores y " + pacientes.size() + " pacientes.");
		return dep;
	}*/
	private static void marshalling(Departamentos dep) throws JAXBException {
		// Creamos el JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Departamentos.class);
		// Creamos el JAXBMarshaller
		Marshaller jaxbM = jaxbC.createMarshaller();
		// Formateo bonito
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		//jaxbM.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE paciente SYSTEM \"paciente.dtd\">");
		//jaxbM.setProperty("com.sun.xml.bind.xmlDeclaration", false);
		// Escribiendo en un fichero
		File XMLfile = new File("./xml/Departamento.xml");
		jaxbM.marshal(dep, XMLfile);
		// Escribiendo por pantalla
		jaxbM.marshal(dep, System.out);
	}
	
	private static void unMarshalling() throws JAXBException {
		// Creamos el JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Departamentos.class);
		// Creamos el JAXBMarshaller
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		// Leyendo un fichero
		File XMLfile = new File("./xml/Empresa.xml");
		// Creando el objeto
		Departamentos dep = (Departamentos) jaxbU.unmarshal(XMLfile);
		// Escribiendo por pantalla el objeto
		System.out.println(dep);
	}
	
	/*private static Pacientes randomPaciente() {
		//String nombre = randomString(empleadosNombres);
		//String apellido = randomString(empleadosApellidos);
		int dia = (int) (Math.random() * 30);
		double sueldo = (double) (18000 + Math.round(Math.random() * 5000));
		//return new Empleado(contador++, nombre, apellido, "1990-01-"+dia, sueldo);
	}
	
	private static String randomString(String[] array) {
		int index = (int) (Math.random() * array.length);
		return array[index];
	}*/
}
