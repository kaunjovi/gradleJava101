package fun.n.games.portfolio;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPortfolio {

	private static final Logger logger = LoggerFactory.getLogger(TestPortfolio.class);

	@Test
	public void testDepositOnlyTransactionOnCashOnlyPortfolio() throws IOException {

		boolean matchTheBooks;

		List<TestDataForPortfolio> testDataSetsForPortfolio = TestDataForPortfolio
				.createTestDataSetFromCSV("src/test/resources/CashOnlyTxns.csv");

		for (TestDataForPortfolio testDataForPortfolio : testDataSetsForPortfolio) {
			matchTheBooks = runTestTransactions(new CashOnlyPortfolio(), testDataForPortfolio);
			assertTrue("The cash does not match up.", matchTheBooks);

		}

	}

	private boolean runTestTransactions(Portfolio myPortfolio, TestDataForPortfolio testDataForPortfolio) {
		BigDecimal expectedCashInPortfolio = testDataForPortfolio.expectedCashInPortfolio;

		BigDecimal cashInPortfolio = new BigDecimal("0.0");
		for (String transaction : testDataForPortfolio.transactions) {
			cashInPortfolio = myPortfolio.transact(transaction);
		}

		logger.debug("The portfolio has {} in liquid cash. It is expected to have {}.", cashInPortfolio,
				expectedCashInPortfolio);

		boolean matchTheBooks = cashInPortfolio.equals(expectedCashInPortfolio);
		return matchTheBooks;
	}
}
