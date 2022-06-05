package pojos;

import java.util.ArrayList;

public class Enfermeros {
	private int id;
	private String nombre;
	private int edad;
	private ArrayList<Pacientes> pacientes= new ArrayList<>();
	
	public Enfermeros(String nombre, int edad) {
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public Enfermeros() {
		// TODO Auto-generated constructor stub
	}

	public Enfermeros(int id, String nombre, int edad) {
		this.id=id;
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public ArrayList<Pacientes> getPacientes(){
		return pacientes;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void addPaciente(Pacientes p) {
		pacientes.add(p);
	}
	@Override
	public String toString() {
		return "Enfermeros [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", Pacientes=" + pacientes.toString()+"]";
	}
	
}
