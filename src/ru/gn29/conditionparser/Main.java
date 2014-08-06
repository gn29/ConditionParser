package ru.gn29.conditionparser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	public static void main(String[] args) {
		new Main().go();
	}
	
	private void go() {
		ExecutorService es = Executors.newFixedThreadPool(3);
		
		Condition condition = new ConditionImpl("это");
		Buffer buffer = new Buffer(5);
		Runnable t1 = new ThreadForPuttingWords(buffer);
		ThreadForAnalysingWords t2 = new ThreadForAnalysingWords(buffer);
		ThreadForAnalysingWords t3 = new ThreadForAnalysingWords(buffer);
		t2.addCondition(condition);
		t3.addCondition(condition);

		es.execute(t1);
		es.execute(t2);
		es.execute(t3);
	}
}
