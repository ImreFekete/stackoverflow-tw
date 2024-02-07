# SnackOverFlow Project

Welcome to the SnackOverFlow Project! This web application aims to provide a platform for users to ask questions, share knowledge, and engage in discussions, much like StackOverflow. The project is built using Spring Boot for the backend and a lightweight React frontend.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Features](#features)
- [Usage](#usage)

## Technologies Used

This project utilizes the following technologies:

- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started): A Java-based framework for building robust and scalable web applications.
- [PostgreSQL](https://www.postgresql.org/docs/current/): A powerful open-source relational database system used to store forum data.
- [React](https://react.dev/learn): A JavaScript library for building user interfaces.

## Getting Started

### Prerequisities
- [Java 17 or later version](https://www.oracle.com/java/technologies/downloads/)
- [Node](https://nodejs.org/en)
- [Docker](https://docs.docker.com/engine/)
- [Docker Compose](https://docs.docker.com/compose/)

1. Clone this repository to your local machine.
```sh
  $ git clone https://github.com/ImreFekete/stackoverflow-tw.git
```
2. Install dependencies
frontend:
```sh
  $ cd frontend/stackoverflow
  $ npm install
```
backend:
```sh
  $ cd backend
  $ mvn install
```
## Run The Application

You can run the application with the help of Docker Compose.
First you will need a .env file in the root directory with the username and password for the postgres database:
```.env
DB_USER= username
DB_PASS= password
```
In the root directory you can run the application with:
```sh
  $ docker compose up
```
the frontend is accessable on localhost:80 in your browser.

## Features

- Main Page Listing: View all questions with details, dates, and answer counts.
- Sorting Functionality: Sort questions on the main page by alphabet, date, or answer count.
- Question Detail Page: Explore individual questions, and access a list of answers with dates and users.
- Adding Questions and Answers: Users can add new questions and answer to existing ones.
- User Authentication: Register new users, log in, and store sessions.

## Usage

1. Register for an account or log in if you already have one.
2. Explore questions, answers, and discussions on various topics.
3. Ask questions and provide answers to share your knowledge.
