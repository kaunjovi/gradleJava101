package ku.ber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePortfolio implements Portfolio {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private int seedMoneyAdditionCounter = 0;
	private BigDecimal money = new BigDecimal(0);
	private int transactionCounter = 0;
	private List<String> auditRegister = new ArrayList<String>();

	private static final String ADD_SEED_MONEY = "ADD_SEED_MONEY";

	@Override
	public BigDecimal showValueInRupees() {
		// TODO Add more than money. It needs to be the value of the assets, as per the
		// market rate, when this call is made.
		return money;
	}

	@Override
	public BigDecimal addSeedMoney(BigDecimal seedMoney, Date transactionDate, String... message) {
		seedMoneyAdditionCounter++;
		money = money.add(seedMoney);

		transactionCounter++;

		StringBuffer auditMessage = new StringBuffer();
		auditMessage.append(transactionCounter + ", ");
		auditMessage.append(transactionDate + ", ");
		auditMessage.append(ADD_SEED_MONEY + ", ");
		auditMessage.append(seedMoney + ", ");
		auditMessage.append(message + ", ");
		auditRegister.add(auditMessage.toString());

		return money;
	}

	@Override
	public BigDecimal transact(String transactionCommand) {

		String[] inputs = transactionCommand.split(",");

		String transactionType = inputs[0].trim().replace("\'", "").toUpperCase();

		if (transactionType.equals(ADD_SEED_MONEY)) {

			BigDecimal amount = new BigDecimal(inputs[2].trim().replace("\'", ""));
			money = money.add(amount);

		} else if (transactionType.equals("WITHDRAW_MONEY")) {
			BigDecimal amount = new BigDecimal(inputs[2].trim().replace("\'", ""));

			// TODO Dont just compare with the money available as liquid cash. Consider the
			// value held as assets as well.

			if (amount.compareTo(money) <= 0) {
				money = money.subtract(amount);
				logger.debug("{} was withdrawn. {} is available now.", amount, money);

			} else {
				logger.debug("Withdrawl attempted for {}. Avilable cash is {}. Try smaller withdrawl.", amount, money);
			}

		} else {
			logger.debug("Some unknown transaction type was requested. Can't handle this type. Yet.");
		}

		return money;
	}

}
