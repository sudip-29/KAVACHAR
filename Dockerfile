FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean bootJar --no-daemon

EXPOSE 10000

CMD ["sh", "-c", "java -jar build/libs/*.jar"]