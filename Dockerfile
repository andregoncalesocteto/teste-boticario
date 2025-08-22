FROM maven:3.9-eclipse-temurin-17

WORKDIR /app

# Copia o projeto inteiro
COPY . /app

# Instala dependências e compila
RUN mvn install -DskipTests

# Roda com hot reload
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.fork=false"]
