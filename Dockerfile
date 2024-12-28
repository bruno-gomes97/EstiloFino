# Etapa de build
FROM maven:3.9.6-openjdk-17-slim AS build

WORKDIR /app

# Copie apenas os arquivos necessários
COPY pom.xml .
COPY src ./src

# Compile o projeto
RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copie o .jar da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
