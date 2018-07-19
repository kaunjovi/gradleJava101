package fun.n.games.learn.lambda;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

interface FunctionalInterface {
	void abstractMethod();
	// you can't have more than one abstract method in an interface.
	// if you do it will not compile.
	// void abstractMaths();
}

interface AnotherFunctionalInterface {
	int doSomeMaths(int x, int y);
}

public class TestLambda001 {

	@Test
	public void test() {

		FunctionalInterface f001 = () -> System.out.println("Hello world from lambda.");
		f001.abstractMethod();

		FunctionalInterface f002 = () -> System.out.println("And we can change the implementation");
		f002.abstractMethod();

		AnotherFunctionalInterface f003 = (x, y) -> {
			System.out.println("Adding " + x + " and " + y + ".");
			return x + y;
		};

		assertTrue(f003.doSomeMaths(10, 20) == 30);

		AnotherFunctionalInterface f004 = (x, y) -> {
			System.out.println("Subtracting " + y + " from " + x + ".");
			return x - y;
		};
		assertTrue(f004.doSomeMaths(20, 10) == 10);
	}

}
