package ru.gn29.conditionparser;

import java.io.*;

public class ThreadForPuttingWords implements Runnable {

	Buffer buffer;
	private final String filePath = "dekorat.txt";

	public ThreadForPuttingWords(Buffer buffer) {
		super();
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				buffer.putLineToBuffer(line);
			}
			System.err.println("\n=== EOF ===\n");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException nullException) {
			nullException.printStackTrace();
		}
	}

}
