package structures;

/**
 * Stack is a data structure where the first element is the last to go (LIFO =
 * last in first out). </br>
 * a[0] <- a[1] <- a[2] <-...<- a[n], where a[0] = base, a[n] = peek
 */
public class Stack<E> {

	private Node<E> top;

	private static class Node<E> {
		final E value;
		Node<E> previous;

		Node(final E value) {
			this.value = value;
			this.previous = null;
		}
	}

	/**
	 * Add a new value at the top of the queue
	 */
	public Stack<E> push(E newValue) {
		// add the base element
		if (this.top == null) {
			this.top = new Node<E>(newValue);
		} else {
			// append the new element at the top
			Node<E> temp = this.top;
			this.top = new Node<E>(newValue);
			this.top.previous = temp;
		}
		return this;
	}

	/**
	 * Retrieve and remove the top of the queue
	 */
	public E pop() {
		E retValue = null;
		if (this.top != null) {
			retValue = this.top.value;
			this.top = this.top.previous;
		}
		return retValue;
	}

	/**
	 * Retrieve the top of the queue, but no remove
	 */
	public E peak() {
		return this.top != null ? this.top.value : null;
	}

	public boolean isEmpty() {
		return this.top == null;
	}

	public void print() {
		Node<E> temp = this.top;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.previous;
		}
	}

	public static void main(String[] args) {
		System.out.println("Stack: ");
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1).push(2).push(3).push(4).push(5);
		stack.print();

		System.out.println("Pop " + stack.pop());
		System.out.println("Top is: " + stack.peak());

		System.out.println("Pop " + stack.pop());
		System.out.println("Top is: " + stack.peak());

		System.out.println("Push 9: ");
		stack.push(9);
		System.out.println("Top is: " + stack.peak());

		System.out.println("Push 7: ");
		stack.push(7);
		System.out.println("Top is: " + stack.peak());

		System.out.println("New stack: ");
		stack.print();
	}

}
