package hash;

import java.util.LinkedList;

class HashTable{
	class Node{
		String key;
		String value;
		
		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	LinkedList<Node>[] data;
	
	HashTable(int size){
		this.data = new LinkedList[size];
	}
	
	// 해시코드 계산
	int getHashCode(String key) {
		int hashcode = 0;
		
		// 입력값의 각 문자 아스키코드를 더함
		for(char c : key.toCharArray()) {
			hashcode += c;
		}
		return hashcode;
	}
	
	// 해시코드 -> index
	int convertToIndex(int hashcode) {
		return hashcode % data.length;
	}
	
	// 검색
	Node searchKey(LinkedList<Node> list, String key) {
		if(list == null) return null;
		for(Node node : list) {
			if(node.key.equals(key)) {
				return node;
			}
		}
		// 해당 index에 찾으려는 key값이 존재하지 않으면 null 반환 
		return null;
	}
	
	// key값과 value로 데이터 저장
	void put(String key, String value) {
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
		
		// 각 key별 hashcode, index 확인해보기 
//		System.out.println(key +", hashcode("+hashcode+"), index(" + index+")");
		
		LinkedList<Node> list = data[index];
		
		// 해당 Index에 기존 생성된 LinkedList가 없는 경우 생성
		if(list == null) {
			list = new LinkedList<Node>();
			data[index] = list;
		}
		Node node = searchKey(list, key);
		
		if(node == null) {
			// key값과 동일한 노드가 없다면 만들어서 넣어줌
			list.addLast(new Node(key, value));
		}else {
			// key값과 동일한 노드가 이미 있다면 대체 
			node.setValue(value);;
		}
	}
	
	// key 값을 가지고 data value를 탐색
	String get(String key) {
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
		LinkedList<Node> list = data[index];
		Node node = searchKey(list, key);
		return node == null? "Not found" : node.getValue();
	}
	
}

public class Test {

	public static void main(String[] args) {
		HashTable h = new HashTable(3);
		h.put("sung", "She is pretty");
		h.put("jin", "She is a model");
		h.put("hee", "She is an angel");
		h.put("min", "She is cute");
		System.out.println(h.get("sung"));
		System.out.println(h.get("jin"));
		System.out.println(h.get("hee"));
		System.out.println(h.get("min"));
		
		// 없는 data?
		System.out.println(h.get("jee"));
		
	}

}
