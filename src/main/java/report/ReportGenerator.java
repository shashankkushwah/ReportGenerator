package report;

import model.Instruction;
import model.Rank;
import report.calculator.RankingCalculator;
import report.calculator.ReportCalculator;
import report.calculator.TotalAmountCalculator;
import utils.date.SettlementUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class ReportGenerator {

    private StringBuilder stringBuilder;
    private Set<Instruction> records;

    public ReportGenerator(){
    }

    public void setRecords(Set<Instruction> records) {
        this.records = records;
    }

    public Set<Instruction> getRecords() {
        return records;
    }

    public String generate(){
        if(records == null){
            throw new NullPointerException("Records not found");
        }
        this.stringBuilder = new StringBuilder();
        records.forEach(SettlementUtils::findSettlementDate);
        totalSellDailyReport(records);
        totalBuyDailyReport(records);
        sellerRankingReport(records);
        buyerRankingReport(records);
        return stringBuilder.toString();
    }

    public String getLastGeneratedReport(){
        return stringBuilder != null? stringBuilder.toString() : null;
    }

    private StringBuilder totalSellDailyReport(Set<Instruction> records) {
        final Map<LocalDate, BigDecimal> totalSellingData = new TotalAmountCalculator().calculate(records, ReportCalculator.outgoingInstructionsPredicate);

        stringBuilder
                .append("\n\n----- Daily Buy Report -----\n\n")
                .append("  Date       Amount\n\n");

        for (LocalDate date : totalSellingData.keySet()) {
            stringBuilder
                    .append(date)
                    .append(" -> $")
                    .append(totalSellingData.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private StringBuilder totalBuyDailyReport(Set<Instruction> records) {
        final Map<LocalDate, BigDecimal> totalBuyingData = new TotalAmountCalculator().calculate(records, ReportCalculator.incomingInstructionsPredicate);

        stringBuilder
                .append("\n\n ----- Daily Sell Report -----\n\n")
                .append("  Date       Amount\n\n");

        for (LocalDate date : totalBuyingData.keySet()) {
            stringBuilder
                    .append(date)
                    .append(" -> $")
                    .append(totalBuyingData.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private StringBuilder sellerRankingReport(Set<Instruction> instructions) {
        final Map<LocalDate, List<Rank>> sellerRankingData =
                new RankingCalculator().calculate(instructions, ReportCalculator.outgoingInstructionsPredicate);

        stringBuilder
                .append("\n\n   -----  Seller Ranking Report -----\n\n")
                .append("Rank          Date            Seller\n");

        for (LocalDate date : sellerRankingData.keySet()) {
            for (Rank rank : sellerRankingData.get(date)) {
                stringBuilder
                        .append(" ")
                        .append(rank.getRank()).append("         ")
                        .append(date).append("         ")
                        .append(rank.getEntity()).append("\n");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder;
    }

    private StringBuilder buyerRankingReport(Set<Instruction> instructions) {
        final Map<LocalDate, List<Rank>> buyerRankingData =
                new RankingCalculator().calculate(instructions, ReportCalculator.incomingInstructionsPredicate);

        stringBuilder
                .append("\n\n   -----  Buyer Ranking Report -----\n\n")
                .append("Rank          Date             Buyer\n");

        for (LocalDate date : buyerRankingData.keySet()) {
            for (Rank rank : buyerRankingData.get(date)) {
                stringBuilder
                        .append(" ")
                        .append(rank.getRank()).append("         ")
                        .append(date).append("         ")
                        .append(rank.getEntity()).append("\n");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder;
    }
}
