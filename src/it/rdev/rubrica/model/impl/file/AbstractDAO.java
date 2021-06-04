package it.rdev.rubrica.model.impl.file;

import java.io.IOException;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.DAO;

/**
 * 
 * Classe astratta che contiene la logica comune per le operazioni di CRUD su file
 * 
 * @author Anna Eliotropio
 *
 * @param <T> entità su cui effettuare le operazioni di CRUD
 */
abstract class AbstractDAO<T> implements DAO<T> {

	protected List<Contact> getAll(){
		List<Contact> list = FileSource.getInstance().getContacts();
		return list;
	}

	protected static void insertJson(Contact contact) {
		try {
			FileSource.getInstance().setContacts(contact);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Qualcosa non va con la scrittura del file");
		}
	}

}