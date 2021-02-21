package binarySearchTree;

// 출처 : https://github.com/gyoogle/tech-interview-for-developer

public class BinarySearchTree {
	
	public class Node {
		private int data;
		private Node left;
		private Node right;
		
		public Node(int data) {
			this.setData(data);
			setLeft(null);
			setRight(null);
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
	}
	
	public Node root;
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	//탐색 연산
	public boolean find(int id){
		Node current = root;
		while(current!=null){
			//현재 노드와 찾는 값이 같으면
			if(current.getData()==id){
				return true;
				//찾는 값이 현재 노드보다 작으면
			} else if(current.getData()>id){
				current = current.getLeft();
				//찾는 값이 현재 노드보다 크면
			} else{
				current = current.getRight();
			}
		}
		return false;
	}
	
	//삭제 연산
	public boolean delete(int id){
		Node parent = root;
		Node current = root;
		
		// parent 기준 제거 대상 노드의 위치 좌측인지, 우측인지 판별
		boolean isLeftChild = false;
		
		// 삭제 대상 노드까지 찾아간다.
		while(current.getData()!=id){
			parent = current;
			// 입력값이 현재 위치 노드 데이터보다 작으면 왼쪽으로 이동
			if(current.getData()>id){
				isLeftChild = true;
				current = current.getLeft();
			}else{
				// 입력값이 현재 위치 노드 데이터보다 크면 오른쪽으로 이동 
				isLeftChild = false;
				current = current.getRight();
			}
			if(current==null){
				return false;
			}
		}
		
		//Case 1: 자식노드가 없는 경우
		if(current.getLeft()==null && current.getRight()==null){
			if(current==root){
				root = null;
			}
			// 삭제 대상 노드가 parent의 왼쪽 자식이면 그냥 지워주기만 하면 됌
			if(isLeftChild==true){
				parent.setLeft(null);
			}else{
				// 삭제 대상 노드가 parent의 오른쪽 자식이면 그냥 지워주기만 하면 됌
				parent.setRight(null);
			}
		}
		
		//Case 2 : 하나의 자식을 갖는 경우
		else if(current.getRight()==null){
			// 2 - 1) 하나의 자식이 왼쪽 자식인 경우 
			if(current==root){
				root = current.getLeft();
			}else if(isLeftChild){
				// 삭제 대상 노드가 parent의 좌측이면, 그 자리에
				// 삭제대상 노드의 왼쪽 자식(왼쪽 밖에 없으므로)으로 교체
				parent.setLeft(current.getLeft());
			}else{
				// 삭제 대상 노드가 parent의 우측이면, 그 자리에 
				// 삭제대상 노드의 왼쪽 자식(왼쪽 밖에 없으므로)으로 교체
				parent.setRight(current.getLeft());
			}
		} else if(current.getLeft()==null){
			// 2 - 2) 하나의 자식이 오른쪽 자식인 경우
			if(current==root){
				root = current.getRight();
			}else if(isLeftChild){
				// 삭제 대상 노드가 parent의 좌측이면, 그 자리에
				// 삭제대상 노드의 오른쪽 자식(오른쪽 밖에 없으므로)으로 교체
				parent.setLeft(current.getRight());
			}else{
				// 삭제 대상 노드가 parent의 우측이면, 그 자리에
				// 삭제대상 노드의 오른쪽 자식(오른쪽 밖에 없으므로)으로 교체
				parent.setRight(current.getRight());
			}
		}
		
		//Case 3 : 두개의 자식을 갖는 경우
		else if(current.getLeft()!=null && current.getRight()!=null){
			// 오른쪽 서브트리의 최소값을 찾음
			Node successor = getSuccessor(current);
			
			// 찾은 오른쪽 서브트리 최솟값을 제거 대상 노드 위치에 저장 
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.setLeft(successor);
			}else{
				parent.setRight(successor);
			}
			
			// 대체한 노드의 왼쪽 노드도 원래 노드의 왼쪽과 연결
			successor.setLeft(current.getLeft());
		}		
		return true;		
	}

	// 오른쪽 자식 노드 subTree중 최솟값 탐색
	public Node getSuccessor(Node deleleNode){
		Node current = deleleNode.getRight();
		Node successsor =null;
		Node successsorParent =null;
		
		// 비어 있는 위치까지 계속 왼쪽 자식노드를 타고 내려감
		// 비었다면, 그 바로 윗 노드가 leaf Node(successor)일 것! 
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.getLeft();
		}
		// 우측 자식 노드와 우측 자식 노드 중 최솟값이 중복값이 아니라면  
		if(successsor!=deleleNode.getRight()){
			// 가장 작은 값을 선택하기 때문에, 대체 노드의 왼쪽 자식은 빈 노드가 된다.
			successsorParent.setLeft(successsor.getRight());
			// 대체할 노드의 오른쪽자식 노드를 삭제할 노드의 오른쪽으로 지정한다. 
			successsor.setRight(deleleNode.getRight());
		}
		return successsor;
	}

	//삽입 연산
	public void insert(int id){
		Node newNode = new Node(id);
		
		// 최초 insert라면 root 설정 후 종료
		if(root==null){
			root = newNode;
			return;
		}
		
		// 이미 트리가 생성되어 있다면, root부터 차례로 비교하며 내려가면서 자기 위치를 찾음
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			// 입력 받은 값 데이터가 현재 위치보다 작으면 왼쪽으로 이동
			if(id < current.getData()){				
				current = current.getLeft();
				// 이동 위치가 비어있다면 현재 위치에 셋팅
				if(current==null){
					parent.setLeft(newNode);
					return;
				}
			}else{
				// 입력 받은 값 데이터가 현재 위치보다 작으면 오른쪽으로 이동
				current = current.getRight();
				// 이동 위치가 비어있다면 현재 위치에 셋팅
				if(current==null){
					parent.setRight(newNode);
					return;
				}
			}
		}
	}
	
	// 중위 순회 방식으로 root부터 차례대로 출력
	public void display(Node root){
		if(root!=null){
			display(root.getLeft());
			System.out.print(" " + root.getData());
			display(root.getRight());
		}
	}

	public static void main(String[] args) {
		
		BinarySearchTree b = new BinarySearchTree();
		//트리에 노드를 삽입
		b.insert(3);b.insert(8);
		b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
		b.insert(20);b.insert(25);b.insert(15);b.insert(16);
		
		System.out.println("트리삽입 결과 : ");
		b.display(b.root);
		System.out.println("");
		System.out.println("이진트리에서 4를 탐색 : " + b.find(4));
		System.out.println("이진트리에서 2를 삭제 : " + b.delete(2));		
		b.display(b.root);
		System.out.println("\n이진트리에서 4를 삭제 : " + b.delete(4));		
		b.display(b.root);
		System.out.println("\n이진트리에서 10을 삭제 : " + b.delete(10));		
		b.display(b.root);
	}

}
