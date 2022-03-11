package sockets.distribuided;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import sockets.DinningSavages;

public class WorkingThread extends Thread {

	private Socket client;
	private DinningSavages pot;
	private static int cnt = 1;

	public WorkingThread(Socket client, DinningSavages pot) {
		this.client = client;
		this.pot = pot;
	}

	public void run() {
		try (Socket s = this.client;
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream())) {
			String op = (String) in.readObject();
			if("cook".equals(op)) {
				int type = pot.cook();
				out.writeInt(type);
				out.flush();
				
			} else if("eat".equals(op)) {
				int type = in.readInt();
				pot.eat(type);
				
				out.writeObject("FileToAccept");
				try(FileReader fr = new FileReader(Server.path);
						BufferedReader br = new BufferedReader(fr)) {
					
					String line;
					
					while ((line=br.readLine()) != null) {
						out.writeObject(line);
					}
					out.writeObject(null);
				}
				out.writeObject("ACK");
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
