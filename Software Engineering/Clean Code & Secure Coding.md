## 클린코드(Clean Code) & 시큐어코딩(Secure Coding)



#### 전문가들이 표현한 '클린코드'


- 한 가지를 제대로 한다.
- 단순하고 직접적이다.
- 특정 목적을 달성하는 방법은 하나만 제공한다.
- 중복 줄이기, 표현력 높이기, 초반부터 간단한 추상화 고려하기 이 세가지가 비결
- 코드를 읽으면서 짐작했던 기능을 각 루틴이 그대로 수행하는 것

<br>


#### 클린코드란?

코드를 작성하는 의도와 목적이 명확하며, 다른 사람이 쉽게 읽을 수 있어야 함

> 즉, 가독성이 좋아야 한다.

<br>

##### 가독성을 높인다는 것은?

다른 사람이 코드를 봐도, 자유롭게 수정이 가능하고 버그를 찾고 변경된 내용이 어떻게 상호작용하는지 이해하는 시간을 최소화 시키는 것...

<br>

클린코드를 만들기 위한 규칙이 있다.

<br>

#### 1.네이밍(Naming)

> 변수, 클래스, 메소드에 의도가 분명한 이름을 사용한다.

```java
int elapsedTimeInDays;
int daysSinceCreation;
int fileAgeInDays;
```

잘못된 정보를 전달할 수 있는 이름을 사용하지 않는다.

범용적으로 사용되는 단어 사용X (aix, hp 등)

연속된 숫자나 불용어를 덧붙이는 방식은 피해야함

> 불용어?
>
> 갖고 있는 데이터에서 유의미한 단어 토큰만을 선별하기 위해서는 **큰 의미가 없는 단어** 토큰을 제거하는 작업이 필요. 여기서 큰 의미가 없다라는 것은 자주 등장하지만 분석을 하는 것에 있어서는 큰 도움이 되지 않는 단어를 의미. 이 의미없는 단어를 불용어라 함.

<br>

#### 2.주석달기(Comment)

> 코드를 읽는 사람이 코드를 작성한 사람만큼 잘 이해할 수 있도록 도와야 함

주석은 반드시 달아야 할 이유가 있는 경우에만 작성하도록 한다.

즉, 코드를 빠르게 유추할 수 있는 내용에는 주석을 사용하지 않는 것이 좋다.

설명을 위한 설명은 달지 않는다.

```java
// 주어진 'name'으로 노드를 찾거나 아니면 null을 반환한다.
// 만약 depth <= 0이면 'subtree'만 검색한다.
// 만약 depth == N 이면 N 레벨과 그 아래만 검색한다.
Node* FindNodeInSubtree(Node* subtree, string name, int depth);
```

<br>

#### 3.꾸미기(Aesthetics)

> 보기좋게 배치하고 꾸민다. 보기 좋은 코드가 읽기도 좋다.

규칙적인 들여쓰기와 줄바꿈으로 가독성을 향상시키자

일관성있고 간결한 패턴을 적용해 줄바꿈한다.

메소드를 이용해 불규칙한 중복 코드를 제거한다.

<br>

클래스 전체를 하나의 그룹이라고 생각하지 말고, 그 안에서도 여러 그룹으로 나누는 것이 읽기에 좋다.

<br>

#### 4.흐름제어 만들기(Making control flow easy to read)

- 왼쪽에는 변수를, 오른쪽에는 상수를 두고 비교

  ```java
  if(length >= 10)
  
  while(bytes_received < bytest_expected)
  ```

- 부정이 아닌 긍정을 다루자

  ```java
  if( a == b ) { // a!=b는 부정
  	// same
  } else {
  	// different
  }
  ```

- if/else를 사용하며, 삼항 연산자는 매우 간단한 경우만 사용

- do/while 루프는 피하자

<br>

#### 5.착한 함수(Function)

> 함수는 가급적 작게, 한번에 하나의 작업만 수행하도록 작성

<br>

온라인 투표로 예를 들어보자

사용자가 추천을 하거나, 이미 선택한 추천을 변경하기 위해 버튼을 누르면 vote_change(old_vote, new_vote) 함수를 호출한다고 가정해보자

```javascript
var vote_changed = function (old_vote, new_vote) {
    
	var score = get_score();
    
	if (new_vote !== old_vote) {
		if (new_vote == 'Up') {
			score += (old_vote === 'Down' ? 2 : 1);
		} else if (new_vote == 'Down') {
			score -= (old_vote === 'Up' ? 2 : 1);
		} else if (new_vote == '') {
			score += (old_vote === 'Up' ? -1 : 1);
		}
	}
	set_score(score);
    
};
```

총점을 변경해주는 한 가지 역할을 하는 함수같지만, 두가지 일을 하고 있다.

- old_vote와 new_vote의 상태에 따른 score 계산
- 총점을 계산

<br>

별도로 함수로 분리하여 가독성을 향상시키자

```javascript
var vote_value = function (vote) {
    
    if(vote === 'Up') {
        return +1;
    }
    if(vote === 'Down') {
        return -1;
    }
    return 0;
    
};

var vote_changed = function (old_vote, new_vote) {
    
    var score = get_score();
    
    score -= vote_value(old_vote); // 이전 값 제거
    score += vote_value(new_vote); // 새로운 값 더함
    set_score(score);
};
```

훨씬 깔끔한 코드가 되었다!

<br>

### 시큐어 코딩

> 안전한 소프트웨어를 개발하기 위해, 소스코드 등에 존재할 수 있는 잠재적인 보안약점을 제거하는 것



##### 보안 약점을 노려 발생하는 사고사례들

- SQL 인젝션 취약점으로 개인유출 사고 발생
- URL 파라미터 조작 개인정보 노출
- 무작위 대입공격 기프트카드 정보 유출

<br>

##### SQL 인젝션 예시

- 안전하지 않은 코드

```java
String query "SELECT * FROM users WHERE userid = '" + userid + "'" + "AND password = '" + password + "'";

Statement stmt = connection.createStatement();
ResultSet rs = stmt.executeQuery(query);
```

<br>

- 안전한 코드

```java
String query "SELECT * FROM users WHERE userid = ?" + AND password = ?";

PrepareStatement stmt = connection.prepareStatement(query);
stmt.setString(1, userid);
stmt.setString(2, password);
ResultSet rs = stmt.executeQuery();
```

적절한 검증 작업이 수행되어야 안전함



입력받는 값의 변수를 `$` 대신 `#`을 사용하면서 바인딩 처리로 시큐어 코딩이 가능하다.

---

### Reference

- https://github.com/gyoogle/tech-interview-for-developer/blob/master/Computer%20Science/Software%20Engineering/%ED%81%B4%EB%A6%B0%EC%BD%94%EB%93%9C(Clean%20Code)%20%26%20%EC%8B%9C%ED%81%90%EC%96%B4%EC%BD%94%EB%94%A9(Secure%20Coding).md