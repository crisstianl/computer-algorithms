package problems;

public class StringReverse {

	public static void main(String[] args) {
		System.out.println(reverse("avaj"));
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
