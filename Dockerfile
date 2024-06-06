# Используем базовый образ с JDK для этапа сборки
FROM eclipse-temurin:17-jdk as builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скрипт gradlew и папку gradle (для зависимостей)
COPY gradlew .
COPY gradle gradle

# Обеспечиваем права на выполнение для gradlew
RUN chmod +x gradlew

# Копируем файлы сборки Gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Скачиваем зависимости, чтобы кешировать этот слой
RUN ./gradlew dependencies --no-daemon

# Копируем остальной исходный код
COPY src src

# Сборка проекта без тестов
RUN ./gradlew clean build -x test --no-daemon

# Используем более лёгкий базовый образ с JRE для этапа выполнения
FROM eclipse-temurin:17-jre

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скомпилированный jar файл из этапа сборки
COPY --from=builder /app/build/libs/*.jar app.jar

# Открываем порт 8080
EXPOSE 8080

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
