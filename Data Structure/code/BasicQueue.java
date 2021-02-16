package queue;

public class BasicQueue {
	
	class Queue{
		private int size = 0; 
		private int rear = -1; 
		private int front = -1;
		
		private Object queue[];

		Queue(int size) { 
		    this.size = size;
		    this.queue = new Object[size];
		}
		
		public boolean isEmpty() {
		    return front == rear;
		}
		
		public boolean isFull() {
		    return (rear == size-1);
		}
		
		public void enQueue(Object o) {
		    
		    if(isFull()) {
		        return;
		    }
		    
		    queue[++rear] = o;
		}
		
		public Object deQueue() {
		    
		    if(isEmpty()) { 
		        return null;
		    }
		    
		    Object o = queue[front];
		    queue[front++] = null;
		    return o;
		}
	}

}
