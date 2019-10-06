package problems;

public class Factorial {

	public static void main(String[] args) {
		System.out.println(factorial(4));
	}

	/**
	 * n! = 1 * 2 * ... * n </br>
	 * 10! = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10
	 */
	static int factorial(int n) {
		if (n < 2) {
			return 1;
		}
		return n * factorial(n - 1);
	}

}
