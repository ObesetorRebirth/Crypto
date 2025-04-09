
# Crypto Trading Backend (Spring Boot)

This is the **Java Spring Boot backend** for a real-time crypto trading simulator application. It handles all the backend functionality, including the WebSocket connection to Kraken, transaction processing, user management, and portfolio tracking.

---

## Tech Stack

- **Java 17+**
- **Spring Boot 3+**
  - Spring Web
  - Spring WebSocket
  - Spring Data JPA
- **Kraken WebSocket Integration**
- **MySQL Database** (instead of PostgreSQL)
- **Docker** support

---

## Features

- **WebSocket Integration**: Connects to Kraken WebSocket for live price updates.
- **RESTful API**: Provides endpoints for buying/selling crypto, checking balances, and managing users and transactions.
- **Transaction History**: Calculates profit/loss for each sell transaction.
- **User Management**: Create users, check balances, reset accounts.
- **Database**: Stores user portfolios, transaction histories, and cryptocurrency prices.
- **Transaction Profit/Loss**: Calculates profit or loss when a user sells a cryptocurrency based on the purchase price.

---

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/ObesetorRebirth/crypto-backend.git
cd crypto-backend
```

### 2. Build the Project

Make sure you have **Java 17** or higher installed. If you're using Maven, build the project with the following command:

```bash
mvn install -DskipTests
```

### 3. Configure the Application

Before running the application, ensure that you have the necessary configurations in place:

- **Database Configuration**: This project uses **MySQL** as the database. 

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crypto_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

- **Kraken WebSocket**: You don’t need to configure it manually — the app connects to Kraken's WebSocket API automatically.

### 4. Run the Application with Docker

To run the application using Docker, follow these steps:

1. **Build the Docker image**

```bash
docker build -t crypto-backend .
```

2. **Run the Docker container**

```bash
docker run -p 8080:8080 --env-file .env crypto-backend
```

Ensure your `.env` file contains the correct MySQL database credentials:

```
MYSQL_USER=your_db_user
MYSQL_PASSWORD=your_db_password
MYSQL_DATABASE=crypto_db
```

This will start the application inside a Docker container, and it will be available at `http://localhost:8080`.

---

## Available Endpoints

The backend provides several RESTful API endpoints to manage users, transactions, and portfolio information.

### User Management

- **Create User**: `POST /user`
- **Get User Balance**: `GET /user/{userId}/balance`
- **Reset User Account**: `PUT /user/{userId}/reset`

### Crypto Management

- **Get Top 20 Cryptos**: `GET /crypto/top20`
- **Get Specific Crypto Info**: `GET /crypto/{cryptoId}`

### Transactions

- **Buy Crypto**: `POST /transaction/{userId}/buy/{cryptoId}?quantity={quantity}`
- **Sell Crypto**: `POST /transaction/{userId}/sell/{cryptoId}?quantity={quantity}`
- **Get Transaction History**: `GET /transaction/{userId}/{transactionType}`
- **Get Profit or Loss for Transaction**: `GET /transaction/{transactionId}/profitOrLoss`

### Holdings

- **Get User Holdings**: `GET /holding/{userId}`
- **Get Specific Crypto Holdings**: `GET /holding/{userId}/{cryptoId}`

---

## WebSocket Integration

The backend connects to Kraken’s WebSocket API to receive real-time price updates for cryptocurrencies. The app subscribes to the following pairs:

- Bitcoin (`BTC/USD`)
- Ethereum (`ETH/USD`)
- Litecoin (`LTC/USD`)
- Other major cryptocurrencies

WebSocket reconnection logic ensures that the backend automatically reconnects and resubscribes if the WebSocket connection is lost.

---

## Developer Notes

- **Price Updates**: The backend listens to Kraken WebSocket and updates crypto prices every time new data is received.
- **Transactions**: When a user sells a crypto, the backend calculates the profit or loss based on the buying price stored in the database.
- **Database**: The app uses MySQL for production, with Docker support for easy setup.

