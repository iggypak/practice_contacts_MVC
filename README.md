# Contacts Manager

Contacts Manager — это приложение для управления списком контактов. Оно предоставляет RESTful API и пользовательский интерфейс на основе Thymeleaf для работы с контактами. Программа использует Spring Boot, JdbcTemplate, и PostgreSQL для хранения данных.

---

## Функциональность

### Через REST API:
- **Получение списка контактов**: `GET /contacts`
- **Получение контакта по ID**: `GET /contacts/{id}`
- **Создание нового контакта**: `POST /contacts/save`
- **Обновление контакта**: `POST /contacts/save`
- **Удаление контакта**: `POST /contacts/delete/{id}`

### Через Web-интерфейс (Thymeleaf):
- **Просмотр списка контактов**: `GET /contacts`
- **Добавление нового контакта**: `GET /contacts/edit`
- **Редактирование существующего контакта**: `GET /contacts/edit/{id}`
- **Удаление контакта**: Кнопка "Delete" на странице списка контактов.

---

## Запуск программы

### 1. **Системные требования**
- Java 17 или выше.
- Docker (для запуска базы данных через `docker-compose`).
- Maven (если сборка выполняется вручную).

### 2. **Подготовка к запуску**
#### Вариант 1: Использование Docker Compose
1. Убедитесь, что Docker и Docker Compose установлены.
2. Выполните команды:
   ```bash
   docker-compose build && docker-compose up
   ```
   Эта команда запускает приложение и базу данных PostgreSQL.

#### Вариант 2: Локальный запуск через Maven
1. Соберите проект:
   ```bash
   mvn clean package
   ```
2. Запустите базу данных PostgreSQL отдельно (например, через Docker):
   ```bash
   docker run --name postgres-db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=contacts_db -p 5432:5432 -d postgres:15
   ```
3. Запустите приложение:
   ```bash
   java -jar target/contacts-manager-1.0.0.jar
   ```

---

## Настройки по умолчанию

Настройки указаны в файле `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://db:5432/contacts_db # Подключение к PostgreSQL
    username: user                             # Имя пользователя базы данных
    password: password                         # Пароль базы данных
  sql:
    init:
      mode: always                             # Автоматически выполнять SQL-скрипты при запуске
server:
  port: 8080                                   # Порт приложения
```
