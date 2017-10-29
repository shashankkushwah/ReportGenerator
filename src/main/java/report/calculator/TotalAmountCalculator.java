package report.calculator;

import model.Instruction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class TotalAmountCalculator implements ReportCalculator<BigDecimal> {

    public Map<LocalDate, BigDecimal> calculate(
            Set<Instruction> instructions, Predicate<Instruction> predicate)
    {
        return instructions.stream()
                .filter(predicate)
                .collect(groupingBy(Instruction::getSettlementDate,
                        mapping(Instruction::getTotalAmount,
                                reducing(BigDecimal.ZERO, BigDecimal::add))));
    }
}
