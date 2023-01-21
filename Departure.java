public class Departure {
   private Airline airline;
   
   public void addFlight() {
      String addFlight = "Flight " + airline.getFlightID() + " Entered The Departure Queue";
      airline.setExitTime(Data.getTotalTime());
      log(addFlight);
   }
   
   public void generateAirline() {
      String display;
      int nextDeparture;
      
      nextDeparture = Data.nextTime(Data.getMeanTakeoffTime());
      display = "Time of Next Departure: " + nextDeparture + " minutes";
      log(display);
      
      airline = Data.generateRandomAirline(nextDeparture);
   }
   
   public Airline getAirline() {
      return airline;
   }
   
   private void log(String message) {
      String log = String.format("Minute %3d - [DEP]: %s%n",  Data.getTotalTime(), message);
      
      System.out.print(log);
      File.addLog(log);
   }
   
   public void stop() {
      log("No More Scheduled Departures");
   }
}