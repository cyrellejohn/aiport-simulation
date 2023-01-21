import java.util.Random;
public class Data {
   private static String[] AIRLINE;
   private static int lineCountLimit;
   private static Random random = new Random();
   private static int totalMinute;
   
   
   // For runway interval
   private static double LANDING_INTERVAL;
   private static double TAKEOFF_INTERVAL;
   
   // For generating the next event
   private static double MEAN_LANDING_TIME;
   private static double MEAN_TAKEOFF_TIME;
   
   static {
      lineCountLimit = 100;
      setAirlineData();
      setOperationalData();
   }
   
   public static void addTotalTime(int time) {
      totalMinute = totalMinute + time;
   }
   
   public static Airline generateRandomAirline(int eventTime) {
      String flightID = AIRLINE[random.nextInt(AIRLINE.length)] + random.nextInt(1001);
      
      return new Airline(flightID, eventTime);
   }
   
   public static int getLandingInterval() {
      return (int) Math.round(LANDING_INTERVAL);
   }
   
   public static int getTakeoffInterval() {
      return (int) Math.round(TAKEOFF_INTERVAL);
   }
   
   public static int getTotalTime() {
      return totalMinute;
   }
   
   public static double getMeanTakeoffTime() {
      return MEAN_TAKEOFF_TIME;
   }
   
   public static double getMeanLandingTime() {
      return MEAN_LANDING_TIME;
   }
   
   public static int getLineCountLimit() {
      return lineCountLimit;
   }
   
   // Generates time of next event 
   public static int nextTime(double meanEventTime) {
      int nextTime;
      double randomDouble;
      
      randomDouble = random.nextDouble();
      
      // Exponential distribution formula
      nextTime = (int) Math.round(-Math.log(1.0 - randomDouble) / meanEventTime);
      
      return nextTime;
   }
   
   public static void reduceTotalTime(int time) {
      totalMinute = totalMinute - time;
   }
   
   private static void setAirlineData() {
      AIRLINE = File.getAirlineData();
   }
   
   private static void setOperationalData() {
      double[] data;
      
      data = File.getOperationalData();
      
      LANDING_INTERVAL = data[0];
      TAKEOFF_INTERVAL = data[1];
      MEAN_LANDING_TIME = data[2];
      
      if (data[3] == 0) {
         MEAN_TAKEOFF_TIME = data[2];
      }
      
      else {
         MEAN_TAKEOFF_TIME = data[3];
      }
   }
   
   public static long toMilli(long minute) {
      return minute * 500;
   }
   
   public static long toNano(long minute) {
      return minute * 60000000000L;
   }
}