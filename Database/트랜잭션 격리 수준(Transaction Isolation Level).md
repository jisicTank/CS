# 트랜잭션 격리수준(Transaction Isolation Level)

### Isolation level

 동시에 여러 트랜잭션이 처리될 때, 특정 트랜잭션이 다른 트랜잭션에서 변경하거나 조회한 데이터를 볼 수 있도록 허용 여부를 결정하는 것

 격리수준은 크게 4가지로 나뉜다.

- READ UNCOMMITTED
- READ COMMITTED
- REPEATABLE READ
- SERIALIZABLE



---

### Read Uncommitted(레벨 0)

각 트랜잭션에서의 변경 내용이 `COMMIT`이나 `ROLLBACK` 여부에 상관 없이 다른 트랜잭션에서 값을 읽을 수 있다.

<img src="https://blog.kakaocdn.net/dn/u1BKr/btqEcois9nW/QgZsUlGnEHWoNHakmJ5g21/img.png" alt="img" style="zoom:80%;" />

```
1. 트랜잭션 1을 시작한다.
2. 트랜잭션 2를 시작한다.
3. 트랜잭션 1이 ID = 1, VAL = 'MIN'인 데이터의 VAL을 KIM으로 변경했다.
4. 트랜잭션 2가 ID = 1을 조회한다. VAL = 'KIM'이 조회되었다.
5. 트랜잭션 1, 2가 종료된다.
```



- **발생 문제점** : Dirty Read

  - Dirty Read

    1. UPDATE 반영 전에 읽는 오류

    - 순서 5번에서 오류가 생겨 롤백이 되었다고 가정하자. `ID = 1`은 다시 MIN 이 된다.
    - 그러나 롤백 전인 순서 4번은 여전히 `VAL = 'KIM'` 으로 인식할 것이다.

    

    2. INSERT 반영 전에 읽는 오류

    - 트랜잭션 1이 특정 데이터를 INSERT한다.
    - 트랜잭션 2가 그 데이터를 읽고 로직을 수행한다.
    - 트랜잭션 1 수행 중 오류가 생겨 롤백된다. INSERT한 데이터가 삭제되었다.
    - 그러나 트랜잭션 2는 이미 로직을 수행한 상태이다.

---

### Read Commited(레벨 1)

 어떤 트랜잭션의 변경 내용이 COMMIT 되어야만 다른 트랜잭션에서 조회할 수 있다. 오라클 DBMS에서 기본으로 사용하고 있고, 온라인 서비스에서 가장 많이 선택되는 격리수준이다.

<img src="https://blog.kakaocdn.net/dn/oXqMg/btqEeqFWXsr/d5WDuP5MjIawLjNQen8Nb1/img.png" alt="img" style="zoom: 67%;" />

```
1. 트랜잭션 1을 시작한다.
2. 트랜잭션 1이 ID = 1인 데이터의 VALUE를 KIM으로 변경했다.
3. 트랜잭션 2가 시작되었다.
4. 트랜잭션 2가 ID = 1인 데이터를 조회한다.MIN이 검색된다.
5. 트랜잭션 1이 커밋을 하고 종료한다.
6. 트랜잭션 2가 ID = 1인 데이터를 조회한다. KIM이 검색된다.
7. 트랜잭션 2가 커밋을 하고 종료한다.
```



- 발생문제점: Non_Repeatable Read
  - Non_Repeatable Read: 하나의 트랜잭션이 같은 값을 조회할 때 다른 값이 검색되는 현상
    위의 경우 Transaction2의 첫 번째 SELECT엔 MIN, 두 번째 SELECT엔 KIM

----

### Repeatable Read(레벨2)

트랜잭션이 시작되고 종료되기 전까지 한 번 조회한 값은 계속 같은 값이 조회되는 격리 수준.

<img src="https://blog.kakaocdn.net/dn/HxHA1/btqEbRFfomB/5t0EvMZy6lOey5290dakTK/img.png" alt="img" style="zoom:67%;" />

```
1. 트랜잭션 1을 시작한다.
2. 트랜잭션 1이 ID = 1인 데이터를 조회한다.
3. 트랜잭션 2가 시작되었다.
4. 트랜잭션 2가 ID = 1인 데이터를 KIM으로 변경한다.
5. 트랜잭션 1이 ID = 1인 데이터를 조회한다.트랜잭션 2의 변경 내역이 보이지 않는다.
6. 트랜잭션 2가 ID = 2인 데이터를 삽입한다.
7. 트랜잭션 1이 ID = 2인 데이터를 조회한다.데이터가 정상적으로 확인된다.
8. 트랜잭션 1, 2가 종료된다.
```



- 발생 문제점: Phantom Read
  - Phantom Read: 마치 유령을 보는 것 처럼 데이터가 사라지거나 없던 데이터가 생기는 현상.
     순서 7에 `SELECT * FROM TABLE WHERE ID =2` 쿼리문으로 ID가 2인 데이터를 조회하고 있다. 만약 순서 8에 정상적으로 종료되지 않아 Transaction 2를 롤백한다면 (2,'KIM') 데이터는 사라질 것이다.
     이 때, 다시 `SELECT * FROM TABLE WHERE ID =2` 쿼리문을 입력하여 데이터를 조회한다면 데이터는 정상적으로 검색되지 않는다. 이전에 조회되었던 데이터가 롤백 처리로 보이지 않게 된 것

---

### Serializable(레벨 3)

 트랜잭션이 특정 테이블을 읽으면 다른 트랜잭션은 그 테이블의 데이터를 추가/변경/삭제할 수 없다.

가장 강력한 격리수준으로 데이터 정합성을 가장 잘 보장한다. 그러나 동시 처리 성능이 가장 떨어짐.

(데이터 정합성과 동시 처리 성능은 반비례)

---

### Reference

- https://joont92.github.io/db/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EA%B2%A9%EB%A6%AC-%EC%88%98%EC%A4%80-isolation-level/
- https://private-space.tistory.com/97

