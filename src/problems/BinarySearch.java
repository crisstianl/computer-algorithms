package problems;

/**
 * Time complexity: O(Log n). </br>
 * Space complexity: O(1) for iterative implementation, O(Log n) for recursive
 * call stack. </br>
 * Description: search a sorted array by repeatedly dividing the search interval
 * in half. If the value searched is less than the middle value then search the
 * left half, otherwise search the right half. Repeat the process until the
 * value is found or the array is empty.
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println("Found 7 in array at index " + search(a, 7));
		System.out.println("Found 17 in array at index " + rsearch(a, 17, 0, a.length - 1));
	}

	static int search(int a[], int v) {
		int l = 0, r = a.length - 1;
		while (l <= r) {
			// calculate the middle
			int m = l + (r - l) / 2;

			if (v == a[m]) { // element is the middle value
				return m;
			} else if (v < a[m]) { // element is smaller than middle value, then search the left array
				r = m - 1;
			} else { // element is greater than middle value, then search the right array
				l = m + 1;
			}
		}

		// value not found, return -1
		return -1;
	}

	static int rsearch(int[] a, int v, int l, int r) {
		// recursive base condition
		if (r >= l) {
			// calculate the middle
			int m = l + (r - l) / 2;

			if (v == a[m]) { // element is the middle value
				return m;
			} else if (v < a[m]) { // element is smaller than middle value, then search the left array
				return rsearch(a, v, l, m - 1);
			} else { // element is greater than middle value, then search the right array
				return rsearch(a, v, m + 1, r);
			}
		}

		// value not found, return -1
		return -1;
	}
}
