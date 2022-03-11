package rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int port = 5555;
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		ProducerConsumer<TextMessage> buf = new MonitorProducerConsumer(6);
		RemoteRMIProducerConsumer<TextMessage> buffer = new RemoteRMIProducerConsumerImpl(buf);
		try {
			RemoteRMIProducerConsumer<TextMessage> stub = (RemoteRMIProducerConsumer<TextMessage>) UnicastRemoteObject.exportObject(buffer,0);
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/buffer", stub);
			System.out.println("Server started on port " + port);
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
