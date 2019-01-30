package structures;

/**
 * Queue is a data structure where the first element in is also the first to go
 * (FIFO = first in first out). </br>
 * a[0] -> a[1] -> a[2] ->...-> a[n], where a[0] = first, a[n] = last
 */
public class Queue<E> {

	private Node<E> front;

	private static class Node<E> {
		final E value;
		Node<E> next;

		Node(final E value) {
			this.value = value;
			this.next = null;
		}
	}

	/**
	 * Add new element at the back of the queue
	 */
	public Queue<E> add(E newValue) {
		// create the head
		if (this.front == null) {
			this.front = new Node<E>(newValue);
		} else {
			// go to the end and append the new element
			Node<E> temp = this.front;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node<E>(newValue);
		}
		return this;
	}

	/**
	 * Retrieve and remove the head of the queue
	 */
	public E poll() {
		E retValue = null;
		if (this.front != null) {
			retValue = this.front.value;
			this.front = this.front.next;
		}
		return retValue;
	}

	/**
	 * Retrieve the head of the queue, but no removing
	 */
	public E peak() {
		return this.front != null ? this.front.value : null;
	}

	public boolean isEmpty() {
		return this.front == null;
	}

	public void print() {
		Node<E> temp = this.front;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
	}

	public static void main(String[] args) {
		System.out.println("Queue: ");
		Queue<Integer> queue = new Queue<Integer>();
		queue.add(1).add(2).add(3).add(4).add(5);
		queue.print();

		System.out.println("\nPoll " + queue.poll());
		System.out.println("Front is: " + queue.peak());

		System.out.println("Poll " + queue.poll());
		System.out.println("Front is: " + queue.peak());

		System.out.println("Add 9: ");
		queue.add(9);
		System.out.println("Front is: " + queue.peak());

		System.out.println("Add 6: ");
		queue.add(6);
		System.out.println("Front is: " + queue.peak());

		System.out.println("New queue: ");
		queue.print();
	}

}
