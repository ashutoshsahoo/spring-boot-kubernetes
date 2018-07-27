FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/SpringBootKubernetes-2.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT","-jar","/app.jar"]
