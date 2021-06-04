package it.rdev.rubrica.model.impl.file;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;
import it.rdev.rubrica.model.Contact;

public class FileSource {

	private static FileSource fs;

	static Path rubricaPath = Path.of(Configuration.getInstance().getValue(ConfigKeys.FILE_PATH));

	private static List<Contact> contacts;

	private FileSource(){

		//controlla se il file già esiste
		if(!Files.exists(rubricaPath)) {
			//TODO chiedere all'utente se vuole creare una rubrica
			//momentaneamente lo creo io vuoto
			try {
				Files.createFile(rubricaPath);
			} catch (IOException e) {
				System.out.println("Qualcosa non va con la creazione file o la cartella padre non esiste");
				e.printStackTrace();
			}
		}
	}

	public static FileSource getInstance() {
		if(fs == null) {
			fs = new FileSource();
		}
		return fs;
	}

	/**
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		try {
			contacts = fromJson();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Qualcosa non va con l'apertura del file");
		}
		return contacts;
	}

	/**
	 * @param contacts2
	 * @throws IOException 
	 */
	public void setContacts(Contact newContact) throws IOException {
		contacts.add(newContact);
		newContact.setId(contacts.size());
		String c = new GsonBuilder()
				.setPrettyPrinting()
				.create()
				.toJson(contacts);
		final Writer write = Files.newBufferedWriter(rubricaPath, Charset.forName("UTF-8"));
		write.write(c);
		write.close();

	}

	private static List<Contact> fromJson() throws IOException{
		List<Contact> list = new ArrayList<>();
		final Reader read = Files.newBufferedReader(rubricaPath, Charset.forName("UTF-8"));
		final JsonReader reader = new JsonReader(read);
		final Contact[] contacts = new Gson().fromJson(reader, Contact[].class);
		
		//ricordare che asList crea una lista immutabile quindi uso new ArrayList

		if(contacts!=null) {
			list = new ArrayList<>(Arrays.asList(contacts));
		} else {
			list = new ArrayList<>();
		}

		return list;
	}

	@Override
	protected void finalize() {
	}

}
