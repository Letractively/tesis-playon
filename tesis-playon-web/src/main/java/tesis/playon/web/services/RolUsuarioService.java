package tesis.playon.web.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.RolUsuario;

@Service("rolUsuarioService")
@Transactional
public class RolUsuarioService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<RolUsuario> findAll() {
	logger.debug("Retrieving all persons");
	// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	// Create a Hibernate query (HQL)
	Query query = session.createQuery("FROM  Person");
	// Retrieve all
	return query.list();
    }

    public RolUsuario get(String id) {
	// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	// Retrieve existing person first
	RolUsuario rolUsuario = (RolUsuario) session.get(RolUsuario.class, id);
	return rolUsuario;
    }

    public void add(RolUsuario rolUsuario) {
	logger.debug("Adding new person");
	// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	// Save
	session.save(rolUsuario);
    }

    public void delete(String id) {
	logger.debug("Deleting existing person");
	// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	// Retrieve existing person first
	RolUsuario rolUsuario = (RolUsuario) session.get(RolUsuario.class, id);
	// Delete
	session.delete(rolUsuario);
    }

    public void edit(RolUsuario rolUsuario) {
	logger.debug("Editing existing person");
	// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	// Retrieve existing person via id
	RolUsuario existingPerson = (RolUsuario) session.get(RolUsuario.class, rolUsuario.getId());
	// Assign updated values to this person
	existingPerson.setNombre(rolUsuario.getNombre());
	existingPerson.setDescripcion(rolUsuario.getDescripcion());
	// Save updates
	session.save(existingPerson);
    }
    
}