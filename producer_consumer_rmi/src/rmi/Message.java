package rmi;

import java.io.Serializable;

public interface Message<T> extends Serializable {
	public void setBody(T body);
	public T getBody();
}
