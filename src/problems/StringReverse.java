package problems;

/**
 * Time complexity: O(n) for iterative implementation, O(Log n) for recursive
 * implementation. </br>
 * Space complexity: O(n2) for both implementations and a call stack for the
 * recursive one. </br>
 * Description: iterate through the string in inverse order and append each
 * character to a new string, or extract the last character from the string
 * recursively.
 */
public class StringReverse {

	public static void main(String[] args) {
		System.out.println(reverse("avaj") + rreverse("dlrow "));
	}

	/**
	 * Iterative
	 */
	static String reverse(String str) {
		StringBuilder res = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			res.append(str.charAt(i));
		}
		return res.toString();
	}

	/**
	 * Recursive
	 */
	static String rreverse(String str) {
		if (str.length() <= 1) {
			return str;
		}
		return str.substring(str.length() - 1) + reverse(str.substring(0, str.length() - 1));
	}
}
