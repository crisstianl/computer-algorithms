package sorting;

/**
 * Time complexity: worst O(n2) array is reversed, average O(n2), best O(n)
 * array is already sorted. </br>
 * Space complexity: O(n) same array. </br>
 * Description: iterate through the array, when an element is greater than his
 * next neighbor swap them. Repeat the process until no more swaps occur.
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] a = { 8, 6, 2, 5, 9, 1, 7, 3, 0, 4 };
		sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	static void sort(int[] a) {
		boolean sorted;
		do {
			sorted = true;
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i] > a[i + 1]) {
					int temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;

					// we must reiterate again
					sorted = false;
				}
			}
		} while (!sorted); // repeat until no more swaps occur
	}

}
