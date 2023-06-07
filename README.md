# This is the blog site

Welcome to our blog site! This project is built using MySQL, JPA, Server-Side Rendering, Thymeleaf, Java, Spring Framework, and Spring Security.

## Project Overview

This blog site provides the following functionality:

- User authentication and authorization with three roles: User, Admin, and Guest
- Users can view posts and leave comments
- Admins have control over posts, comments, and users
- Guest users can only view posts

## Technologies Used

This project utilizes the following technologies:

- MySQL: A relational database management system
- JPA (Java Persistence API): A Java specification for accessing, managing, and persisting data between Java objects and the database
- Server-Side Rendering: Rendering web pages on the server side before sending them to the client
- Thymeleaf: A Java-based templating engine for server-side rendering in web applications
- Java: The programming language used for the server-side implementation
- Spring Framework: A popular Java framework for building enterprise-level applications
- Spring Security: A powerful authentication and authorization framework for securing web applications

## API Mapping

Here is a brief overview of the API mapping in this project:

- `GET /getInfo`: Retrieves information by calling the `debugTable()` method in the `mainModel` class and redirects to the `/home` page.
- `GET /data`: Retrieves data from the `usr` table and returns it as a list of maps.
- `POST /insertPost`: Inserts a new post into the database. If an image is provided, it is saved as a byte array.
- `POST /insertUsr`: Inserts a new user into the database after encoding the password using the BCrypt algorithm.
- `POST /insertComment`: Inserts a new comment for a specific post into the database.
- `GET /image/{image_id}`: Retrieves an image from the database based on the specified `image_id`.
- `POST /deleteComment`: Deletes a comment from the database based on the specified `comment_id`.
- `POST /deleteUsr`: Deletes a user from the database based on the specified `usr_id`.
- `POST /deletePost`: Deletes a post from the database based on the specified `post_id`.

These are just a few examples of the mapping methods used in the project. For more details and other methods, please refer to the source code.

## Installation and Usage

To use this project locally, please follow these steps:

1. Clone the repository.
2. Configure the MySQL database settings in the application properties file.
3. Build and run the project using your preferred Java development environment.
4. Access the blog site through the provided URL.

Feel free to explore the different features and functionalities of the blog site. If you have any questions or feedback, please don't hesitate to contact us.

Thank you for your interest in our project!
