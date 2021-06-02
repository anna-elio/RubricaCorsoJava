package it.rdev.rubrica.model.impl.file;

import java.sql.SQLException;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact> implements ContactDAO {

	

	public List<Contact> getAll() {
		return null;
		
	}

	@Override
	public boolean persist(Contact o) throws SQLException {
		return false;
		
	}

	@Override
	public boolean delete(Contact t) throws SQLException {
		return false;
		
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		return false;
		
	}

}
