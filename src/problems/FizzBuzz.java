package problems;

public class FizzBuzz {

	public static void main(String[] args) {
		fizzbuzz(100);
	}

	static void fizzbuzz(int max) {
		for (int i = 1; i <= max; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				System.out.print("FizzBuzz ");
			} else if (i % 5 == 0) {
				System.out.print("Buzz ");
			} else if (i % 3 == 0) {
				System.out.print("Fizz ");
			} else {
				System.out.print(i + " ");
			}
		}
	}

}
