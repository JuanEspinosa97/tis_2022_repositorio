package pojos;

public class Enfermeros {
	private int id;
	private String nombre;
	private int edad;
	private String rol;
	
	
	public Enfermeros(String nombre, int edad) {
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public Enfermeros() {
		// TODO Auto-generated constructor stub
	}

	public Enfermeros(int id, String nombre, int edad, String rol) {
		this.id=id;
		this.nombre=nombre;
		this.edad=edad;
		this.rol=rol;
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
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Enfermeros [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", rol=" + rol + "]";
	}

	
	
}
