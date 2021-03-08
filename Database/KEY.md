# KEY

 한 릴레이션(Relation)에서 각각의 튜플(Tuple)을 유일하게 식별하기 위해 사용하는 하나 혹은 그 이상의 속성(Attribute)의 집합



### (1) 후보키 (Candidate Key)

: Attribute들 중에서 Tuple을 유일하게 식별할 수 있는 Attribute들의 부분집합.

- 모든 Relation은 반드시 하나 이상의 후보키를 가져야한다. (키 무결성)
- Relation에 있는 모든 Tuple에 대해서 유일성과 최소성을 만족시켜야 한다. 
- 유일성: 하나의 key로 하나의 Tuple을 유일하게 식별
   ex) ‘주민번호’
- 최소성: Record를 식별하는데 꼭 필요한 Attribute들로만 구성되어 있는 성질.
   ex) ‘주민번호+학번’은 최소성을 만족시키지 않음. ‘주민번호’만으로 Record 구별 가능.



### (2) 기본키 (Primary Key)

: 후보키에서 선택된 키

- NULL 값을 가질 수 없다. (개체 무결성)
- 한 릴레이션에서 특정 튜플을 유일하게 구별할 수 있는 속성.
- 기본키로 정의된 속성(Attribute)에는 동일한 값이 중복되어 저장이 불가. (개체 무결성)

<img src="https://user-images.githubusercontent.com/71415474/109600718-e260be80-7b60-11eb-9f8e-bfaeb08c69e0.png" alt="image-20210302132917946" style="zoom:50%;" />



### (3) 대체키 (Alternate Key)

: 후보키가 둘 이상일 때 기본키를 제외한 나머지 후보키들을 말합니다.

- **보조키** 라고도 함.
  Ex) <학생> Relation에서 학번이 기본키라면 남은 후보키 ‘주민번호’가 대체키

<img src="https://user-images.githubusercontent.com/71415474/109600742-ee4c8080-7b60-11eb-9901-1e5e81b02681.png" alt="image-20210302132943917" style="zoom:50%;" />



### (4) 슈퍼키 (Super Key)

: 한 Relation 내에 있는 속성(Attribute)들의 집합으로 구성된 키.

- Relation을 구성하는 모든 Tuple 중 슈퍼키로 구성된 속성의 집합과 동일한 값은 나타내지 않는다.
- Relation 내 모든 Tuple에 대해 유일성은 만족하나, 최소성은 만족 X

<img src="https://user-images.githubusercontent.com/71415474/109600762-fc020600-7b60-11eb-87d8-656cd509a7e1.png" alt="image-20210302133059719" style="zoom: 50%;" />



### (5) 외래키 (Foreign Key)

: 관계를 맺고 있는 Relation A, Relation B에서 A가 참조하고 있는 B의 기본키와 같은 A의 속성(Attribute)

- 외래키는 참조되는 Relation의 기본키와 대응되어 Relation 간에 참조관계를 표현한다.
- 외래키로 지정되면 참조하는 테이블의 기본키에 없는 값은 입력할 수 없다. (참조 무결성)

<img src="https://user-images.githubusercontent.com/71415474/109600778-07553180-7b61-11eb-94d3-36c863057e45.png" alt="image-20210302133141357" style="zoom:67%;" />

---

### 면접질문

1. **한 릴레이션에서 특정 튜플을 유일하게 구별할 수 있는 속성을 무엇이라 하는가?**

   기본키 (Primary Key)

2. **PK / FK ?**

   Primary Key: 한 릴레이션에서 특정 튜플을 유일하게 구별할 수 있는 속성.

   - NULL 값을 가질 수 없으며, 동일한 값이 중복되어 저장이 불가. (개체 무결성)

   Foreign Key: 하나의 테이블에 있는 Column(열)으로는 그 의미를 표현할 수 없는 경우, 다른 테이블의 Primary-Key Column의 값을 반드시 참조해야 하는 Key

---

### Reference

- 출처: https://woovictory.github.io/2019/01/30/DatabaseBasic/