package rmi;



public class GuiTest {

	public static void main(String[] args) {

		int m = 2; // broj proizvodjaca
		int n = 3; // broj potrosaca

		String host = "localhost";
		int port = 5555;

		ProducerConsumer<TextMessage> buffer
				= new ClientProducerConsumerBuffer(host, port);

		Put[] p = new Put[m];
		Get[] c = new Get[n];

		for (int i = 0; i < p.length; i++) {
			p[i] = new Put(buffer);
		}

		for (int i = 0; i < c.length; i++) {
			c[i] = new Get(buffer);
		}

	}

}
