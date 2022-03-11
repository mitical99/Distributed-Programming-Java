package kdp;

import java.io.Serializable;

public interface ProducerConsumerBuffer<T> {
	public void put(T message);
	public T[] get();
}
