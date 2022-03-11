package kdp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		int port = 4000;

		ProducerConsumerBuffer<String> buffer = new SemaphoreHoneyBeesBuffer(5);
		try (ServerSocket server = new ServerSocket(port)) {
			while(true) {
				Socket client = server.accept();
				(new WorkingThread(buffer, client)).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
