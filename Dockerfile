FROM dougschuindt/alpine-jdk16:latest

COPY /target/nossacara-*.jar /opt/nossacara.jar

ENTRYPOINT ["java", "-jar", "/opt/nossacara.jar"]

EXPOSE 8089