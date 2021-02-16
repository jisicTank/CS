# 스택과 큐(Stack & Queue)

## 스택(Stack)

* 입력과 출력이 한 곳(방향)으로 제한
* LIFO(Last In First Out, 후입선출): 가장 나중에 들어온 것이 가장 먼저 나옴

### 언제 사용할까?

* 함수의 콜스택, 문자열 역순 출력, 연산자 후위표기법 등

### Stack의 특징

* 자주 사용하는 메소드로 push, pop, isEmpty, isFull 이 있다. 
* push, pop 과정에 다음 값이 들어갈 최상단 위치를 기억하는 `스택 포인터(SP)`가 필요(처음 기본값 -1)
* 기본적으로 크기가 정적으로 구성되지만 `연결리스트, 동적 배열`을 활용해 `최대 크기가 존재하지 않도록 구현`할 수 있다.
* 시간복잡도 삽입 O(1), 삭제 O(1), 탐색 O(n)

[java 코드로 스택 살펴보기](https://github.com/jisicTank/CS/tree/main/Data%20Structure/code)





## 큐(Queue)

* 입력과 출력을 한 쪽 끝(front, rear)로 제한
* FIFO(First In First Out, 선입선출): 가장 먼저 들어온 것이 가장 먼저 나옴

### 언제 사용할까?

* 버퍼, 마구 입력된 것을 처리하지 못하고 있는 상황, BFS

### Queue의 특징

* 큐의 가장 첫 원소를 front, 끝 원소를 rear라고 부름

* 접근은 가장 첫 원소와 끝 원소로만 가능

* 자주 사용하는 메소드로 enQueue, deQueue, isEmpty, isFull이 있음

* 시간복잡도 삽입 O(1), 삭제 O(1), 탐색 O(n)

* 일반 큐(선형)는 빈 메모리가 남아 있어도, 꽉 차있는 것으로 판단할 수 있음(rear가 끝에 도달했을 때) -> `원형 큐`

  



### 원형 큐

* 논리적으로 배열의 처음과 끝이 연결되어 있는 것으로 간주함
* 초기 공백 상태일 때 front와 rear가 0
* 공백, 포화 상태를 쉽게 구분하기 위해 자리 하나를 항상 비워둠

> (index + 1) % size로 순환시킨다

[java 코드로 큐 알아보기](https://github.com/jisicTank/CS/tree/main/Data%20Structure/code)





## Quiz

* 기본적인 스택과 큐를 구현해보세요.
* Stack과 Queue의 차이점은 무엇인가요?(N사 전화면접)
  https://devowen.com/285

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

## 참고 자료

* https://github.com/gyoogle/tech-interview-for-developer



## Quiz 모범 답안



https://devowen.com/285

<img width="927" alt="cs9" src="https://user-images.githubusercontent.com/46706670/108017404-2380ab00-7058-11eb-81d3-701f423a5328.png">





