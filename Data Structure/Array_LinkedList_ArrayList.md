# Array, LinkedList, ArrayList



## Array

* 데이터를 **<u>나열</u>**하고, 각 데이터를 **<u>인덱스에 대응</u>**하도록 구성한 데이터 구조

### 배열이 왜 필요할까?

* 같은 종류의 데이터를 효율적으로 관리하기 위해 사용
* 같은 종류의 데이터를 순차적으로 저장

<br>

* **배열의 장점**
  * 빠른 접근 가능
* **배열의 단점**
  * 추가/삭제가 어려움 -> 데이터 수가 정적일 때 효율적, 동적일 때 불편함



**--> List 등장**

<br>

### Collection Framework

* 데이터 군을 저장하는 클래스들을 표준화한 설계
* List, Set인터페이스가 상속해 이용하고 있음

<br>

**JCF(자바 컬렉션 프레임워크) 계층구조**

![jdchoi_20140220_JCF]($md-images/jdchoi_20140220_JCF-20210216002222366.png)

<br>

**자바 컬렉션 프레임워크의 핵심 인터페이스간 상속계층도**

![스크린샷 2021-02-16 오전 12.20.31]($md-images/sc1.png)

인터페이스 List와 Set을 구현한 컬렉션 클래스들은 서로 공통부분이 많아, 공통된 부분을 뽑아 Collection인터페이스를 정의했는데, Map 인터페이스는 이들과는 다른 형태로 컬렉션을 다루기 때문에 같은 상속계층도에 포함되지 못함.

<br>

**List 인터페이스를 상속하는 컬렉션클래스(ArrayList, LinkedList)**

![스크린샷 2021-02-16 오전 12.30.07]($md-images/cs2.png)

<br>



## LinkedList

![linked_list1]($md-images/linked_list1.png)

* 연속적인 메모리 위치에 저장되지 않는 선형 데이터 구조(포인터로 연결)
* 각 노드는 데이터 필드와 다음 노드에 대한 참조를 포함하는 노드로 구성(노드: 데이터부분+포인터부분)

<br>

### 연결리스트가 왜 필요할까?

* **<u>데이터 수가 동적으로 변화</u>**하는 선형 데이터를 쉽게 삽입/삭제할 수 있음

<br>

* **연결리스트의 장점**
  * 같은 타입의 동적 데이터 관리
  * 삽입/삭제 용이
* **연결리스트의 단점**
  * 임의의 k번째 데이터에 대한 접근을 할 수 없음. 즉 첫 번째 노드부터 순차적으로 요소에 액세스(이진 탐색 불가)
  * 포인터 메모리 공간이 각 노드에 필요



<br>

일반적인 노드 구현 예시

```c
// A linked list node(c Language) 
struct Node 
{ 
  int data; 
  struct Node *next; 
}; 
```

[java 코드로 LinkedList 이해하기](https://github.com/jisicTank/CS/tree/main/Data%20Structure)



## ArrayList

* LinkedList와 차별적으로 배열과 유사한 형태로 List 컬렉션을 관리하는 자료구조

> JCF 계층 구조를 보면 LinkedList는 AbstractSequentialList를 상속하고, ArrayList는 List 인터페이스를 구현한 AbstractList를 상속하고 있음

![jdchoi_20140225_arrayvslinkedlist11]($md-images/jdchoi_20140225_arrayvslinkedlist11.png)



실제로 ArrayList는 Object배열을 이용해서 데이터를 순차적으로 저장한다. 예를 들면, 첫 번째로 저장한 객체는 Object배열의 0번째 위치에 저장되고 그 다음에 저장하는 객체는 1번째 위치에 저장된다. 이런 식으로 계속 배열에 순서대로 저장되며, 배열에 **<u>더 이상 저장할 공간이 없으면 보다 큰 새로운 배열을 생성해서 기존의 배열에 저장된 내용을 새로운 배열로 복사한 다음에 저장</u>**된다.

<br>

### ArrayList가 왜 필요할까?

* 같은 타입의 데이터 수가 동적으로 변화하면서, 특정 K번째 위치의 데이터 조회가 빈번한 경우 빠르게 탐색할 수 있다.(이분탐색 사용 가능)

* ArrayList의 장점
  * List 컬렉션(데이터군)을 배열과 같이 index를 통해 빠르게 접근할 수 있다.
  * 포인터를 위한 메모리 사용X
* ArrayList의 단점
  * 크기가 한정되어 있기 때문에 삽입/삭제에 상당한 연산이 수행된다.

<br>

<br>

**중간 퀴즈**

* 저희는 서비스의 X기능은 동일한 유형을 가진 다량의 데이터를 다루는데 조회를 자주 하진 않지만, 데이터 추가나 삭제가 빈번합니다. 시스템을 효율적으로 관리하기 위해 어떤 자료구조를 사용해야 할까요?
* ArrayList와 LinkedList, 각각 어떤 때 사용하면 좋을까요?

<br><br>

## LinkedList와 ArrayList 비교



### ArrayList의 삽입/삭제

![스크린샷 2021-02-16 오전 12.48.29]($md-images/cs3.png)

<br>

### LinkedList의 삽입/삭제

![스크린샷 2021-02-16 오전 1.04.14]($md-images/cs4.png)

<br>

### ArrayList와 비교한 LinkedList의 장단점

![스크린샷 2021-02-16 오전 1.05.13]($md-images/cs5.png)

<br>

### ArrayList와 LinkedList의 비교 성능(주의: 단위 omega가 아닌 theta임)

![스크린샷 2021-02-16 오전 1.05.41]($md-images/cs6.png)



<br><br>

## Quiz

* Array와 LinkedList의 차이가 무엇인가요?(N사 전화면접)
  https://devowen.com/285



<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

참고 문서

* https://www.nextree.co.kr/p6506/
* https://github.com/gyoogle/tech-interview-for-developer
* 자바의 정석 기초편
* 패스트캠퍼스 알고리즘/기술면접 완전 정복 올인원 패키지 Online 강의자료



## Quiz 모범 답안



https://devowen.com/285

![스크린샷 2021-02-16 오전 1.20.31]($md-images/cs7.png)





https://medium.com/@Aaron__Kim/%EA%B8%B0%EC%88%A0-%EB%A9%B4%EC%A0%91-%EC%A4%80%EB%B9%84-db-os-nw-e03cdfe07966

![스크린샷 2021-02-16 오전 1.21.22]($md-images/cs8.png)