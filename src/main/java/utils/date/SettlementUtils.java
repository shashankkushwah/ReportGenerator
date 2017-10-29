package utils.date;

import model.Instruction;

import java.time.LocalDate;
import java.util.Currency;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class SettlementUtils {

    public static void findSettlementDate(Instruction instruction) {
        // Select proper strategy based on the Currency
        final IWorkingDays workingDaysMechanism = getWorkingDaysStrategy(instruction.getCurrency());

        // find the correct settlement date
        final LocalDate newSettlementDate =
                workingDaysMechanism.findFirstWorkingDate(instruction.getSettlementDate());

        if (newSettlementDate != null) {
            // set the correct settlement date
            instruction.setSettlementDate(newSettlementDate);
        }
    }

    private static IWorkingDays getWorkingDaysStrategy(Currency currency) {
        IWorkingDays workingDays;
        switch (currency.getCurrencyCode()){
            case "AED":
                workingDays = ArabicWorkingDays.getInstance();
                break;
            case "SAR":
                workingDays = ArabicWorkingDays.getInstance();
                break;
            default:
                workingDays = EnglishWorkingDays.getInstance();
                break;
        }
        return workingDays;
    }
}
