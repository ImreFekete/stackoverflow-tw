FROM openjdk:17-jdk

WORKDIR /app

COPY ./backend/target/stackoverflow-tw-0.0.1-SNAPSHOT.jar /app/stackoverflow-tw-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "stackoverflow-tw-0.0.1-SNAPSHOT.jar"]