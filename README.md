# 🏡 Real Estate Management System (Admin Portal)

A monolithic web application designed for managing real estate information. The backend is built with **Java 8** and **Spring Boot**, while the view layer uses **JSP** integrated with **SiteMesh** for layout decoration and **Bootstrap** for a responsive administrator interface.

## ✨ Key Features (Admin)

- **Security & Access Control:** Secure authentication system with role-based authorization (e.g., Admin vs. Staff).
- **Property & Customer Management:** Full CRUD (Create, Read, Update, Delete) capabilities for handling building details and customer profiles.
- **Staff Assignment:** Efficiently allocate specific buildings to designated staff members for targeted management.
- **Transaction Tracking:** Monitor and manage interactions, workflows, and business transactions between staff and customers.

## 🛠 Tech Stack

**Backend:**
- **Java 1.8 (Java 8)**
- **Spring Boot 2.0.9.RELEASE**
- **Spring Data JPA** & **Hibernate** (ORM)
- **Spring Security** (Authentication & Authorization)
- **ModelMapper** (Entity-DTO mapping)

**View Layer & Frontend:**
- **JSP (JavaServer Pages)** & **JSTL**
- **SiteMesh 2.4.2** (Template Decorator for Layouts)
- **DisplayTag 1.2** (Data table rendering and pagination)
- HTML5, CSS3, JavaScript, **Bootstrap**

**Database & Tools:**
- **MySQL** (MySQL Connector 8.0.13)
- **Maven** (Dependency management & `.war` packaging)
- **Log4j** (Logging framework)

## 🚀 Local Setup Instructions

### Prerequisites
- **JDK 1.8** installed on your machine.
- **Maven** installed and configured.
- **MySQL Server** installed and running.

### Installation Steps

**1. Clone the repository**
```bash
git clone https://github.com/huytranminhcs0707-lab/Project.git
cd Project
```

**2. Create the Database**
You need to create a local MySQL database named `estateadvance` before running the application. You can do this using either the command line or a GUI tool:

*Option A: Using MySQL Command Line*
1. Open your terminal or command prompt.
2. Log in to your MySQL server:
   ```bash
   mysql -u root -p
   ```
3. Enter your MySQL password.
4. Run the following SQL command:
   ```sql
   CREATE DATABASE estateadvance;
   ```

*Option B: Using MySQL Workbench*
1. Open MySQL Workbench and connect to your local server.
2. Click the **Create a new schema** icon in the toolbar.
3. Enter `estateadvance` as the schema Name.
4. Click **Apply** -> **Apply** -> **Finish**.

**3. Application Configuration**
Review the configuration in `src/main/resources/application.properties`. Make sure the credentials match your local MySQL setup:
```properties
spring.datasource.url = jdbc:mysql://localhost:3306/estateadvance
spring.datasource.username = root
spring.datasource.password = 123456

spring.jpa.hibernate.ddl-auto = none

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
spring.jpa.properties.hibernate.enable_lazy_load_no_trans = true

spring.jpa.show-sql = true
spring.jpa.properties.hibernate.format_sql = true

spring.mvc.view.prefix = /WEB-INF/views/
spring.mvc.view.suffix = .jsp
```
*(Note: If you are running the project for the first time and the tables are not yet created, you can temporarily change `spring.jpa.hibernate.ddl-auto` to `update`).*

**4. Build and Run**
You can run the application by executing the `SpringBootWebApplication.java` file in your IDE (IntelliJ IDEA / Eclipse).

Alternatively, use the terminal:
```bash
mvn clean install
mvn spring-boot:run
```

**5. Access the Application**
Once the server starts (default port: 8080), open your browser and go to:
👉 **http://localhost:8080/admin/building-list** *(Or your designated admin entry point)*

## 👤 Author
- Github: [@huytranminhcs0707-lab](https://github.com/huytranminhcs0707-lab)
