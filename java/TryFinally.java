package java;

public class TryFinally {

	public static void main(String[] args) throws Exception {
		try {
			System.out.println(test1());
		} catch (Throwable th) {
			th.printStackTrace(System.out);
		}

		try {
			System.out.println(test2());
		} catch (Throwable th) {
			th.printStackTrace(System.out);
		}

		try {
			System.out.println(test3());
		} catch (Throwable th) {
			th.printStackTrace(System.out);
		}

		try {
			System.out.println(test4());
		} catch (Throwable th) {
			th.printStackTrace(System.out);
		}

		try {
			System.out.println(test5());
		} catch (Throwable th) {
			th.printStackTrace(System.out);
		}

	}

	static String test1() throws Exception {
		try {
			throw new Exception("exception in try");
		} catch (Exception e) {
			throw new Exception("exception in catch");
		}
	}

	static String test2() throws Exception {
		try {
			throw new Exception("exception in try");
		} finally {
			throw new Exception("exception in finally");
		}
	}

	static String test3() throws Exception {
		try {
			throw new Exception("exception in try");
		} catch (Exception e) {
			throw new Exception("exception in catch");
		} finally {
			throw new Exception("exception in finally");
		}
	}

	static String test4() throws Exception {
		try {
			return "success try";
		} finally {
			throw new Exception("exception in catch");
		}
	}

	static String test5() throws Exception {
		try {
			return "success try";
		} finally {
			return "success finally";
		}
	}

}
