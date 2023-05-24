# Дипломный проект по профессии «Тестировщик»
Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
## Инструкция по запуску тестов
#### Для MySQL:

1. Выполнить команду в терминале: `docker-compose up node mysql`
2. Выполнить команду в терминале: `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
3. Выполнить команду в терминале: `./gradlew clean test "-Durl=jdbc:mysql://localhost:3306/app"`
4. Выполнить команду в терминале: `./gradlew allureServe`

#### Для PostgreSQL:

1. Выполнить команду в терминале: `docker-compose up node postgres`
2. Выполнить команду в терминале: `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
3. Выполнить команду в терминале: `./gradlew clean test "-Durl=jdbc:postgresql://localhost:5432/app"`
4. Выполнить команду в терминале: `./gradlew allureServe`