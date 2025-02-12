## 일정 관리 어플

---

[1. 개발환경](#1-개발환경)<br>
[2. 요구사항](#2-요구사항)<br>
[3. 도전기능](#3-도전기능)<br>
[4. API 명세서](#4-API-명세서)<br>
[5. ERD](#5-ERD)<br>
[6. 패키지](#6-패키지)<br>
[7. 주요 commit](#7-주요-commit)


### 1. 개발환경
   
#### 기술 스택
   
+ JDK Amazon Corretto 17.0.13
+ Spring Framework Boot 3.4.1
+ Gradle 8.11.1
+ Database: H2(개발), MySQL(운영)

#### 의존성 버전 정보

+ Spring Boot Starter Web 3.4.22
+ Spring Boot DATA JPA 3.4.22
+ Project Lombok 1.18.362
+ Slf4j 2.0.162
+ Apache Tomcat Embed Socket 10.1.342
+ Favre Lib BCrypt 0.10.22
+ Hibernate ORM 6.6.5 Final
+ H2 Database h2:2.3.2322
+ MySQL Connector-j9.1.022

### 2. 요구사항

+ Lv 0. API 명세 및 ERD 작성
+ Lv 1. 일정 CRUD
  + 일정이 가지는 필드 : 유저 고유식별자, 일정 제목, 일정 내용, 작성일, 수정일
  + 작성일, 수정일 필드는 JPA Auditing을 활용
  + 일정 생성, 조회, 수정, 삭제 기능 가져야한다
+ Lv 2. 유저 CRUD
  + 유저가 가지는 필드 : 유저명, 이메일, 작성일, 수정일
  + 작성일, 수정일 필드는 JPA Auditing을 활용
  + 유저 생성, 조회, 수정, 삭제 기능 가져야한다
+ Lv 3. 회원가입
  + 유저에 비밀번호 필드 추가
+ Lv 4. 로그인(인증)
  + Cookie/Session을 활용해 로그인 기능을 구현한다
  + 필터를 활용해 인증 처리한다
  + 조건: 이메일과 비밀번호를 활용해 로그인 기능을 구현한다
  + 회원가입, 로그인 요청은 인증 처리에서 제외한다
  + 로그인 시 이메일과 비밀번호가 일치하지 않을 경우 HTTP Status code 401을 반환한다

### 3. 도전기능

+ Lv 5. 다양한 예외처리 적용하기
  + Validation을 활용해 다양한 예외처리 적용
+ Lv 6. 비밀번호 암호화 
  + PasswordEncoder를 사용해 비밀번호 암호화
+ Lv 7. 댓글 CRUD
  + 생성한 일정에 댓글을 남길 수 있다
  + 댓글이 가지는 필드: 댓글내용, 작성일, 수정일, 유저 고유 식별자, 일정 고유 식별자
  + 작성일, 수정일 필드는 JPA Auditing을 활용
  + 댓글 저장, 조회, 수정, 삭제 기능 가져여한다
+ Lv 8. 일정 페이징 조회
  + offset / limit : SELECT 쿼리에 적용한다
  + 일정을 Spring Data JPA의 Pageable과 Page 인터페이스를 활용하여 페이지네이션을 구현한다
  + 할일 제목, 할일 내용, 댓글 개수, 일정 작성일, 일정 수정일, 일정 작성 유저명 필드를 조회
  + 디폴트 페이지 크기는 10으로 적용하고 정의 수정일을 기준으로 내림차순 정렬한다

### 4. API 명세서

[API 명세서 노션 링크](https://www.notion.so/198890e2b4ff80aea130dc4580364bbd?v=198890e2b4ff81f28621000cc29261ab&pvs=4)

![API명세서1](/image/API명세서1.png)
![API명세서2](/image/API명세서2.png)
![API명세서3](/image/API명세서3.png)

### 5. ERD

![ERD](/image/schedulerERD.png)

### 6. 패키지

![package](/image/package.png)

+ 소스 파일 내부에 각 역할 별로 패키지를 나누었다
+ src/config 패키지에는 프로그램 전체적으로 사용하는 파일이 있다
+ src/domain 패키지에는 도메인별로 각각 comment, login, member, schedule 패키지가 있다
+ 또한 도메인 패키지에 각각 Controller, Service, Repository, DTO, Entity 패키지로 파일을 분류했다
+ exception 패키지에는 예외처리 관련 파일이 있다

### 7. 주요 commit

+ [일정, 유저 CRUD와 회원가입 기능 구현](https://github.com/Hokirby/Scheduler/commit/2c178ed115a43d7215f457c0b69a8a0572389a50)<br>
+ [사용자 예외 설정 및 ControllerAdvice 사용해 예외 처리](https://github.com/Hokirby/Scheduler/commit/800cee24716dd0f7d209b7c4ad07ff3a9991afea)<br>
+ [로그인 기능 수정](https://github.com/Hokirby/Scheduler/commit/19feb0711cc57149c7de0821f8e3c38147b04551)<br>
  + 작성자명 사용해 로그인 -> 작성자 이메일 주소 사용해 로그인<br>
+ [Bean Validation 적용해 유효성 검사](https://github.com/Hokirby/Scheduler/commit/90aa5d67300675d69f4721f0d066e59f7ce9770a)<br>
+ [비밀번호 암호화 기능 추가](https://github.com/Hokirby/Scheduler/commit/58e433fe9aa4b5ad4a53ce23677ace31bc08682d)<br>
+ [댓글 CRUD 구현](https://github.com/Hokirby/Scheduler/commit/b8bdfc1307475e6962f0024d08b5b86d7df64f6d)<br>
+ [댓글 페이징 JPQL 오류 해결](https://github.com/Hokirby/Scheduler/commit/8829fbca68678776c63b5a9fed799d39204c29a7)

