public static class RecursiveStuff {
	public static int lenStr(String s) {
		if (s.equals("")) {
			return 0;
		}
		else {
			return 1 + lenStr(s.substring(1));
		}
	}

	public static int fib(int n) {
		if (n < 2) {
			return 1;
		}

		else {
			return fib(n - 1) + fib(n - 2);
		}
	}

	public static int count(String s, char c) {
		if (s.equals("")) {
			return 0;
		}

		else if (s.charAt(0) == c) {
			return 1 + count(s.substring(1));
		}

		else {
			return count(s.substring(1));
		}
	}

	public static int bunnyEars2(int bunnies) {
		if (bunnies == 0) {
			return 0;
		}

		else if (bunnies % 2 == 0) {
			return 3 + bunnyEars2(bunnies - 1);
		}
		else {
			return 2 + bunnyEars2(bunnies - 1);
		}
	}

	private int strCountH(String original, String match, int length) {
		if (original.length() < length) {
			return 0;
		}

		else if (original.substring(0,length).equals(match)) {
			return 1 + strCountH(original.substring(length), match, length);
		}

		else {
			return strCountH(original.substring(length), match, length);
		}
	}

	public static int strCount(String original, String match) {
		return strCountH(original, match, match.length());
	}

	public static int sumDigits(int n) {
		if (n / 10 == 0) {
			return n;
		}

		else {
			return n % 10 + sumDigits(n / 10);
		}
	}

	public static String allStar(String str) {
		if (str.equals("")) {
			return str;
		}

		if (str.length() == 1) {
			return str;
		}

		else {
			return Character.toString(str.charAt(0)) + "*" + allStar(str.substring(1));
		}
	}
}
