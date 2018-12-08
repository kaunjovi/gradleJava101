package fun.n.games.portfolio;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CashOnlyPortfolio implements Portfolio {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private BigDecimal cash = new BigDecimal("0");

	@Override
	public BigDecimal transact(String transaction) {
		String[] args = transaction.split(",");

		if (args[0].trim().toUpperCase().equals("DEPOSIT")) {

			BigDecimal amount = new BigDecimal(args[1].trim().toUpperCase());
			cash = cash.add(amount);
			logger.debug("Added {} to portfolio. Now the value of cash is {}.", amount, cash);

		} else if (args[0].trim().toUpperCase().equals("WITHDRAW")) {

			BigDecimal amount = new BigDecimal(args[1].trim().toUpperCase());
			cash = cash.subtract(amount);
			logger.debug("Added {} to portfolio. Now the value of cash is {}.", amount, cash);
		} else {
			logger.debug("An unsupported transaction was attempted [{}]", transaction);
		}

		return fetchLiquidCashValue();
	}

	@Override
	public BigDecimal fetchLiquidCashValue() {

		return cash;
	}

}
