package ku.ber.portfolio;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCashOnlyPortfolio {

	private static final Logger logger = LoggerFactory.getLogger(TestCashOnlyPortfolio.class);

	@Test
	public void cashOnlyPortfolio() throws IOException {

		List<TestDataSet> testDataSets = TestDataSets.generateTestDataSets("src/test/resources/CashOnlyTxns.csv");

		for (TestDataSet testDataSet : testDataSets) {
			boolean matchTheBooks = TestDataSets.runTestTransactions(new CashOnlyPortfolio(), testDataSet);
			assertTrue("The cash does not match up.", matchTheBooks);

		}

	}

}
