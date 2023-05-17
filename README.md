# Дипломный проект по профессии «Тестировщик»
Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
## Инструкция по запуску тестов
#### Для MySQL:
1. В `application.properties` для `spring.datasource.url=` указать значение `jdbc:mysql://localhost:3306/app`
2. В классе `SQLHelper` для переменной `url` указать значение `jdbc:mysql://localhost:3306/app`
3. Выполнить команду в терминале: `docker-compose up node mysql`
4. Выполнить команду в терминале: `java -jar artifacts/aqa-shop.jar`
5. Выполнить команду в терминале: ./gradlew clean test
6. Выполнить команду в терминале: ./gradlew allureServe

#### Для PostgreSQL:
1. В `application.properties` для `spring.datasource.url=` указать значение `jdbc:postgresql://localhost:5432/app`
2. В классе `SQLHelper` для переменной `url` указать значение `jdbc:postgresql://localhost:5432/app`
3. Выполнить команду в терминале: `docker-compose up node postgres`
4. Выполнить команду в терминале: `java -jar artifacts/aqa-shop.jar`
5. Выполнить команду в терминале: ./gradlew clean test
6. Выполнить команду в терминале: ./gradlew allureServe