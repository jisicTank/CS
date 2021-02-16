package stack;

public class DynamicLinkedListStack {
	
	public class Node {

	    public int data;
	    public Node next;

	    public Node() {
	    }

	    public Node(int data) {
	        this.data = data;
	        this.next = null;
	    }
	}
	public class Stack {
	    private Node head;
	    private Node top;

	    public Stack() {
	        head = top = null;
	    }

	    private Node createNode(int data) {
	        return new Node(data);
	    }

	    private boolean isEmpty() {
	        return top == null ? true : false;
	    }

	    public void push(int data) {
	        if (isEmpty()) { // 스택이 비어있다면
	            head = createNode(data);
	            top = head;
	        }
	        else { //스택이 비어있지 않다면 마지막 위치를 찾아 새 노드를 연결시킨다.
	            Node pointer = head;

	            while (pointer.next != null)
	                pointer = pointer.next;

	            pointer.next = createNode(data);
	            top = pointer.next;
	        }
	    }

	    public int pop() {
	        int popData;
	        if (!isEmpty()) { // 스택이 비어있지 않다면!! => 데이터가 있다면!!
	            popData = top.data; // pop될 데이터를 미리 받아놓는다.
	            Node pointer = head; // 현재 위치를 확인할 임시 노드 포인터

	            if (head == top) // 데이터가 하나라면
	                head = top = null;
	            else { // 데이터가 2개 이상이라면
	                while (pointer.next != top) // top을 가리키는 노드를 찾는다.
	                    pointer = pointer.next;

	                pointer.next = null; // 마지막 노드의 연결을 끊는다.
	                top = pointer; // top을 이동시킨다.
	            }
	            return popData;
	        }
	        return -1; // -1은 데이터가 없다는 의미로 지정해둠.

	    }

	}


	public static void main(String[] args) {

	}

}
