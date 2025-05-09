FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el contenido del proyecto
COPY . .

# Asegurarse de que mvnw tenga permisos de ejecución
RUN chmod +x mvnw

# Construir el proyecto sin ejecutar los tests
RUN ./mvnw clean package -DskipTests

# Exponer el puerto en el que correrá tu app
EXPOSE 8080

# Comando para correr el archivo WAR
CMD ["java", "-jar", "target/Aktivai-0.0.1-SNAPSHOT.war"]
