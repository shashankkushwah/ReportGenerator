package model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class Instruction {

    private final TradeType tradeType;
    private final String entity;
    private final LocalDate instructionDate;
    private LocalDate settlementDate;

    private final BigDecimal unitPrice;
    private final Currency currency;
    private final BigDecimal agreedFx;
    private final long units;

    public Instruction(TradeType tradeType, long units, String entity, LocalDate instructionDate, LocalDate settlementDate, BigDecimal unitPrice, Currency currency, BigDecimal agreedFx) {
        this.tradeType = tradeType;
        this.units = units;
        this.entity = entity;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.unitPrice = unitPrice;
        this.currency = currency;
        this.agreedFx = agreedFx;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public String getEntity() {
        return entity;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public long getUnits() {
        return units;
    }

    public BigDecimal getTotalAmount(){
        final BigDecimal total = unitPrice.multiply(agreedFx).multiply(new BigDecimal(units));
        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(entity);
        sb.append("    ");
        sb.append(tradeType);
        sb.append("    ");
        sb.append(agreedFx);
        sb.append("    ");
        sb.append(currency.getCurrencyCode());
        sb.append("    ");
        sb.append(instructionDate);
        sb.append("    ");
        sb.append(settlementDate);
        sb.append("    ");
        sb.append(units);
        sb.append("    ");
        sb.append(unitPrice);
        sb.append("    ");
        sb.append(getTotalAmount());
        return sb.toString();
    }
}
