package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Generar.GenerarAleatorio;
import db.interfaces.DBmanager;
import db.jdbc.JDBCmanager;
import logging.MyLogger;

import pojos.*;

public class testhospital {
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static DBmanager dbman = new JDBCmanager();
	public static void hospitalxml() throws JAXBException, IOException {

		Hospital Hospital = generarHospital();
		marshalling(Hospital);
		//unMarshalling();
	}
	
	private static Hospital generarHospital() {
		Hospital hospital = new Hospital();
		ArrayList<Departamentos> departamentos = dbman.buscarDepartamentos();
		ArrayList<Doctores> doc = dbman.buscarDoctores();
		for (int i=0;i<departamentos.size();i++) {
			ArrayList<Doctores> d= dbman.buscarDoctoresDepartamento(i+1);
			for (int j=0;j<d.size();j++) {
				departamentos.get(i).addDoctores(d.get(j));
			}
			hospital.addDepartamento(departamentos.get(i));
		}
			
	
		LOGGER.info("Se ha generado una Hospital con " + departamentos.size() + 
				" departamentos y " + doc.size() + " Doctoress.");
		return hospital;
	}
	private static void marshalling(Hospital hospital) throws JAXBException {
		// Creamos el JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Hospital.class);
		// Creamos el JAXBMarshaller
		Marshaller jaxbM = jaxbC.createMarshaller();
		// Formateo bonito
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		//jaxbM.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE Hospital SYSTEM \"Hospital.dtd\">");
		//jaxbM.setProperty("com.sun.xml.bind.xmlDeclaration", false);
		// Escribiendo en un fichero
		File XMLfile = new File("./XML/Hospital.xml");
		jaxbM.marshal(hospital, XMLfile);
		// Escribiendo por pantalla
		jaxbM.marshal(hospital, System.out);
	}
	
	private static void unMarshalling() throws JAXBException {
		// Creamos el JAXBContext
		JAXBContext jaxbC = JAXBContext.newInstance(Hospital.class);
		// Creamos el JAXBMarshaller
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		// Leyendo un fichero
		File XMLfile = new File("./xml/Hospital.xml");
		// Creando el objeto
		Hospital hospital = (Hospital) jaxbU.unmarshal(XMLfile);
		// Escribiendo por pantalla el objeto
		System.out.println(hospital);
	}
	

	

}