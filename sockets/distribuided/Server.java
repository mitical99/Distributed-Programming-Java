package sockets.distribuided;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import sockets.DinningSavages;
import sockets.SemaphoreDinningSavages;

public class Server {

	public static final String path = "C:\\Users\\pc\\Desktop\\KDPSocket\\DinningSavagesSocket\\src\\sockets\\distribuided\\SrvFile.txt";

	public static void main(String[] args) {

		int port = 5555;

		try (ServerSocket server = new ServerSocket(port);) {
			DinningSavages pot = new SemaphoreDinningSavages(5, 4, path);
			
			System.out.println("Server started on port " + port);
			
			while(true) {
				Socket client = server.accept();
				(new WorkingThread(client,pot)).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
