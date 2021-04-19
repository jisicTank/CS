# Blocking & Non-Blocking, Sync & Async

I/O 작업은 Kernel level에서만 수행할 수 있다. 따라서, Process, Thread는 커널에게 I/O를 요청해야 한다.

<img width="204" alt="스크린샷 2021-04-19 오전 10 43 11" src="https://user-images.githubusercontent.com/46706670/115274999-3577ea80-a17c-11eb-8f03-3d32da9bf5bf.png">

<br><br>

이와 관련해 2006년 8월 29일 당시 IBM DeveloperWorks Library에 [Boost application performance using asynchronous I/O](https://developer.ibm.com/articles/l-async/)라는 article이 아래 도표와 함께 등장했는데, 대략 AIO(Asynchronous I/O)를 소개하고 권장하는 글이었다.

<img width="532" alt="스크린샷 2021-04-19 오전 10 46 58" src="https://user-images.githubusercontent.com/46706670/115275013-37da4480-a17c-11eb-96ef-b0df5f7c8fca.png">



## Blocking / Non-blocking

**호출되는 함수가 바로 리턴하느냐 마느냐가 관심사**

**주로 I/O의 읽기 쓰기에서 사용**

<br><br>

* 호출된 함수가 자신이 할 일을 모두 마칠 때까지 제어권을 계속 가지고서 호출한 함수에게 돌려주지 않으면 Block
* 호출된 함수가 자신이 할 일을 채 마치지 않았더라도 바로 제어권을 건네주어 호출한 함수가 다른 일을 진행할 수 있도록 해주면 Non-block



## Synchronous / Asynchronous

**호출되는 함수의 작업 완료 여부를 누가 신경쓰느냐가 관심사**

<br><br>

* 호출된 함수의 수행 결과 및 종료를 호출한 함수(호출된 함수뿐 아니라 호출한 함수도 함께) 신경 쓰면 Synchronous
* 호출된 함수의 수행 결과 및 종료를 호출된 함수 혼자 직접 신경 쓰고 처리한다면(call back fn) Asynchronous

<br><br>

 ### Synchronous

<img width="721" alt="스크린샷 2021-04-19 오전 10 57 06" src="https://user-images.githubusercontent.com/46706670/115275073-49235100-a17c-11eb-9a56-aaa3cf9efff2.png">

* Thread1이 작업을 시작시키고, Task1이 끝날 때까지 기다렸다 Task2를 시작
* 작업 요청을 했을 때 요청의 결과값을 직접 받음
* 요청의 결과값이 return값과 동일
* **호출한 함수가 작업 완료를 신경씀**

<br><br>

### Asynchronous

<img width="725" alt="스크린샷 2021-04-19 오전 10 57 15" src="https://user-images.githubusercontent.com/46706670/115275080-4b85ab00-a17c-11eb-9374-210cd953edd6.png">

* Thread1이 작업을 시작시키고, 완료를 기다리지 않고 Thread1은 다른 일을 처리함
* 작업 요청을 했을 때 요청의 결과값을 간접적으로 받음
* 요청의 결과값이 return값과 다를 수 있음
* 해당 요청 작업은 별도의 스레드에서 수행
* 호출된 함수(callback fn)이 작업 완료를 신경씀

<br><br>

## 교차 Table

<img width="532" alt="스크린샷 2021-04-19 오전 10 46 58" src="https://user-images.githubusercontent.com/46706670/115275013-37da4480-a17c-11eb-96ef-b0df5f7c8fca.png">

<br><br>

### Blocking + Synchronous & Non-blocking + Asynchronous

<img width="820" alt="스크린샷 2021-04-19 오전 11 03 23" src="https://user-images.githubusercontent.com/46706670/115275086-504a5f00-a17c-11eb-8f33-acb3f9674a8d.png">

> Case Study: 대표님, 개발자 좀 더 뽑아주세요..
>
> Blocking & Synchronous
>
> 나 : 대표님, 개발자 좀 더 뽑아주세요..
> 대표님 : 오케이, 잠깐만 거기 계세요!
> 나 : …?!!
> 대표님 : (채용 공고 등록.. 지원자 연락.. 면접 진행.. 연봉 협상..)
> 나 : (과정 지켜봄.. 궁금함.. 어차피 내 일 하러는 못 가고 계속 서 있음)
>
> <img width="596" alt="스크린샷 2021-04-19 오후 1 17 14" src="https://user-images.githubusercontent.com/46706670/115275258-88ea3880-a17c-11eb-91a0-0c608dc73666.png"><br><br>
>
> Non-blocking & Asynchronous
>
> 나 : 대표님, 개발자 좀 더 뽑아주세요..
> 대표님 : 알겠습니다. 가서 볼 일 보세요.
> 나 : 넵!
> 대표님 : (채용 공고 등록.. 지원자 연락.. 면접 진행.. 연봉 협상..)
> 나 : (열일중..)
> 대표님 : 한 분 모시기로 했습니다~!
> 나 : 😍



<br><br>

### Non-blocking + Synchronous

<img width="810" alt="스크린샷 2021-04-19 오전 11 04 38" src="https://user-images.githubusercontent.com/46706670/115275181-74a63b80-a17c-11eb-8cbb-eb5006bbbeca.png">



> Case Study: 대표님, 개발자 좀 더 뽑아주세요..
>
> Non-blocking & Synchronous
>
> 나 : 대표님, 개발자 좀 더 뽑아주세요..
> 대표님 : 알겠습니다. 가서 볼 일 보세요.
> 나 : 넵!
> 대표님 : (채용 공고 등록.. 지원자 연락.. 면접 진행.. 연봉 협상..)
> 나 : 채용하셨나요?
> 대표님 : 아직요.
> 나 : 채용하셨나요?
> 대표님 : 아직요.
> 나 : 채용하셨나요?
> 대표님 : 아직요~!!!!!!
>
> <br>
>
> <img width="592" alt="스크린샷 2021-04-19 오후 1 17 36" src="https://user-images.githubusercontent.com/46706670/115275266-8ab3fc00-a17c-11eb-871b-b473676f3987.png">





<br><br>

### Blocking + Asynchronous

<img width="823" alt="스크린샷 2021-04-19 오전 11 05 03" src="https://user-images.githubusercontent.com/46706670/115275187-77089580-a17c-11eb-940f-56b98a0fb767.png">



> Blocking & Asynchronous
>
> 나 : 대표님, 개발자 좀 더 뽑아주세요..
> 대표님 : 오케이, 잠깐만 거기 계세요!
> 나 : …?!!
> 대표님 : (채용 공고 등록.. 지원자 연락.. 면접 진행.. 연봉 협상..)
> 나 : (안 궁금함.. 지나가는 말로 여쭈었는데 붙잡혀버림.. 딴 생각.. 못 가고 계속 서 있음)

<br>

이 케이스에서는 방식의 이점이 별로 없어 의도적으로 사용하는 경우는 거의 존재하지 않다. 그러나 대표적인 케이스로 Node.js와 MySQL의 조합에서 이 방식이 구현되곤 하는데 Node.js가 Async로 작동하더라도 결국 DB 호출시 MySQL이 제공하는 드라이버가 Blocking 방식이라 이와 같은 방법이 구현되는 것이다. 이는 Java의 JDBC도 유사하다고 한다. (다만 Node.js는 싱글 쓰레드 루프 기반이라 멀티 쓰레드 기반인 Java Servlet 컨테이너보다 문제가 두드러져 보인다.)

**NonBlocking-Async 방식을 쓰는데 그 과정 중에 하나라도 Blocking으로 동작하는 놈이 포함되어 있다면 의도하지 않게 Blocking-Async로 동작**할 수 있다.

<br><br>

# 출처

* [[카카오 면접\] Blocking I/O, Syncronous Non-Blocking I/O, Asyncronous Non-Blocking I/O](https://bk-investing.tistory.com/38)
* [동기 vs 비동기 (feat. blocking vs non-blocking)](https://velog.io/@wonhee010/%EB%8F%99%EA%B8%B0vs%EB%B9%84%EB%8F%99%EA%B8%B0-feat.-blocking-vs-non-blocking)
* [커널 - Wikipedia](https://ko.wikipedia.org/wiki/%EC%BB%A4%EB%84%90_(%EC%BB%B4%ED%93%A8%ED%8C%85)#:~:text=%EC%BB%B4%ED%93%A8%ED%84%B0%20%EA%B3%BC%ED%95%99%EC%97%90%EC%84%9C%20%EC%BB%A4%EB%84%90(kernel,%ED%95%B5%EC%8B%AC(%E6%A0%B8%E5%BF%83)%EC%9D%B4%EB%9D%BC%EA%B3%A0%EB%8F%84%20%ED%95%9C%EB%8B%A4.)

