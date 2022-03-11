package rmi;

public class MonitorHoneyBeesBuffer {
	
	private static final int H = 20;
	private int cap = 0;
	private String[] pot = new String[H];
	
	public synchronized void collect(String item) {
		while(cap == H) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pot[cap++] = item;
		if(cap == H) notifyAll();
	}
	
	public synchronized String[] eat() {
		while(cap != H) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] honey = pot;
		cap = 0;
		notifyAll();
		return honey;
	}
}
