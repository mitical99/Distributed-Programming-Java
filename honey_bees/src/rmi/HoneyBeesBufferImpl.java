package rmi;

import java.rmi.RemoteException;

public class HoneyBeesBufferImpl implements HoneyBeesBuffer<String> {

	private MonitorHoneyBeesBuffer buffer;
	
	public HoneyBeesBufferImpl(MonitorHoneyBeesBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public String[] eat() throws RemoteException {
		String[] honey = buffer.eat();
		return honey;
	}

	@Override
	public void collect(String item) throws RemoteException {
		buffer.collect(item);
	}

}
