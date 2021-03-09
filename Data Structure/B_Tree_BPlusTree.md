# B-Tree & B+ Tree

<br>

> **이진 트리**는 하나의 부모가 두 개의 자식밖에 가지질 못하고, 균형이 맞지 않으면(skewed Tree) 검색 효율이 선형검색 급으로 떨어진다. 하지만 이진 트리 구조의 간결함과 균형만 맞다면 검색, 삽입, 삭제 모두 O(logN)의 성능을 보이는 장점이 있기 때문에 계속 개선시키기 위한 노력이 이루어지고 있다.



## Balanced Tree

* 삽입과 삭제시 필요하면 스스로 균형을 유지함
* AVL Tree, 2-3(-4) Tree, Red-Black Tree, B-Tree...
* **항상 log(N)의 검색 성능!**

<br><br>

## B Tree

- 하나의 노드에 여러가지가 배치되는 트리구조
- 한 노드에 M개의 자료가 배치되면 M차 B-Tree
  - M이 짝수냐 홀수냐에 따라 알고리즘이 다름

* 데이터베이스, 파일 시스템에서 널리 사용되는 트리 자료구조의 일종
* 이진 트리를 확장해서, **트리의 균형을 맞춰주는 것이 B Tree의 핵심 기능!**
* **2개 이상 더 많은 수의 자식을 가질 수 있도록 일반화된 특징!**

<br>

```
대량의 데이터를 처리해야 할 때, 검색 구조의 경우 하나의 노드에 많은 데이터를 가질 수 있다는 점은 상당히 큰 장점이다.

대량의 데이터는 메모리보다 블럭 단위로 입출력하는 하드디스크 or SSD에 저장해야하기 때문!

ex) 한 블럭이 1024 바이트면, 2바이트를 읽으나 1024바이트를 읽으나 똑같은 입출력 비용 발생. 따라서 하나의 노드를 모두 1024바이트로 꽉 채워서 조절할 수 있으면 입출력에 있어서 효율적인 구성을 갖출 수 있다.

→ B-Tree는 이러한 장점을 토대로 많은 데이터베이스 시스템의 인덱스 저장 방법으로 애용하고 있음
```

<br><br>

**5차 B-Tree**

<img width="927" alt="스크린샷 2021-03-08 오후 1 26 51" src="https://user-images.githubusercontent.com/46706670/110274369-07967680-8012-11eb-8dcd-e01461ea4337.png">

<br>

### 규칙

- 노드의 자료수가 N이면, 자식 수는 N+1이어야 함
- 각 노드의 자료는 정렬된 상태여야함
- 루트 노드는 적어도 2개 이상의 자식을 가져야함
- 루트 노드를 제외한 모든 노드는 적어도 M/2개의 자료를 가지고 있어야함
- 외부 노드로 가는 경로의 길이는 모두 같음.
- 입력 자료는 중복 될 수 없음



<br><br><br>

## B+ Tree

데이터의 빠른 접근을 위한 **인덱스 역할만 하는 비단말 노드(not Leaf)**가 추가로 있음

(기존의 B-Tree와 데이터의 연결리스트로 구현된 색인구조)

B-Tree의 변형 구조로, **<u>index 부분</u>**과 **<u>leaf 노드로 구성된 순차 데이터 부분</u>**으로 이루어진다. 인덱스 부분의 key 값은 leaf에 있는 key 값을 직접 찾아가는데 사용함.

<img width="688" alt="스크린샷 2021-03-08 오전 11 33 58" src="https://user-images.githubusercontent.com/46706670/110267793-ddd65300-8003-11eb-9eb3-4c0c04d44d51.png">





##### 장점

> 블럭 사이즈를 더 많이 이용할 수 있음 (key 값에 대한 하드디스크 액세스 주소가 없기 때문)
>
> leaf 노드끼리 연결 리스트로 연결되어 있어서 범위 탐색에 매우 유리함

##### 단점

> B-tree의 경우 최상 케이스에서는 루트에서 끝날 수 있지만, B+tree는 무조건 leaf 노드까지 내려가봐야 함



<img width="682" alt="스크린샷 2021-03-08 오전 11 34 41" src="https://user-images.githubusercontent.com/46706670/110267808-e595f780-8003-11eb-9152-379fbf01fcca.png">



<img width="675" alt="스크린샷 2021-03-08 오전 11 35 04" src="https://user-images.githubusercontent.com/46706670/110267817-ea5aab80-8003-11eb-8a46-cf25162d1733.png">





## B Tree & B+Tree


B-tree는 각 노드에서 key와 data 모두 들어갈 수 있고, data는 disk block으로 포인터가 될 수 있음

B+tree는 각 노드에서 key만 들어감. 따라서 data는 모두 leaf 노드에만 존재

B+tree는 add와 delete가 모두 leaf 노드에서만 이루어짐



**참고자료** : [B tree와 B+tree](https://wangin9.tistory.com/entry/B-tree-B-tree)

[](https://velog.io/@mongle/Data-structure-B-tree-B-tree)



# 출처

* https://github.com/gyoogle/tech-interview-for-developer/
* [이진트리, b트리, b*트리, b+트리 - T.SteveTV(Youtube)](https://www.youtube.com/watch?v=b2Ly05Fn7ks)
* [B-Tree의 개념](https://www.youtube.com/watch?v=WBqKyrL6u-Q)