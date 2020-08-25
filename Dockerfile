FROM maven:3.6.3-jdk-8
MAINTAINER Kavya Nadella
COPY . /usr/src/app/
WORKDIR /usr/src/app/

RUN mvn -f /usr/src/app/pom.xml clean package
RUN mv target/consumerWeatherApp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar