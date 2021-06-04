package it.rdev.rubrica.model.impl.file;

import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact> implements ContactDAO {

	@Override
	public List<Contact> getAll() {
		if(super.getAll() == null) {
			System.out.println("Rubrica vuota");
		}
		return super.getAll();
	}

	@Override
	public boolean persist(Contact o){
		super.insertJson(o);
		return true;
	}

	@Override
	public boolean delete(Contact t){
		//TODO da testare
		List<Contact> contacts = super.getAll();
		contacts.remove(t.getId()-1);
		return false;
		
	}

	@Override
	public boolean update(Contact t) {
		//TODO da testare
		List<Contact> contacts = super.getAll();
		for(Contact cnt : contacts) {
			if(cnt.equals(t)) {
				cnt=t;
			}
		}
		return false;
		
	}

}
