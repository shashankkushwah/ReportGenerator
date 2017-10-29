package utils.date;

import java.time.LocalDate;
/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public interface IWorkingDays {
    LocalDate findFirstWorkingDate(LocalDate date);
}
