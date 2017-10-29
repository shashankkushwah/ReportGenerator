import model.Instruction;
import model.genrator.DataGenerator;
import report.ReportGenerator;

import java.util.Set;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public class Main {

    public static void main(String[] args) {

        Set<Instruction> data = DataGenerator.getData();

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.setRecords(data);
        reportGenerator.generate();
        String report = reportGenerator.getLastGeneratedReport();
        System.out.print(report);
    }
}
