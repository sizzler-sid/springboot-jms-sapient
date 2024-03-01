FROM eclipse-temurin:17-jdk
WORKDIR /opt
ENV PORT 8072
EXPOSE 8072
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar