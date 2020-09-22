
## Kakao B2B API 서비스 개발 코딩테스트

### 프로젝트 정보
  #### 1. Development environment
  > 1-1 
  * IDE: intellij 2020.02
  * MDB: H2
  * JAVA: 1.8
  * spring version: maven 기반의 spring boot 2.3.3 version 
  * Client: javascript, jquery, html
  * library: 
        기본적인 spring boot dependency, 사용자  암호화 처리를 위한 jbcrypt , API Document 페이지 제공을 위한 swagger dependency
  * Configuration: YML 설정파일( application.yml, api_config.yml )
****

  #### 2. Install
  >   2-1 https://github.com/junghun133/Kakaobank_map clone 으로 프로젝트 가져오기(public)  
  >   2-2 H2 다운로드 및 설정  
  
       - https://www.h2database.com OS에 맞는 H2 다운로드  
      
       - install path\H2\bin\h2w.bat (mac경우 h2.sh) 실행으로 H2 데이터베이스 기동  
      
       - 데이터베이스 파일 생성(초기 하기의 정보를 입력 후 connect시 자동으로 DB file 생성됩니다)   
           jdbcUrl: jdbc:h2:\~/map (이후 로그인시에는 jdbc:h2:tcp://localhost/~/map 으로 접속가능)    
           id: kakaobank  
           password: kakaobankgood  
  >    2-3 localhost port 8080 중복 확인 (8080 중복시 application.yml 의 port 변경)
****
  #### 3. Initialize
  >   3-1 run시 기초 데이터 (자동 삽입)
  
  >>TBL_User
  
  id|password|name
  ---|---|---|
  user1|user1password|roy|
  user2|user2password|richard|
  user3|user3password|sally|
  user4|user4password|nancy|
  user5|user5password|harry|
  user6|user6password|helen|

  >>TBL_KEYWORD
  
  keyword|hitCount|
  ---|---|
  카카오뱅크|45|
  박정훈|20|
  카카오은행|13|
  카카오|14|
  카카오톡|12|
  박정훈지원자|0|
  카카오헤어|2|
  카카오페이|1|
  카카오게임|5|
  카카오장보기|6|
  
****
  #### 4. Uniqueness
  * API 확장성을 위해 Abstract fatory pattern 구현, Request/Response 추상화를 통해 새로운 API 추가를 용이하도록 개발하였습니다.
  * Map 전체 데이터 각 Map 정보별 상세조회 API url 을 함께 response에 전달하여 불필요한 통신을 줄였습니다.
  * Map 전체 데이터 요청시 결과가 존재하지 않는 키워드에 대해서 재요청시 DB 데이터 조회 후 response를 전달하기때문에 불필요한 통신을 줄였습니다.
  * Swagger 구현으로 요구사항에 대해 자동 문서화가 가능하도록 구현하였습니다.
  
  
  #### 5. How to run?
  >5-1. Run configuration 설정 KakaoMapApiApplication main class로 실행  
  >5-2. http://localhost:8080/login.html 로그인 화면 이동하여 상기 유저 정보 중 1개의 데이터로 로그인 
  ![kakao_login](https://user-images.githubusercontent.com/13414116/93765600-1fd72c80-fc50-11ea-9602-bb1ca135c81f.png)
  >5-3. 로그인 성공 후 index.html 이동  
  ![kakao_main](https://user-images.githubusercontent.com/13414116/93765716-50b76180-fc50-11ea-977a-7962236d94fb.png)
  >5-4. 검색어 입력 후 찾기  
  >5-5. 페이지 하단 검색 결과 확인  
  >5-6. 15개 이상의 데이터라면 페이지 가장 하단으로 스크롤 다운하여 데이터 갱신(45개 까지)  
  >5-7. 각 결과의 [더보기] 버튼 클릭하여 상세정보 확인  

  ※주의사항: 로그인시 전달받는 데이터인 APIKey가 index 페이지의 전역변수로 사용하고 있어 페이지 갱신시 원활한 동작이 되지 않을 수 있습니다. 그럴 경우 다시 로그인부터 진행바랍니다.


  swagger url -> http://localhost:8080/swagger-ui.htm
  
