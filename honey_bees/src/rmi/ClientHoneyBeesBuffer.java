package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientHoneyBeesBuffer {
	
	
	private HoneyBeesBufferImpl buffer;
	
	public ClientHoneyBeesBuffer(String host, int port) {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			Registry reg = LocateRegistry.getRegistry(host, port);
			buffer = (HoneyBeesBufferImpl) reg.lookup("/pot");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void collect(String item) {
		try {
			buffer.collect(item);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] eat() {
		String[] honey = null;
		try {
			honey = buffer.eat();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return honey;
	}
}
