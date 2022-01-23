# Internship project

## Description:
Backend application built on Spring Core to keep track of employees and their workload by department.

## Stack of technologies:
* Java 17

* Spring - core, MVC, Data JPA, Security (Basic Authorization)

* Database - PostgreSql.

* ORM - Hibernate

* API Documentation: Open API (former Swagger)

* Checkstyle/Formatter: Google formatter (GitHub - google/google-java-format: Reformats Java source code to comply with Google Java Style.)

* Containerize the app with Docker.

* DB migrations: Liquibase

* Tests - Mockito, JUnit5

* Build - maven

* Libs:

  * Lombok
  * ModelMapper

## REST API:

* ### authorization
  * [POST] registration
  * [POST] login

* ### employees
  * [GET] employees
  * [GET] employees/{id}
  * [POST] employees
  * [PATCH] employees/{id}
  * [DELETE] employees/{id}
  * [POST] employees/upload (requestParam file)
  * [GET] employees/available?moment=...
  * [GET] employees/report?type=...

* ### departments
  * [GET] departments
  * [GET] departments/{id}
  * [POST] departments
  * [PATCH] departments/{id}
  * [DELETE] departments/{id}
  * [POST] departments/{id}/{user_id}
  * [DELETE] departments/{id}/{user_id}

* ### projects
  * [GET] projects
  * [GET] projects/{id}
  * [POST] projects
  * PATCH] projects/{id}
  * [DELETE] projects/{id}
  * [POST] projects/{id}/{user_id}
  * [DELETE] projects/{id}/{user_id}

## Use Cases:

#### An admin can:
* to register
* to log in the system
* to log out the system
* to create project
* to delete project
* to create department
* to delete department
* to create employee account
* to delete employee account
* to change information about employees account, department or project
* to see all porjects
* to see all departments
* to see all employees
* to see required employee
* to assign employee to project
* to remove employee from project
* to move employee to a lower or higher position
* to download reports about department's workloads
* to download reports about employees availability within current month or others
* to load new users from CSV file

#### An employee can:
* to log in the system
* to log out the system
* to change information about him
* to see all employees on his project
* to see all projects where he was assigned
* to see all departments
* to see a required department

#### A lead can:
* to log in the system
* to log out the system
* to see all projects
* to see information about required project
* to see all employees in a required project
* to see all employees
* to see required employee
* to download all types of reports