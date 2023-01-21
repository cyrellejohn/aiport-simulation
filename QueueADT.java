public interface QueueADT<T> {
   // Add an item at the back of the list
   public void enqueue(T item);
   
   // Delete an item at the front of the list
   public T dequeue();
   
   // Get the item on the back of the list
   public T back();
   
   // Get the item on the front of the list
   public T front();
   
   // Return true if list is empty
   public boolean isEmpty();
   
   // Print all items on the list
   public void print();
   
   // Get the number of items on the list
   public int size();
}