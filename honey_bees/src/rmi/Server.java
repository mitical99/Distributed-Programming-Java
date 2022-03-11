package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int port = 4000;
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		MonitorHoneyBeesBuffer buf = new MonitorHoneyBeesBuffer();
		HoneyBeesBuffer<String> buffer = new HoneyBeesBufferImpl(buf);
		
		try {
			HoneyBeesBuffer<String> stub = (HoneyBeesBuffer<String>) UnicastRemoteObject.exportObject(buffer, 0);
			Registry reg = LocateRegistry.createRegistry(port);
			reg.rebind("/pot", stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
