# Redis(REmote Dictionary Server)

### 1. Radis?

레디스(Redis)는 고성능 key-value 저장소로서 리스트, 해시, 셋 정렬된 셋 등 여러 형식의 자료구조를 지원하는 NoSQL. 메모리에 상주하면서 RDBMS의 캐시 솔루션으로서 주로 사용되며 라인, 삼성전자, 네이버, Stackoverflow, 인스타그램 등 여러 IT 대기업에서도 사용하는 검증된 오픈소스 솔루션이다.

출처의 필자는 회사에서 RDBMS의 read부하를 줄이기 위해 Redis 클러스터 형태로 운영하고 있다. 

<br>

### 2. 사용용도

 Redis는 Message Queue, Shared Memory, Remote Dictionary 용도로 사용할 수 있다. 특히 Remote Dictionary로서 RDBMS의 캐시 솔루션으로 사용 용도가 굉장히 높다. RDBMS에서 SELECT 쿼리문을 날려 특정 데이터들을 FETCH했을 때, RDBMS의 구조상 Memory에서 읽어들이는 것이 DISK에서 데이터를 꺼내오는 것보다 천배 가량 더 빠르기 때문. 

 이 때 Redis같은 유연한 자료구조를 가지는 인메모리 Key-value 솔루션을 사용하여 DB 부하의 Read 연산의 부하를 분산시키는 데 적용하는 것은 바람직한 것 같습니다. (DB 캐시 적중률을 높이면 되겠지만 어느 정도 한계가 있다고 생각합니다) 

> Message Queue?
>
> 프로세스 또는 프로그램 인스턴스가 데이터를 서로 교환할때 사용하는 통신방법. 메시지 지향 미들웨어(MOM: Message Oriented Middleware)를 구현한 시스템.
>
> Shared memory ?
>
> 보통 프로세스에서 사용되는 메모리영역은 해당 프로세스만이사용할수 있다. 하지만 때때로 여러개의 프로세스가 특정 메모리영역을 사용했으면 하는때가 있을것이다. 
>
> System V IPC 설비중의 하나인 "공유메모리"를 통해서 이러한일을 할수있다.
>
> 출처: https://unabated.tistory.com/entry/공유-메모리-shared-memory [랄라라]

<br>

### 3. Redis 특징

- **Key-Value Store**

 레디스는 거대한 맵(Map) 데이터 저장소이다. Key와 value가 매핑된 단순한 맵 데이터 저장소로서 데이터를 레디스에 쉽고 편하게 읽고 쓸 수 있다. 장점은 익히기 쉽고 직관적인 데 있고 단점은 Key-value 형태로 저장된 데이터를 레디스 자체내에서 처리하는 것이 어렵다는 것.



![img](https://t1.daumcdn.net/cfile/tistory/9986063F5CF286F82C)

<br>

- **다양한 데이터 타입**

Key로 참조되는 Value 타입을 다양하게 지정하여 저장 가능. **List, String, Set, Sorted set** 등 여러 데이터를 저정하여 손쉽고 편리하게 데이터를 저장이 가능하다.

<br>

- **Persistence**

Redis는 데이터를 disk에 저장할 수 있다. 따라서 Redis는 서버가 shutdown된 후에 restart 하더라도 disk에 저장해놓은 데이터를 다시 읽어서 데이터가 유실되지 않는다. redis의 데이터를 disk에 저장하는 방식은 snapshot, AOF 방식이 있다.

Snapshot : 스냅샷은 RDB에서도 사용하고 있는 어떤 특정 시점의 데이터를 DISK에 옮겨담는 방식을 말한다. Blocking 방식의 SAVE와 Non-blocking 방식의 BGSAVE 방식이 있다.

AOF : Redis의 모든 write/update 연산 자체를 모두 log 파일에 기록하는 형태. 서버가 재시작할 시 write/update를 순차적으로 재실행, 데이터를 복구한다.

레디스 공식문서에서의 권장사항은 RDBMS의 rollback 시스템같이 두 방식을 혼용해서 사용하는 것입니다. 주기적으로 snapshot으로 백업하고 다음 snapshot까지의 저장을 AOF 방식으로 수행하는 것이죠.

<br>

- **ANSI C로 작성** 

C언어로 작성되어 Java와 같이 가상머신 위에서 동작하는 언어에서 발생하는 성능 문제에 대해 자유롭다. 곧바로 기계어로 동작하지 않고 어떤 가상의 머신 위에서 인터프리터된 언어로 가동하는 경우에는 가비지컬렉션(Garbage Collection) 동작에 따른 성능 문제가 발생할 수 밖에 없다. 하지만 C언어로 작성된 Redis는 이런 이슈에 대해 자유롭다. 

> Garbage Collection?
>
> 메모리 관리 기법중 하나로, 프로그램이 동적으로 할당했던 메모리 영역 중에서 필요없게 된 영역을 해제하여 다른 용도로 사용할 수 있게 해주는 기능.

<br>

- **서버 측 복제 및 샤딩을 지원**

읽기 성능 증대를 위한 서버 측 복제를 지원하고 쓰기 성능 증대를 위한 클라이언트 측 샤딩을 지원한다.

> 샤딩?
>
> 관계형 데이터베이스에서 대량의 데이터를 처리하기 위해 데이터를 분할하는 것. 수평 분할(Horizontal Partioning)과 동일

<br>

### 4. Redis 아키텍처



- **Redis Topology**

레디스는 아래 그림과 같이 Master-slave 형태로 데이터를 복제해서 운영할 수 있습니다. 이 master-slave 간의 복제는 non-blocking 상태로 이루어진다.

> Master-slave?
>
> 한 명이 관리자가 되어 다른 모든 것을 제어
>
> non-blocking
>
> 네트워크 통신이 완료될 때까지 기다리지 않고 다른 작업을 수행할 수 있다. 블로킹은 그 반대

<br>

- **Redis Sharding**

레디스에서 데이터를 샤딩하여 레디스의 read성능을 높일 수 있습니다. 예로들어 #1~#999, #1000~#1999 ID 형태로 데이터를 나누어서 데이터의 용량을 확장하고 각 서버에 있는 Redis의 부하를 나누어 줄일 수 있습니다. 

<br>

- **Redis Cluster**

 레디스는 이전에는 Clustering을 지원하지 않았지만 Clustering을 지원하면서 대부분의 회사가 Redis를 클러스터로 묶어서 가용성 및 안정성있는 캐시 매니져로서 사용하고 있다. Single Instance로서 레디스를 사용할 때는 Sharding이나 Topology로서 커버해야했던 부분을 Clustering을 이용함으로서 어플리케이션을 설계하는 데 좀 더 수월해졌다고 볼 수 있습니다.

> 그래서 Redis Cluster란?
>
> 여러 레디스 노드상에서 **데이터를 자동으로 분할**하는 방법을 제공. 레디스 클러스터로 얻을 수 있는 장점은 간단히 다음과 같다.
>
> - **데이터셋을 여러 노드로 자동 분할**하는 기능
> - 전체 노드 중 일부에 장애가 발생하거나 나머지 클러스터와 통신할 수 없는 경우에 조작할 수 있는 기능



![img](https://t1.daumcdn.net/cfile/tistory/99ACC94D5CF28B282E)



<br>

<br>

### 5. 사용시 주의할 점



- **장애가 났을 경우 그에 대비한 운영 플랜이 세워줘야 함**

 Redis는 인메모리 데이터 저장소로서 서버에 장애가 났을 경우 데이터 유실이 발생한다. 따라서 위의 Snapshot과 AOF 기능을 통한 복구 시나리오가 제대로 세워져 있어야 데이터 유실에 대비한 사고에 대처할 수 있다.

<br>

- **캐시 솔루션으로 사용할 시 잘못된 데이터가 캐시되는 것을 방지, 예방해야 함**

 회사에서 Redis를 운영 중 전에 개발자의 실수로 작성된 로직으로 캐시 데이터가 잘못 캐싱되어 올바르지 않은 데이터가 FETCH되어 한동안 데이터가 꼬이는 일이 있다. 레디스와 캐싱하고자 하는 데이터 저장소의 데이터가 서로 일치하는 지 주기적인 모니터링과 이를 방지하기 위한 사내 솔루션을 개발하는 것이 좋다

<br>

---

### Reference

- https://engkimbs.tistory.com/869 [새로비]
- 논블로킹:https://alnet.tistory.com/9
- 가비지컬렉션: https://velog.io/@litien/%EA%B0%80%EB%B9%84%EC%A7%80-%EC%BB%AC%EB%A0%89%ED%84%B0GC
- 샤딩:https://sophia2730.tistory.com/entry/Databases-Database-Sharding%EC%83%A4%EB%94%A9
- 레디스 클러스터:https://medium.com/garimoo/redis-documentation-2-%EB%A0%88%EB%94%94%EC%8A%A4-%ED%81%B4%EB%9F%AC%EC%8A%A4%ED%84%B0-%ED%8A%9C%ED%86%A0%EB%A6%AC%EC%96%BC-911ba145e63