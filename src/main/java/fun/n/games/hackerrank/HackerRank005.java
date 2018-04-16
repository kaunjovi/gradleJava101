package fun.n.games.hackerrank;

import java.util.Scanner;

public class HackerRank005 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		if (input >= 2 && input <= 20) {

			// System.out.println("The input is [" + input + "]");

			for (int counter = 1; counter >= 1 && counter <= 10; counter++) {
				System.out.println(input + " x " + counter + " = " + (input * counter));
			}
		} else {
			System.out.println("The input was outside the expected range. The input was [" + input + "]");
		}
	}
}
