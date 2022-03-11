package sockets;

public class Test {

	public static void main(String[] args) {
	
		String  host = "localhost";
		int port = 5555;
	
		DinningSavages pot = new RemoteDinningSavages(host, port);
		
		
		new Chef(pot);

		for(int i = 0; i < 3; i++) {
			new Savage(pot, i);
		}
	}

}
