// Author: Cyrelle John A. Domingo
// Date Written: December 12, 2021

// Purpose: File Path Validator or Checker

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckPath {
   private String path;
   
   // Required variables to get the filepath depending on the OS
   private final String N = System.getProperty("line.separator"); // Same function as \n
   private final String SEPARATOR = System.getProperty("file.separator");
   private final String WORKING_DIR = System.getProperty("user.dir");
   private final String IMPORTANT_DIR = WORKING_DIR + SEPARATOR + "important"; // Directory of .txt files
      
   public String getFilePath() {
      return path;
   }
   
   // Set the file path
   public void setFilePath(String filename) {
      Path dir = Paths.get(IMPORTANT_DIR);
      Path filePath;
      
      try {
         // Create directory if not exists
         if (!Files.exists(dir)) {
            Files.createDirectory(dir);
         }
         
         path = IMPORTANT_DIR + SEPARATOR + filename;
         filePath = Paths.get(path);
         
         // Create file if not exists
         if (!Files.exists(filePath)) {
            Files.createFile(filePath);
         }
         
         // Return if file is empty
         if (Files.size(filePath) == 0) {
            return;
         }
      }
      
      catch (Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
}