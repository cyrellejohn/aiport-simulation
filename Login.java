import java.util.Scanner;
import javax.swing.JOptionPane;

public class Login {
   private final String ADMIN_PASS = "default";
   private final String ADMIN_USER = System.getProperty("user.name");
   private final String N = System.getProperty("line.separator");
   
   public void run() {
      String username;
      String password;
      Scanner input = new Scanner(System.in);
      
      System.out.println(N + "Airport Runway Simulation");
      System.out.print(N + "Enter username: ");
      username = input.nextLine();
      System.out.print("Enter password: ");
      password = input.nextLine();
      verifyLogin(username, password);
   }
   
   public void verifyLogin(String username, String password) {
      ClientProgram program = new ClientProgram();
      
      if (username.equals(ADMIN_USER) && password.equals(ADMIN_PASS)) {
         JOptionPane.showMessageDialog(null, "Welcome Admin!");
         program.start();
      }
      
      else {
         JOptionPane.showMessageDialog(null, "Try again!");
         run();
      }
   }
}