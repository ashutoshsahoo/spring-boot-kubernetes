# set the base image
FROM adoptopenjdk/openjdk11:alpine-jre


# arguments

ARG IMAGE_VERSION

# extra metadata
LABEL author="Ashutosh Sahoo"
LABEL version=${IMAGE_VERSION}
LABEL description="Spring boot application."

# expose port
EXPOSE 8080

# Add a volume pointing to /tmp
VOLUME /tmp

# add the application to the container
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=UTC","-cp","app:app/lib/*","com.ashu.demo.SpringBootKubernetesApplication"]
