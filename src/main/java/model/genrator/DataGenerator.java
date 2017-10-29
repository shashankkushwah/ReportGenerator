package model.genrator;

import model.Instruction;
import model.TradeType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class DataGenerator {

    private static final int NO_OF_DAYS = 10;
    private static final int MAX_RECORD_PER_DAY = 100;
    private static final int MIN_RECORD_PER_DAY = 1;
    private static final LocalDate END_DATE = LocalDate.now().minusWeeks(1);
    private static final LocalDate START_DATE = END_DATE.minusDays(NO_OF_DAYS);

    public static Set<Instruction> getData(){
        Set<Instruction> data = new HashSet<>();
        long seed = 234898023L;
        Random random = new Random(seed);
        for (int i = 0; i < NO_OF_DAYS; i++) {

            final int noOfRecords = MIN_RECORD_PER_DAY + random.nextInt(MAX_RECORD_PER_DAY - MIN_RECORD_PER_DAY);
            for (int j = 0; j < noOfRecords; j++) {

                TradeType tradeType = random.nextBoolean() ? TradeType.BUY : TradeType.SELL;
                int entityId = random.nextInt();
                String entity = entityAtIndex(entityId);

                int currencyId = random.nextInt();
                Currency currency = currencyAtIndex(currencyId);

                BigDecimal unitPrice = new BigDecimal(1 + random.nextDouble() * 100);
                BigDecimal rate = exchangeRate(currency);

                Instruction instruction = new Instruction(tradeType, random.nextInt(100), entity, START_DATE.minusDays(i), START_DATE.minusDays(i + random.nextInt(2)) , unitPrice, currency, rate);
                data.add(instruction);
            }
        }
        return data;
    }

    private static String entityAtIndex(int index){
        String entity;
        index = Math.max(0, index);
        index = index % 7;
        switch (index){
            case 1: entity = "Entity1"; break;
            case 2: entity = "Entity2"; break;
            case 3: entity = "Entity3"; break;
            case 4: entity = "Entity4"; break;
            case 5: entity = "Entity5"; break;
            case 6: entity = "Entity6"; break;
            default: entity = "Entity0"; break;
        }
        return entity;
    }

    private static Currency currencyAtIndex(int index){
        Currency currency;
        index = Math.max(0, index);
        index = index % 4;
        switch (index){
            case 1: currency = Currency.getInstance("EUR"); break;
            case 2: currency =  Currency.getInstance("USD"); break;
            case 3: currency =  Currency.getInstance("SAR"); break;
            default: currency =  Currency.getInstance("AED"); break;
        }
        return currency;
    }

    public static BigDecimal exchangeRate(Currency currency){
        BigDecimal rate;
        switch (currency.getCurrencyCode()){
            case "EUR": rate = new BigDecimal(0.67); break;
            case "AED": rate = new BigDecimal(0.22); break;
            case "SGP": rate = new BigDecimal(0.50); break;
            default: rate = new BigDecimal(1); break;
        }
        return rate;
    }
}
