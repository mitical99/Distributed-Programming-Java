package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HoneyBeesBuffer<T> extends Remote {
	T[] eat() throws RemoteException;
	void collect(T item) throws RemoteException;
}
