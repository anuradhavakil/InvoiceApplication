# Invoice Application Demo Project

Invoice application demo project demonstrates working of Restful application built using Spring Boot, MySQL as RDBMS and UI technologies of HTML5, Bootstrap CSS and JQuery.

## Getting Started

To get started - clone the repository locally and build the project using gradle build system.

### Prerequisites

Java 7
IDE of your choice (Intellij recommended)
MySQL


### Setup

A step by step series of examples that tell you have to get a development env running

1.  Clone the repository

```
git clone
```

2.  Build the project

```
./gradlew clean build
```

3. Running the spring based web application

```
./gradlew bootRun
```

4. In order to create new invoice go to:

http://localhost:8080/invoice

5. After the invoice is created successfully to view the line items go to:

http://localhost:8080/invoice/{invoiceid}

6. To test the service through postman -

url:http://localhost:8080/invoice


payload:

```
{
 "name":"Kim",
 "email":"Kim_Mark@intuit.com",
 "dueDate":"2017-06-20",
 "invoiceLineItems":[{"itemDescription":"water", "amount":35},{"itemDescription":"wood", "amount":40}]
```

## Assumptions
MySQL database created outside of the application.  The tables required for the application can be created via sql scripts included in the project

### Future Work
Connection pooling for Database
UI Enhancements

### Technologies used
Java - 1.7
Spring boot
Bootstrap CSS
jQuery
MySQL DB