package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteRMIProducerConsumer<T> extends Remote {

	public void put(T item) throws RemoteException;
	public T get() throws RemoteException;
}
