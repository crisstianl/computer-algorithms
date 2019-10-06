package sorting;

/**
 * Time complexity: O(nLogn) for all 3 cases (worst, average, best).</br>
 * Space complexity: O(n2) auxiliary array and call stack.</br>
 * Description: divide and conquer algorithm, divide the array into 2 halves
 * recursively and then merge the two sorted halves.
 */
public class MergeSort {

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
			// calculate middle
			int m = (l + r) / 2;

			// sort first partition
			sort(a, l, m);
			// sort second partition
			sort(a, m + 1, r);

			// merge sorted partitions
			merge(a, l, m, r);
		}
	}

	static void merge(int[] a, int l, int m, int r) {
		// create temporal arrays t1=[left, middle-1] t2=[middle, right]
		int[] t1 = new int[m - l + 1];
		int[] t2 = new int[r - m];

		// copy the left array elements
		for (int i = 0; i < t1.length; i++) {
			t1[i] = a[l + i];
		}
		// copy the right array elements
		for (int j = 0; j < t2.length; j++) {
			t2[j] = a[m + j + 1];
		}

		// merge the 2 partitions sorted
		int i = 0, j = 0, k = l;
		while (i < t1.length && j < t2.length) {
			if (t1[i] <= t2[j]) {
				a[k] = t1[i];
				k++;
				i++;
			} else {
				a[k] = t2[j];
				k++;
				j++;
			}
		}

		// copy the remaining elements from the left partition if any
		while (i < t1.length) {
			a[k] = t1[i];
			k++;
			i++;
		}

		// copy the remaining elements from the right partition if any
		while (j < t2.length) {
			a[k] = t2[j];
			k++;
			j++;
		}
	}

}
