import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.nio.charset.Charset;

public class File {
   private static String[] airline;
   private static int logFileLineCount;
   private static String logPath;
   private static double[] operationalData;
   
   static {
      setAirlineData("airline.txt");
      setLogData("log.txt");
      setOperationalData("operational_data.txt");
      
      emptyLog();
   }
   
   public static void addLog(String log) {
      if (logFileLineCount == Data.getLineCountLimit()) {
         return;
      }
      
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath, Charset.forName("UTF-8"), true))) {
         writer.write(log, 0, log.length());
      }
      
      catch (Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
      
      logFileLineCount++;
   }
   
   private static void emptyLog() {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath, Charset.forName("UTF-8")))) {
         writer.write("");
      }
      
      catch (Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
   
   public static String[] getAirlineData() {
      return airline;
   }
   
   private static int getAllLineCount(String filePath) {
      int lineCount = 0;
      
      /* Uses try-with-resources statement to close resource automatically and FileReader is used with explicit 
         character enconding to avoid problems related to character encoding*/
      try (LineNumberReader reader = new LineNumberReader(new FileReader(filePath, Charset.forName("UTF-8")))) {
         reader.skip(Short.MAX_VALUE);
         lineCount = reader.getLineNumber();
      }
      
      catch (Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
      
      return lineCount;
   }
   
   public static double[] getOperationalData() {
      return operationalData;
   }
   
   private static void setAirlineData(String filename) {
      CheckPath check = new CheckPath();
      check.setFilePath(filename); // File input
      String filePath = check.getFilePath();
      
      try (LineNumberReader reader = new LineNumberReader(new FileReader(filePath, Charset.forName("UTF-8")))) {
         String currentLine;
         int line = 0;
         int totalLineCount = getAllLineCount(filePath);
         airline = new String[totalLineCount];
         
         while ((currentLine = reader.readLine()) != null) {
            airline[line] = currentLine;
            line++;
         }
      }
      
      catch (Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
   
   private static void setLogData(String filename) {
      CheckPath check = new CheckPath();
      check.setFilePath(filename);
      
      logPath = check.getFilePath();
   }
   
   private static void setOperationalData(String filename) {
      CheckPath check = new CheckPath();
      check.setFilePath(filename);
      operationalData = new double[4];
      
      try (LineNumberReader reader = new LineNumberReader(new FileReader(check.getFilePath(), Charset.forName("UTF-8")))) {
         String currentLine;
         int i = 0;

         // Assign each line in the file to currentLine
         while ((currentLine = reader.readLine()) != null) {
            // Separate currentLine when encountered with ', ' and assign to stringArr
            String[] stringArr = currentLine.split(", ");
            
            for (String key : stringArr) {
               operationalData[i] = Double.parseDouble(key);
               
               i++;
            }
         }
      }
      
      catch (Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
}