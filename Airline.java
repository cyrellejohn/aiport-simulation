public class Airline {
   private int delayTime;
   private int exitTime;
   private String flightID;
   private int scheduledTime;
   
   public Airline(String randomID, int randomTime) {
      flightID = randomID;
      scheduledTime = randomTime;
   }
   
   public int getDelayTime() {
      return delayTime;
   }
   
   public int getExitTime() {
      return exitTime;
   }
   
   public String getFlightID() {
      return flightID;
   }
   
   public int getScheduledTime() {
      return scheduledTime;
   }
   
   public void setDelayTime(int time) {
      delayTime = exitTime - time;
   }
   
   public void setExitTime(int time) {
      exitTime = time;
   }
   
   public void updateScheduledTime(int time) {
      scheduledTime = scheduledTime - time;
   }
}