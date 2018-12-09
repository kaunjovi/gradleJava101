package ku.ber.portfolio;

import java.math.BigDecimal;

public interface Portfolio {

	public BigDecimal transact(String transaction);

	public BigDecimal fetchLiquidCashValue();

}
