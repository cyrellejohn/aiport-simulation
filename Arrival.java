public class Arrival {
   private Airline airline;
   
   public void addFlight() {
      String addFlight = "Flight " + airline.getFlightID() + " Entered The Arrival Queue";
      airline.setExitTime(Data.getTotalTime());
      log(addFlight);
   }
   
   public void generateAirline() {
      String display;
      int nextArrival;
      
      nextArrival = Data.nextTime(Data.getMeanLandingTime());
      display = "Time of Next Arrival: " + nextArrival + " minutes";
      log(display);
      
      airline = Data.generateRandomAirline(nextArrival);
   }
   
   public Airline getAirline() {
      return airline;
   }
   
   private void log(String message) {
      String log = String.format("Minute %3d - [ARR]: %s%n",  Data.getTotalTime(), message);
      
      System.out.print(log);
      File.addLog(log);
   }
   
   public void stop() {
      log("No More Scheduled Arrivals");
   }
}