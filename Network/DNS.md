# DNS(Domain Name System)

<br>

## IP 주소와 Hosts

* 클라이언트가 서버로 접속(통신)하기 위해서는 IP 주소를 반드시 알아야 한다.
* 네트워크에 연결되어 있는 컴퓨터(장치)를 Host라고 지칭한다.
* IP 주소를 하나하나 기억하기 어렵기 때문에, Domain을 사용한다.
* 모든 운영체제에서는 hosts라는 파일에 domain과 IP 주소를 기록한다.(DNS가 아님)
* [hosts in the wikipedia](https://en.wikipedia.org/wiki/Hosts_(file))
* hosts 파일을 변조함으로써 손쉽게 원하지 않은 페이지로 이동하게 될 수 있음

<br>

### Before DNS

* `Stanford Research Institute`에서 수작업으로 호스트를 관할해 처리

<br><br>

### After DNS

* Domain Name System Server

<img width="796" alt="스크린샷 2021-03-22 오후 11 59 29" src="https://user-images.githubusercontent.com/46706670/112091955-d1dcba80-8bd9-11eb-84e4-9e251ca5c565.png">

* Internet Service Privider(ISP)가 DNS Server IP 제공 -> 보안 위험 잔존
* [Public DNS Server - Google Search](https://www.google.com/search?q=Public+dns+Server&oq=Public+dns+Server&aqs=chrome..69i57.5025j0j7&sourceid=chrome&ie=UTF-8)

> DNS Server 변경 방법
>
> Mac: 설정 - 네트워크 - 고급 - DNS - 원하는 DNS Server IP 추가
>
> Windows - 제어판 - 네트워크와 인터넷 - 네트워크와 공유센터 - 아답터 설정 변경 - 커넥션즈 우측 - Properties - Internet Protocol Version 4 click - Use the follwing DNS server address 변경

<br><br>

### DNS 내부 구조

* Domain Name Server는 계층적으로 존재하며 동작함.
* blog.example.com. (맨 뒤의 `.`은Root domain, 거꾸로 Top-level, Second-level, sub domain)
* 각각의 레벨에 따른 서버가 존재하고, 각 레벨의 서버는 하위 레벨 서버의 목록과 IP를 가지고 있음.
* Root 부터 하위 레벨로 내려가며 원하는 전체 도메인에 해당하는 서버를 찾아감.

<br>

#### Domain 등록 과정과 원리

* 전세계에 존재하는 13개의 Root Name Server
* Registry 등록소(Top-level domain) - `.com`, `.org`, `.kr` 등
* Registar 등록대행자
* Resistrant 등록자
* Record Type에 따라 마지막 최종 도메인, IP 주소에 대해서만 `A` 로 저장함.
* Client는 ISP에 따라 DNS Server를 통해 Root Name Server로 Domain에 해당하는 Server의 IP를 찾아감.
* DNS Server가 반복적으로 authoritave name server - server의 IP를 찾아 클라이언트에게 제공함.

<img width="900" alt="스크린샷 2021-03-23 오전 12 32 51" src="https://user-images.githubusercontent.com/46706670/112091967-da34f580-8bd9-11eb-9ee1-873abd85602c.png">

* nslookup naver.com(Terminal) - 도메인 & IP 주소 조회
* DNS Server는 Cache를 통해 반복적으로 Server의 주소를 찾아가는 것을 방지함.
* nslookup -type=ns naver.com

<br>

#### CNAME

* [What is DNS record type in Wikipedia](https://en.wikipedia.org/wiki/List_of_DNS_record_types)
* 도메인을 다른 도메인으로 가리키게 하는 방법

<img width="352" alt="스크린샷 2021-03-23 오전 12 58 44" src="https://user-images.githubusercontent.com/46706670/112091977-def9a980-8bd9-11eb-8456-085348f5c364.png">



<br><br>



## Github에 Domain 붙이기

<img width="892" alt="스크린샷 2021-03-23 오전 1 04 00" src="https://user-images.githubusercontent.com/46706670/112092003-eae56b80-8bd9-11eb-8111-f6674d140e0d.png">

* Settings - Pages - Custom Domain - DNS Server(ex.Freenom)에 Github IP 주소 세팅

<br><br><br>

# 출처 

* [WEB2-Domain Name System - 생활코딩](https://www.youtube.com/watch?v=zrqivQVj3JM&list=PLuHgQVnccGMCI75J-rC8yZSVGZq3gYsFp)

