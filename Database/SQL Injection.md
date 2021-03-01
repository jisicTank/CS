# SQL Injection

해커가 보안상의 취약점을 이용해 특정 SQL문을 주입하고 실행되게 하여 데이터베이스가 비정상적인 동작을 하도록 조작하는 공격행위.



### 공격방식

**1. Error based SQL Injection**

 논리적 에러를 이용한 SQL Injection. 로그인 페이지를 타겟으로 행해지는 공격으로 True/False의 논리적 연산 오류를 이용하여 로그인 인증 쿼리가 무조건 True가 나오게 인증을 무력화 시킨다.
 WHERE절에 **'or 1=1**이란 문구를 넣어주면서 무조건 참이 되는 쿼리문으로 인증을 해제한다.

![image-20210227195709352](https://user-images.githubusercontent.com/71415474/109386781-77d13800-7940-11eb-8d27-94d93f87d23f.png)

![img](https://t1.daumcdn.net/cfile/tistory/9958373C5C8890FA03)



**2. Union based SQL Injection**

  SQL 에서 Union 키워드는 두 개의 쿼리문에 대한 결과를 통합해서 하나의 테이블로 보여주게 하는 키워드이다. 정상적인 쿼리문에 Union 키워드를 사용하여 인젝션에 성공하면, 원하는 쿼리문을 실행할 수 있다.
 Union Injection을 성공하기 위해서는 두 가지의 조건이 있는데, 하나는 Union 하는 두 테이블의 컬럼 수가 같아야 하고, 데이터 형이 같아야 합니다. 

![img](https://t1.daumcdn.net/cfile/tistory/99BD4C3C5C8890FA0A)

※ 데이터 명, 컬럼 수, 데이터 형은 어떻게 알 수 있을까?

에러문을 통해 알아낼 수 있다. 해커들은 에러문을 유발시켜 그 정보를 활용해 여러가지 정보를 얻어낸다. 예를 들면 컬럼수 같은 경우 ORDER BY절을 이용하여 발생하는 에러문에서 컬럼수를 유추할 수 있다.

![image-20210227203549992](https://user-images.githubusercontent.com/71415474/109386788-861f5400-7940-11eb-80ef-4fe47bc08a7d.png)



**3. Blind SQL Injection**

 데이터베이스로부터 특정한 값이나 데이터를 전달받지 않고, 참과 거짓 정보만 알 수 있을 때 사용한다. 로그인 폼에 SQL Injection이 가능하다고 가정했을 때, 로그인 성공과 실패 메시지를 이용하여 DB의 테이블 정보 등을 추출할 수 있다.

- **Boolean based SQL**
   limit 키워드를 통해 하나의 테이블만 조회하고, SUBSTR 함수로 첫 글자만, 그리고 마지막으로 ASCII 를 통해서 ASCII 값으로 변환한다. 만약에 조회되는 테이블 명이 Users 라면 ‘U’ 자가 ASCII 값으로 조회가 될 것이고, 뒤의 100 이라는 숫자 값과 비교를 하게 된다. 거짓이면 로그인 실패가 될 것이고, 참이 될 때까지 뒤의 100이라는 숫자를 변경해 가면서 비교한다.

![img](https://t1.daumcdn.net/cfile/tistory/99525F3C5C8890F90E)

- **Time based SQL**
  쿼리문이 실행되는 타임라인으로 DB정보를 얻는다. LENGTH(DATABASE()) = 1 가 참이면 SLEEP(2) 가 동작하고, 거짓이면 동작하지 않는다. 이를 통해서 숫자 1 부분을 조작하여 데이터베이스의 길이를 알아 낼 수 있다. 

![img](https://t1.daumcdn.net/cfile/tistory/99CAFB395C88914513)

​	

### 방어 방법

**1. 입력값 검증**

 입력값 검증 시, **SQLI(SQL Injection)에 사용되는 특수문자나, SQL  명령어들이 있는지 검사**한다.

```java
/* 입력값 검사하는 코드의 예 */

// 특수문자 공백 처리
final Pattern SpecialChars = Pattern.compile(“[‘\”\\-#()@;=*/+]”);
UserInput = SpecialChars.matcher(UserInput).replaceAll(“”);

//DDL, DML 등을 검증
final String regex = “(union|select|from|where|delete|insert)”;

final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
final Matcher matcher = pattern.matcher(UserInput);

if(matcher.find()){
    out.println(“<script>alert(‘STOP SQL-Injection’);</script>”)
};
```



**2. Prepared Statement 구문 사용**

 **미리 쿼리에 대한 컴파일을 수행**하고, 입력값을 나중에 넣는 방식이다. 그렇게 함으로써 비정상적인 입력값이 들어와도, 이미 **쿼리 플랜**(Query Plan)이 세워져 있으므로 SQLI를 막을 수 있게 된다.

```java
String query="SELECT * FROM users WHERE ID=? and PW=?";

conn = DriverManager.getConnection(url, "scott","123456");  

pstmt = conn.prepareStatement(query);   // 여기에서 미리 쿼리에 대한 컴파일 수행
pstmt.setString(1, ID);
pstmt.setString(2, PW);

pstmt.executeUpdate();  // 쿼리 실행
```



**3. SQL 서버 오류 발생 시, 에러 메시지 감추기**

 데이터베이스 에러 발생 시 따로 처리를 해주지 않았다면, 에러가 발생한 쿼리문과 함께 에러에 관한 내용을 반환해준다. 여기서 테이블명 및 컬럼명 그리고 쿼리문이 노출이 될 수 있기 때문에, 데이터 베이스에 대한 오류발생 시 사용자에게 보여줄 수 있는 페이지를 제작 혹은 메시지박스를 띄우도록 조치한다.

---

### Reference

- 인젝션 참고: https://mrrootable.tistory.com/25
- 인젝션 참고2: https://noirstar.tistory.com/264
- 인젝션 참고3: https://medium.com/pocs/sql-injection%EC%9D%B4%EB%9E%80-3b57f2415ef4
- limit: https://gent.tistory.com/254
- order by + 숫자: https://aboutitdev.tistory.com/25

