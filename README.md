# Retail Manager REST API

## Overview

The Retail Manager REST API is a Spring Boot application designed to manage retail operations. It provides various API endpoints for managing products, categories, customers, invoices, and more. The application includes features such as JWT authentication, exception handling, and CI/CD workflows using GitHub Actions.

This project includes:
- A **web client** built with **Angular**, available in the `frontend` branch.  [Link to the frontend branch](https://github.com/youssef-faik/retail-manager-rest-api/tree/frontend).
- An **Android mobile client** available in a separate repository: [Link to the mobile app](https://github.com/youssef-faik/retail-manager-mobile-app).


## Key Features

- Manage products, categories, customers, and invoices
- JWT authentication for secure access
- Exception handling and global exception handler
- Docker and Docker Compose configurations
- CI/CD workflows using GitHub Actions
- MySQL database configurations

## Prerequisites

- **Java 17** (Runtime: OpenJDK 17 Oracle)
- **Spring Boot 3.0.5** (Web, Security, JPA, Validation)
- **MySQL** (Database)
- **Swagger (SpringDoc OpenAPI)** (API Documentation)
- **JWT (Json Web Token)** (Secure Authentication)
- **WebSockets** (Real-time Communication)
- **JasperReports** (Report Generation)
- **Docker + Jib** (Containerization & Deployment)
- **Angular** (Web Client)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/youssef-faik/retail-manager-rest-api.git
   cd retail-manager-rest-api
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Set up the MySQL database:
   ```bash
   docker-compose up -d db
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Running Tests

To run the tests, use the following command:
```bash
mvn test
```

## Building the Project

To build the project, use the following command:
```bash
mvn clean package
```

## 📖 API Documentation (Swagger)
Once the application is running, the API documentation is available here:
👉 http://localhost:8080/swagger-ui.html

🖼️ Swagger Preview

![Swagger Preview](/screenshots/1730130581501.jpeg)

## 🎨 Web Client (Frontend Angular)
The frontend of this application is built with Angular and is available in the frontend branch.
To run the frontend locally:
```bash
git checkout frontend
npm install
ng serve
```
The web interface will be accessible here:
👉 http://localhost:4200

## 🖼️ Frontend Preview
### Dashboard
![dashboard](/screenshots/filled%20dashboard.png)
### Create invoice - step 1
![create invoice - step 1](/screenshots/ajouter_facture1.png)
### Create invoice - step 2
![create invoice - step 2](/screenshots/ajouter_facture2.png)
### Create invoice - step 3 printing
![Create invoice - step 3 printing](/screenshots/ajouter_facture_imprimer.png)

#### Products management 
![products management](/screenshots/paggination_prodiui.png)

## API Endpoints

### Authentication

- **POST /api/v1/auth/login**: Authenticate user and retrieve JWT token.

### Products

- **GET /api/v1/products**: Retrieve a list of all products.
- **GET /api/v1/products/{id}**: Retrieve details of a product by ID.
- **POST /api/v1/products**: Create a new product.
- **PUT /api/v1/products/{id}**: Update details of a product by ID.
- **DELETE /api/v1/products/{id}**: Delete a product by ID.

### Categories

- **GET /api/v1/categories**: Retrieve a list of all categories.
- **GET /api/v1/categories/{id}**: Retrieve details of a category by ID.
- **POST /api/v1/categories**: Create a new category.
- **PUT /api/v1/categories/{id}**: Update details of a category by ID.
- **DELETE /api/v1/categories/{id}**: Delete a category by ID.

### Customers

- **GET /api/v1/customers**: Retrieve a list of all customers.
- **GET /api/v1/customers/{ice}**: Retrieve details of a customer by ICE.
- **POST /api/v1/customers**: Create a new customer.
- **PUT /api/v1/customers/{ice}**: Update details of a customer by ICE.
- **DELETE /api/v1/customers/{ice}**: Delete a customer by ICE.

### Invoices

- **GET /api/v1/invoices**: Retrieve a list of all invoices.
- **GET /api/v1/invoices/{id}**: Retrieve details of an invoice by ID.
- **POST /api/v1/invoices**: Create a new invoice.
- **GET /api/v1/invoices/{id}/pdf**: Retrieve a PDF file of an invoice by ID.

### Dashboard

- **GET /api/v1/dashboard/sales**: Retrieve sales data within a given period.
- **GET /api/v1/dashboard/orders**: Retrieve order data within a given period.
- **GET /api/v1/dashboard/customers**: Retrieve customer data within a given period.

## Docker and Docker Compose

The project includes Docker and Docker Compose configurations for running the application and its dependencies. The `docker-compose.yml` file defines the services and their configurations.

To start the application using Docker Compose, run the following command:
```bash
docker-compose up -d
```

## CI/CD Workflows

The project includes GitHub Actions workflows for continuous integration and deployment. The workflows are defined in the `.github/workflows` directory.

### CI Workflow

The CI workflow (`build.yml`) runs on pull requests to the `main` branch and includes the following steps:
- Checkout code
- Set up JDK 17
- Cache Maven packages
- Build with Maven

### CD Workflow

The CD workflow (`deploy.yml`) runs on pushes to the `main` branch and includes the following steps:
- Checkout code
- Set up JDK 17
- Generate new build number
- Login to Docker Hub
- Run Maven clean package and push to Docker Hub
- Update and commit app version in `docker-compose.yml`
- Deploy to AWS Elastic Beanstalk

## Database Configuration

The application uses MySQL as the database. The database configurations are defined in the `application.yml` file for both development and production profiles.

### Development Profile

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ibsys-retail-manager
    username: root
    password: root
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop
    properties:
      hibernate:
        format_sql: true
```

### Production Profile

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://awseb-e-ppmm73m8mx-stack-awsebrdsdatabase-r582s3v92z42.capwucesgzey.eu-west-3.rds.amazonaws.com:3306/ibsys-retail-manager
    username: superadmin
    password: superadmin
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop
    properties:
      hibernate:
        format_sql: true
```

## Security Configuration

The application uses JWT authentication for secure access. The security configurations are defined in the `SecurityConfiguration` class.

## Exception Handling

The application includes exception handling and a global exception handler. The exception handling is defined in the `GlobalExceptionHandler` class.