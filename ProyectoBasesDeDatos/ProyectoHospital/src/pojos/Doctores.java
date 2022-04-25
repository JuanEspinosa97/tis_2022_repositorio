package pojos;

public class Doctores{
	private int id;
	private String nombre;
	private int numColegiado;
	private int edad;
	private boolean sexo;
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
	
}