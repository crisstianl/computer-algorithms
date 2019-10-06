package structures;

/**
 * Double linked list is an ordered data structured where each element has a
 * reference to the right neighbor but also a reference to the left neighbor.
 * </br>
 * a[0] <-> a[1] <-> a[2] <-> ... <->a[n]
 */
public class DoubleLinkedList<E> {

	private Node<E> head;
	private Node<E> tail;

	private static class Node<E> {
		final E value;
		Node<E> next;
		Node<E> previous;

		Node(final E value) {
			this.value = value;
			this.next = null;
			this.previous = null;
		}
	}

	public DoubleLinkedList<E> add(E newValue) {
		// create the head
		if (this.head == null) {
			this.head = new Node<E>(newValue);
			this.tail = this.head;
		} else {
			// add a new tail and link it with the current one
			Node<E> temp = this.tail;
			this.tail = new Node<E>(newValue);
			temp.next = this.tail;
			this.tail.previous = temp;
		}
		return this;
	}

	public DoubleLinkedList<E> remove(E newValue) {
		// empty
		if (this.head == null) {
			return this;
		}
		// remove the head
		if (this.head.value.equals(newValue)) {
			this.head = this.head.next;
			return this;
		}
		// remove the tail
		if (this.tail.value.equals(newValue)) {
			this.tail = this.tail.previous;
			return this;
		}
		// remove the element
		Node<E> temp = this.head;
		while (temp != null) {
			if (temp.value.equals(newValue)) {
				temp.previous = temp.next;
				temp.next.previous = temp.previous;
				break;
			}
			temp = temp.next;
		}
		return this;
	}

	public E head() {
		return this.head.value;
	}

	public E tail() {
		return this.tail.value;
	}

	public int size() {
		int count = 0;
		Node<E> temp = this.head;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	public void print() {
		Node<E> temp = this.head;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
	}

	public void reverse() {
		Node<E> temp = this.tail;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.previous;
		}
	}

	public static void main(String[] args) {
		System.out.print("List: ");
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.add(1).add(2).add(3).add(4).add(5).add(6).add(7);
		list.print();

		System.out.print("\nReversed: ");
		list.reverse();

		System.out.print("\nDelete 1: ");
		list.remove(1);
		System.out.println("\nHead is: " + list.head() + " tail is: " + list.tail());

		System.out.print("Delete 7: ");
		list.remove(7);
		System.out.print("\nHead is: " + list.head() + " tail is: " + list.tail());

		System.out.print("\nDelete 5: ");
		list.remove(5);
		System.out.print("\nHead is: " + list.head() + " tail is: " + list.tail());

		System.out.print("\nAdd 9: ");
		list.add(9);
		System.out.print("\nHead is: " + list.head() + " tail is: " + list.tail());

		System.out.print("\nNew list: ");
		list.print();
	}

}
