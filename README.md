# AEDAPI

자동심장충격 관리정보 조회 API(정부데이터 data.go.kr) API 연동 Toy project
https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15000652

- RestAPI
- JPA
- Querydsl
- Hateoas
- maven
- Spring boot 2.2
- Exception handling
- H2 
- JWT

>시나리오

->  유저 회원가입 및 토큰 발급(JWT)

->  현재 위치를 시도/시군구 or 좌표 데이터로 API에 요청

->  현재 위치 근방 AED설치 장소정보 응답
