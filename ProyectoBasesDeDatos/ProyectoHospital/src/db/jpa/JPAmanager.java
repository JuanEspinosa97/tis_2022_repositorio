package db.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

import db.interfaces.UsuariosInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import pojos.Rol;
import pojos.Usuario;

public class JPAmanager implements UsuariosInterface{
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	EntityManagerFactory factory;
	EntityManager em;
	
	public void connect() {
		factory = Persistence.createEntityManagerFactory("hospital-provider");
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		if (getRoles().size() == 0) {
			addRol(new Rol("paciente"));
			addRol(new Rol("doctor"));
			addRol(new Rol("enfermero"));
			addRol(new Rol("jefe"));
		}
	}

	public List<Rol> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM Roles", Rol.class);
		return q.getResultList();
	}


	public void addRol(Rol rol) {
		em.getTransaction().begin();
		em.persist(rol);
		em.getTransaction().commit();
	}
		

	public void disconnect() {
		em.close();
		factory.close();		
	}

	@Override
	public Usuario checkLogin(String email, String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] hash = md.digest();
			Query q = em.createNativeQuery("SELECT * FROM Usuarios WHERE email=? AND password=?", Usuario.class);
			q.setParameter(1, email);
			q.setParameter(2, hash);
			List<Usuario> usuarios = q.getResultList();
			if(usuarios.size() == 0) {
				return null;
			}
			return usuarios.get(0);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.warning("Error en MessageDigest\n" + e);
		}
		return null;
	}

	@Override
	public Rol getRolById(int rolId) {
		Query q = em.createNativeQuery("SELECT * FROM Roles WHERE Id=?", Rol.class);
		q.setParameter(1, rolId);
		List<Rol> roles = q.getResultList();
		if(roles.size() == 0) {
			return null;
		}
		return roles.get(0);
	}

	@Override
	public void addUsuario(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
	}
	}
