# 객체지향 설계원칙: SOLID

### 1. S: SRP(Single Responsibility)  - 단일 책임 원칙

> "어떤 클래스를 변경해야하는 이유는 오직 하나뿐이어야 한다."

클래스는 단 한 개의 책임을 가져야 한다.

클래스를 변경하는 이유는 단 한개여야 한다.

```java
// Before
class UserSettings {
	constructor(user) {
        this.user = user;
    }
    
    changeSettings(setting) {
		if (this.verifyCredentials()){
            //....
        }
    }
    
    verifyCredentials() {
        //....
    }

}
```

```java
// After
class UserAuth {
	constructor(user) {
        this.user = user;
    }
    
    verifyCredentials() {
        //....
    }
}

class UserSettings {
	constructor(user) {
        this.user = user;
        this.auth = new UserAuth(user);
    }
    
    changeSettings(settings) {
        if(this.auth.verifyCredentials()){
            //....
        }
    }
}
```



---

### Reference

- SRP: https://black-jin0427.tistory.com/192