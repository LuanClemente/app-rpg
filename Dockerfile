# Dockerfile para backend Java Spring Boot
# Usa imagem oficial do OpenJDK
FROM openjdk:17-jdk-slim

# Define diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e o diretório de dependências
COPY pom.xml .
COPY src ./src

# Faz o build do projeto
RUN ./mvnw package -DskipTests || mvn package -DskipTests

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa o jar gerado
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
