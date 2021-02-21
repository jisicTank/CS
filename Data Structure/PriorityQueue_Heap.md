# 우선순위 큐(Priority Queue), 힙(Heap)



## 우선순위 큐(Priority Queue)

* **우선순위의 개념을 큐에 도입한 자료구조**
* **우선순위 큐를 위해 `Heap 자료구조`가 만들어졌다.**

> 데이터들이 우선순위를 가지고 있음. 우선순위가 높은 데이터가 먼저 나감

<br>

### 언제 사용할까?

* 시뮬레이션 시스템
* 작업 스케줄링
* 수치해석 계산
* 네트워크 트래픽 제어

ex) 컴퓨터에서는 **우선순위** 개념이 종종 필요할 때가 있다. 예를 들면, 운영 시스템에서 시스템 프로세스는 응용 프로세스보다 더 높은 우선순위를 갖는다. 따라서 자료구조에서도 이러한 우선순위를 지원하는 것이 필요하다.

출처: https://lipcoder.tistory.com/entry/우선순위-큐Priority-Queue [기록공간]

<br>

### 어떻게 구현할까?

* 배열
* 연결리스트
* **힙(가장 효율적!)** -> **<u>삽입, 삭제 O(log N)</u>**

<br><br>

## 힙(Heap)

* **완전 이진 트리의 일종**
* **반정렬 상태**
  (힙의 목적이 삭제 연산에서 가장 크거나 작은 값을 효율적으로 찾아내기만 하면 되는 것이므로 전체를 정렬할 필요 X)
* **힙 트리는 중복된 값 허용(이진 탐색 트리는 중복값 허용X)**

<br>

*** `완전 이진 트리(Complete Binary Tree)`

- 마지막 레벨은 노드가 왼쪽에 몰려있고, 마지막 레벨을 제외하면 `포화 이진 트리` 구조를 띄고 있는 이진 트리

*** `포화 이진 트리`(Perfect Tree)

- 정 이진 트리에서 모든 단말 노드(leaf)의 깊이가 같은 이진 트리

*** `정 이진 트리(Full Binary Tree)`

* 모든 노드의 차수가 0 또는 2인 이진 트리

<br>



### 힙의 종류

* **최대 힙(max heap)**

부모 노드의 키 값이 자식 노드의 키 값보다 **크거나 같은** 완전 이진 트리

* **최소 힙(min heap)**

부모 노드의 키 값이 자식 노드의 키 값보다 **작거나 같은** 완전 이진 트리

![minMaxHeap](https://user-images.githubusercontent.com/46706670/108621137-2fafa280-7474-11eb-93fe-3dff38b8eab6.png)



<br>

### 힙의 구현

* **힙을 저장하는 표준적인 자료구조는 `배열`**
* **구현을 쉽게 하기 위해 배열의 첫 번째 인덱스인 0은 사용 X**
  (자식 - 부모 인덱스 탐색을 쉽게 하기 위함)
* **특정 위치의 노드 번호는 새로운 노드가 추가되어도 변하지 않음**
  (ex. 루트 노드(1)의 오른쪽 노드 번호는 항상 3)

<br>

**부모 노드와 자식 노드 관계**

> * 왼쪽 자식 index = (부모 index) * 2
> * 오른쪽 자식 index = (부모 index) * 2 + 1
> * 부모 index = (자식 index) / 2





<br>

#### 힙의 삽입

1. **힙에 새로운 요소가 들어오면, 일단 새로운 노드를 힙의 마지막 노드에 삽입**
2. **새로운 노드를 부모 노드들과 교환**

```java
void insert_max_heap(int x) {
    // 힙 크기를 하나 증가하고, 마지막 노드에 x를 넣음
    maxHeap[++heapSize] = x; 

    for(int i = heapSize; i > 1; i /= 2) {
        // 마지막 노드가 자신의 부모 노드보다 크면 swap
        if(maxHeap[i/2] < maxHeap[i]) {
            swap(i/2, i);
        } else {
            break;
        }
    }
}
```



<br>

#### 힙의 삭제

1. **최대 힙에서 최댓값은 루트 노드이므로 루트 노드가 삭제됨**
   **(최대 힙에서 삭제 연산은 최댓값 요소를 삭제하는 것)**
2. **삭제된 루트 노드에는 힙의 <u>마지막 노드</u>를 가져옴**
3. **힙을 재구성**

```java
int delete_max_heap() {
    if(heapSize == 0) // 배열이 비어있으면 리턴
        return 0;
    
    int item = maxHeap[1]; // 루트 노드의 값을 저장
    maxHeap[1] = maxHeap[heapSize]; // 마지막 노드 값을 루트로 이동
    maxHeap[heapSize--] = 0; // 힙 크기를 하나 줄이고 마지막 노드 0 초기화
    
  	// 힙을 재구성(root부터 마지막 내부노드까지)
    for(int i = 1; i*2 <= heapSize;) {
        // 마지막 노드(root)가 왼쪽 노드와 오른쪽 노드보다 크면 끝
        if(maxHeap[i] > maxHeap[i*2] && maxHeap[i] > maxHeap[i*2+1]) {
            break;
        }
        // 왼쪽 노드가 더 큰 경우, swap
        else if (maxHeap[i*2] > maxHeap[i*2+1]) {
            swap(i, i*2);
            i = i*2;
        }
        // 오른쪽 노드가 더 큰 경우
        else {
            swap(i, i*2+1);
            i = i*2+1;
        }
    }
    return item;
}
```



## Quiz

* Priority Queue의 동작 원리가 어떻게 되나요? (N사 전화면접)
  https://devowen.com/285
* 다음 그림에서 새로운 데이터의 삽입, 삭제는 어떻게 이뤄지나요?
  https://okky.kr/article/673648
* 우선순위 큐를 구현해보세요.
  https://okky.kr/article/673648



## 참고 자료

* https://github.com/gyoogle/tech-interview-for-developer/blob/master/Computer%20Science/Data%20Structure/Heap.md



## Quiz 모범 답안



<img width="934" alt="스크린샷 2021-02-21 오후 8 59 19" src="https://user-images.githubusercontent.com/46706670/108624336-ae620b00-7487-11eb-8c2f-f73a4499789f.png">



