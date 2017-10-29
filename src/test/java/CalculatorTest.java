import model.Instruction;
import model.Rank;
import model.TradeType;
import model.genrator.DataGenerator;
import org.junit.Before;
import org.junit.Test;
import report.calculator.RankingCalculator;
import report.calculator.TotalAmountCalculator;
import utils.date.SettlementUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CalculatorTest {

    private static final LocalDate MONDAY    = LocalDate.of(2017, 3, 20);
    private static final LocalDate TUESDAY   = LocalDate.of(2017, 3, 21);

    private Set<Instruction> mockData;

    @Before
    public void setUp() throws Exception {
        mockData = getMockData();
    }

    @Test
    public void testDailyIncomingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyIncomingAmount = new TotalAmountCalculator().calculate(mockData, RankingCalculator.incomingInstructionsPredicate);

        assertEquals(2, dailyIncomingAmount.size());
        assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY), BigDecimal.valueOf(2050.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyIncomingAmount.get(TUESDAY), BigDecimal.valueOf(2050.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyOutgoingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount = new TotalAmountCalculator().calculate(mockData, RankingCalculator.outgoingInstructionsPredicate);

        assertEquals(2, dailyOutgoingAmount.size());
        assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY), BigDecimal.valueOf(2050.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyOutgoingAmount.get(TUESDAY), BigDecimal.valueOf(2050.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
//        assertTrue(Objects.equals(dailyOutgoingAmount.get(WEDNESDAY), BigDecimal.valueOf(700.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyIncomingRanking() throws Exception {
        final Map<LocalDate, List<Rank>> dailyIncomingEntityRanks = new RankingCalculator().calculate(mockData, RankingCalculator.incomingInstructionsPredicate);

        assertEquals(2, dailyIncomingEntityRanks.size());

        assertEquals(1, dailyIncomingEntityRanks.get(MONDAY).size());
        assertEquals(1, dailyIncomingEntityRanks.get(TUESDAY).size());

        assertTrue(dailyIncomingEntityRanks.get(MONDAY).contains(new Rank(1, "Test Entity 2", MONDAY)));
        assertTrue(dailyIncomingEntityRanks.get(TUESDAY).contains(new Rank(1, "Test Entity 1", TUESDAY)));

    }

    @Test
    public void testDailyOutgoingRanking() throws Exception {
        final Map<LocalDate, List<Rank>> dailyOutgoingEntityRanks = new RankingCalculator().calculate(mockData, RankingCalculator.outgoingInstructionsPredicate);

        assertEquals(2, dailyOutgoingEntityRanks.size());

        assertEquals(1, dailyOutgoingEntityRanks.get(MONDAY).size());
        assertEquals(1, dailyOutgoingEntityRanks.get(TUESDAY).size());

        assertTrue(dailyOutgoingEntityRanks.get(MONDAY).contains(new Rank(1, "Test Entity 2", MONDAY)));
        assertTrue(dailyOutgoingEntityRanks.get(TUESDAY).contains(new Rank(1, "Test Entity 1", TUESDAY)));
    }


    private static Set<Instruction> getMockData() {
        final Set<Instruction> instructions = new HashSet<>();
        final Instruction mockInstruction1 = new Instruction(
                TradeType.BUY,
                100,
                "Test Entity 1",
                LocalDate.of(2017, 3, 20),
                LocalDate.of(2017, 3, 21),
                new BigDecimal(20.5), Currency.getInstance("SGD"), DataGenerator.exchangeRate(Currency.getInstance("SGD")));
        final Instruction mockInstruction2 = new Instruction(
                TradeType.BUY,
                100,
                "Test Entity 2",
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20),
                new BigDecimal(20.5), Currency.getInstance("SGD"), DataGenerator.exchangeRate(Currency.getInstance("SGD")));
        final Instruction mockInstruction3 = new Instruction(
                TradeType.SELL,
                100,
                "Test Entity 1",
                LocalDate.of(2017, 3, 20),
                LocalDate.of(2017, 3, 21),
                new BigDecimal(20.5), Currency.getInstance("SGD"), DataGenerator.exchangeRate(Currency.getInstance("SGD")));
        final Instruction mockInstruction4 = new Instruction(
                TradeType.SELL,
                100,
                "Test Entity 2",
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20),
                new BigDecimal(20.5), Currency.getInstance("SGD"), DataGenerator.exchangeRate(Currency.getInstance("SGD")));

        instructions.add(mockInstruction1);
        instructions.add(mockInstruction2);
        instructions.add(mockInstruction3);
        instructions.add(mockInstruction4);

        instructions.forEach(SettlementUtils::findSettlementDate);

        return instructions;
    }
}