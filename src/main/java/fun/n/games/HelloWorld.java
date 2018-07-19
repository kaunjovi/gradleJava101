package fun.n.games;

import static org.junit.Assert.assertTrue;

public class HelloWorld {
	public static void main(String[] args) {

		System.out.println("Hello world.");

		// This will throw a nice assertion error, if you like that sort of thing.
		assertTrue("This will break the flow.", false);

		System.out.println("Yo. You should not see me.");
	}
}
