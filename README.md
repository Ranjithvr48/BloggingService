# Project Name: Blogging Platform

![Blogging Platform Logo](https://tse4.mm.bing.net/th?id=OIP.g_U9qq46tlzGLfUXCepJQAHaEM&pid=Api&P=0&h=180)![Api logo](https://tse3.mm.bing.net/th?id=OIP.3Dduvw7ioc7xnI8YVmFyFAHaEO&pid=Api&P=0&h=180)

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Installation](#installation)
5. [Usage](#usage)
6. [API Endpoints](#api-endpoints)
7. [Database Schema](#database-schema)
8. [Contributing](#contributing)


## Introduction

Blogging Platform is a backend project that aims to provide a robust and scalable platform for users to create and manage blog posts, comment on posts, follow other users, and interact with the blogging community. The platform is designed to offer a seamless user experience and allow bloggers to showcase their writing skills and engage with a wider audience.

## Features

- User Registration and Authentication: Users can sign up, log in, and log out securely using email and password credentials.

- Create and Manage Posts: Authenticated users can create new blog posts, edit their existing posts, and delete their posts.

- Comment on Posts: Authenticated users can add comments to blog posts to share their thoughts and feedback.

- Follow Other Users: Users can follow other bloggers to receive updates and notifications about their new posts.

- Pagination: Posts and comments are paginated to enhance the user experience.

- User-Friendly API: The project provides a well-documented and user-friendly API for easy integration with front-end applications.

## Technologies Used

- Java Spring Boot: For building the backend server and managing RESTful endpoints.

- MySQL: As the relational database management system to store user and blog-related data.

- Hibernate: For object-relational mapping between Java entities and the MySQL database.


- Swagger: For API documentation and testing.

- Maven: As the build tool to manage dependencies and run tasks.

## Installation

1. Clone the repository from [GitHub link](https://github.com/Omkar6627/BlogPost.git).
2. Install Java JDK and Maven on your machine.
3. Set up a MySQL database and configure the database connection in the application.properties file.
4. Run the Maven build to compile the project.
5. Start the application using the command.

## Usage

1. After starting the application, access the API documentation at [http://localhost:8080/swagger-ui.html](http://65.0.199.193:8080/swagger-ui/index.html#/) for information on available endpoints and how to interact with the API.

2. Use tools like Postman or any API client to test the various API endpoints.

## API Endpoints

The project provides the following API endpoints:

- POST /user/signup: Register a new user with the system.
- POST /user/signIn: Authenticate and log in a user.
- DELETE /user/signOut: Log out a user.
- POST /post: Create a new blog post.
- DELETE /post: Delete a blog post.
- POST /comment: Add a comment to a blog post.
- DELETE /comment: Delete a comment from a blog post.
- POST /follow: Follow a user.
- DELETE /unfollow/target/{followId}: Unfollow a user.
- GET /post: Get paginated blog posts.
- PUT /post/{postId}: Update a blog post.
- GET /comment/{postId}: Get paginated comments for a blog post.
- PUT /comment/{commentId}: Update a comment.

## Database Schema

The database schema consists of the following tables:

1. User: Stores user details like name, email, and password.
2. Post: Contains blog post information, including title, content, and user author.
3. Comment: Stores comments on blog posts, along with the user who made the comment.
4. AuthenticationToken: Manages user authentication tokens.

## Contributing

We welcome contributions from the community. 


## Contact

For any questions or feedback related to this project, please reach out to [Omkar66527@gmail.com].

