FROM openjdk:11-jdk-slim
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/transactions-api-0.0.1-SNAPSHOT.jar transactions.jar
EXPOSE 9090
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar transactions.jar
