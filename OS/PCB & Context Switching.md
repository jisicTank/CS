## PCB & Context Switching



#### Process Management

> CPU가 프로세스가 여러개일 때, CPU 스케줄링을 통해 관리하는 것을 말함

이때, CPU는 각 프로세스들이 누군지 알아야 관리가 가능함

프로세스들의 특징을 갖고있는 것이 바로 `Process Metadata`

- Process Metadata
  - Process ID
  - Process State
  - Process Priority
  - CPU Registers : PC(프로그램 카운터 - 다음에 수행할 명령어의 위치를 저장하고 있음)
  - Owner
  - CPU Usage
  - Memeory Usage

이 메타데이터는 프로세스가 생성되면 `PCB(Process Control Block)`이라는 곳에 저장됨

<br />

<br />

#### PCB(Process Control Block)

> 프로세스 메타데이터들을 저장해 놓는 곳, 하나의 PCB 안에는 하나의 프로세스의 정보가 담김
>
> 보호된 메모리 영역 안에 위치. 일부 운영체제에서는 커널의 스택에 처음 위치

[<img src="https://camo.githubusercontent.com/6341ca44629c93eb107563ab612ebfc581b2251ae312394b011ea70ee873b523/68747470733a2f2f74312e6461756d63646e2e6e65742f6366696c652f746973746f72792f323536373341353035384632313143323234" alt="img" style="zoom:50%;" />](https://camo.githubusercontent.com/6341ca44629c93eb107563ab612ebfc581b2251ae312394b011ea70ee873b523/68747470733a2f2f74312e6461756d63646e2e6e65742f6366696c652f746973746f72792f323536373341353035384632313143323234)

##### 다시 정리해보면?

```
프로그램 실행 → 프로세스 생성 → 프로세스 주소 공간에 (코드, 데이터, 스택) 생성 
→ 이 프로세스의 메타데이터들이 PCB에 저장
```

<br />

##### PCB가 왜 필요한가요?

> CPU에서는 프로세스의 상태에 따라 교체작업이 이루어진다. (interrupt가 발생해서 할당받은 프로세스가 wating 상태가 되고 다른 프로세스를 running으로 바꿔 올릴 때)
>
> 이때, **앞으로 다시 수행할 대기 중인 프로세스에 관한 저장 값을 PCB에 저장해두는 것**이다.

##### PCB는 어떻게 관리되나요?

> Linked List 방식으로 관리함
>
> PCB List Head에 PCB들이 생성될 때마다 붙게 된다. 주소값으로 연결이 이루어져 있는 연결리스트이기 때문에 삽입 삭제가 용이함.
>
> 즉, 프로세스가 생성되면 해당 PCB가 생성되고 프로세스 완료시 제거됨

<br />

이렇게 수행 중인 프로세스를 변경할 때, CPU의 레지스터 정보가 변경되는 것을 `Context Switching`이라고 한다.

<br />

<br />

#### Context Switching

> CPU가 이전의 프로세스 상태를 PCB에 보관하고, 또 다른 프로세스의 정보를 PCB에 읽어 레지스터에 적재하는 과정

보통 **인터럽트가 발생**하거나, 실행 중인 **CPU 사용 허가시간을 모두 소모**하거나, **입출력을 위해 대기**해야 하는 경우에 Context Switching이 발생

```
즉, 프로세스가 Ready → Running, Running → Ready, Running → Waiting처럼 상태 변경 시 발생!
```

<img src="https://user-images.githubusercontent.com/24764210/110253006-684e9080-7fcb-11eb-9647-00dc17a879d2.png" alt="3-4" style="zoom:67%;" />

scheduler dispatch(Ready → Running) : 준비상태에 있던 프로세스가 CPU를 할당받아서 실행되는 것

interrupt, CPU time slice 만료(Running → Ready) :  프로세스가 할당된 시간에 모든 명령을 실행하지 못하여 넘어가는 Time out

I/O 작업 및 할당(Running → Waiting) : I/O 작업이 필요하여 작업 완료 시 까지 대기 상태로 전이

System Call(Running → Waiting) : 또 다른 서비스 호출이 필요한 경우 대기 상태로 전이

<br />

<br />

##### Context Switching의 OverHead란?

overhead는 과부하라는 뜻으로 보통 안좋은 말로 많이 쓰인다.

하지만 프로세스 작업 중에는 OverHead를 감수해야 하는 상황이 있다.

```
프로세스를 수행하다가 입출력 이벤트가 발생해서 대기 상태로 전환시킴
이때, CPU를 그냥 놀게 놔두는 것보다 다른 프로세스를 수행시키는 것이 효율적
```

즉, CPU에 계속 프로세스를 수행시키도록 하기 위해서 다른 프로세스를 실행시키고 Context Switching 하는 것

CPU가 놀지 않도록 만들고, 사용자에게 빠르게 일처리를 제공해주기 위한 것이다.

<br />

<br />

### **커널과 쉘**

사용자가 입력한 명령어는 일련의 과정을 거쳐 하드웨어를 제어한다.

우리가 명령어를 입력하게 되면 컴퓨터 내부에서는 **쉘(Shell)** 이 이를 받아들이고, 명령어를 해석하여 **커널(Kernel)** 을 통해 하드웨어를 조작한다.

<br />

**사용자(명령) -> 쉘(해석) -> 커널(명령 수행 후 결과 전송) -> 쉘(해석) -> 사용자(결과 확인)**

![캡처](https://user-images.githubusercontent.com/24764210/111192257-373e1380-85fc-11eb-8ce1-e8e297f4f59e.JPG)

<img src="https://user-images.githubusercontent.com/24764210/108018518-537d7d80-705b-11eb-84d9-4db80b3ae24e.png" alt="os-5" style="zoom: 33%;" />

> **커널** : 소프트웨어와 하드웨어간의 커뮤니케이션을 관리하는 프로그램. 입출력을 관리하고 소프트웨어로부터의 요청(System Call)을 컴퓨터에 있는 하드웨어(CPU, 메모리, 저장장치등)가 처리 할 수 있도록 요청을 변환하는 역할. 
>
> **쉘** : 사용자와 운영체제간에 대화를 가능하게 해주는 `명령어 해석기 역할`.

<br />

<br />

<br />

<br />

<br />

[자료 출처]

http://blog.skby.net/%EB%AC%B8%EB%A7%A5%EA%B5%90%ED%99%98-context-switching/