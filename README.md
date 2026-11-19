# Order Producer – AWS SQS & Lambda PoC

## Overview

Order Producer is a Spring Boot application that demonstrates asynchronous message processing using **Amazon Simple Queue Service (SQS)** and **AWS Lambda**.

The application exposes a REST API to accept order requests and publish order messages to an Amazon SQS queue. An AWS Lambda function consumes messages from the queue and processes them asynchronously.

## Architecture

```text
Client (Postman)
      |
      v
Spring Boot REST API
      |
      v
SQS Producer Service
      |
      v
Amazon SQS Queue
      |
      v
AWS Lambda Consumer
      |
      v
Message Processing
      |
      +---- Success: Message processed
      |
      +---- Failure: Retry
                    |
                    v
              Dead Letter Queue
```

## Technologies Used

- Java 21
- Spring Boot
- Maven
- Amazon SQS
- AWS Lambda
- AWS SDK for Java
- Postman
- AWS CloudWatch

## Features

- REST API to receive order requests.
- Asynchronous message publishing to Amazon SQS.
- AWS Lambda-based message consumption.
- Automatic retry for failed message processing.
- Dead Letter Queue (DLQ) for messages that repeatedly fail.
- CloudWatch logging for monitoring Lambda execution.

## API Endpoint

**Create Order**

```http
POST /api/orders
Content-Type: application/json
```

### Sample Request

```json
{
  "orderId": "ORD103",
  "customerName": "Test User",
  "amount": 2000,
  "status": "CREATED"
}
```

## Local Setup

### Prerequisites

- Java 21
- Maven
- AWS account
- AWS credentials with appropriate SQS permissions

### Steps

1. Clone the repository.

```bash
git clone <your-github-repository-url>
```

2. Navigate to the project directory.

```bash
cd order-producer
```

3. Configure your AWS region and credentials securely.

4. Run the application.

```bash
mvn spring-boot:run
```

5. Test the API using Postman:

```http
POST http://localhost:8080/api/orders
```

## AWS Configuration

The application publishes messages to the configured SQS queue.

- AWS Region: `eu-north-1`
- Main Queue: `order-processing-queue`
- Dead Letter Queue: `order-processing-dlq`
- Lambda Function: `order-processing-lambda`

## Testing

The PoC covers:

- Successful order message publishing.
- Successful Lambda message processing.
- Failed message processing.
- Automatic message retries.
- Moving repeatedly failed messages to the DLQ.
- Verifying execution logs in CloudWatch.

## Future Enhancements

- Integrate PostgreSQL for order persistence.
- Implement duplicate message handling and idempotency.
- Add monitoring and CloudWatch alarms.
- Improve error handling and automated testing.

## Author

Java Backend Developer | AWS SQS & Lambda Learning PoC
