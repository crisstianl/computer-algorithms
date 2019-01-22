package sorting;

/**
 * Time complexity: O(nLogn) for all 3 cases (worst, average, best).</br>
 * Space complexity: O(n) same array.</br>
 * Description: build a heap from the array and rearrange elements to form a max
 * heap (parent nodes are greater or equal than child nodes). Swap the first and
 * last node from the heap and delete it. Repeat the procedure until 1 node
 * remains.
 */
public class HeapSort {

	public static void main(String[] args) {
		int[] a = { 8, 6, 2, 5, 9, 1, 7, 3, 0, 4 };
		sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	static void sort(int[] a) {
		// Build heap (rearrange array)
		for (int i = a.length / 2 - 1; i >= 0; i--)
			heapify(a, a.length, i);

		// One by one extract an element from heap
		for (int i = a.length - 1; i >= 0; i--) {
			// Move current root to end
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;

			// call max heapify on the reduced heap
			heapify(a, i, 0);
		}
	}

	static void heapify(int[] a, int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && a[l] > a[largest])
			largest = l;

		// If right child is larger than largest so far
		if (r < n && a[r] > a[largest])
			largest = r;

		// If largest is not root
		if (largest != i) {
			int swap = a[i];
			a[i] = a[largest];
			a[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(a, n, largest);
		}
	}

}
