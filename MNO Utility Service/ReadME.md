# README: MNO Utility Service Ecosystem

## Project Overview

This project involves the creation of a WSDL document and the implementation of a backend API for a Telecommunication Company's services. The API simulates essential telecommunication services such as airtime purchase, balance checking, and airtime transfer. This solution targets developers and companies aiming to integrate with a robust SOAP API for telecom functionalities.

## Features Implemented

- **WSDL Design**: Designed a comprehensive WSDL document outlining the SOAP services provided by the telecommunication company. The services include airtime purchase, balance checking, airtime transfer, and more.

- **SOAP API Implementation**: Developed a Spring Boot application that serves SOAP endpoints as defined by the WSDL. This backend API simulates the core functionalities of telecommunication services.

- **User Authentication**: Integrated a simple authentication mechanism within the API to ensure security. Authentication is performed using credentials passed within SOAP headers.

- **Documentation**: Included WSDL documentation and Swagger documentation for RESTful endpoints related to user authentication. This documentation provides a clear guide for developers on how to utilize the SOAP endpoints.

- **Testing**: Implemented comprehensive unit tests for each SOAP endpoint, covering various scenarios including successful transactions and potential error cases.

- **CORS Implementation**: Enabled CORS to allow web applications from different origins to interact securely with the SOAP API.

## Directory Structure

```plaintext
/MNO-Utility-Service-Ecosystem
|-- /src
|   |-- /main
|   |   |-- /java
|   |   |   |--/com/miniproject/mnoutilityservice
|   |   |   |   |   |   |   |   |-- Application.java
|   |   |   |   |   |   |   |   |   |- /config
|   |   |   |   |   |   |   |   |   |-- /endpoint
|   |   |   |   |   |   |   |   |   |-- /entity
|   |   |   |   |   |   |   |   |   |-- /exception
|   |   |   |   |   |   |   |   |   |-- /repository
|   |   |   |   |   |   |   |   |   |-- /service
|   |   |   |   |   |   |   |   |   |-- /security
|   |   |-- /resources
|   |       |-- activities.xsd
|   |       |-- application.properties
|   |       |-- activities.xsd
|-- /test
|   |-- /java
|-- pom.xml
|-- README.md
|-- wsdl
    |-- activities.wsdl
    |-- users.wsdl
```

## Setup and Running the Project

### Prerequisites

- Java JDK 11 or later
- Maven 3.6 or later
- An IDE like IntelliJ IDEA or Eclipse

### Steps to Run

1. **Clone the Repository**
   ```bash
   git clone https://example.com/MNO-Utility-Service-Ecosystem.git
   cd MNO-Utility-Service-Ecosystem
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

The application will start and be accessible via `http://localhost:4040`.

## Integration Guidelines for Developers

- **Documentation**: Refer to the `wsdl/users.wsdl` and `wsdl/activities.wsdl` for details on the SOAP operations available.
- **Consuming the SOAP Services**: Sample client code and detailed integration steps are provided in the `docs/` directory to help developers make requests to the SOAP endpoints.
- **Security and Authentication**: Ensure to pass valid authentication tokens in the SOAP headers as per the guidelines in the Swagger documentation.

## Testing

Unit tests can be run with the following command:

```bash
mvn test
```

These tests validate the functionality of the SOAP endpoints and ensure that the application handles edge cases and errors gracefully.

## Contributing

Contributions to the project are welcome. Please fork the repository, make your changes, and submit a pull request.

## Conclusion

This project successfully simulates a variety of telecommunication services through a well-documented and secure SOAP API. It stands ready for integration with systems requiring telecom functionalities, offering a solid foundation for further development and customization.

For more details or support, please contact the development team at `georgejunior.boadu@gmail.com`.