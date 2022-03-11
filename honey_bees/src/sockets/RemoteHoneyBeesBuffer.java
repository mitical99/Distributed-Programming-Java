package kdp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class RemoteHoneyBeesBuffer implements ProducerConsumerBuffer<String> {

	private String host;
	private int port;

	public RemoteHoneyBeesBuffer(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void put(String message) {
		try (Socket socket = new Socket(host, port);
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
			out.writeObject("put");
			out.writeObject(message);
			out.flush();
			
			in.readObject(); //ACK
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String[] get() {
		try (Socket socket = new Socket(host, port);
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
		out.writeObject("get");
		String[] pot = (String[]) in.readObject();
		return pot;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
