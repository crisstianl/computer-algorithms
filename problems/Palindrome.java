package problems;

public class Palindrome {

	public static void main(String[] args) {
		System.out.println("Level = " + palindrome("level"));

	}

	static boolean palindrome(String str) {
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

}
