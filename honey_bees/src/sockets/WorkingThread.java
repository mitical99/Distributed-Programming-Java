package kdp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WorkingThread extends Thread {

	private Socket client;
	private ProducerConsumerBuffer<String> buffer;

	public WorkingThread(ProducerConsumerBuffer<String> buffer, Socket client) {
		this.buffer = buffer;
		this.client = client;
	}

	public void run() {
		try (Socket socket = this.client;
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			
			String op = (String) in.readObject();
			if("put".equalsIgnoreCase(op)) {
				String msg = (String) in.readObject();
				buffer.put(msg);
				
				out.writeObject("ACK");
			} else if ("get".equalsIgnoreCase(op)) {
				String[] honey = buffer.get();
				out.writeObject(honey);
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
