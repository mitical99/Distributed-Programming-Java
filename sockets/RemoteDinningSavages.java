package sockets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class RemoteDinningSavages implements DinningSavages {

	private String host;
	private int port;
	private static int cnt = 1;

	public RemoteDinningSavages(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	@Override
	public int cook() {
		int type = 0;
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

			out.writeObject("cook");
			type = (int) in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public void eat(int type) {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

			out.writeObject("eat");
			out.writeInt(type);
			out.flush();
			
			String op = (String) in.readObject();
			if(op.equalsIgnoreCase("fileToAccept")) {
				try(FileWriter f = new FileWriter("C:\\Users\\pc\\Desktop\\KDPSocket\\DinningSavagesSocket\\src\\sockets\\" + "Savage_" + cnt + ".txt");
						PrintWriter pw = new PrintWriter(f)){
						
						String line;
						while( !(line = (String) in.readObject()).equals("null")) {
							pw.write(line + "\n");
						}
					}
				cnt++;
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
