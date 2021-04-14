# CPU 스케줄링



### 스케줄링(Scheduling)

> CPU를 잘 사용하기 위해 프로세서를 할당하는 일련의 과정.

- 조건 : 오버헤드 ↓ / 사용률 ↑ / 기아 현상 ↓
- 목표
  1. `Batch System`: 가능하면 많은 일을 수행. 시간(time) 보단 처리량(throughout)이 중요.
  2. `Interactive System`: 빠른 응답 시간. 적은 대기 시간.
  3. `Real-time System`: 기한(deadline) 맞추기.



### 스케줄링 큐

> 스케줄링을 위해 사용하는 데이터베이스는 큐로 구성.
>
> 프로세스 제어블록(PCB)이 리스트 형태로 연결.

![KakaoTalk_20210413_093522209](https://user-images.githubusercontent.com/24764210/114480063-b430cd00-9c3c-11eb-877f-4e711f3bb7fc.jpg)

- 준비 큐 : 프로세스 하나를 선택하는 큐로 시스템에 하나만 존재.
- 자기테이프, 디스크, 단말기 하나하나가 큐.
- 링크드리스트로 매달린 것이 큐에 대기 중인 프로세스이다.
- 머리(Head)와 꼬리(Tail) 부분은 PCB의 첫 번째 항목과 마지막 항목의 포인터를 가리킨다.



### 큐잉 도표

> 프로세서 스케줄링의 흐름을 도식화한 것.

![KakaoTalk_20210413_093557344](https://user-images.githubusercontent.com/24764210/114480444-88621700-9c3d-11eb-9b62-f26c1b756389.jpg)

- 간략한 큐잉 도표

![KakaoTalk_20210413_093726179](https://user-images.githubusercontent.com/24764210/114480448-88621700-9c3d-11eb-914e-fb919115ba1b.jpg)



### 선점 / 비선점 스케줄링

##### 비선점 (nonpreemptive)
> 다른 프로세스에 할당된 자원을 스스로 반납할 때까지 빼앗을 수 없는 스케줄링

- 프로세스 종료 or I/O 등의 이벤트가 있을 때까지 실행 보장
- 응답시간의 예측이 편하며, 일괄처리 방식에 적합
- 단점으론 덜 중요한 작업이 자원을 할당 받으면 중요한 작업이 와도 먼저 처리 될수 없음
- `FCFS`, `SJF`, `HRN`, `우선순위`



##### 선점 (preemptive)

> 현재 실행 중인 프로세스를 인터럽트할 수 있거나 준비 상태로 이동시킬 수 있는 스케줄링. 

- 우선순위가 높은 프로세스를 빠르게 처리할수 있음
- 어떤 프로세스가 자원을 사용하고 있을 때 우선순위가 더 높은 프로세스가 올 경우 자원을 강탈함
- 빠른 응답 시간을 요구하는 시스템에서 사용
- 오버헤드가 크다(문맥교환이 자주 일어나므로)
- `Round Robin`, `SRT`, `선점 우선순위`, `다단계 큐`, `다단계 피드백큐`



### 알고리즘 성능 평가 기준

- 프로세서 사용률 : 시간당 CPU 사용 시간 비율
- 처리율 : 시간당 처리한 작업의 비율
- **반환시간** : CPU burst time(프로세서 실행 시간)
- **대기시간** : 대기열에 들어와 CPU를 할당받기까지 기다린 시간
- 반응시간 : 대기열에서 처음으로 CPU를 얻을 때까지 걸린 시간



### 스케줄링 알고리즘

##### 비선점 스케줄링

- FCFS (First Come First Served, 선입 선처리 스케줄링)

  > 큐에 도착한 순서대로 CPU 할당.
  
  - 실행 시간이 짧은 게 뒤로 가면 평균 대기 시간이 길어짐.
  
  - 일괄처리 시스템 : 매우 효율적
  
    대화식 시스템 : 사용자가 빠른 응답을 요구하기 때문에 부적합
  
    <img src="https://user-images.githubusercontent.com/24764210/114482556-b6e1f100-9c41-11eb-9a94-57021dc1850c.jpg" alt="KakaoTalk_20210413_101527199_02" style="zoom:67%;" />
  
    
  
    Q. 반환시간 , 대기 시간 계산
  
    <img src="https://user-images.githubusercontent.com/24764210/114483210-f230ef80-9c42-11eb-9f68-a5613cc74604.PNG" alt="캡처" style="zoom:67%;" />
  
    - FCFS 간트도표
  
    <img src="https://user-images.githubusercontent.com/24764210/114482570-bd706880-9c41-11eb-8cd1-5246d8ee8623.jpg" alt="KakaoTalk_20210413_101527199" style="zoom:67%;" />
  
    
  
    - FCFS - 실행 시간이 짧은 순으로 진행했을 때
  
    <img src="https://user-images.githubusercontent.com/24764210/114482579-c06b5900-9c41-11eb-8eb4-480e1976026d.jpg" alt="SDA" style="zoom:67%;" />
  
    ​		평균반환시간 : (3 + 6 + 30) / 3 = 13
  
    ​		평균대기시간 : (0 + 3 + 6) / 3 = 3
  
    ​		→ 실행시간이 짧은 순으로 처리할 때 시간이 단축됨.



- SJF (Shortest Job First, 최소 작업 우선 스케줄링)

  > 프로세서 버스트(프로그램 실행시간)가 가장 짧은 작업에 CPU 할당.

  - 두 프로세스가 동일한 프로세서 버스트를 가지면 FCFS 적용.

  - FCFS 보다 평균 대기 시간 감소, 짧은 작업에 유리

    <img src="https://user-images.githubusercontent.com/24764210/114487928-65d6fa80-9c4b-11eb-8d04-88a908ac3a30.jpg" alt="KakaoTalk_20210413_112629155_01" style="zoom:67%;" />

    

    - SJF도 선점이 가능. (= SRT, 최소 잔여시간 우선 스케줄링)

      : **BUT!** 문맥교환 시간이 소요되므로 반드시 비선점에 비해 유리하다고는 할 수 없다.

    <img src="https://user-images.githubusercontent.com/24764210/114487932-67a0be00-9c4b-11eb-90b2-99f169c1df33.jpg" alt="KakaoTalk_20210413_112629155" style="zoom:67%;" />

    ​		비선점 평균 반환시간 : 14.25 / 선점 평균 반환시간 : 13

    ​		비선점 평균 대기시간 : 7.75 / 선점 평균 대기시간 : 6.5

    

- 우선순위 스케줄링

  > 우선순위를 계산하여 우선순위가 높은 프로세스에게 CPU 할당.

  -  문제점 

    - 기아 상태 : 실행 준비는 되었으나 우선 순위가 높은 프로세스들이 계속 들어오면 우선순위가 낮은 프로세스들은 무한정 기다려야 한다.

    - 해결 방법 : **에이징(Aging)** - 오랫동안 시스템에서 대기하는 프로세스들의 우선순위를 점진적으로 증가시키는 방법. 

      예) 15분마다 프로세스의 우선순위를 1씩 증가

    <img src="https://user-images.githubusercontent.com/24764210/114488978-450fa480-9c4d-11eb-9644-dc516b8fcf8a.jpg" alt="KakaoTalk_20210413_113931185_01" style="zoom:67%;" />

    

    <img src="https://user-images.githubusercontent.com/24764210/114488982-46d96800-9c4d-11eb-92c7-b77d41b31203.jpg" alt="KakaoTalk_20210413_113931185" style="zoom:67%;" />



- HRN 스케줄링

  > SJF 기법의 약점이었던 긴 작업과 짧은 작업 간의 지나친 불평등을 어느 정도 보완
  >
  > 우선순위를 계산하여 프로세스에 CPU 할당.

  - 우선순위 = (대기시간 + 실행시간) / (실행시간)



##### 선점 스케줄링

- Round Robin (순환 할당 스케줄링)

  > 시분할 시스템을 위해 특별히 설계된 것으로 규정 시간량(Tim Quantum) 또는 시간 할당량(Time Slice)이라고 하는 단위의 시간만큼 CPU 할당.

  - 규정시간량이 커지면 선입선처리 방식으로 변함.

    규정시간량이 작으면 속도는 빠르지만 문맥교환 때문에 발생하는 오버헤드가 너무 커져 시스템 성능이 감소.

  - 준비 큐 상태

    <img src="https://user-images.githubusercontent.com/24764210/114498543-3cc06500-9c5f-11eb-8f02-452064ca8111.jpg" alt="KakaoTalk_20210413_134519073_01" style="zoom:67%;" />

    

  <img src="https://user-images.githubusercontent.com/24764210/114498551-3f22bf00-9c5f-11eb-9505-272da22f2232.jpg" alt="KakaoTalk_20210413_134519073" style="zoom: 80%;" />

  ​							규정 시간량이 4일 때, 평균반환시간은 (20 + 7 + 24 + 22) / 4 = 18.25이고

  ​							평균 대기시간은 (12 + 13 + 15 +17) /4 = 11.75

  

- SRT (Shortest Remaining Time, 최소 잔여시간 우선 스케줄링) 

  > SJF의 선점 버전



- Multi-level Queue (다단계 큐 스케줄링)

  > 준비 큐를 여러 개 사용하는 기법.
  
  - 각각의 큐는 자신의 스케줄링 알고리즘을 수행하며, 큐와 큐 사이에도 우선순위를 부여한다.
  
  - 우선순위가 높은 프로세스가 들어오면 하던 프로세서를 반납하고 준비 큐에 들어오고 우선순위가 높은 프로세스가 CPU를 할당받는다.
  
     ![KakaoTalk_20210413_151054409](https://user-images.githubusercontent.com/24764210/114504992-a98d2c80-9c6a-11eb-9923-9f0f32059308.jpg)



- 다단계 피드백 큐 스케줄링

  > 다단계 큐와 비슷하나 큐마다 timeout을 설정하여 timeout초과시 우선순위가 낮은 다음단계 큐로 이동할 수 있다.

  ![KakaoTalk_20210413_151054409_01](https://user-images.githubusercontent.com/24764210/114505270-17395880-9c6b-11eb-8e1d-1b2489cf633b.jpg)





### Q. 면접질문

- CPU 스케줄링이란 무엇인가요?
- CPU 스케줄링 방법에는 대표적으로 어떤 것들이 있나요?





























