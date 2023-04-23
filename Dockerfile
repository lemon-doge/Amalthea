FROM openjdk:17-oracle

#ENV DB_HOST jdbc:postgresql://postgres-server:5432/postgres
#ENV DB_USERNAME postgres
#ENV DB_PASSWORD password

ARG JAR_FILE=target/amalthea.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=256m","/app.jar"]

