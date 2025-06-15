# Online shop

* Контрольная работа #3 для курса "Конструирование программного обеспечения", ВШЭ ФКН ПИ, 2 курс.

* Big homework #3 for "Software Engineering" course, HSE FCS SE, 2nd year

## Requirements

- docker
- docker-compose
- Postman (for testing)
- open ports: 8081, 8082, 8083, 5432, 9092, 2181

## Building and running

Для сборки проекта:

```shell
docker compose build
```

Для запуска проекта (все логи будут выведены в терминал):

```shell
docker compose up
```

Для запуска проекта в фоновом режиме:
```shell
docker compose up -d
```

Для остановки проекта, запущенного в фоновом режиме:
```shell
docker compose stop
```

## API

Сетевой интерфейс поддерживает следующую функиональность:

### Payment Service

**1. Создание аккаунта**
```
POST http://localhost:8081/api/payments/account
Content-Type: application/json

{ "userId": 123 }
```

Пример ответа (200 OK):
```
{ 
  "userId": 123, 
  "balance": 10.0 
}
```

**2. Пополнение счёта:**
```
POST http://localhost:8081/api/payments/deposit
Content-Type: application/json

{ "userId": 123, "amount": 10.0 }
```

Пример ответа (200 OK):
```
{ 
  "userId": 123, 
  "balance": 10.0 
}
```

**3. Просмотр баланса:**
```
GET http://localhost:8081/api/payments/balance/1
```

Пример ответа (200 OK):
```
{ 
  "userId": 123, 
  "balance": 10.0 
}
```

### Order Service

**4. Создание заказа:**
```
POST http://localhost:8081/api/orders
Content-Type: application/json

{ "userId": 123, "amount": 4.0 }
```

Пример ответа (200 OK):
```
{ 
  "orderId": 1, 
  "userId": 123, 
  "amount": 4.0, 
  "status": "CREATED", 
  "createdAt": "1889-04-20T18:30:00.000+00:00"
}
```

**5. Просмотр всех заказов пользователя:**
```
GET http://localhost:8081/api/orders/user/123
```

Пример ответа (200 OK):
```
[
  { 
    "orderId": 1, 
    "userId": 123, 
    "amount": 4.0, 
    "status": "FINISHED", 
    "createdAt": "1889-04-20T18:30:00.000+00:00"
  }
]
```

**6. Просмотр информации по заказу:**
```
GET http://localhost:8081/api/orders/1
```

Пример ответа (200 OK):
```
{ 
  "orderId": 1, 
  "userId": 123, 
  "amount": 4.0, 
  "status": "FINISHED", 
  "createdAt": "1889-04-20T18:30:00.000+00:00"
}
```