import model.Instruction;
import model.TradeType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionTest {

    @Test
    public void testTradeAmountCalc() throws Exception {
        final BigDecimal agreedFx = BigDecimal.valueOf(0.50);
        final BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        final int units = 200;

        final Instruction mockInstruction = new Instruction(
                TradeType.BUY,
                units,
                "Test Entity",
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20), // Its a Monday
                pricePerUnit, Currency.getInstance("SGD"), agreedFx);

        // test initialization
        assertEquals(agreedFx, mockInstruction.getAgreedFx());
        assertEquals(pricePerUnit, mockInstruction.getUnitPrice());
        assertEquals(units, mockInstruction.getUnits());

        final BigDecimal correct = pricePerUnit
                                    .multiply(agreedFx)
                                    .multiply(BigDecimal.valueOf(units))
                                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(correct, mockInstruction.getTotalAmount());
    }
}