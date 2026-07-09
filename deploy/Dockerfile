FROM eclipse-temurin:25-jre AS builder
WORKDIR /usr/local/temp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --launcher

FROM eclipse-temurin:25-jre
ARG VERSION=latest
LABEL version=${VERSION}
LABEL maintainer="Ashutosh Sahoo"
LABEL description="Sample spring boot application for k8s environment"

WORKDIR /usr/local/app
RUN useradd --user-group --system --create-home --no-log-init app
USER app
COPY --from=builder /usr/local/temp/application/spring-boot-loader/ ./
COPY --from=builder /usr/local/temp/application/snapshot-dependencies/ ./
COPY --from=builder /usr/local/temp/application/dependencies/ ./
COPY --from=builder /usr/local/temp/application/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]