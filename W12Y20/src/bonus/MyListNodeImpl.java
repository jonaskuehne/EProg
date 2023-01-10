package bonus;

public class MyListNodeImpl<T> implements MyListNode<T> {
	
	private T value;
	private MyListNode<T> next;

	
	public MyListNodeImpl(T value) {
		this.value = value;
	}
	
	@Override
	public T value() {
		return value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public MyListNode<T> next() {
		return next;
	}

	@Override
	public void setNext(MyListNode<T> next) {
		this.next = next;
	}

}
