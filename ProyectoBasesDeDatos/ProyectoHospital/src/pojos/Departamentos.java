package pojos;

public class Departamentos {
	private int id;
	private String nombre;
	private int numEmpleados;
	
	public Departamentos(String nombre, int numEmpleados) {
		this.nombre = nombre;
		this.numEmpleados = numEmpleados;
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
}