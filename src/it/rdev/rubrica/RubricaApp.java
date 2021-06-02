package it.rdev.rubrica;

import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;

public class RubricaApp {

	private static RubricaController controller;
	
	public static void main(String[] strings) {
		//new AppFrame().setVisible(true);
		controller = new RubricaController();
		System.out.println(controller.getContactList());
		Contact c = new Contact()
				.setName("B")
				.setSurname("b");
		controller.addContact(c);
		c.addEmails("AAAAAA@gmail.com").addPhoneNumbers("123456");
		System.out.println(c.getId());
		controller.updateContact(c);
		System.out.println(controller.getContactList());
	}
	
}
