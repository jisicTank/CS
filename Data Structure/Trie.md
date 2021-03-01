# 트라이(Trie)

* 문자열에서 검색을 빠르게 도와주는 자료구조

> 정수형에서 이진탐색트리를 이용하면 시간복잡도 O(log N)
> 하지만 문자열에서 적용했을 때, 문자열 최대 길이가 M이면 O(M * log N)이 된다.
>
> 트라이를 활용하면? -> O(M)으로 문자열 검색이 가능함!

<br>

* Prefix Tree
* K진 트리 구조
* 단어 사전을 트라이로 생성 후 찾을 단어를 트라이를 사용하여 검색
* 트라이의 Root 노드는 항상 공백문자열(빈 문자열) 상태를 의미함

![Trie]($md-images/109415821-559def80-79fe-11eb-8189-dcea704d4463.png)



> 예시 그림에서 주어지는 배열의 총 문자열 개수는 8개인데, 트라이를 활용한 트리에서도 마지막 끝나는 노드마다 '네모' 모양으로 구성된 것을 확인하면 총 8개다.

<br>



## Trie 구현

<img width="692" alt="스크린샷 2021-02-28 오후 7 50 06" src="https://user-images.githubusercontent.com/46706670/109415833-62224800-79fe-11eb-9c6b-be7e4a9d5a0d.png">



```java
class Node{
	Object data;
  Node child[];
}
```

### 구축

* Root 노드부터 시작해 단어의 첫 글자부터 트라이를 탐색
* 만약 처리중인 철자에 해당하는 자식이 있다면, 자식 노드로 이동
* 만약 처리중인 철자에 해당하는 자식이 없다면, 생성
* 탐색된 마지막 노드에 현재 입력된 단어의 정보를 추가



### 검색

* Root 노드부터 시작해 검색할 단어의 첫 글자부터 트라이를 탐색
* 만약 처리중인 철자에 해당하는 자식이 있다면, 자식 노드로 이동
* 만약 처리중인 철자에 해당하는 자식이 없다면, 단어사전에 존재하지 않는 단어!
* 탐색이 완료되면, 탐색된 마지막 노드의 정보 이용



[Java Code로 Trie 살펴보기](https://github.com/jisicTank/CS/tree/master/Data%20Structure/code)



## Trie 활용 문제

* [BOJ 5052 전화번호 목록](https://www.acmicpc.net/problem/5052)
* [BOJ 9202 Boggle](https://www.acmicpc.net/problem/9202)

