# JOIN

### 조인(JOIN)

두 개 이상의 테이블이나 데이터베이스를 연결하여 데이터를 조회하는 방법

테이블을 조인할 때는 적어도 하나의 컬럼을 서로 공유해야한다.



---

### JOIN 종류

- CROSS JOIN
- INNER JOIN
- SELF JOIN
- OUTER JOIN
  - LEFT OUTER JOIN
  - RIGHT OUTER JOIN
  - FULL OUTER JOIN



예제를 통한 학습을 위하여 다음 테이블을 먼저 만들어 놓자.

```sql
CREATE TABLE GIRL_GROUP( 
    id INT PRIMARY KEY,
    name VARCHAR2(32) NOT NULL,
    debut DATE NOT NULL,
    hit_song_id INT
);

CREATE SEQUENCE girlgroup_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE SONG (
    id INT PRIMARY KEY,
    title VARCHAR2(60) NOT NULL,
    lyrics VARCHAR2(60)
);

CREATE SEQUENCE song_seq START WITH 101 INCREMENT BY 1;

INSERT INTO song VALUES (song_seq.NEXTVAL, 'Tell Me', 'tell me tell me tetetete tel me');
INSERT INTO song VALUES (song_seq.NEXTVAL, 'Gee', 'GEE GEE GEE GEE GEE BABY BABY'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '미스터', '이름이 뭐야 미스터'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, 'Abracadabra', '이러다 미쳐 내가 여리여리'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '8282', 'Give me a call Baby baby'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '기대해', '기대해'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, 'I Don"t car', '다른 여자들의 다리를'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, 'Bad Girl Good Girl', '앞에선 한 마디 말도'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '피노키오', '뉴예삐오'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '별빛달빛', '너는 내 별빛 내 마음의 별빛'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, 'A', 'A 워오우 워오우워 우우우'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '나혼자', '나 혼자 밥을 먹고 나 혼자 영화 보고'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, 'LUV', '설레이나요 '); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '짧은치마', '짧은 치마를 입고 내가 길을 걸으면'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, '위아래', '위 아래 위위 아래'); 
INSERT INTO song VALUES (song_seq.NEXTVAL, 'Dumb Dumb' , '너 땜에 하루종일'); 

INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'원더걸스', '2007-09-12',101);
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'소녀시대', '2009-06-03', 102);
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'카라', '2009-07-30', 103); 
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'브라운아이드걸스', '2008-01-17', 104); 
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'다비치', '2009-02-27', 105); 
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'2NE1', '2009-07-08', 107); 
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'f(x)', '2011-04-20', 109); 
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'시크릿', '2011-01-06', 110); 
INSERT INTO girl_group VALUES (girlgroup_seq.NEXTVAL,'레인보우', '2010-08-12', 111); 
INSERT INTO girl_group (id, name, debut) VALUES (girlgroup_seq.NEXTVAL,'에프터 스쿨', '2009-11-25'); 
INSERT INTO girl_group (id, name, debut) VALUES (girlgroup_seq.NEXTVAL,'포미닛', '2009-08-28');
```

<img src="C:\Users\oh12s\Desktop\TIL\Coding Test\md-image\image-20210220210719690.png" alt="image-20210220210719690" style="zoom: 67%;" />

<img src="C:\Users\oh12s\Desktop\TIL\Coding Test\md-image\image-20210220210747998.png" alt="image-20210220210747998" style="zoom: 67%;" />

- CROSS JOIN

  > 교차조인, 데카르트 곱(Cartesian Product)이라고 하며 테이블 간에 연결될 수 있는 모든 경우의 수를 산출하여 나타낸다.  

![JOIN](https://t1.daumcdn.net/cfile/tistory/99341D335A8A363D06)

```sql
SELECT S.ID, S.TITLE, GG.NAME
FROM GIRL_GROUP GG
CROSS JOIN SONG S;

--또는

SELECT S.ID, S.TITLE, GG.NAME
FROM GIRL_GROUP GG, SONG S;
```



- INNER JOIN

  > 교집합. 기준 테이블과 조인 테이블에서 공통된 요소들을 통해 결합하는 조인. 일반적으로 '조인'이라하면 INNER JOIN을 의미한다.

![JOIN](https://t1.daumcdn.net/cfile/tistory/99799F3E5A8148D703)

```sql
SELECT GG.ID, GG.NAME, S.TITLE
FROM GIRL_GROUP GG
JOIN SONG S
ON S.ID = GG.HIT_SONG_ID;

--또는

SELECT GG.ID, GG.NAME, S.TITLE
FROM GIRL_GROUP GG, SONG S
WHERE S.ID = GG.HIT_SONG_ID;
```

- SELF JOIN
  :  하나의 테이블을 여러번 복사해서 조인한다.
- ![img](https://camo.githubusercontent.com/3600303a038c6cc6f6189738e96de0f791673b542f84c1895afa9b32a4fb6208/68747470733a2f2f696d67312e6461756d63646e2e6e65742f7468756d622f523132383078302f3f73636f64653d6d746973746f72793226666e616d653d687474702533412532462532466366696c6532352e75662e746973746f72792e636f6d253246696d61676525324639393334314433333541384133363344303631344538)

```SQL
SELECT S1.TITLE, S2.ID
FROM SONG S1
JOIN SONG S2
ON S1.ID = S2.ID;
```



- OUTER JOIN
  : 조인하는 여러테이블에서 한 쪽에는 데이터가 있고, 한 쪽에는 데이터가 없는 경우, 데이터가 있는 쪽 테이블의 내용을 모두 출력하는 것. 조인 조건(ON)에 맞지 않아도 해당하는 행을 출력.

  ![img](https://t1.daumcdn.net/cfile/tistory/26310B3458340C9F1C)

  - LEFT OUTER JOIN
    : 왼쪽에 있는 테이블의 모든 결과를 가져온 후, 오른쪽 테이블 데이터를 매칭. 데이터가 없는 경우 NULL로 표시

    ```SQL
    SELECT GG.ID, GG.NAME, S.TITLE
    FROM GIRL_GROUP GG
    LEFT OUTER JOIN SONG S
    ON S.ID = GG.HIT_SONG_ID;
    ```

  - RIGHT OUTER JOIN
    : 오른쪽에 있는 테이블의 모든 결과를 가져온 후, 왼쪽 테이블 데이터를 매칭. 데이터가 없는 경우 NULL로 표시

    ```SQL
    SELECT GG.ID, GG.NAME, S.TITLE
    FROM GIRL_GROUP GG
    RIGHT OUTER JOIN SONG S
    ON S.ID = GG.HIT_SONG_ID;
    ```

  - FULL OUTER JOIN
    : LEFT OUTER JOIN과 RIGHT OUTER JOIN을 합친 것으로, 양쪽 모두 조건이 일치하지 않는 것까지 모두 결합해 출력. 두 테이블이 가지고 있는 모든 데이터를 출력하므로 기준테이블은 사실 의미가 없다.

    ```SQL
    SELECT GG.ID, GG.NAME, S.TITLE
    FROM GIRL_GROUP GG
    FULL OUTER JOIN SONG S
    ON S.ID = GG.HIT_SONG_ID;
    ```

    

---

### Reference

- 그림참조: https://coding-factory.tistory.com/87
- OUTER JOIN: https://rh-cp.tistory.com/44