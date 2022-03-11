package rmi;

import java.rmi.RemoteException;

public class RemoteRMIProducerConsumerImpl implements RemoteRMIProducerConsumer<TextMessage> {

	private ProducerConsumer<TextMessage> buffer;
	
	public RemoteRMIProducerConsumerImpl(ProducerConsumer<TextMessage> buffer) {
		this.buffer=buffer;
	}
	
	@Override
	public void put(TextMessage item) throws RemoteException {
		buffer.put(item);
	}

	@Override
	public TextMessage get() throws RemoteException {
		return buffer.get();
	}

}
