package utils.date;

import java.time.DayOfWeek;
/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class ArabicWorkingDays extends WorkingDays {

    private static ArabicWorkingDays instance = null;

    public static ArabicWorkingDays getInstance() {
        if (instance == null) {
            instance = new ArabicWorkingDays();
        }
        return instance;
    }

    private ArabicWorkingDays() {
        super();
    }

    @Override
    protected void setupWorkingDays() {
        this.isWorkingDayMap.put(DayOfWeek.SUNDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.FRIDAY, false); // in arabia those are not working days
        this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false); // in arabia those are not working days
    }
}
