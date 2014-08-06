package ru.gn29.conditionparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThreadForAnalysingWords implements Runnable {
	private Buffer buffer;
	private List<Condition> conditions;
	private final Object conditionMonitor = new Object();
	private final String filePath = "outwrite.txt";
	
	public ThreadForAnalysingWords(Buffer buffer) {
		this.buffer = buffer;
		conditions = new ArrayList<Condition>();
	}
	
	public void addCondition(Condition condition) {
		synchronized(conditionMonitor) {
			conditions.add(condition);
		}
	}
	
	@Override
	public void run() {
		System.err.println("Analizer " + Thread.currentThread().getName() + " started");
		while(true) {
			String line = buffer.getLineFromBuffer();
			String[] words = line.split(" ");
			analizeWords(words);
		}
	}
	
	private void analizeWords(String[] words) {
		for(String word : words) {
			analizeWord(word);
		}
	}
	
	private void analizeWord(String word) {
		synchronized(conditionMonitor) {
			for(Condition condition : conditions) {
				if(condition.isSatisfy(word)){
					System.out.print(word + ", ");
					writeWordToFile(word);
					break;
				}
			}
		}
	}
	
	private void writeWordToFile(String word) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath), true))) {
			writer.write(word + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
