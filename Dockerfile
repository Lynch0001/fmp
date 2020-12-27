FROM openjdk:8
ADD build/libs/gradledemo-0.0.1-SNAPSHOT.jar gradledemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "gradledemo-0.0.1-SNAPSHOT.jar"]
