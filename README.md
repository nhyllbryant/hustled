````markdown
# Hustled Project

Hustled is a web-based application designed for job seekers and employers, providing functionalities such as job listings, job applications, candidate profiles, employer dashboards, and much more. This project uses Java, Spring Boot, Maven, and various front-end technologies.

## Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [Directory Structure](#directory-structure)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

Hustled is an integrated platform that connects job seekers with employers. The project consists of a backend built with Java and Spring Boot, a front-end with various HTML templates, and a robust configuration for email services, job postings, and candidate tracking.

## Tech Stack

- **Backend**: Java, Spring Boot, Maven
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL/PostgreSQL (via JPA/Hibernate)
- **Email Service**: PHPMailer
- **Version Control**: Git

## Project Structure

The project is organized into various directories that separate concerns like backend services, frontend files, configurations, and more.

### Backend (Java)

- **src/main/java/com/example/hustled**: Contains the core application code.
  - **config**: Configuration classes (e.g., `SecurityConfig.java`).
  - **controller**: Controllers handling web requests.
  - **dto**: Data Transfer Objects for data exchange.
  - **entity**: JPA entities representing the database models.
  - **repository**: Repositories for database interaction.
  - **service**: Business logic and service layer.
  - **util**: Utility classes and helper functions.
  - **HustledApplication.java**: Main application entry point.

- **src/main/resources**: Contains static files and configurations.
  - **jobzilla**: Frontend assets like CSS, fonts, images, and JS.
  - **application.properties**: Main application configuration file.

### Frontend (HTML/CSS/JS)

- **jobzilla**: Contains frontend assets for the UI.
  - **css**: Stylesheets for the project.
  - **images**: Image assets (logos, icons, etc.).
  - **js**: JavaScript files for dynamic behavior.
  - **vendor**: External libraries and plugins.

- **templates**: Various HTML templates for different pages.
  - **candidate**: HTML pages related to candidate profiles and dashboards.
  - **employer**: HTML pages for employer dashboards and job management.
  - **error-404.html**: Custom 404 error page.
  - **login.html**: Login page for authentication.
  - **register.html**: Registration page for new users.
  - **job-detail.html**: Job details page for job seekers.
  - **dashboard.html**: Dashboard page for users.

### Configuration Files

- **.mvn/wrapper**: Maven wrapper files for consistent builds.
- **pom.xml**: Maven project descriptor for dependencies and plugins.
- **application.properties**: Configurations related to the application, such as DB credentials and environment settings.

### Test Directory

- **test/java/com/example/hustled**: Unit and integration tests for the application.

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/hustled.git
````

2. **Install dependencies**:
   The project uses Maven to manage dependencies. Make sure you have Maven installed. Run the following command to install all required dependencies:

   ```bash
   mvn install
   ```

3. **Configure the database**:

   * Update the `application.properties` file with your database credentials.
   * Make sure your database (MySQL/PostgreSQL) is set up and accessible.

4. **Setup PHPMailer**:
   If you are using email functionality, configure the PHPMailer by updating the necessary configurations in `mail.php` and `contactform.php`.

## Running the Application

1. **Run the backend application**:
   From the root of the project, run the following command to start the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

2. **Access the frontend**:
   After starting the backend, you can access the frontend by navigating to `http://localhost:8080` in your browser.

## Directory Structure

Here's an overview of the key directories in this project:

```
.
├── .mvn/                 # Maven wrapper files
├── src/                  # Source code
│   ├── main/
│   │   ├── java/         # Java source code
│   │   ├── resources/    # Resources like static files and configurations
│   └── test/             # Unit and integration tests
├── jobzilla/             # Frontend assets (CSS, JS, images)
├── sql/                  # Database-related SQL files (e.g., schema, migrations)
├── templates/            # HTML templates
├── .gitignore            # Git ignore file
├── mvnw                  # Maven wrapper for Unix systems
├── mvnw.cmd              # Maven wrapper for Windows
├── pom.xml               # Maven project descriptor
└── application.properties # Application configurations
```

## Configuration

### application.properties

This file contains various configuration properties, including database connection settings and other application-specific properties. Make sure to update the file according to your environment.

```properties
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hustled
spring.datasource.username=root
spring.datasource.password=password

# Server settings
server.port=8080

# Email configuration (PHPMailer)
mail.smtp.host=smtp.example.com
mail.smtp.port=587
mail.smtp.username=user
mail.smtp.password=password
```

## Contributing

We welcome contributions! If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
...
