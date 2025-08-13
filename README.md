# Casper Config Server

Casper 생태계의 애플리케이션 설정을 관리하는 Spring Cloud Config Server입니다.

## 개요

분산 시스템을 위한 외부 설정을 제공하는 중앙집중식 설정 서버입니다. MySQL 데이터베이스에 설정 데이터를 저장하고 민감한 값에 대한 암호화를 지원합니다.

## 주요 기능

- **데이터베이스 기반 설정 저장** - MySQL에 설정 저장
- **암호화 지원** - 민감한 데이터 암호화/복호화
- **프로필 기반 설정** - 환경별 다른 설정
- **REST API** - 설정 CRUD 및 표준 Spring Cloud Config 엔드포인트
- **상태 모니터링** - Actuator 엔드포인트를 통한 모니터링

## 사전 요구사항

- Java 21+
- MySQL 8.0+
- Gradle 8.0+

## 환경 변수 설정

환경 변수 파일(`.env` 또는 시스템 환경변수)에 다음을 설정하세요:

```bash
MYSQL_URL=mysql://localhost:3306/casper_config
MYSQL_USER=your_mysql_user
MYSQL_PASSWORD=your_mysql_password
ENCRYPT_KEY=your_encryption_key_here
ENCRYPT_SALT=your_encryption_salt_here
```

> ⚠️ **보안 주의사항**: 실제 암호화 키와 솔트는 충분히 복잡하고 긴 값을 사용하세요.

## 데이터베이스 설정
 
1. MySQL 데이터베이스 생성:
```sql
CREATE DATABASE casper_config;
```

2. 설정 테이블 생성:
```sql
CREATE TABLE tbl_environment_configuration (
    id BINARY(16) NOT NULL PRIMARY KEY,
    application VARCHAR(20) NOT NULL,
    profile VARCHAR(24) NOT NULL,
    label VARCHAR(100) NOT NULL,
    prop_key VARCHAR(100) NOT NULL,
    prop_value TEXT NOT NULL
);
```

## 애플리케이션 실행

### 로컬 개발 환경

1. 환경 변수 설정 후 애플리케이션 실행:
```bash
./gradlew bootRun
```

2. 서버가 `http://localhost:8888`에서 실행됩니다.

## API 사용법 (Postman)

### 1. 환경설정 관리 API

#### 환경설정 저장
```http
POST http://localhost:8888/api/env
Content-Type: application/json

{
    "application": "myapp",
    "profile": "dev",
    "label": "main",
    "properties": {
        "database.url": "jdbc:mysql://localhost:3306/myapp_dev",
        "database.username": "dev_user",
        "database.password": "my_secret_password"
    }
}
```

> 💡 **자동 암호화**: 평문으로 전송된 모든 값은 서버에서 자동으로 암호화되어 저장됩니다. `{cipher}` 접두사를 수동으로 붙일 필요가 없습니다.

#### 모든 환경설정 조회
```http
GET http://localhost:8888/api/env/myapp/dev/main
```

응답 예시:
```json
{
    "database.url": "jdbc:mysql://localhost:3306/myapp_dev",
    "database.username": "dev_user",
    "database.password": "decrypted_password"
}
```

#### 특정 키 환경설정 조회
```http
GET http://localhost:8888/api/env/myapp/dev/main/database.url
```

#### 특정 키 환경설정 삭제
```http
DELETE http://localhost:8888/api/env/myapp/dev/main/database.url
```

#### 모든 환경설정 삭제
```http
DELETE http://localhost:8888/api/env/myapp/dev/main
```

### 2. Spring Cloud Config 표준 API

#### 설정 조회 (표준 형식)
```http
GET http://localhost:8888/myapp/dev
GET http://localhost:8888/myapp/dev/main
```

응답 예시:
```json
{
    "name": "myapp",
    "profiles": ["dev"],
    "label": "main",
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "myapp-dev-main",
            "source": {
                "database.url": "jdbc:mysql://localhost:3306/myapp_dev",
                "database.username": "dev_user",
                "database.password": "decrypted_password"
            }
        }
    ]
}
```

### 3. 암호화/복호화 API

#### 값 암호화
```http
POST http://localhost:8888/encrypt
Content-Type: text/plain

my_secret_password
```

응답:
```
AQCKx8xPJ5N8C2YHnBWJ5+nMqpZ8XbWzEqK3...
```

#### 값 복호화
```http
POST http://localhost:8888/decrypt
Content-Type: text/plain

AQCKx8xPJ5N8C2YHnBWJ5+nMqpZ8XbWzEqK3...
```

응답:
```
my_secret_password
```

### 4. 액추에이터 API

#### 헬스 체크
```http
GET http://localhost:8888/actuator/health
```

#### 애플리케이션 정보
```http
GET http://localhost:8888/actuator/info
```

#### 설정 새로고침
```http
POST http://localhost:8888/actuator/refresh
```

## Postman 컬렉션 사용법

### 기본 워크플로우

1. **설정 저장**:
   - `/api/env` POST 엔드포인트에 평문으로 전송
   - 서버에서 자동으로 암호화하여 저장

2. **설정 조회**:
   - Spring Cloud Config 표준 엔드포인트 또는 커스텀 엔드포인트 사용
   - 암호화된 값은 자동으로 복호화되어 반환

3. **설정 관리**:
   - 필요시 DELETE 엔드포인트로 설정 삭제

### 테스트 시나리오 예시

**1. 설정 저장 (평문으로 전송)**
```http
POST /api/env
Content-Type: application/json

{
    "application": "user-service",
    "profile": "production",
    "label": "v1.0",
    "properties": {
        "spring.datasource.url": "jdbc:mysql://prod-db:3306/userdb",
        "spring.datasource.username": "prod_user",
        "spring.datasource.password": "my_database_password",
        "external.api.key": "secret_api_key_123",
        "logging.level.root": "WARN"
    }
}
```

**2. 설정 조회 및 확인**
```http
GET /user-service/production/v1.0
```

> 💡 서버에서 자동으로 민감한 값들을 암호화하여 저장하고, 조회 시에는 복호화된 값을 반환합니다.

## 클라이언트 애플리케이션 설정

Spring Boot 클라이언트에서 사용하려면:

**application.yml:**
```yaml
spring:
  application:
    name: myapp
  profiles:
    active: dev
  config:
    import: "configserver:http://localhost:8888"
  cloud:
    config:
      fail-fast: true
      retry:
        initial-interval: 1000
        max-attempts: 6
```
