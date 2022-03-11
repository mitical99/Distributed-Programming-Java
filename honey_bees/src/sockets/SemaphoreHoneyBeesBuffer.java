package kdp;

import java.util.concurrent.Semaphore;

public class SemaphoreHoneyBeesBuffer implements ProducerConsumerBuffer<String> {

	private Semaphore mutex;
	private Semaphore full;

	private static final int H = 20;
	private int N;
	private String[] pot;
	private int cap = 0;

	public SemaphoreHoneyBeesBuffer(int n) {
		N = n;
		pot = new String[H];
		mutex = new Semaphore(1, true);
		full = new Semaphore(0);
	}

	@Override
	public void put(String message) {
		mutex.acquireUninterruptibly();
		pot[cap++] = message;
		if (cap == H) {
			full.release();
		} else {
			mutex.release();
		}

	}

	@Override
	public String[] get() {
		full.acquireUninterruptibly();
		String[] honey = pot;
		cap = 0;
		mutex.release();
		return honey;
	}

}
