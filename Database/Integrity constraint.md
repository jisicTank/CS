# 무결성 제약조건

### 무결성?

현실 속 실제값과 내가 DB에 저장한 데이터 값이 일치하는 정확성



### 1. 개체 무결성

 Relation에서 기본키를 구성하는 속성(Attribute)는 Null 값이나 중복값을 가질 수 없다.

<img src="https://user-images.githubusercontent.com/71415474/109600855-353a7600-7b61-11eb-99ab-e97842fcadcb.png" alt="image-20210302133931159" style="zoom: 67%;" />



### 2. 참조 무결성

 외래키 값은 Null이거나 참조 Relation의 기본키 값과 동일해야 함

![image-20210302134018407](https://user-images.githubusercontent.com/71415474/109600888-4daa9080-7b61-11eb-97b2-6d176cb979bb.png)

### 3. 도메인 무결성

: 특정 속성(Attribute) 값이 그 속성이 정의된 도메인에 속한 값이어야 함

### 4. 고유 무결성

: 특정 속성에 대해 고유한 값을 가지도록 조건이 주어진 경우, 그 속성값은 모두 다 달라야한다.

### 5. NULL 무결성

: 특정 속성 값에 NULL이 올 수 없다는 조건이 주어진 경우, 그 속성값은 NULL 값이 올 수 없다.

### 6. KEY 무결성

: 한 Relation에는 최소한 하나의 키가 존재해야 한다.

![image-20210302134134440](https://user-images.githubusercontent.com/71415474/109600916-58652580-7b61-11eb-8cff-1cd92d5d23eb.png)

---

### 면접질문

1. **무결성에 대해 얘기해보세요.**

   무결성이란 현실 속 실제값과 내가 DB에 저장한 데이터 값이 일치하는 정확성을 말한다.

   1. 개체 무결성

   : Relation에서 기본키를 구성하는 속성(Attribute)는 Null 값이나 중복값을 가질 수 없다.

   2. 참조 무결성

   : 외래키 값은 Null이거나 참조 Relation의 기본키 값과 동일해야 함

   3. 도메인 무결성

   : 특정 속성(Attribute) 값이 그 속성이 정의된 도메인에 속한 값이어야 함

   4. 고유 무결성

   : 특정 속성에 대해 고유한 값을 가지도록 조건이 주어진 경우, 그 속성값은 모두 다 달라야한다.

   5. NULL 무결성

   : 특정 속성 값에 NULL이 올 수 없다는 조건이 주어진 경우, 그 속성값은 NULL 값이 올 수 없다.

   6. KEY 무결성

   : 한 Relation에는 최소한 하나의 키가 존재해야 한다.



2. **무결성을 유지하려는 이유가 무엇인가요?**

   무결성이 유지가 되어야 DB에 저장된 데이터 값과 거기에 해당하는 현실 세계의 실제값이 일치하는지 신뢰할 수 있기 때문이다.

---

### Reference

- 출처: https://limkydev.tistory.com/161
- 출처: https://woovictory.github.io/2019/01/30/DatabaseBasic/

