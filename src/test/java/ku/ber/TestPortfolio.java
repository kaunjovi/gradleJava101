package ku.ber;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPortfolio {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() throws ParseException {

		// TODO: It is quite irritating to have to start from setting up everything
		// every time. Find solution and put it up on net. Shortcut for logger, debug
		// etc. should be up there and ready to use.

		// Portfolio portolio = new SimplePortfolio();
		//
		// BigDecimal valueOfPortfolio = portolio.showValueInRupees();
		// assertTrue("The portfolio was supposed to have zero monertary value at this
		// point.",
		// valueOfPortfolio.intValue() == 0);
		//
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		// valueOfPortfolio = portolio.addSeedMoney(new BigDecimal(10000),
		// simpleDateFormat.parse("19770601"),
		// "Let the games begin");
		// logger.debug("Value of portfolio is {}.", valueOfPortfolio);
		// assertTrue("The portfolio was supposed to have 10k at this point.",
		// valueOfPortfolio.equals(new BigDecimal(10000)));

		Portfolio portfolio = new SimplePortfolio();

		List<String> transactions = new ArrayList<String>();
		transactions.add(" 'ADD_SEED_MONEY', '19770601', '10000'");
		transactions.add(" 'WITHDRAW_MONEY', '19770602', '5000'");
		transactions.add(" 'WITHDRAW_MONEY', '19770603', '5000'");
		transactions.add(" 'WITHDRAW_MONEY', '19770604', '5000'");

		int transactionCounter = 0;

		for (String transaction : transactions) {
			BigDecimal result = portfolio.transact(transaction);

			transactionCounter++;

			if (transactionCounter == 1) {
				logger.debug("The amount is {}.", result);
				assertTrue("10K was expected in the account.", result.equals(new BigDecimal(10000)));
			}

		}
	}

}
