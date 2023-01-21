// Queue implementation using Singly Linked List
public class DynamicQueue<T> implements QueueADT<T> {
   LinkedList<T> list;
   
   public DynamicQueue() {
      list = new LinkedList<T>();
   }
   
   // Basic Operations
   
   @Override
   public void enqueue(T item) {
      list.addLast(item);  
   }
   
   @Override
   public T dequeue() {
      if (isEmpty() == true) {
         throw new IndexOutOfBoundsException("Queue is Empty");
      }
      
      T value = front();
      list.deleteFirst();
      
      return value;
   }
   
   // Supplementary Operations
   
   @Override
   public T back() {
      return list.getTail();
   }
   
   @Override
   public T front() {
      return list.getHead();
   }
   
   @Override
   public boolean isEmpty() {
      return list.getSize() == 0;
   }
   
   @Override
   public void print() {
      list.print();
   }
   
   @Override
   public int size() {
      return list.getSize();
   }
}