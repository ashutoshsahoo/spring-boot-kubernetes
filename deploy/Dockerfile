FROM openjdk:17-oracle as builder
WORKDIR /usr/local/temp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-oracle
ARG VERSION=1.0.0
LABEL version=${VERSION}
LABEL maintainer="Ashutosh Sahoo"
LABEL description="Sample spring boot application"

WORKDIR /usr/local/app
COPY --from=builder /usr/local/temp/dependencies/ ./
COPY --from=builder /usr/local/temp/spring-boot-loader/ ./
COPY --from=builder /usr/local/temp/snapshot-dependencies/ ./
#COPY --from=builder /usr/local/temp/resources/ ./
COPY --from=builder /usr/local/temp/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]