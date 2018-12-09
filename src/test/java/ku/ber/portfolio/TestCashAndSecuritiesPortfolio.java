package ku.ber.portfolio;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class TestCashAndSecuritiesPortfolio {

	@Test
	public void test() throws IOException {

		List<TestDataSet> testDataSets = TestDataSets
				.generateTestDataSets("src/test/resources/CashAndSecuritiesTxns.csv");

		for (TestDataSet testDataSet : testDataSets) {
			boolean matchTheBooks = TestDataSets.runTestTransactions(new CashOnlyPortfolio(), testDataSet);
			assertTrue("The cash does not match up.", matchTheBooks);

		}
	}

}
