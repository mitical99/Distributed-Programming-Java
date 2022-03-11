package rmi;

public interface ProducerConsumer<T> {
	public void put(T item);
	public T get();
}
