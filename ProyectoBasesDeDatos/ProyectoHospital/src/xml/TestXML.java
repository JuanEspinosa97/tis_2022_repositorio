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
	private static String[] empleadosNombres = {"Juan", "Alfonso", "Elisa", "Sergio", "Adolfo", "Germ�n", "Carlos", "Alejandro"};
	private static String[] empleadosApellidos = {"Gonzalez", "Vico", "Garc�a", "Palomo", "Coello", "Colmenarejo", "Lopez"};
	private static int contador = 0;
	public static void main(String[] args) throws JAXBException, IOException {
		MyLogger.setupFromFile();
		//Empleado empleado = randomEmpleado();
		//Empresa empresa = generarEmpresa();
		//marshalling(empresa);
		unMarshalling();
	}
	
	private static Empresa generarEmpresa() {
		Empresa emp = new Empresa();
		ArrayList<Departamento> departamentos = new ArrayList<>();
		for(int d = 0; d < 5; d++) {
			Departamento dep = new Departamento();
			dep.setNombre("Dep-" + d);
			departamentos.add(dep);
			emp.addDepartamento(dep);
		}
		
		ArrayList<Empleado> empleados = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			Empleado e = randomEmpleado();
			empleados.add(e);
			int randDep = (int)(Math.random() * 5);
			Departamento dep = departamentos.get(randDep);
			dep.addEmpleado(e);
			e.setDepartamento(dep);
		}
		LOGGER.info("Se ha generado una empresa con " + departamentos.size() + 
				" departamentos y " + empleados.size() + " empleados.");
		return emp;
	}
	private static void marshalling(Empresa emp) throws JAXBException {
		// Creamos el JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Empresa.class);
		// Creamos el JAXBMarshaller
		Marshaller jaxbM = jaxbC.createMarshaller();
		// Formateo bonito
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		//jaxbM.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE empleado SYSTEM \"empleado.dtd\">");
		//jaxbM.setProperty("com.sun.xml.bind.xmlDeclaration", false);
		// Escribiendo en un fichero
		File XMLfile = new File("./xml/Empresa.xml");
		jaxbM.marshal(emp, XMLfile);
		// Escribiendo por pantalla
		jaxbM.marshal(emp, System.out);
	}
	
	private static void unMarshalling() throws JAXBException {
		// Creamos el JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Empresa.class);
		// Creamos el JAXBMarshaller
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		// Leyendo un fichero
		File XMLfile = new File("./xml/Empresa.xml");
		// Creando el objeto
		Empresa emp = (Empresa) jaxbU.unmarshal(XMLfile);
		// Escribiendo por pantalla el objeto
		System.out.println(emp);
	}
	
	private static Empleado randomEmpleado() {
		String nombre = randomString(empleadosNombres);
		String apellido = randomString(empleadosApellidos);
		int dia = (int) (Math.random() * 30);
		double sueldo = (double) (18000 + Math.round(Math.random() * 5000));
		return new Empleado(contador++, nombre, apellido, "1990-01-"+dia, sueldo);
	}
	
	private static String randomString(String[] array) {
		int index = (int) (Math.random() * array.length);
		return array[index];
	}
}
