public class Runway {
   private Arrival arrival;
   private boolean continueRun;
   private Departure departure;
   private int runwayNextTime;
   private boolean stop;
   
   private DynamicQueue<Airline> arrivalQueue;
   private DynamicQueue<Airline> departureQueue;
   
   public Runway(Arrival arrivalFlight, Departure takeoffFlight) {
      arrival = arrivalFlight;
      departure = takeoffFlight;
      continueRun = false;
      stop = false;
      
      arrivalQueue = new DynamicQueue<>();
      departureQueue = new DynamicQueue<>();
   }
   
   private void checkRunway() {
      int runwayTime;
      int scheduledArrivalTime = arrival.getAirline().getScheduledTime() + Data.getTotalTime();
      int scheduledDepartureTime = departure.getAirline().getScheduledTime() + Data.getTotalTime();
      
      while (runwayNextTime < scheduledArrivalTime && runwayNextTime < scheduledDepartureTime || continueRun == true) {         
         stop = false;
         
         runwayTime = runwayNextTime - Data.getTotalTime();
         if (runwayTime < 0) {
            runwayNextTime = Data.getTotalTime();
            runwayTime = 0;
         } 
         pause(runwayTime);
              
         Data.addTotalTime(runwayTime);
         updateRunway();
         Data.reduceTotalTime(runwayTime);
         
         if (stop == true) {
            break;
         }
         
         scheduledArrivalTime = arrival.getAirline().getScheduledTime() + Data.getTotalTime();
         scheduledDepartureTime = departure.getAirline().getScheduledTime() + Data.getTotalTime();
      }
   }
   
   public void close() {
      continueRun = true;
      checkRunway();
   }
   
   private void log(String message) {
      String log = String.format("Minute %3d - [RWY]: %s%n",  Data.getTotalTime(), message);
      
      System.out.print(log);
      File.addLog(log);
   }
   
   public void open() {
      int arrivalTime;
      int departureTime;
      
      log("Runway is Open");
      arrival.generateAirline();
      departure.generateAirline();
      
      arrivalTime = arrival.getAirline().getScheduledTime();
      departureTime = departure.getAirline().getScheduledTime();
      
      if (arrivalTime <= departureTime) {
         runwayNextTime = arrivalTime;
      }
      
      else {
         runwayNextTime = departureTime;
      }
   }
   
   private void pause(int time) {
      try {
         Thread.sleep(Data.toMilli(time));
      }
      
      catch (InterruptedException e) {
      
      }
   }
   
   public void start() {
      int arrivalTime = arrival.getAirline().getScheduledTime();
      int departureTime = departure.getAirline().getScheduledTime();
         
      while (arrivalTime <= departureTime) {
         checkRunway();
         departure.getAirline().updateScheduledTime(arrivalTime); 
         
         pause(arrivalTime);
         Data.addTotalTime(arrivalTime);
         arrival.addFlight();  
         arrivalQueue.enqueue(arrival.getAirline());
         arrival.generateAirline();
              
         arrivalTime = arrival.getAirline().getScheduledTime();
         departureTime = departure.getAirline().getScheduledTime();
      }
      
      while (departureTime < arrivalTime) {
         checkRunway();
         arrival.getAirline().updateScheduledTime(departureTime);
                 
         pause(departureTime);  
         Data.addTotalTime(departureTime);
         departure.addFlight();    
         departureQueue.enqueue(departure.getAirline());
         departure.generateAirline();
                  
         arrivalTime = arrival.getAirline().getScheduledTime();
         departureTime = departure.getAirline().getScheduledTime();
      }
   }
   
   private String updateAirlineStatus(int time) {
      if (time >= -15) {
         return "On Time";
      }
      
      return "Delayed";
   }
   
   public void updateRunway() {
      int delayTime;
      String flightID;
      String runway;
      String status;
      
      if (!arrivalQueue.isEmpty()) {
         int arrivalExitTime = arrivalQueue.front().getExitTime();
         arrivalQueue.front().setDelayTime(Data.getTotalTime());
         delayTime = arrivalQueue.front().getDelayTime();
         flightID = arrivalQueue.front().getFlightID();
         status = updateAirlineStatus(delayTime);
         
         runway = "Flight " + flightID + " Landed " + status + " | Scheduled Arrival Time: " + arrivalExitTime + " minutes | Delay: " + Math.abs(delayTime) + " minutes";
         arrivalQueue.dequeue();
         log(runway);
         
         runwayNextTime = runwayNextTime + Data.getLandingInterval();
      }
      
      else if (arrivalQueue.isEmpty() && !departureQueue.isEmpty()) {
         int departureExitTime = departureQueue.front().getExitTime();
         departureQueue.front().setDelayTime(Data.getTotalTime());
         delayTime = departureQueue.front().getDelayTime();
         flightID = departureQueue.front().getFlightID();
         status = updateAirlineStatus(delayTime);
         
         runway = "Flight " + flightID + " Landed " + status + " | Scheduled Takeoff Time: " + departureExitTime + " minutes | Delay: " + Math.abs(delayTime) + " minutes";
         departureQueue.dequeue();
         log(runway);
         
         runwayNextTime = runwayNextTime + Data.getTakeoffInterval();
      }
      
      else {
         stop = true;
      }
   }
}