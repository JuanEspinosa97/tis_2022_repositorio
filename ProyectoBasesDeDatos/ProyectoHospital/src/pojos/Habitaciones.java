package pojos;

public class Habitaciones {
	private int id;
	private int numHabitaciones;
	private boolean estado;
	
	public Habitaciones(int numHabitaciones, boolean estado) {
		this.numHabitaciones=numHabitaciones;
		this.estado=estado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumHabitaciones() {
		return numHabitaciones;
	}
	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

}
