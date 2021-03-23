# UDP(User Datagram Protocol)

* OSI 7 Layer의 Transport Layer(전송 계층)에 해당하는 프로토콜
* TCP와 자주 비교되는 프로토콜



## UDP 들어가기 전, TCP 다시 훑어보기

* 신뢰성 있는 데이터 통신을 가능하게 해주는 프로토콜
* 특징: Connection 연결(3 way-handshake) - 양방향 통신
* 데이터의 순차 전송을 보장
* Flow Control(흐름 제어)
* Congestion Control(혼잡 제어)
* Error Detection(오류 감지)
* Segment(세그먼트) - TCP 프로토콜의 PDU(Protocol Data Unit)

<img width="890" alt="스크린샷 2021-03-23 오전 1 23 43" src="https://user-images.githubusercontent.com/46706670/112092134-34ce5180-8bda-11eb-9af4-baca09069ee5.png">

* 전송의 신뢰성은 보장하지만 매번 Connection을 연결해서 시간 손실 발생
* 패킷을 조금만 손실해도 재전송



## UDP(User Datagram Protocol)

* TCP 보다 신뢰성이 떨어지지만 전송 속도가 일반적으로 빠른 프로토콜(순차 전송 X, 흐름 제어 X, 혼잡 제어 X)
* Connectionless(3 way-handshake X)
* Error Detection
* User Datagram - UDP 프로토콜의 PDU
* 데이터를 TCP처럼 쪼개지 않음(일반적으로 Application 단에서 쪼개줘야 함)
* 비교적 데이터의 신뢰성이 중요하지 않을 때 사용(ex. 영상 스트리밍)
* (TCP와 UDP의 최대 PDU 크기는 이론상 65,535바이트, but 실제로는 헤드 크기를 빼야함 + IP 헤드)
* (네트워크 상의 PDU 크기 중 가장 큰 크기를 MTU(Maximum Transmission Unit)이라고 함)

<img width="683" alt="스크린샷 2021-03-23 오전 1 31 48" src="https://user-images.githubusercontent.com/46706670/112092143-3861d880-8bda-11eb-8fd4-af66f4e2c0c4.png">



<img width="604" alt="스크린샷 2021-03-23 오전 1 33 59" src="https://user-images.githubusercontent.com/46706670/112092153-3dbf2300-8bda-11eb-91bf-e4c38fcd7831.png">



- TCP와 UDP는 왜 나오게 됐는가?

  1. IP의 역할은 Host to Host (장치 to 장치)만을 지원한다. 장치에서 장치로 이동은 IP로 해결되지만, 하나의 장비안에서 수많은 프로그램들이 통신을 할 경우에는 IP만으로는 한계가 있다.
  2. 또한, IP에서 오류가 발생한다면 ICMP에서 알려준다. 하지만 ICMP는 알려주기만 할 뿐 대처를 못하기 때문에 IP보다 위에서 처리를 해줘야 한다.

  - 1번을 해결하기 위하여 포트 번호가 나오게 됐고, 2번을 해결하기 위해 상위 프로토콜인 TCP와 UDP가 나오게 되었다.

  - *ICMP : 인터넷 제어 메시지 프로토콜로 네트워크 컴퓨터 위에서 돌아가는 운영체제에서 오류 메시지를 전송받는데 주로 쓰임

- 그렇다면 TCP와 UDP가 어떻게 오류를 해결하는가?

  - TCP : 데이터의 분실, 중복, 순서가 뒤바뀜 등을 자동으로 보정해줘서 송수신 데이터의 정확한 전달을 할 수 있도록 해준다.
  - UDP : IP가 제공하는 정도의 수준만을 제공하는 간단한 IP 상위 계층의 프로토콜이다. TCP와는 다르게 에러가 날 수도 있고, 재전송이나 순서가 뒤바뀔 수도 있어서 이 경우, 어플리케이션에서 처리하는 번거로움이 존재한다.

- UDP는 왜 사용할까?

  - UDP의 결정적인 장점은 데이터의 신속성이다. 데이터의 처리가 TCP보다 빠르다.
  - 주로 실시간 방송과 온라인 게임에서 사용된다. 네트워크 환경이 안 좋을때, 끊기는 현상을 생각하면 된다.

- DNS(Domain Name Service)에서 UDP를 사용하는 이유

  - Request의 양이 작음 -> UDP Request에 담길 수 있다.
  - 3 way handshaking으로 연결을 유지할 필요가 없다. (오버헤드 발생)
  - Request에 대한 손실은 Application Layer에서 제어가 가능하다.
  - DNS : port 53번
  - But, TCP를 사용할 때가 있다! 크기가 512(UDP 제한)이 넘을 때, TCP를 사용해야한다.



## DNS와 UDP

* **DNS과 UDP 통신 프로토콜을 사용함.**

DNS는 데이터를 교환하는 경우임

이때, TCP를 사용하게 되면, 데이터를 송신할 때까지 세션 확립을 위한 처리를 하고, 송신한 데이터가 수신되었는지 점검하는 과정이 필요하므로, Protocol overhead가 UDP에 비해서 큼.

------

DNS는 Application layer protocol임.

모든 Application layer protocol은 TCP, UDP 중 하나의 Transport layer protocol을 사용해야 함.

(TCP는 reliable, UDP는 not reliable임) / DNS는 reliable해야할 것 같은데 왜 UDP를 사용할까?

사용하는 이유

1. TCP가 3-way handshake를 사용하는 반면, UDP는 connection 을 유지할 필요가 없음.

2. DNS request는 UDP segment에 꼭 들어갈 정도로 작음.

   DNS query는 single UDP request와 server로부터의 single UDP reply로 구성되어 있음.

3. UDP는 not reliable이나, reliability는 application layer에 추가될 수 있음. (Timeout 추가나, resend 작업을 통해)

DNS는 UDP를 53번 port에서 사용함.

------

그러나 TCP를 사용하는 경우가 있음.

Zone transfer 을 사용해야하는 경우에는 TCP를 사용해야 함.

(Zone Transfer : DNS 서버 간의 요청을 주고 받을 떄 사용하는 transfer)

만약에 데이터가 512 bytes를 넘거나, 응답을 못받은 경우 TCP로 함.



# Quiz

* TCP와 UDP의 차이는 무엇입니까?

<br><br><br><br><br><br><br><br><br><br><br><br><br><br>

# 출처

* [10분 테코톡 - 르윈의 UDP](https://www.youtube.com/watch?v=ikDVGYp5dhg)
* [Reference Image1](https://www.gatevidyalay.com/transmission-control-protocol-tcp-header/)
* [Reference Image2](https://skminhaj.wordpress.com/2016/02/15/tcp-segment-vs-udp-datagram-header-format/)
* [Reference Image3](https://www.dpstele.com/snmp/transport-requirements-udp-tcp.php)
* [TCP와 UDP의 최대 패킷 크기](https://gammabeta.tistory.com/1878#:~:text=TCP%EC%99%80%20UDP%EC%9D%98%20%EC%B5%9C%EB%8C%80%20%ED%8C%A8%ED%82%B7%20%ED%81%AC%EA%B8%B0%EB%8A%94%20%EC%9D%B4%EB%A1%A0%EC%83%81%2065%2C535,%ED%81%AC%EA%B8%B0%EB%8A%94%201518%20%EB%B0%94%EC%9D%B4%ED%8A%B8%EC%9D%B4%EB%8B%A4.)
* [Tech-interview-for-developer - UDP란?](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Computer%20Science/Network/UDP.md#20190826%EC%9B%94-bym-udp%EB%9E%80)
* [신입 SW개발자 기술 면접 예상 질문 모음 2](https://preamtree.tistory.com/42)
* [네트워크 관련 면접 질문 모음](https://jw910911.tistory.com/42)







# Quiz 답안

* TCP와 UDP의 차이는 무엇입니까?

<img width="927" alt="스크린샷 2021-03-23 오후 1 21 54" src="https://user-images.githubusercontent.com/46706670/112092413-c76ef080-8bda-11eb-8d24-272c6eb0e778.png">

<img width="745" alt="스크린샷 2021-03-23 오후 1 23 23" src="https://user-images.githubusercontent.com/46706670/112092508-fb4a1600-8bda-11eb-92ff-c19e7769e5fb.png">

