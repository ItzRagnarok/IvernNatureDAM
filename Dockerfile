# Etapa 1: Compilación
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
# Compila el proyecto saltándose los tests para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
# Copia el archivo .jar generado en la etapa anterior
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]