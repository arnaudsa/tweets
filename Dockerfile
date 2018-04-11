FROM java:8
MAINTAINER Arnaud Santana Alves <arnaudsalves@gmail.com>
RUN mkdir -p /opt/app
ADD target/tweets.jar /opt/app/tweets.jar
EXPOSE 8080
CMD ["java", "-jar",  "/opt/app/tweets.jar"]