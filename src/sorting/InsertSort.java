package sorting;

/**
 * Time complexity: worst O(n2) array is reversed, average O(n2), best O(n)
 * array is sorted. </br>
 * Space complexity: O(n) same array. </br>
 * Description: create a sorted sequence from the first element in the array.
 * Iterate from the 2nd element and insert each element, denoted key, into the
 * sorted sequence in such way the new sequence remains sorted.
 */
public class InsertSort {

	public static void main(String[] args) {
		int[] a = { 8, 6, 2, 5, 9, 1, 7, 3, 0, 4 };
		sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int key = a[i];
			int j = i - 1;
			// right shift all elements from the sequence greater than current element
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j--;
			}
			// insert the current element in the sorted sequence
			a[j + 1] = key;
		}
	}

}
