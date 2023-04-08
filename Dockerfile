FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY target/Kulinar-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT java -jar /app/Kulinar-0.0.1-SNAPSHOT.jar