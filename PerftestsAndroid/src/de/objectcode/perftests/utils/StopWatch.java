package de.objectcode.perftests.utils;

public class StopWatch {
	
	private Measurement m0;
	private Measurement m1;
	
	class Measurement {
		long tNs;
		long tMs;
		long heapTotal;
		public Measurement() {
			tNs = System.nanoTime();
			tMs = System.currentTimeMillis();
			heapTotal = Runtime.getRuntime().totalMemory();
		}
		public String diff(Measurement o) {
			StringBuilder b = new StringBuilder();
			b.append("time: \t").append((tNs-o.tNs)/1000000).append(" ms\n");
			b.append("heapTotal: \t").append(heapTotal-o.heapTotal).append(" bytes\n");
			return b.toString();
		}
	}
	
	public StopWatch() {
		start();
	}
	
	public void start() {
		m0 = new Measurement();
	}

	public String stop() {
		m1 = new Measurement();
		return m1.diff(m0);
	}
}
