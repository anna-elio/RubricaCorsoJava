package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;

public class FileSource {

	private static FileSource fs;

	public static FileSource getInstance() {
		if(fs == null) {
			try {
				fs = new FileSource();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Il file rubrica non esiste");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("Il file rubrica ha una codifica differente");
			}
		}
		return fs;
	}

	private BufferedReader reader;
	private BufferedWriter writer;

	private FileSource() throws UnsupportedEncodingException, FileNotFoundException {
		File rubrica = new File(Configuration.getInstance().getValue(ConfigKeys.FILE_PATH));
		if(rubrica.exists()) {
			try {
				reader = Files.newBufferedReader(rubrica.toPath(), Charset.forName("UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				rubrica.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the reader
	 */
	public BufferedReader getReader() {
		return reader;
	}

	/**
	 * @return the writer
	 */
	public BufferedWriter getWriter() {
		return writer;
	}

	@Override
	protected void finalize() {
		if( reader != null ) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if( writer != null ) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
