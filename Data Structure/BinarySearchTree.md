# 이진탐색트리(Binary Search Tree)

* **이진 탐색(Binary Search) + 연결리스트(LinkedList)**

<br>

**이진 탐색**

>  탐색에 소요되는 시간복잡도
> $$
> O(log_2N)
> $$
> 



**연결리스트**

> 삽입, 삭제의 시간복잡도 O(1)
>
> but 탐색하는 시간 복잡도 O(N)



이 2가지를 합해 장점을 모두 얻는 것이 `이진탐색트리`이다.



![binarySearchTree]($md-images/108627909-343b8180-749b-11eb-9ed1-1273d7a327e8.png)



<br><br>

## 이진탐색트리의 특징

* 각 노드의 자식이 2개 이하(이진 트리)
* 각 노드의 왼쪽 자식은 부모보다 작고, 오른쪽 자식은 부모보다 큼
* **<u>중위순회(in-order)</u>** 방식(왼쪽 - 루트 - 오른쪽) 구조 -> **<u>정렬된 순서</u>**를 읽을 수 있음
* **<u>중복값 노드는 존재하지 않음</u>**

> **중복이 없어야 하는 이유?**
>
> 검색 목적 자료구조인데, 중복이 많은 경우에 트리를 사용하여 검색 속도를 느리게 할 필요가 없음
>
> (트리에 삽입하는 것보다, 노드에 count값을 가지게 하여 처리하는 것이 훨씬 효율적)

<br>

## 이진탐색트리의 핵심 연산

* 검색
* 삽입
* 삭제
* 트리 생성
* 트리 삭제

[Java 코드로 이진탐색트리 살펴보기](https://github.com/jisicTank/CS/tree/master/Data%20Structure/code)

<br>

## 이진탐색트리의 시간 복잡도(모든 연산)

* **균등 트리(Blanced Binary Tree)**
  $$
  O(log_2N)
  $$
  

* **편향 트리(Skewed Tree)** - 별로 효율적이지 못한 경우 <- root가 최솟값, 최댓값

$$
O(N)
$$



<br>

### 보다 나은 성능을 보이는 이진 탐색 트리들

* 높이의 균형을 유지함으로써 O(logN)의 탐색 복잡도 보장. 삽입, 삭제 연산이 복잡
* AVLTree
* Red-Black Tree

<br>

## 이진탐색트리 VS 힙 비교

* 힙은 `자식 노드가 부모 노드보다 크면 오른쪽으로 삽입, 작으면 왼쪽으로 삽입`과 같은 조건이 존재하지 않는다.(**힙은 비교적 느슨하게 정렬**되어 있음)
* 힙은 BST에 비해 **완전이진트리**여야 한다는 제약조건을 가짐
* 이진탐색트리에서의 노드별 값 크기 순이 **왼쪽 자식 < 부모 < 오른쪽 자식** 순으로 된다.
* 이진탐색트리에서는 **중복값**을 다루지 않는다.
* **특정 키값을 가지는 원소를 빠르게 검색**할 때 이진 탐색 트리는 빠르게 검색이 가능
  (<-> 힙은 특정한 키값을 검색하는데 별로 좋은 방법이 없다.)



## Quiz

* BST와 Binary Tree에 대해 설명하세요.(N사 전화면접)
  https://devowen.com/285

## 참고 자료

* https://github.com/gyoogle/tech-interview-for-developer/
* https://hochoon-dev.tistory.com/entry/
* https://madplay.github.io/post/binary-search-tree-in-java
* https://scarletbreeze.github.io/articles/2019-07/%ED%8C%8C%EC%9D%B4%EC%8D%AC%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0(20-23)



## Quiz 모범 답안

<img width="943" alt="스크린샷 2021-02-22 오전 12 53 49" src="https://user-images.githubusercontent.com/46706670/108630523-86cf6a80-74a8-11eb-86a8-922ecb7944aa.png">

