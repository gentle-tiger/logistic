# 📦 물류 관리 및 배송 시스템을 위한 MSA 기반 플랫폼 개발




영속성 전이 
컨텍스트 생애주기 
직렬화 -> 패키지 분석
체이닝 
FEIGN 클라이언트 OCP 만족 

언리리턴 처리 
반대의 경우를 해서 리턴 IF 가 아닌 




## 🛠 맡은 엄무
- 물류 관리 및 배송 시스템에서 제가 맡은 허브 서비스는 각 물류 허브 간의 이동 정보를 효율적으로 제공하는 역할을 수행합니다. 구체적으로, 주문 서비스에서 주문 요청이 들어오면 출발 허브부터 도착 허브까지의 최적 이동 경로를 계산하여 반환합니다.

### 허브 서비스 
- 허브 간 이동 경로 계산:
   - 주문이 접수되면, 출발지와 도착지 사이의 이동 경로를 다익스트라 알고리즘을 활용하여 계산합니다.
   - 경로 탐색 시 Hub to Hub Relay 방식을 채택하여, 각 허브의 위/경도 정보를 기반으로 실제 물류 이동 거리를 산출합니다.
   - 매 호출마다 높은 TPS(초당 트랜잭션 수)를 고려하여, 일일 TPS가 1,000 건 수준에서는 무리가 없으나, 10,000 건 이상일 경우 성능 저하와 로딩 지연을 최소화할 수 있도록 설계하였습니다.



## Eureka Server 등록 및 관리
- 서비스 등록
   - 초기 설정 단계에서 각 서비스의 URL을 할당한 후, Eureka Server에 등록하여 중앙 집중형 서비스 디스커버리 환경을 구성하였습니다.
   - 각 마이크로서비스가 Eureka Server에 자동 등록되도록 설정하여, 클라이언트 간의 동적 통신이 가능하도록 하였습니다.

- FeignClient 기반 통신
   - 서비스 간 통신은 선언적 REST 클라이언트인 FeignClient를 사용하여, 인터페이스 기반의 간결하고 유지보수하기 쉬운 코드로 구현하였습니다.



## 💡 기술 도입 배경
### FeignClient 도입 배경
- 개발 생산성 향상:
   - FeignClient를 도입함으로써, 복잡한 REST 통신 코드를 간소화하고, 인터페이스 기반의 선언적 방식으로 서비스를 호출할 수 있게 되었습니다.

- 유지보수성 강화:
   - 다른 대안에 비해, 코드 가독성이 높고 테스트가 용이하여, 향후 서비스 확장과 유지보수 측면에서 유리하다고 판단하였습니다.



## 🚀 프로젝트 개선방향 (향후 계획)
### TPS 최적화:
   - 주문 발생 시 허브 이동 경로 조회 API의 TPS 증가에 따른 부하를 고려하여, 성능 모니터링 도구를 도입하고, 부하 분산 및 캐시 전략을 추가로 개선할 예정입니다.

### 실시간 모니터링 강화:
   - 각 서비스의 응답 시간과 자원 사용량을 실시간으로 모니터링할 수 있는 대시보드를 구축하여, 장애 발생 시 빠른 대응 체계를 마련할 계획입니다.



## ♻️ 리팩터링 (향후 진행 예정)
### 코드 품질 개선:
   - 기존 코드 리뷰 과정에서 도출된 개선사항을 반영하여, 각 서비스의 모듈화를 더욱 세분화하고, 중복 코드를 제거하는 방향으로 리팩터링을 진행할 예정입니다.

### 객체 직렬화 개선:
   - Redis 캐시 관련 직렬화/역직렬화 문제를 해결한 현재 상태를 기반으로, 향후 Java8 날짜 타입 및 Builder 패턴 객체의 직렬화 방식을 표준화하여 안정성을 높일 계획입니다.

### 서비스 경계 재설계:
   - 각 도메인 간의 경계와 책임 분리를 명확하게 하기 위해, DDD 원칙에 기반한 서비스 설계 및 테스트 케이스 추가를 통해 코드의 확장성을 강화할 예정입니다.






## ⚙️ 서비스 구성 및 실행방법
### 서비스 엔드포인트

| 도메인           | 포트   | 기본 URL                                   |
|------------------|--------|--------------------------------------------|
| API Gateway      | 18081  | http://localhost:18081                     |
| Eureka           | 8761   | http://localhost:8761                      |
| Auth             | 18082  | http://localhost:18082/api/v1/auth           |
| User             | 18082  | http://localhost:18082/api/v1/users          |
| DeliveryManager  | 18082  | http://localhost:18082/api/v1/users/delivery-managers |
| Hub              | 18083  | http://localhost:18083/api/v1/hubs           |
| Order            | 18084  | http://localhost:18084/api/v1/orders         |
| Product          | 18085  | http://localhost:18085/api/v1/products       |
| Company          | 18085  | http://localhost:18085/api/v1/companies      |
| AI               | 18086  | http://localhost:18086/api/v1/ai             |
| Slack            | 18087  | http://localhost:18087/api/v1/slack          |
| Delivery         | 18088  | http://localhost:18088/api/v1/deliveries      |

### 데이터베이스 정보

| 도메인           | DB 명         | DB 사용자  | DB 포트 |
|------------------|---------------|------------|---------|
| User             | /db_user      | postgres   | 5432    |
| DeliveryManager  | /db_deliverymgr | postgres | 5432    |
| Hub              | /db_hub       | postgres   | 5432    |
| Order            | /db_order     | postgres   | 5432    |
| Product          | /db_product   | postgres   | 5432    |
| Company          | /db_company   | postgres   | 5432    |
| AI               | /db_ai        | postgres   | 5432    |
| Slack            | /db_slack     | postgres   | 5432    |
| Delivery         | /db_delivery  | postgres   | 5432    |

###  실행 방법
 
1. **환경 구축**
   - **PostgreSQL 설치**  
     - 로컬에 PostgreSQL을 설치하거나 Docker 컨테이너를 사용하여 실행합니다.
   - **Redis 설치**  
     - 로컬에 Redis를 설치하거나 Docker 컨테이너를 사용하여 실행합니다.

2. **애플리케이션 실행**

3. **API 검증 및 이용**
   - **Postman** 또는 Swagger UI를 이용하여 백엔드 서비스의 엔드포인트에 정상적으로 접근할 수 있는지 확인합니다.


## 📄 프로젝트 개요 및 목적
### 프로젝트 개요
본 프로젝트는 Spring Cloud와 MSA를 활용해 백엔드 시스템을 구축하는 것을 목표로 하며, 특히 물류서비스의 비즈니스 특성을 고려하여 각 서비스의 역할과 경계를 명확히 설정하였습니다. 이를 통해 독립적이고 확장 가능한 아키텍처를 설계하였습니다.

### 프로젝트 목적
본 프로젝트는 Spring Cloud, Eureka, API Gateway 등을 활용해 복잡한 분산 시스템을 구축하며, 물류서비스 도메인을 철저히 분석하여 서비스 분리와 독립적 데이터 관리/통신 체계를 마련하는 한편, GeminiAPI, RESTful API, Spring Security를 적용해 통합 및 보안을 강화하는 것을 목표로 하였습니다.

## 📊 ERD
- [ERD 명세서](https://github.com/2025-nbc-logistics-project/logistic/wiki/ERD-%EB%AA%85%EC%84%B8%EC%84%9C)

## 🛠️ 기술 스택

### 백엔드
- **Spring Boot 3.4.4**: 애플리케이션 기본 프레임워크
- **Spring Cloud**: 마이크로서비스 환경 구성
  - **Eureka**: 서비스 등록 및 발견
  - **API Gateway**: API 라우팅 및 집약
  - **OpenFeign**: 서비스 간 HTTP 클라이언트 통신
- **Spring Security**: 인증 및 권한 관리
- **Zipkin**: 분산 트레이싱 및 이벤트 메시지 추적

### 데이터베이스
- **PostgreSQL**: 관계형 데이터베이스
- **JPA**: ORM 기반 데이터 매핑
- **Spring Data**: 데이터 접근 계층의 효율적 관리

### 캐시 및 메시징
- **Redis**: 캐시 및 메시징 도구

### 통합 API
- **GeminiAPI**: AI 연동 및 질문/답변 처리
- **RESTful API**: 서비스 간 표준화된 데이터 통신

### 기타
- **MSA 아키텍처**: 독립적이고 확장 가능한 시스템 구성
- **이벤트 소싱**: 데이터 무결성과 이력 관리를 위한 패턴

### 빌드 및 협업 도구
- **Gradle**: 빌드 툴
- **Swagger**: API 문서화 도구
- **Notion**: 협업 및 문서 관리
- **git**: 버전 관리 시스템
- **GitHub**: 협업 및 소스 코드 관리

## 📚 API Docs
- [API Docs](https://github.com/2025-nbc-logistics-project/logisti)

  
## 👥 팀원 역할분담
- **[최호진](https://github.com/gentle-tiger):** 허브 컨텍스트
- **[이종원](https://github.com/zapzookj):** 주문 컨텍스트, 알람 컨텍스트
- **[이채연](https://github.com/dkki4887):** 유저 컨텍스트, API 게이트웨이
- **[손세라](https://github.com/srrrn):** 업체 컨텍스트, AI 컨텍스트

