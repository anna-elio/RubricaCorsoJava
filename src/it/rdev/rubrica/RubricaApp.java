package it.rdev.rubrica;

import it.rdev.rubrica.controller.RubricaController;

public class RubricaApp {

	private static RubricaController controller;
	
	public static void main(String[] strings) {
		//new AppFrame().setVisible(true);
		controller = new RubricaController();
		System.out.println(controller.getContactList());;
	}
	
}
