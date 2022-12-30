package com.assessments.hcf;

public class HighestCommonFactr {

	public static void main(String[] args) {
		HighestCommonFactr highestCommonFactr = new HighestCommonFactr();

		int numbers[] = { 10, 20, 30, 40, 50 };

		int result = highestCommonFactr.highestCommonFactor(numbers);

		System.out.println(result);

	}

	/**
	 * This method is used to find the highest common factor of given array.
	 * 
	 * @param numbers as an integer array
	 * @return returns {@code highestCommonFactor} of an array.
	 */
	public int highestCommonFactor(int numbers[]) {
		int result = numbers[0];

		int size = numbers.length;
		for (int i = 1; i < size; i++) {
			result = gcd(numbers[i], result);
		}
		return result;
	}

	/**
	 * This method is used to find gcd of two numbers.
	 * 
	 * @param a Operand one
	 * @param b Operand two
	 * @return highest common factor of {@code a} and {@code b}
	 */
	private int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}

}