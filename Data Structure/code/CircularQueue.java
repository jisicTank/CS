package queue;

public class Circular {
	public class Circular_Queue {
	    
	    int rear = 0;            //최초 0에서부터 시작
	    int front = 0;            //최초 0에서부터 시작
	    int maxsize = 0;        //배열의 크기
	    int[] circular_Queue;          //배열
	    
	    public Circular_Queue(int maxsize)
	    {
	        this.maxsize = maxsize;
	        circular_Queue = new int[this.maxsize];
	    }
	    
	    public boolean Isempty()    //배열이 공백 상태인지 체크하는 메서드입니다.
	    {
	        if(rear == front)
	        {
	            return true;
	        }
	        return false;
	    }
	    public boolean Isfull()        //배열이 포화 상태인지 체크하는 메서드입니다.
	    {
	        if((rear+1)%maxsize == front)
	        {
	            return true;
	        }
	        return false;
	    }
	    
	    public void EnQueue(int num)
	    {
	        if(Isfull())            //배열이 포화상태일경우
	        {
	            System.out.println("큐가 가득 찼습니다");
	        }
	        else                //배열이 포화상태가 아닐경우
	        {
	            rear = (rear+1) % maxsize;
	            circular_Queue[rear]=num;
	        }
	    }
	    
	public int DeQueue()
	    {
	        if(Isempty())         //배열이 공백상태이면 -1반환
	        {
	            return -1;
	        }
	        else                 //배열이 공백상태가 아니라면
	        {
	            front = (front+1)%maxsize;
	            return circular_Queue[front];
	        }
	    }
	}
	public static void main(String[] args) {

	}

}
