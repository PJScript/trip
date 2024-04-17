![Auto Assign](https://github.com/like-lion-3team/demo-repository/actions/workflows/auto-assign.yml/badge.svg)

![Proof HTML](https://github.com/like-lion-3team/demo-repository/actions/workflows/proof-html.yml/badge.svg)

# TourFinder

## 프로젝트 소개

여행 코스 기록, 공유 서비스.


![Group 17](https://github.com/PJScript/trip/assets/74460103/44f03209-2301-42d6-9835-f7715c6fb591)

![Group 18](https://github.com/PJScript/trip/assets/74460103/80e51141-5527-4413-a6f5-da28db5fd1bc)

![Group 19](https://github.com/PJScript/trip/assets/74460103/8b3513b0-566c-47e3-ba78-9c3365942413)

![Group 14](https://github.com/PJScript/trip/assets/74460103/dd981f99-db2e-4bc5-91d3-2ad46e23b82b)

![Group 13](https://github.com/PJScript/trip/assets/74460103/f8b769eb-b85c-4750-b2b3-43aa79f7f45b)

![Group 15](https://github.com/PJScript/trip/assets/74460103/5f4ceb38-99b2-4e51-88fe-97035ac3ad07)

![Group 10](https://github.com/PJScript/trip/assets/74460103/246f4973-cb20-4cfb-ae25-7efbefb73ee0)



<details>
  <summary> 시작하기</summary>
  프로젝트를 실행하기 위한 가이드 입니다.

### 프로젝트 다운로드 및 환경변수 설정

1. 소스 코드 복제:

   ````bash
   git clone https://github.com/like-lion-3team/trip.git
   ````

2. 환경변수 설정:


아래 환경 변수에  올바른 값을 추가 해주세요.
REDIRECT_URI 부분은 각 소셜 사업자 설정 화면에서 아래 주소와 똑같이 입력 해주세요.
````
DB_PASSWORD={데이터베이스 비밀번호};
DB_URL={데이터베이스 URL};
DB_USERNAME={데이터베이스 유저네임};
GOOGLE_CLIENT_ID={GCP 클라이언트 아이디};
GOOGLE_CLIENT_SECRET={GCP 시크릿키};
GOOGLE_REDIRECT_URI=http://localhost:8080/api/v1/oauth2/google/callback
JWT_SECRET={JWT 시크릿키로 사용하고 싶은 값 아무거나};
KAKAO_CLIENT_ID={카카오 로그인에 사용할 클라이언트 아이디};
KAKAO_CLIENT_SECRET={카카오 로그인에 사용할 시크릿키};
KAKAO_REDIRECT_URI=http://localhost:8080/api/v1/oauth2/kakao/callback;
KTO_SERVICE_KEY={한국 관광공사 서비스키};
NAVER_CLIENT_ID={네이버 로그인에 사용할 클라이언트 아이디};
NAVER_CLIENT_SECRET={네이버 로그인에 사용할 클라이언트 시크릿};
NAVER_REDIRECT_URI=http://localhost:8080/api/v1/oauth2/naver/callback;
NAVER_DEVELOPERS_CLIENT_ID={검색 api 사용을 위한 클라이언트 아이디};
NAVER_DEVELOPERS_CLIENT_SECRET={검색 api 사용을 위한 시크릿키};
NCP_CLIENT_ID={지도 활용을 위한 NCP 클라이언트 아이디};
NCP_SECRET={지도 활용을 위한 NCP 시크릿 키};
REDIS_HOST={레디스 호스트};
REDIS_PASSWORD={레디스 비밀번호};
REDIS_PORT={레디스 접속 포트};
REDIS_USERNAME={레디스 유저네임. 기본값이면 default 라고 써야함 };
SMTP_PASSWORD={구글 메일서버 비밀번호};
SMTP_USERNAME={구글 메일 보낼 때 발신자로 사용할 계정};
````

3. 실행

환경변수를 모두 입력 했으면 IDE에서 프로젝트를 시작 해주세요
- application.yaml 파일에 ${KEY} 형태로 환경변수를 참조하고 있습니다. 에러가 난다면 환경변수를 제대로 입력 했는지 확인 해주세요. <br />
- 프로젝트 실행 후 http://localhost:8080/swagger-ui/index.html 경로로 접속하면 API 문서를 볼 수 있습니다.
</details>









## 규칙
1. mater / main 브랜치에는 절대로 Push 하지 않는다.
2. 새로운 기능이나 수정사항은 dev 에서 파생된 브랜치에서 작업하고 dev로 merge 한다.
3. 3명이상 승인을 받은 후 pr을 merge 한다.

## 개발 플로우
1. clone
3. 개발할 기능 내용이 담긴 브랜치 생성 예) feat/login, fix/login
4. 개발 완료 후 깃허브 원격 저장소로 push
5. Pull Request 생성 dev <- feat/login
6. 팀원끼리 코드 리뷰 하고 머지
7. 로컬에서 작업중이던 팀원들은 로컬 브랜치 최신화

## 역할 분담
#### 김찬규 ( 팀장 )
- 코스 관련 기능
#### 박준수
- 회원 인증 인가 관련 기능 
#### 오시은
- 게시판 관련 기능
#### 민선익
- 코스 관련 기능
#### 김주홍
- 회원 인증 인가 관련 기능


## ERD
https://dbdiagram.io/d/like-lion-3team-65f15337b1f3d4062ccba812

![스크린샷 2024-04-18 오전 2 10 25](https://github.com/PJScript/trip/assets/74460103/4ca8af2a-a4c7-432d-a736-d24f11f0cc8c)






## 기술 스택

<img width="1016" alt="스크린샷 2024-04-18 오전 2 34 26" src="https://github.com/PJScript/trip/assets/74460103/9c1c60e3-ebd6-4a89-8391-fbd6f7f3a2de">



### 트러블 슈팅

  <details class="accordion">
    <summary class="accordionTitle">소셜 계정으로 로그인 할 때 토큰 처리 문제</summary>
    <div class="accordionInner">
       소셜 계정으로 로그인 할 때, redirect_uri로 이동하고 로그인이 완료되면 엑세스 토큰을 발급해주는데 이 토큰을 어떻게 처리할지 고민이였습니다.

사용자가 인증을 완료하고 redirect_uri로 이동하는데
Spring Boot 서버에서  @GetMapping("/google/callback") 형식으로 해당 redirect_uri를 처리하고 있습니다.

초기에는 해당 엔드포인트에서 로그인 처리후 엑세스 토큰을 바로 전달해주었는데
이렇게 되면 프론트 입장에서는 화면에 json 데이터만 뜨고 다른 제어가 불가능하다는 문제가 생김

해결
 @GetMapping("/google/callback") 엔드포인트에서 
소셜 사업자 정보와, 랜덤 UUID 정보를 포함하여 아래 프론트 엔드포인트로 리다이렉트 시키고
/oauth2/callback?socialProvider=GOOGLE&token=randomUUID

랜덤 UUID를 레디스 저장소에 저장하여 임시 사용자로 처리한 뒤 인증하는 형태로 처리했습니다.

이후 프론트에서는 위 엔드포인트에 해당하는 화면을 만들고 자바스크립트로 url에 있는 randomUUID를 추출하여 다시 서버로 로그인 허가 요청을 보냅니다.

GET  /{socialProviderName}/authorize?token={randomUUID} 이 형태로 로그인 허가 요청을 보내면

Spring Boot 서버에서는 레디스를 조회하고 해당 key가 있다면 value로 저장되어있는 엑세스 토큰을 전달 하고 프론트에서는 제어 가능한 페이지가 있으니 해당 페이지에서 토큰을 받아 처리하였습니다.

     </div>
  </details>
  


