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
		
	}
	public static void leerhospitalxml() throws JAXBException, IOException {
		unMarshalling();
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
		JAXBContext jaxbC = JAXBContext.newInstance(Hospital.class);
		Marshaller jaxbM = jaxbC.createMarshaller();
		jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		File XMLfile = new File("./XML/Hospital.xml");
		jaxbM.marshal(hospital, XMLfile);
		jaxbM.marshal(hospital, System.out);
	}
	
	private static void unMarshalling() throws JAXBException {
		JAXBContext jaxbC = JAXBContext.newInstance(Hospital.class);
		Unmarshaller jaxbU = jaxbC.createUnmarshaller();
		File XMLfile = new File("./xml/Hospital.xml");
		Hospital hospital = (Hospital) jaxbU.unmarshal(XMLfile);
		ArrayList<Departamentos>departamentos=hospital.getDepartamentos();
		for (int i=0;i<departamentos.size();i++) {
			ArrayList<Doctores>doctores=departamentos.get(i).getDoctores();
			for (int j=0;j<doctores.size();j++) {
				doctores.get(j).setDepartamento(departamentos.get(i));
				dbman.addDoctorConID(doctores.get(j));
			}
			dbman.actualizarDoctoresDepartamentos();
		}
		System.out.println(hospital);
	}
	

	

}