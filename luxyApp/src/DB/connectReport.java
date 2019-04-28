package DB;
/**
 *
 * @author Sherry Zhuang
 */

//Imports
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class connectReport
{
    //Default directory
    static final String DIR_REPORT = "Reports/";

    public void exportReport(String getFile, String getType) throws JRException,
            ClassNotFoundException, SQLException
    {
        //Format date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YY-HHmm");
        LocalDateTime localDate = LocalDateTime.now();
        String date = dtf.format(localDate);
        
        //Input and output files
        String inputReport = getFile;
        String outputReport = "Reports/PDF/" + getType + "Report_" + date + ".pdf";

        //Compile jrxml file
        JasperReport jasperReport = JasperCompileManager.compileReport(inputReport);

        Connection con = DB.connectDB.getConnection();

        //Parameters
        Map<String, Object> parameters = new HashMap<>();

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, con);

        //Check output directory exists
        File outDir = new File(DIR_REPORT);
        outDir.mkdirs();

        //Export input
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(print);
        exporter.setExporterInput(exporterInput);

        //Export output
        OutputStreamExporterOutput exporterOutput
                = new SimpleOutputStreamExporterOutput(outputReport);
        exporter.setExporterOutput(exporterOutput);

        //Set configuration
        SimplePdfExporterConfiguration configuration
                = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        //Export report
        exporter.exportReport();
    }
} //End connectReport