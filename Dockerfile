FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar loja-spring-boot.jar
ENTRYPOINT ["java","-jar","/loja-spring-boot.jar"]