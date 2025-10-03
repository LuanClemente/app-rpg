# Etapa 1: build do backend (Spring Boot)
FROM maven:3.9.6-eclipse-temurin-17 AS backend-build
WORKDIR /app
COPY pom.xml ./
COPY src ./src/
RUN mvn clean package -DskipTests

# Etapa 2: build do frontend (React)
FROM node:20 AS frontend-build
WORKDIR /frontend
COPY frontend/package.json frontend/package-lock.json* ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Etapa 3: imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia backend
COPY --from=backend-build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Copia frontend build para pasta estática do backend
COPY --from=frontend-build /frontend/build /app/static

# Expõe a porta (Railway injeta automaticamente a variável PORT)
EXPOSE 8080

# Sobe a aplicação
CMD ["java", "-jar", "app.jar"]
