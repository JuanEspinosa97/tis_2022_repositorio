package db.interfaces;

import java.util.List;

import pojos.Rol;
import pojos.Usuario;

public interface UsuariosInterface{

	void connect();

	void disconnect();

	List<Rol> getRoles();

	void addRol(Rol rol);

	Usuario checkLogin(String email, String pass);

	Rol getRolById(int rolId);

	void addUsuario(Usuario usuario);
	
}