package pojos;

public class Doctores{
	private int id;
	private String nombre;
	private String numColegiado;
	private int edad;
	private boolean sexo;
	private Departamentos departamento;
	
	public Doctores(String nombre, String numColegiado, int edad, boolean sexo, Departamentos departamento) {
		super();
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
	public String getNumColegiado() {
		return numColegiado;
	}
	public void setNumColegiado(String numColegiado) {
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