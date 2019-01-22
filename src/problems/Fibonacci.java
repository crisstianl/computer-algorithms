package problems;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println("Fibonacci sequece: ");
		fibonacci(20);

		System.out.println("\n\nFibonacci sequece recursive: ");
		System.out.println(rfibonacci(20));
	}

	/**
	 * Fn = Fn-1 + Fn-2 </br>
	 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610
	 */
	static void fibonacci(int n) {
		int a = 0, b = 1;
		System.out.print(a + " " + b + " ");
		for (int i = 1; i <= n - 2; i++) {
			int c = a + b;
			a = b;
			b = c;
			System.out.print(c + " ");
		}
	}

	/**
	 * Recursive
	 */
	static int rfibonacci(int n) {
		int a = 0;
		if (n == 0) {
			a = 0;
		} else if (n == 1) {
			a = 1;
		} else {
			a = rfibonacci(n - 1) + rfibonacci(n - 2);
		}
		System.out.print(a + " ");
		return a;
	}
}
