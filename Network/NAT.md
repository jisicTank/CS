# NAT(Network Address Translation)

<br><br>

## NAT란?

IP 패킷에 있는 출발지 및 목적지의 IP 주소와 TCP/UDP 포트 숫자 등을 바꿔 재기록하면서 네트워크 트래픽을 주고 받게 하는 기술

(NAT가 호스트 간의 통신에 있어서 복잡성을 증가시킬 수 있으므로 네트워크 성능에 영향을 줄 수 있다.)

<br><br>

## NAT는 왜 쓸까?

* IP 주소 절약과 보안

<br>

### IP 주소 절약

NAT 기술을 이용하면 하나의 공인 IP 주소를 사용해 여러 대의 호스트가 인터넷에 접속할 수 있다. 

> 예를 들어 대부분의 경우 집에 인터넷 회선을 개통하고 공유기를 사용해 여러 PC를 이용하는 경우이다. 이 방법은 인터넷 회선의 공인 IP 하나로 인터넷을 이용하는 것이며, 여러 PC는 사설 IP로 동작된다. 
>
> 이러한 방법은 인터넷 공유기에 NAT 기능이 탑재되어 있기 때문에 가능한데, 부족한 공인 IP를 절약하는 효과가 있어서 IPv6가 예상보다 많이 이용되지 않는 결과를 초래했다.

<br><br>

### 보안

NAT 동작의 특성상 IP를 숨길 수 있는 기능이 있다. 예를 들어, 라우터(또는 공유기 등) 외부로 트래픽이 나갈 때는 사설 IP가 공인 IP로 바뀌므로 외부의 공격자가 라우터 안 쪽에 있는 사설 IP를 모르게 된다. 때문에 최종 목적지 호스트로의 공격이 어려워져 내부 네트워크 및 호스트를 보호할 수 있다.

> *** 참고: 사설 IP 대역
>
> 인터넷 기술 표준을 정의하는 IETF의 RFC 1918 문서에 따르면, 전 세계 공통으로 다음과 같은 주소들이 사설 IP 주소 대역임을 정의하고 있다.(공인 IP로 사용 불가능한 대역, 사설 IP 대역으로만 사용)
>
> * 단일 A 클래스 사설 네트워크 대역: 10.0.0.0 ~ 10.255.255.255
> * 16개의 인접한 B 클래스 사설 네트워크 대역: 172.16.0.0 ~172.31.255.255
> * 256개의 인접한 C 클래스 사설 네트워크 대역: 192.168.0.0 ~ 192.168.255.255



<br><br><br><br>

## 동작 원리

집에서 인터넷 공유기를 통해 외부의 웹 서버로 접근하려는 경우, 해당 요청 패킷은 반드시 공유기(게이트웨이)를 거치게 되어 있다. 이 때 출발지의 사설 IP 주소가 그대로 외부 인터넷에 나가게 되면, 수신 측인 웹 서버는 알 수 없는 사설망의 IP 주소이므로 응답을 어디로 보내줘야 하는지 알 수 없다.

<img width="589" alt="스크린샷 2021-04-13 오전 1 24 44" src="https://user-images.githubusercontent.com/46706670/114536092-b4a48480-9c8b-11eb-87c0-5f0fad35b443.png">



따라서 NAT는 다음과 같은 과정을 거치게 된다.

1. 패킷 헤더에 출발지와 목적지의 주소를 기록한다. 이때, 출발지는 자신의 사설망 IP로 기록한다.
2. 기본 게이트웨이(공유기 등) 에서는 외부로 나가는 패킷을 인식하게 되면, 출발지의 IP 주소를 게이트웨이 자신의 공인 IP 주소로 변경한다. 이 때 별도의 NAT 테이블을 보관한다.
3. 웹 서버에서 수신한 데이터를 처리한 후, 응답으로 보내는 패킷에 출발지와 목적지의 IP 주소를 다시 기록해 보낸다. 특히 이때, 목적지의 IP 주소는 기본 게이트웨이의 공인 IP 주소가 된다.
4. 기본 게이트웨이에서 웹 서버가 보낸 패킷을 받으면, 기록해뒀던 NAT 테이블을 참조해 최종 목적지인 호스트의 사설 IP 주소로 변경해 해당 호스트로 패킷을 전달한다.

<br><br>

단, 사설 네트워크에 한 대의 호스트가 아닌 여러 대의 호스트가 같은 목적지와 통신하고자 하면, 되돌아오는 패킷의 최종 목적지가 어디가 되어야 하는지 모르는 혼선이 생길 수 있다. 이를 해결하기 위해 별도의 추가 포트를 설정해 패킷을 구분하는 PAT(= NAPT) 방식을 사용하게 된다.(아래 NAT 종류 구분에서 자세히 설명)

NAT 과정에서는 패킷에 변화가 생기기 때문에 IP나 TCP/UDP의 체크섬(Checksum)도 다시 계산되어 재기록해야 하기 때문에 필연적으로 네트워크의 성능에 영향을 미치게 된다.

<br><br><br><br>

## 주소 할당 방식에 따른 NAT 종류 구분

<br><br>

### Static NAT(1:1 NAT)

* 공인 IP 주소와 사설 IP 주소가 1:1로 매칭이 되는 방식
* 공인 IP 주소 절약 효과는 없음
* 주로 사설 IP 주소를 사용하는 서버가 여러가지 역할을 할 때, 포트포워딩을 목적으로 사용함

> 포트 포워딩(Port Forwarding): 예를 들어 하나의 서버에 여러 서비스를 운영 중인 경우, 특정 서비스에 임의의 포트를 지정해 해당 포트에 특정 서비스의 경로를 지정해주는 일종의 교통정리를 해주는 역할을 하는 기능

<br><br>

### Dynamic NAT(N:N NAT)

* 여러 개의 공인 IP 주소 대비 사설 IP 개수가 더 많을 경우 사용하는 방식

<br><br>

### PAT(Port Address Translation = NAPT : Network Address Port Translation(1: N))

* 공인 IP 주소 1개에 사설 IP 주소 여러개가 매칭 되는 방식
* 사설 네트워크 내 각 호스트에 임의의 포트번호를 지정해 사설 IP와 해당 포트번호를 공인 IP 주소와 해당 포트 번호로 매칭/치환하는 방식
* 포트 번호를 지정하는 이유는 외부에서 들어오는 패킷을 내부 네트워크 내 올바른 목적지로 전달해주기 위함

<img width="688" alt="스크린샷 2021-04-13 오전 1 41 19" src="https://user-images.githubusercontent.com/46706670/114536113-ba01cf00-9c8b-11eb-91a1-d39aaff9c56f.png">

PAT 예제: 7575 포트와 22 포트를 활용, 호스트까지 패킷이 전달된다.(Wikimedia)



<br><br><br><br>

## 패킷 방향에 따른 NAT 종류 구분

<br><br>

### SNAT(Source Network Address Translation)

* 내부에서 외부로 나가는 패킷의 출발지(Source) IP 주소를 게이트웨이의 공인 IP 주소로 변경하는 것
* Source NAT, SNAT, IP 마스커레이드라고도 불림
* 대표적으로 사용되는 장비로 가정에서 흔히 쓰는 인터넷 공유기

<br><br>

### DNAT(Destination Network Address Translation)

* 외부에서 내부로 들어오는 패킷에 있는 목적지(Destination) IP 주소를 변경하는 것
* 대표적으로 사용되는 장비로 방화벽 또는 로드밸런서(Load Balancer)

<br><br>

### Twice-NAT

* 출발지 주소와 목적지의 주소가 모두 바뀌는 방식

* 주로 서로 다른 두 지점간 연결을 위한 중간 네트워크 허브 구조를 이용할 때 사용

  

<img width="689" alt="스크린샷 2021-04-13 오전 1 49 21" src="https://user-images.githubusercontent.com/46706670/114536125-bd955600-9c8b-11eb-8462-3a4d80cb0bbd.png">

<br><br>

### 헤어핀(Hairpin NAT) 또는 NAT Loopback

* 내부에서 내부로의 NAT
* 위 그림에서 서버 B의 트래픽이 외부망을 거치지 않고 바로 서버 A로 접근하는 방식을 의미
  (e.g. IP 주소가 아닌, 도메인 주소를 가지고 서비스 중인 웹 서버인 경우)

<br><br><br><br>

# 출처

* [NAT - 네트워크 주소 변환](https://www.stevenjlee.net/2020/07/11/%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-nat-network-address-translation-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%A3%BC%EC%86%8C-%EB%B3%80%ED%99%98/)