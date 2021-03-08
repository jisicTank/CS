# 데이터베이스 (Database)

### 1. 데이터베이스
 : 통합하여 관리되는 데이터의 집합

- 중복된 데이터를 없애고, 자료를 구조화하여 효율적인 처리를 할 수 있음
- 왜 사용할까?
   -> 데이터 베이스를 사용하지 않으면 프로그램을 종료하는 순간 전부 날아간다..

### 2. DBMS
 : 데이터 베이스 관리 시스템(DataBase Management System). 다수의 사용자가 데이터베이스 내의 데이터를 접근할 수 있도록 해주는 소프트 웨어 도구의 집합.

- 사용자의 요구에 따라 즉각적인 처리와 응답
- 생성, 수정, 삭제를 통한 최신 데이터 유지
- 데이터를 계층 또는 탐색 형식으로 저장. 이를 파일 시스템으로 저장하며 테이블 간에는 아무런 관계가 없다.
- 데이터에 대한 많은 보안이 제공되지 않음
- 정규화를 수행할 수 없어 데이터가 높은 중복성을 가질 수 있다.

### 3. RDBMS

: 관계형 데이터 모델인 RDB를 생성, 수정, 관리하는 시스템

- 모든 데이터가 2차원 테이블 형태로 표현
- RDBMS의 테이블들은 서로 연관되어 있어 DBMS 이상의 효율적 데이터 저장, 구성 및 관리가 가능하다.
- 정규화를 통해 데이터의 중복성을 최소화하며 트랜잭션을 수행하는 것이 더 쉽다.
- 데이터의 원자성, 일관성, 격리 및 내구성을 유지하며 데이터 무결성을 높인다.
- MySQL, Oracle, Maria DB , Ms-Sql등..



**<간단 설명>**

1. 정규화?
    : **데이터의 중복을 줄이고 무결성을 향상시키기 위해 RDB를 재디자인**하는 것

2. 트랜잭션?
    : **데이터베이스의 상태를 변화시키기 위해 수행하는 작업의 단위.** 상태를 변화 시킨다는 것은 SQL을 이용해 데이터베이스에 접근하는 것을 의미



### 4. 기본 용어

1. **엔티티(Entity)**: 사람, 장소, 사물, 사건 등과 같이 독립적으로 존재하면서 고유하게 식별이 가능한 실세계의 객체.
    Ex) 사원번호가 1234이고 이름이 홍길동인 사원, 학과 번호가 D316인 학과.

2. **스키마(Schema)**: 데이터를 설명하는 데이터. “메타데이터” 라고도 함.
    Ex) 홍길동, 1993-02-02 라는 데이터를 이름, 생년월일 이라는 스키마로 설명 가능. 

3. **테이블(=릴레이션) 용어**
   Table(=Relation): RDBMS에서 정보를 구분하여 저장하는 기본 단위. 하나의 릴레이션은 하나의 엔티티에 관한 정보를 담고 있다.
   - 식별자(identifier): RDB에서 각각을 구분할 수 있는 논리적 개념
   - 식별자의 특성
   - 유일성: 하나의 릴레이션에서 모든 행은 서로 다른 키 값을 가져야함
   - 최소성: 꼭 필요한, 최소한의 속성들로 키를 구성
   - 튜플(Tuple) = 행(row) = 레코드(Record)
     - Cardinality: 튜플의 수
   - 어트리뷰트(Attribute) = 열(column) = 속성 = 필드 
     - Degree: 어트리뷰트의 수
   - 도메인(Domain): 릴레이션에서 각각의 Attribute들이 취할 수 있는 타입의 원자 값의 집합
        Ex) 초등학교 학년은 1~6학년이므로 “학년” Attribute의 Domain은 {1, 2, 3, 4, 5, 6}
        - 원자값: 더 이상 분리된 수 없는 값. 하나의 레코드에서 각 필드에는 원자값만이 정의될 수 있다.

![image-20210228210429338](C:\Users\oh12s\Desktop\면접스터디\SKILL\Oracle\image\image-20210228210429338.png)

---

### 면접 질문

1. **RDBMS는 무엇의 약자인지 영문 FULL NAME을 말해보시오**

   Relation DataBase Management System  

2. **대표적인 DBMS를 아는대로 설명하시오**

   Oracle, Mysql, Maria DB, Ms-Sql, Cubrid, sybase, Db2 , infomix

3. **데이터의 가장 작은 논리적 단위로서 파일구조의 데이터 항목 또는 데이터 필드에 해당 되는 것을 무엇이라 하는가****?**

   속성(Attribute)