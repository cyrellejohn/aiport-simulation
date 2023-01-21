import java.util.Scanner;

public class ClientProgram {
   private Arrival arrival;
   private Departure departure;
   private Runway runway;
   
   private final String N = System.getProperty("line.separator");
   
   public ClientProgram() {
      arrival = new Arrival();
      departure = new Departure();
      runway = new Runway(arrival, departure);
   }
   
   public void start() {
      long endTime;
      long startTime;
      int time;
      
      Scanner input = new Scanner(System.in);
      
      System.out.print(N + "How Long Would You Like This Simulation to Run? ");
      while(!input.hasNextInt()) {
         System.out.print("How Long Would You Like This Simulation to Run? ");
         input.next();
      }
      time = input.nextInt();
      
      startTime = System.nanoTime();
      endTime = startTime + Data.toNano(time);
      
      System.out.println();
      
      runway.open();
      while (System.nanoTime() < endTime) {
         runway.start();
      }
      arrival.stop();
      departure.stop();
      runway.close();
   }
}