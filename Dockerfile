FROM openjdk:8u171-jre-alpine3.7
MAINTAINER TeraHorse
COPY build/libs/*.jar /home/fobit.jar
EXPOSE 5000
CMD java -jar /home/fobit.jar

