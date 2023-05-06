FROM openjdk:17-jdk-alpine
WORKDIR application
EXPOSE 8080
COPY target/Ibsys-retail-manager.jar ./
ENV SPRING_PROFILES_ACTIVE=development
ENTRYPOINT ["java","-jar","Ibsys-retail-manager.jar"]
