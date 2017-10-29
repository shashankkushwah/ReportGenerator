package report.calculator;

import model.Instruction;
import model.TradeType;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public interface ReportCalculator<T> {

    // Create a predicate for incoming
    Predicate<Instruction> incomingInstructionsPredicate = instruction -> instruction.getTradeType().equals(TradeType.SELL);

    // Create a predicate for outgoing
    Predicate<Instruction> outgoingInstructionsPredicate = instruction -> instruction.getTradeType().equals(TradeType.BUY);

    Map<LocalDate, T> calculate(Set<Instruction> instructions, Predicate<Instruction> predicate);
}
