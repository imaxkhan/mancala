FROM openjdk:17
LABEL maintainer="imax.tmd"
VOLUME /app
EXPOSE 8080
ARG JAR_FILE=./target/mancala-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /app/mancala-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/mancala-0.0.1-SNAPSHOT.jar"]
