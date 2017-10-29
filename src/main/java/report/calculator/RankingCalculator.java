package report.calculator;

import model.Instruction;
import model.Rank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class RankingCalculator implements ReportCalculator<List<Rank>>{

    public Map<LocalDate, List<Rank>> calculate(
            Set<Instruction> instructions, Predicate<Instruction> predicate)
    {
        final Map<LocalDate, List<Rank>> ranking = new HashMap<>();
        instructions.stream()
                .filter(predicate)
                .collect(groupingBy(Instruction::getSettlementDate, toSet()))
                .forEach((date, dailyInstructionSet) -> {

                    Map<Instruction, BigDecimal> amountMap = new HashMap<>();

                    dailyInstructionSet.stream()
                            .collect(groupingBy(Instruction::getEntity, toList()))
                            .forEach((entity, dailyEntitySet) -> {
                                BigDecimal totalAmount = dailyEntitySet.stream()
                                        .map(Instruction::getTotalAmount)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                amountMap.put(dailyEntitySet.get(0), totalAmount);
                            });

                    final AtomicInteger rank = new AtomicInteger(1);
                    final LinkedList<Rank> ranks = amountMap.keySet().stream()
                            .sorted((a, b) -> amountMap.get(b).compareTo(amountMap.get(a)))
                            .map(instruction -> new Rank(rank.getAndIncrement(), instruction.getEntity(), date))
                            .collect(toCollection(LinkedList::new));

                    ranking.put(date, ranks);
                });
        return ranking;
    }
}
