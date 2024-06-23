# Etapa de construcao
FROM maven:3.8.1-openjdk-17 AS build

# Define o diretorio de trabalho
WORKDIR /app

# Copia os arquivos de configuracao do Maven e o codigo-fonte
COPY pom.xml .
COPY src ./src

# Compila o aplicativo
RUN mvn -e -B -DskipTests clean package

# Etapa de execucao
FROM openjdk:17-jdk-slim

# Define o diretorio de trabalho
WORKDIR /app

# Copia o artefato construido da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expor a porta da aplicacao
EXPOSE 8080

# Comando para executar a aplicacao
CMD ["java", "-jar", "app.jar"]
