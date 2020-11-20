# AEDAPI

자동심장충격 관리정보 조회 API(정부데이터 data.go.kr) API 연동 Toy project


- RestAPI
- JPA
- Querydsl
- Hateoas
- maven
- Spring boot 2.2
- Exception handling
- H2 

>시나리오

->  유저 회원가입 및 토큰 발급(JWT)

->  현재 위치를 정확한 주소가 아닌 근사 주소값을 API에 요청

->  주소관련 Open API(도로명 주소 API, Daum API 등) 사용하여 정확한 주소확인

->  현재 위치 근방 AED설치 장소정보 응답
