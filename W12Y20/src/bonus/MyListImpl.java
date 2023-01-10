package bonus;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl<T> implements MyList<T> {
	
	private int size;
	private MyListNode<T> head;

	@Override
	public T get(int index) {
		return getNode(index).value();
	}

	@Override
	public MyListNode<T> getNode(int index) {
		if (index >= size) {
			throw new NoSuchElementException();
		}
		
		MyListNode<T> tempNode = head;
		
		for (int i = 0; i < index; ++i) {
			tempNode = tempNode.next();
		}
		
		return tempNode;
	}
	
	@Override
	public MyListNode<T> getHead() {
		return head;
	}

	@Override
	public void set(int index, T value) {
		getNode(index).setValue(value);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void addFirst(T value) {
		if (isEmpty()) {
			head = new MyListNodeImpl<T>(value);
		} else {
			MyListNode<T> newHead = new MyListNodeImpl<T>(value);
			newHead.setNext(head);
			head = newHead;
		}
		++size;
	}

	@Override
	public void addLast(T value) {
		if (isEmpty()) {
			head = new MyListNodeImpl<T>(value);
		} else {
			getNode(size - 1).setNext(new MyListNodeImpl<T>(value));
		}
		++size;
	}

	@Override
	public void addAll(MyList<T> other) {
		if (this.isEmpty()) {
			this.head = other.getHead();
		} else {
			this.getNode(this.size - 1).setNext(other.getHead());
		}
		
		this.size += other.getSize();
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		T val = head.value();
		
		if (size == 1) {
			clear();
		} else {
			head = head.next();
			--size;
		}
		
		return val;
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		if (size == 1) {
			return removeFirst();
		} else {
			MyListNode<T> secondLast = getNode(size - 2);
			T val = secondLast.next().value();
			
			secondLast.setNext(null);
			
			--size;
			
			return val;
		}
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			MyListNode<T> node = head;

			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public T next() {
				T val = node.value();
				
				node = node.next();
				
				return val;
			}
			
		};
	}
}
