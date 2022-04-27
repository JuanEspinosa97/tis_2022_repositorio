package pojos;

public class Enfermeros {
	private int id;
	private String nombre;
	private int edad;
	
	
	public Enfermeros(String nombre, int edad) {
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public Enfermeros() {
		// TODO Auto-generated constructor stub
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
	
}
