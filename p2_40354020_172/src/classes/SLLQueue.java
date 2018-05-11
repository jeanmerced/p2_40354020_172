package classes;

import interfaces.Queue;

public class SLLQueue<E> implements Queue<E> {
    
	// inner class for nodes in singly linked lists
	private static class Node<E> {   
		private E element; 
		private Node<E> next; 
		
		public Node() {
			this(null);
		}
		
		public Node(E e) {
			this(e, null);
		}
		
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		
		public E getElement() {
			return element;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setElement(E e) {
			element = e;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
		
		public void clean() {
			element = null;
			next = null;
		}
	}	
	
	private Node<E> first, last;   // references to first and last node
	private int size; 
	
	public SLLQueue() {           // initializes instance as empty queue
		first = last = null; 
		size = 0; 
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E first() {
		if (isEmpty()) 
			return null;
		return first.getElement(); 
	}
	
	public E dequeue() {
		if(isEmpty()) 
			return null;
		Node<E> ntr = first;
		first = first.getNext();
		if(size == 1)
			last = null;
		size--;
		E etr = ntr.getElement();
		ntr.clean();
		return etr;
	}
	
	public void enqueue(E e) {
		if (size == 0) 
			first = last = new Node<>(e); 
		else { 
			Node<E> newest = new Node<>(e);
			last.setNext(newest); 	
			last = newest;
		}
		size++; 
	}
	
}

