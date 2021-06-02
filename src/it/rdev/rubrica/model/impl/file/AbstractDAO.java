package it.rdev.rubrica.model.impl.file;

import it.rdev.rubrica.model.DAO;

/**
 * 
 * Classe astratta che contiene la logica comune per le operazioni di CRUD su database relazionale
 * 
 * @author Danilo Di Nuzzo
 *
 * @param <T> entità su cui effettuare le operazioni di CRUD
 * @param <D> tipo di cui è formato l'ID dell'entità da gestire (tipo del database)
 */
abstract class AbstractDAO<T> implements DAO<T> {

	protected void executeCmd() {

	}
	
	protected void executeInsert() {
		
	}
	
	protected void executeUpdate() {
		
	}

}