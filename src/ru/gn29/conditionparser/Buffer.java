package ru.gn29.conditionparser;

import java.util.LinkedList;
import java.util.List;

public class Buffer  {
	
	private List<String> lines;
	private int maxBufferSize;
	public Object putMonitor = new Object();
	
	public Buffer(int maxBufferSize) {
		lines = new LinkedList<String>();
		this.maxBufferSize = maxBufferSize;
				
	}
	
	public void putLineToBuffer(String line) {		
		synchronized(lines) {
//			if(lines.size() >= maxBufferSize) {
					try {
						while(lines.size() >= maxBufferSize) {
							lines.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//			}
			if(line != null && line != "") {
				lines.add(line);
				lines.notifyAll();
			}
		}
	}
	
	public String getLineFromBuffer() {
		String line = null;
		synchronized(lines) {
//			if(lines.isEmpty()) {				
					try {
						while(lines.isEmpty()) {
							lines.wait();
						} 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//			}
				line = lines.remove(0);
				lines.notifyAll();
				return line;
		}
	}
}
