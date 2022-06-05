package pojos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "Departamento")
@XmlAccessorType(XmlAccessType.FIELD)
public class Departamentos implements Serializable{
	private static final long serialVersionUID = -6905878871613131766L;
	
	@XmlElement
	private int id;
	@XmlElement
	private String nombre;
	@XmlElement
	private int numEmpleados;
	
	@XmlElement(name = "Doctor")
	@XmlElementWrapper(name = "Doctores")
	private ArrayList <Doctores> doctores=new ArrayList<>();
	@XmlTransient
	private Hospital hospital;
	
	
	public Departamentos(String nombre, int numEmpleados) {
		this.nombre = nombre;
		this.numEmpleados = numEmpleados;
	}
	public Departamentos(int id, String nombre, int numEmpleados) {
		this.nombre=nombre;
		this.id=id;
		this.numEmpleados=numEmpleados;
	}
	
	public Departamentos() {
		super();
	}


	public int getNumEmpleados() {
		return numEmpleados;
	}
	public void setNumEmpleados(int numEmpleados) {
		this.numEmpleados = numEmpleados;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamentos other = (Departamentos) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public void addDoctores(Doctores e) {
			doctores.add(e);
	}
	
 

	@Override
	public String toString() {
		return "Departamentos [id=" + id + ", nombre=" + nombre + ", numEmpleados=" + numEmpleados + "]";
	}
}
