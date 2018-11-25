package ku.ber;

import java.math.BigDecimal;
import java.util.Date;

public interface Portfolio {

	BigDecimal showValueInRupees();

	BigDecimal addSeedMoney(BigDecimal seedMoney, Date transactionDate, String... message);

	BigDecimal transact(String transaction);

}
