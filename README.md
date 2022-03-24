# 스프링 배치 - Spring Boot 기반으로 개발하는 Spring Batch
### 강사 : 정수원
- - -
## Project Setting
- project: Gradle
- Language: Java
- Spring Boot: 2.5.2
- Packaging: jar
- Java: OpenJDK11
- - - 
## Dependencies
- Spring Boot Batch
  - implementation 'org.springframework.boot:spring-boot-starter-batch'
- H2
  - runtimeOnly 'com.h2database:h2:2.1.210'
- Lombok
  - compileOnly 'org.projectlombok:lombok:1.18.20'
- MariaDB
  - runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'