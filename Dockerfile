# Etapa de construção
FROM maven:3.8.1-openjdk-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos de configuração do Maven e o código-fonte
COPY pom.xml .
COPY src ./src

# Compila o aplicativo
RUN mvn -e -B -DskipTests clean package

# Etapa de execução
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o artefato construído da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]