Digital Banking Platform - Project Requirements

Project Vision

Build a secure, scalable, and maintainable Digital Banking Platform using Spring Boot following enterprise-grade development practices.

The platform will allow customers to manage bank accounts, beneficiaries, and transactions while enabling administrators to manage customers and accounts.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Technology Stack:

Backend
Java 21
Spring Boot 3.x
Spring Security
Spring Data JPA
Hibernate
MapStruct
Lombok
Database
PostgreSQL
Build Tool
Maven
Testing
JUnit 5
Mockito
AssertJ
Documentation
Swagger / OpenAPI
Containerization
Docker
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
User Roles

CUSTOMER

Customer can:

Register
Login
View Profile
Update Profile
Create Account
View Account
Add Beneficiary
View Beneficiaries
Delete Beneficiary
Transfer Money
View Transaction History
ADMIN

Administrator can:

View Customers
Search Customers
Activate Customers
Deactivate Customers
Freeze Accounts
Unfreeze Accounts
Functional Requirements
Authentication Module
Register User

User should be able to register using:

Username
Email
Password
Login

User should be able to login and receive JWT token.

Authorization

Role-based authorization must be implemented.

Supported Roles:

CUSTOMER
ADMIN
Customer Module

Features:

Create Customer
Get Customer
Update Customer
Account Module

Features:

Create Account
View Account Details
View Account Balance
Search Accounts
Pagination Support
Beneficiary Module

Features:

Add Beneficiary
View Beneficiary
Delete Beneficiary
Transaction Module

Features:

Transfer Money
View Transaction History
Admin Module

Features:

View Customers
Search Customers
Freeze Accounts
Activate Customers