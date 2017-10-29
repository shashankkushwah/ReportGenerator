package utils.date;

import java.time.DayOfWeek;
/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class EnglishWorkingDays extends WorkingDays {

    private static EnglishWorkingDays instance = null;

    public static EnglishWorkingDays getInstance() {
        if (instance == null) {
            instance = new EnglishWorkingDays();
        }
        return instance;
    }

    private EnglishWorkingDays() {
        super();
    }

    @Override
    protected void setupWorkingDays() {
        this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.FRIDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false);
        this.isWorkingDayMap.put(DayOfWeek.SUNDAY, false);
    }
}