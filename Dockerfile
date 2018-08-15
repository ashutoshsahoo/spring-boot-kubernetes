# set the base image
FROM openjdk:10-jre-slim

# author
MAINTAINER Ashutosh Sahoo

# extra metadata
LABEL version="3.0.0"
LABEL description="Spring boot application with Dockerfile."

#The application's jar file
ENV JAR_NAME SpringBootKubernetes-3.0.0.jar

# Add a volume pointing to /tmp
VOLUME /tmp

# add the application to the container
ADD target/$JAR_NAME app.jar

# expose port
EXPOSE 8080

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=UTC","-jar","/app.jar"]
