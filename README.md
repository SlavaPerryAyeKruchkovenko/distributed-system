1. Сборка проекта RUN ./gradlew clean build
2. Сборка докер образа  docker build -t distributed-system .
3. Запуска образа указывая порты docker run -p 8080:3000 distributed-system
4. Открываем http://localhost:8080/