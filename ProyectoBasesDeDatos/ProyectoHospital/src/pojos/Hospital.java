package pojos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hospital")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hospital implements Serializable{

	private static final long serialVersionUID = 8773040371752431375L;

	@XmlElement(name = "departamento")
	@XmlElementWrapper(name = "departamentos")
	private ArrayList<Departamentos> departamentos;
	
	public Hospital() {
		super();
		departamentos = new ArrayList<>();
	}

	public ArrayList<Departamentos> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(ArrayList<Departamentos> departamentos) {
		this.departamentos = departamentos;
	}
	
	public void addDepartamento(Departamentos e) {
		if(!departamentos.contains(e))
			departamentos.add(e);
	}
	
	public void removeDepartamento(Departamentos e) {
		departamentos.remove(e);
	}

	@Override
	public String toString() {
		return "Empresa [departamentos=" + departamentos + "]";
	}
}