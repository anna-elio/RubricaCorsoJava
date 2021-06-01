package it.rdev.rubrica.model.impl.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact> implements ContactDAO {
	
	private final String TABLE_CONTACT = "contacts";
	private final String TABLE_EMAILS = "emails";
	private final String TABLE_PHONE = "phone";

	public List<Contact> getAll() {
		List<Contact> contacts = new ArrayList<>();
		try {
			ResultSet contact = this.executeQuery("SELECT id, name, surname FROM " + TABLE_CONTACT);
			while(contact.next()) {
				contacts.add(
						new Contact()
						.setId(contact.getInt("id"))
						.setName(contact.getString("name"))
						.setSurname(contact.getString("surname")));
			}
			for(Contact c : contacts) {
				List<String> emails = new ArrayList<>();
				ResultSet email = this.executeQuery("SELECT email FROM " + TABLE_EMAILS
						+ " WHERE id_contact=" + c.getId());
				while(email.next()) {
					emails.add(email.getString("email"));
				}
				c.setEmails(emails);
			}
			for(Contact c : contacts) {
				List<String> phones = new ArrayList<>();
				ResultSet phone = this.executeQuery("SELECT phone FROM " + TABLE_PHONE
						+ " WHERE id_contact=" + c.getId());
				while(phone.next()) {
					phones.add(phone.getString("email"));
				}
				c.setPhoneNumbers(phones);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
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
