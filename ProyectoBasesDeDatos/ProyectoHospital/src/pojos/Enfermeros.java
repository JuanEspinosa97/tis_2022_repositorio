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

	public Enfermeros(int id, String nombre, int edad) {
		this.id=id;
		this.nombre=nombre;
		this.edad=edad;
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

	@Override
	public String toString() {
		return "Enfermeros [id=" + id + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
}
