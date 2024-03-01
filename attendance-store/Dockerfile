FROM eclipse-temurin:17-jdk
WORKDIR /opt
ENV PORT 8092
EXPOSE 8092
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar