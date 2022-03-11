package sockets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class SemaphoreDinningSavages implements DinningSavages {

	private Semaphore[] mutex;
	private Semaphore[] confirm;
	private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
	private Semaphore chef;
	private int[] food;
	private String path;
	private int timesCooked = 0;
	private int maxFoodInPots;

	public SemaphoreDinningSavages(int maxFoodInPots, int numOfTypes, String path) {
		mutex = new Semaphore[numOfTypes];
		confirm = new Semaphore[numOfTypes];
		food = new int[numOfTypes];
		this.maxFoodInPots = maxFoodInPots;
		this.path = path;
		chef = new Semaphore(0);
		for (int i = 0; i < numOfTypes; i++) {
			mutex[i] = new Semaphore(1);
			confirm[i] = new Semaphore(0);
			food[i] = 0;
		}
	}

	@Override
	public int cook() {
		chef.acquireUninterruptibly();

		int type = queue.remove();
		food[type] = maxFoodInPots;
		try (FileWriter fw = new FileWriter(path); PrintWriter pw = new PrintWriter(fw)) {

			for (int i = 0; i < maxFoodInPots; i++) {
				pw.write("Times cooked: " + timesCooked + " type: " + type + 
						" data: " + i + " food:(id: " + (int)(Math.random() * 100 + 1) + ")\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timesCooked++;
		confirm[type].release();
		
		return type;
	}

	@Override
	public void eat(int type) {
		mutex[type].acquireUninterruptibly();

		if (food[type] == 0) {
			queue.add(type);
			chef.release();
			confirm[type].acquireUninterruptibly();
		}
		food[type]--;
		mutex[type].release();
	}

}
