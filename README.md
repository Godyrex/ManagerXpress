# ManagerXpress

![Project Logo](logo-.png)

## Description

ManagerXpress is a full-stack application developed with Angular and Spring Boot. It is a powerful web application designed to simplify and streamline data management for businesses. Offering an intuitive and feature-rich platform, ManagerXpress makes it easy to create and manage databases.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Node.js](https://nodejs.org/) installed
- [Angular CLI](https://angular.io/cli) installed
- [Java](https://www.java.com/) installed
- [MongoDB](https://www.mongodb.com/) installed and configuredled

## Installation

### Frontend (Angular)

1. Create a new Angular project:
    ```bash
    ng new ManagerXpressFront
    ```
    
2. Navigate to the `ManagerXpressFront` folder.

3. Copy the contents of the `ManagerXpressFront` folder from this repository into your new Angular project.

4. Run `npm install` to install dependencies.

5. Run `ng serve` to start the development server.

6. Open `http://localhost:4200/` in your browser.

### Backend (Spring Boot)

1. Navigate to the `ManagerXpressBack` folder.

2. Open `src/main/resources/application.properties`.

3. Update the MongoDB configuration with your own settings:
    ```properties
    spring.data.mongodb.host=your-mongodb-host
    spring.data.mongodb.port=your-mongodb-port
    spring.data.mongodb.database=your-mongodb-database
    spring.data.mongodb.username=your-mongodb-username
    spring.data.mongodb.password=your-mongodb-password
    ```

4. Run intellij to start the Spring Boot application.


## License

This project is licensed under the [MIT License] - see the [LICENSE](LICENSE) file for details.

## Contact

Feel free to reach out for any questions or feedback:

**LinkedIn:** [Oussema Ouakad](https://www.linkedin.com/in/ouakadoussema/)

**Email:** oussouakad@gmail.com

Your inquiries and thoughts are important, and I look forward to connecting with you!
