FROM node:23 AS ng-builder
WORKDIR /src 

RUN npm i -g @angular/cli
COPY ./client/src src

COPY ./client /*.json .

RUN npm ci 
RUN ng build 

FROM openjdk:23-jdk AS j-builder
WORKDIR /src 

COPY --from=ng-builder /src/dist/client-side/browser src/main/resources/static

COPY src src
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod a+x mvnw && ./mvnw package -Dmaven.test.skip=true

FROM openjdk:23-jdk

WORKDIR /app
COPY --from=j-builder /src/target/ecommerce-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
ENV SPRING_DATA_MONGODB_URI=uri
ENV SPRING_DATA_MONGO_DATEBASE=database
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/practice1csf
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Bobododo123!
EXPOSE ${PORT}

SHELL ["/bin/sh","-c"]
ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar
