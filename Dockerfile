# Первая стадия сборки (build)
FROM openjdk:17-jdk-slim AS build
WORKDIR /app

# Копируем файлы Gradle
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle/

# Даем права на выполнение Gradle Wrapper
RUN chmod +x gradlew

# Устанавливаем зависимости (кэшируется)
RUN ./gradlew dependencies --no-daemon

# Копируем исходники
COPY src /app/src

# Собираем JAR-файл
RUN ./gradlew bootJar --no-daemon

# Проверяем, что JAR-файл создан
RUN ls -la /app/build/libs/  # Эта команда покажет содержимое папки с JAR-файлами

# Вторая стадия (финальный образ)
FROM openjdk:17-jdk-slim
WORKDIR /app

# Копируем JAR-файл из стадии сборки
COPY --from=build /app/build/libs/Order-Service-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду запуска
CMD ["java", "-jar", "app.jar"]