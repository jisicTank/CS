package stack;


public class BasicStack {
	
	static int MAX_SIZE = 1000;
	static Object stack[];
	
	class Stack{
		private int sp = -1;
		
		public void push(Object o) {
		    if(isFull(o)) {
		        return;
		    }
		    
		    stack[++sp] = o;
		}
		
		public void pushDynamicArrayStack(Object o) {
		    
		    if(isFull(sp)) {
		        
		        Object[] arr = new Object[MAX_SIZE * 2];
		        System.arraycopy(stack, 0, arr, 0, MAX_SIZE);
		        stack = arr;
		        MAX_SIZE *= 2; // 2배로 증가
		    }
		    
		    stack[sp++] = o;
		}
		
		public Object pop() {
		    
		    if(isEmpty(sp)) {
		        return null;
		    }
		    
		    Object o = stack[sp--];
		    return o;
		    
		}
		
		private boolean isFull(Object o) {
			return sp + 1 == MAX_SIZE ? true : false;
		}
		
		private boolean isEmpty(int cnt) {
			return sp == -1 ? true : false;
		}
	}
	
	
	
	public static void main(String args[]){
		
	}
	
	
}
