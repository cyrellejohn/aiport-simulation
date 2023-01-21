import javax.swing.JOptionPane;

public class StartHere {
   public static void main(String[] args) {
      String adminUser = System.getProperty("user.name");
      Login login = new Login();
      
      JOptionPane.showMessageDialog(null, "Admin Username: " + adminUser);
      JOptionPane.showMessageDialog(null, "Admin Password: default");
      login.run();
      
      JOptionPane.showMessageDialog(null, "Simulation Over");
   }
}