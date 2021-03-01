package DAY04.Trie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Trie {
	static int W, N;
	static TrieNode root = new TrieNode();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/DAY04/Trie/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		W = Integer.parseInt(br.readLine());
		for(int i = 0; i < W; i++) {
			insert(br.readLine());
		}
		System.out.println(root.toString("",0));
		
	}
	
	static void insert(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			int wordIndex = word.charAt(i) - 'A';
			if(current.children[wordIndex] == null) {
				current.children[wordIndex] = new TrieNode();
			}
			current = current.children[wordIndex];
		}
		current.isEnd = true;
	}
	
	
	static boolean containNode(String word) {
		TrieNode current = root;
		for(int i =0; i<word.length(); i++) {
			int wordIndex = word.charAt(i) - 'A';
			if(current.children[wordIndex] == null) {
				return false;
			}
			current = current.children[wordIndex];
		}
		return current.isEnd;
	}
}

class TrieNode{
	TrieNode[] children = new TrieNode[26];
	boolean isEnd;
	
	public String toString(String current, int depth) {
		StringBuilder sb = new StringBuilder(current);
		sb.append(isEnd ? "." : "");
		
		for(int i = 0; i < children.length; i++) {
			if(children[i] != null) {
				sb.append("\n");
				for(int j = 0; j < depth; j++) {
					sb.append("_");
				}
				sb.append(children[i].toString((char)('A' + i)+"",depth + 1));
			}
		}
		return sb.toString();
	}
	
	/*
	 * HashMap = Key, Value
	 * key -> valueO(1)
	 * key가 완성된 경우에만 사용 가능합니다.
	 * 
	 * 다른 자료구조와 비교 시
	 * 단어 길이 vs 단어 개수
	 */
	
	
}