package it.rdev.rubrica.model.impl.rdbms;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact, BigInteger> implements ContactDAO {

	private final String TABLE_CONTACT = "contacts";
	private final String TABLE_EMAILS = "emails";
	private final String TABLE_PHONE = "phones";

	public List<Contact> getAll() {
		List<Contact> contacts = new ArrayList<>();
		try {
			ResultSet rs = this.executeQuery("SELECT c.id, c.name, c.surname, e.email, p.phone "
					+ "FROM " + TABLE_CONTACT + " as c LEFT OUTER JOIN " + TABLE_EMAILS + " as e ON c.id=e.id_contact "
					+ " LEFT OUTER JOIN " + TABLE_PHONE + " as p on p.id_contact=c.id");
			while(rs.next()) {
				Contact c = new Contact().setId(rs.getInt("id"));
				if(contacts.contains(c)) {
					c = contacts.get(contacts.indexOf(c));
				} else {
					c.setName(rs.getString("name"));
					c.setSurname(rs.getString("surname"));
					contacts.add(c);
				}
				if( rs.getString("email") != null ) {
					c.addEmails(rs.getString("email"));
				}
				if( rs.getString("phone") != null ) {
					c.addPhoneNumbers(rs.getString("phone"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	@Override
	public boolean persist(Contact o) throws SQLException {
		BigInteger id = this.executeInsert("INSERT INTO " + TABLE_CONTACT + " (name, surname) VALUES (?, ?)",
				o.getName(), o.getSurname());
		o.setId(id.intValue());
		insertEmailAndPhone(o, id.intValue());
		
		return id != null;
	}

	/**
	 * @param o
	 * @param intValue
	 * @throws SQLException 
	 */
	private void insertEmailAndPhone(Contact c, Integer id) throws SQLException {
		if( c.getEmails() != null ) {
			Object[] emailsParam = new Object[c.getEmails().size() * 2];
			Iterator<String> emailIt = c.getEmails().iterator();
			StringBuffer sb = new StringBuffer("INSERT INTO emails VALUES ");
			for(int i = 0; i<emailsParam.length && emailIt.hasNext();) {
				if( i > 0) {
					sb.append(", ");
				}
				sb.append("(?, ?)");
				emailsParam[i++] = emailIt.next();
				emailsParam[i++] = id;
			}
			this.executeUpdate(sb.toString(), emailsParam);
		}
		
		if( c.getPhoneNumbers() != null ) {
			Object[] phonesParam = new Object[c.getPhoneNumbers().size() * 2];
			Iterator<String> phoneIt = c.getPhoneNumbers().iterator();
			StringBuffer sb = new StringBuffer("INSERT INTO phones VALUES ");
			for(int i = 0; i<phonesParam.length && phoneIt.hasNext();) {
				if( i > 0) {
					sb.append(", ");
				}
				sb.append("(?, ?)");
				phonesParam[i++] = phoneIt.next();
				phonesParam[i++] = id;
			}
			this.executeUpdate(sb.toString(), phonesParam);
		}
		
	}

	@Override
	public boolean delete(Contact t) throws SQLException {
		int rows = this.executeUpdate("DELETE FROM " + TABLE_CONTACT + " WHERE id = ?", t.getId());
		return rows > 0;
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		this.executeUpdate("DELETE FROM emails WHERE id_contact = ?", t.getId());
		this.executeUpdate("DELETE FROM phones WHERE id_contact = ?", t.getId());
		
		insertEmailAndPhone(t, t.getId());
		return true;
	}

	private void insertEmailAndPhone(Contact c, Integer id) throws SQLException {
		// Per evitare di effettuare una INSERT per ogni email e numero di telefono
		// creo due insert multiple
		if( c.getEmails() != null ) {
			Object[] emailsParam = new Object[c.getEmails().size() * 2];
			Iterator<String> emailIt = c.getEmails().iterator();
			StringBuffer sb = new StringBuffer("INSERT INTO emails VALUES ");
			for(int i = 0; i<emailsParam.length && emailIt.hasNext();) {
				if( i > 0) {
					sb.append(", ");
				}
				sb.append("(?, ?)");
				emailsParam[i++] = emailIt.next();
				emailsParam[i++] = id;
			}
			this.executeUpdate(sb.toString(), emailsParam);
		}
		
		if( c.getPhoneNumbers() != null ) {
			Object[] phonesParam = new Object[c.getPhoneNumbers().size() * 2];
			Iterator<String> phoneIt = c.getPhoneNumbers().iterator();
			StringBuffer sb = new StringBuffer("INSERT INTO phones VALUES ");
			for(int i = 0; i<phonesParam.length && phoneIt.hasNext();) {
				if( i > 0) {
					sb.append(", ");
				}
				sb.append("(?, ?)");
				phonesParam[i++] = phoneIt.next();
				phonesParam[i++] = id;
			}
			this.executeUpdate(sb.toString(), phonesParam);
		}
	}
}
