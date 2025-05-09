# Imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Instala Maven
RUN apk add --no-cache maven

# Crea el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el contenido del proyecto al contenedor
COPY . .

# Ejecuta el build del proyecto sin tests
RUN mvn clean package -DskipTests

# Expone el puerto donde correrá tu app
EXPOSE 8080

# Comando para ejecutar el WAR
CMD ["java", "-jar", "target/Aktivai-0.0.1-SNAPSHOT.war"]
