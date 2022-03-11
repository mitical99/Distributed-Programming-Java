package rmi;

public class MonitorProducerConsumer implements ProducerConsumer<TextMessage> {

	private TextMessage[] buffer;
	private int head, tail, cap, N;

	public MonitorProducerConsumer(int bufferSize) {
		N = bufferSize;
		head = tail = cap = 0;
		buffer = new TextMessage[bufferSize];
	}

	@Override
	public synchronized void put(TextMessage item) {
		while (cap == N) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[tail] = item;
		tail = (tail + 1) % N;
		cap++;
		notifyAll();
	}

	@Override
	public synchronized TextMessage get() {
		while (cap == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		TextMessage ret = buffer[head];
		head = (head + 1) % N;
		cap--;
		notifyAll();
		return ret;
	}

}
