# store

## 쇼핑몰 제작 프로젝트
Spring boot 제작 쇼핑몰(옷뿌리오)

### 프로젝트 정보
  #### 1. Development environment
  > 1-1 
  * IDE: intellij 2020.02
  * MDB: H2
  * JAVA: 1.8
  * spring version: spring boot 2.4 
  * Client: javascript, jquery, thymeleaf, bootstrap(css)
  * library: 
        JPA, queryDSL, swagger, hateoas(api), AOP, spring security, login api(google, facebook, naver) 

****
  #### 2. Initialize
  >   2-1 default sql script
      - 상품 30개, 이미지 45개

****
  
  ~~#### 3. How to run?~~ 구글,페이스북, 네이버 로그인 API로 인해 실행시 provider 설정필요
  >3 build 
  >>3-1. clone 후 gradle>build>bootjar 로 jar파일 생성 
  
  >>3-2. 파일이름 변경 후 java -jar test_shopping_mall.jar 실행 
  
  swagger  -> http://localhost:8080/swagger-ui/index.html 이동 후 /api-docs 입력 후 explore 클릭
  
  ![home](https://user-images.githubusercontent.com/13414116/102690874-cd57dd00-424b-11eb-99ab-e886f4e29654.png)

