# store

## 쇼핑몰 제작 다우 테스트
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
        JPA, queryDSL, swagger, hateoas(api), AOP

****

  #### 2. Install (clone시)
  >   2-1 https://github.com/junghun133/store clone 
  >   2-2 H2 설정 확인 
           url: jdbc:h2:mem:clothes
           username: daou
           password: daou
  >    2-3 localhost port 8080 중복 확인 (8080 중복시 application.yml 의 port 변경)
****
  #### 3. Initialize
  >   3-1 default sql script
      - 상품 30개, 이미지 45개

****
  
  #### 4. How to run?
  >4-1. https://github.com/junghun133/store/test_shopping_mall.jar 다운로드
  >4-2. java -jar test_shopping_mall.jar 실행

  swagger url -> http://localhost:8080/swagger-ui/index.html 이동 후 /api-docs 입력 후 explore 클릭
  
  ![home](https://user-images.githubusercontent.com/13414116/102690874-cd57dd00-424b-11eb-99ab-e886f4e29654.png)

