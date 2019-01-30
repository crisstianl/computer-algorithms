package structures;

/**
 * Linked list is an ordered data structured where each element has a reference
 * to the next one and so on. </br>
 * a[0] -> a[1] -> a[2] -> ... ->a[n]
 */
public class LinkedList<E> {

	private Node<E> head;

	private static class Node<E> {
		final E value;
		Node<E> next;

		Node(final E value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList<E> add(E newValue) {
		// create the head
		if (this.head == null) {
			this.head = new Node<E>(newValue);
		} else {
			// append a new element to the last element
			Node<E> temp = this.head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node<E>(newValue);
		}
		return this;
	}

	public LinkedList<E> remove(E newValue) {
		// list is empty
		if (this.head == null) {
			return this;
		}
		// remove the head
		if (this.head.value.equals(newValue)) {
			this.head = this.head.next;
		} else {
			// find the element, remove it, and reconstruct the links
			Node<E> temp = this.head;
			while (temp.next != null) {
				if (temp.next.value.equals(newValue)) {
					temp.next = temp.next.next;
					break;
				}
				temp = temp.next;
			}
		}
		return this;
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
		Node<E> temp = head;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
	}

	public static void main(String[] args) {
		System.out.print("List: ");
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1).add(2).add(3).add(4).add(5).add(6).add(7);
		list.print();
		System.out.println("\nSize: " + list.size());

		System.out.print("Delete 1: ");
		list.remove(1);
		list.print();
		System.out.println("\nSize: " + list.size());

		System.out.print("Delete 5: ");
		list.remove(5);
		list.print();
		System.out.println("\nSize: " + list.size());

		System.out.print("Delete 7: ");
		list.remove(7);
		list.print();
		System.out.println("\nSize: " + list.size());

		System.out.print("Add 9: ");
		list.add(9);
		list.print();
		System.out.println("\nSize: " + list.size());
	}

}
