package pojos;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name= "Doctor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"nombre", "numColegiado", "edad", "sexo"})
public class Doctores implements Serializable{
	private static final long serialVersionUID = 6633526622689289021L;
	
	@XmlAttribute
	private int id;
	@XmlElement
	private String nombre;
	@XmlElement
	private int numColegiado;
	@XmlElement
	private int edad;
	@XmlElement
	private boolean sexo;
	
	@XmlTransient
	private Departamentos departamento;
	
	public Doctores(String nombre, int numColegiado, int edad, boolean sexo, Departamentos departamento) {
		super();
		this.nombre=nombre;
		this.numColegiado=numColegiado;
		this.edad=edad;
		this.sexo=sexo;
		this.departamento=departamento;
	}
	public Doctores() {
		super();
	}
	public Doctores(int id, String nombre, int numColegiado, int edad, boolean sexo, Departamentos departamento) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.numColegiado=numColegiado;
		this.edad=edad;
		this.sexo=sexo;
		this.departamento=departamento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumColegiado() {
		return numColegiado;
	}
	public void setNumColegiado(int numColegiado) {
		this.numColegiado = numColegiado;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public boolean isSexo() {
		return sexo;
	}
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctores other = (Doctores) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Doctores [id=" + id + ", nombre=" + nombre + ", numColegiado=" + numColegiado + ", edad=" + edad
				+ ", sexo=" + sexo + ", departamento=" + departamento.getId() + "]";
	}
	
}