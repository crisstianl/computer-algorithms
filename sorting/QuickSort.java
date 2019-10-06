package sorting;

/**
 * Time complexity: worst O(n2) array is already sorted in increasing or
 * decreasing order, average O(nLogn), best O(nLogn).</br>
 * Space complexity: O(n) same array but requires call stack.</br>
 * Description: divide and conquer algorithm, divide the array into 2 partitions
 * recursively, and rearrange all elements in such way that those lower than the
 * pivot are in the left partition and those greater than the pivot are in the
 * right. The pivot can be the first, median or last element
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] a = { 8, 6, 2, 5, 9, 1, 7, 3, 0, 4 };
		sort(a, 0, a.length - 1);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	static void sort(int[] a, int l, int r) {
		// recursive stop condition
		if (l < r) {
			// pivot index
			int pi = partition(a, l, r);

			// sort left partition p1 = [left, pivot_index - 1]
			sort(a, l, pi - 1);

			// sort right partition, p2 = [pivot_index + 1, right]
			sort(a, pi + 1, r);
		}
	}

	static int partition(int[] a, int l, int r) {
		// pivot = last element
		int pivot = a[r];
		// pivot index = to be calculated
		int pi = l - 1;

		// when an element is lower than the pivot swap the element with the one at pi
		for (int j = l; j < r; j++) {
			if (a[j] <= pivot) {
				// swap arr[pi+1] and arr[j]
				pi++;
				int temp = a[pi];
				a[pi] = a[j];
				a[j] = temp;
			}
		}

		// last swap: move the pivot to the new position at pi
		pi++;
		int temp = a[pi];
		a[pi] = pivot;
		a[r] = temp;

		return pi;
	}

}
