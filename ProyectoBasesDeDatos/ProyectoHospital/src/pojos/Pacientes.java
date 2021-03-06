package pojos;

import java.sql.Date;

public class Pacientes{
	private int id;
	private String nombre;
	private int edad;
	private boolean sexo;
	private String motivoIngreso;
	private Date fechaIngreso;
	private Doctores doctor;
	private Habitaciones habitacion;
	
	
	public Pacientes(String nombre,int edad ,boolean sexo, String motivoIngreso, Date fechaIngreso, Doctores doctor, Habitaciones habitacion) {
		super();
		this.nombre = nombre;
		this.edad= edad;
		this.sexo = sexo;
		this.fechaIngreso = fechaIngreso;
		this.motivoIngreso = motivoIngreso;
		this.setDoctor(doctor);
		this.setHabitacion(habitacion);
	}
	
	public Pacientes() {
		// TODO Auto-generated constructor stub
	}

	public Pacientes(int id, String nombre, int edad, boolean sexo, String motivo, Date fecha, Doctores doc,
			Habitaciones hab) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.edad= edad;
		this.sexo = sexo;
		this.fechaIngreso = fecha;
		this.motivoIngreso = motivo;
		this.doctor=doc;
		this.habitacion=hab;
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
	public boolean isSexo() {
		return sexo;
	}
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
	public String getMotivoIngreso() {
		return motivoIngreso;
	}
	public void setMotivoIngreso(String motivoIngreso) {
		this.motivoIngreso = motivoIngreso;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Doctores getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctores doctor) {
		this.doctor = doctor;
	}

	public Habitaciones getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitaciones habitacion) {
		this.habitacion = habitacion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return "Pacientes [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", sexo=" + sexo + ", motivoIngreso="
				+ motivoIngreso + ", fechaIngreso=" + fechaIngreso + ", doctor=" + doctor.getId() + ", habitacion=" + habitacion.getId()
				+"]";
	}

}