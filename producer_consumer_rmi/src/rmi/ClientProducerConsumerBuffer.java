package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientProducerConsumerBuffer implements ProducerConsumer<TextMessage> {

	private RemoteRMIProducerConsumer<TextMessage> buffer;

	@SuppressWarnings("unchecked")
	public ClientProducerConsumerBuffer(String host, int port) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			Registry reg = LocateRegistry.getRegistry(host, port);
			buffer = (RemoteRMIProducerConsumer<TextMessage>) reg.lookup("/buffer");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void put(TextMessage item) {
		try {
			buffer.put(item);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public TextMessage get() {
		TextMessage ret=null;
		try {
			ret=buffer.get();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
