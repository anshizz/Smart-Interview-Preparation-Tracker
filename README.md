# Smart Interview Preparation Tracker

A production-quality, portfolio-worthy full-stack web application built using Java EE technologies to help students track their DSA, aptitude, CS fundamentals, projects, and interview preparation progress.

## Features
- **Secure Authentication**: BCrypt hashing, session management, and HTTP-only cookies.
- **Dashboard & Analytics**: Chart.js integration, GitHub-style activity heatmap, Readiness Score calculation.
- **Question Tracker**: Full AJAX CRUD operations for tracking coding problems, platforms, difficulty, and notes.
- **Smart Goals & Revisions**: Set and monitor preparation deadlines dynamically.
- **Achievement System**: Unlock badges for consistency and mastery.
- **PDF Export**: Generate a professional, multi-page performance report using iText 7.

## Technology Stack
- **Frontend**: JSP, Bootstrap 5, JavaScript ES6, Fetch API, Chart.js
- **Backend**: Java 17 Servlets, MVC Architecture, DAO Pattern
- **Database**: MySQL 8+
- **Libraries**: BCrypt, Gson, iText 7, HikariCP, SLF4J
- **Server**: Apache Tomcat 10+
- **Build**: Maven

## Architecture
The application adheres to the robust **Model-View-Controller (MVC)** architectural pattern coupled with the **Data Access Object (DAO)** pattern:
- `Model`: POJO classes mapping to the database schema.
- `DAO`: Handles all JDBC interactions securely (using PreparedStatement).
- `Service`: Contains business logic (e.g., calculating Readiness Score, Badges).
- `Controller`: Jakarta Servlets routing HTTP requests.
- `View`: JSP pages styled with CSS variables and Bootstrap.

## Database Schema
The database `interview_tracker` contains:
- `users`: Authentication and profile details
- `questions`: User's problem-solving history
- `goals`: Targets and deadlines
- `revisions`: Topic-based revision scheduling
- `activity_logs`: Timestamped event history
- `user_badges`: Awarded achievements

*(ER Diagram Placeholder)*

## Installation Guide
1. **Clone repository**: `git clone <repository_url>`
2. **Create database**: Ensure MySQL is running on `localhost:3306`.
3. **Execute SQL**: 
   - Run `database/schema.sql`
   - Run `database/massive-sample-data.sql`
4. **Configure DB**: Verify credentials in `src/main/resources/db.properties`.
5. **Build**: Run `mvn clean package`
6. **Deploy**: Copy the generated WAR file from `target/interview-tracker.war` to Tomcat 10's `webapps` directory.

## Screenshots
*(Add images here inside `docs/` folder)*
- Login Page
- Dashboard
- Analytics
- Question Tracker
- Goal Tracker
- PDF Export

## Resume Bullet Points
- **Developed** a full-stack SaaS-style interview preparation dashboard utilizing Java EE (Servlets/JSP), Maven, and MySQL, effectively adhering to MVC and DAO design patterns.
- **Architected** real-time asynchronous CRUD operations and dynamic table filtering using Fetch API and Gson, minimizing page reloads and enhancing UX.
- **Engineered** robust data visualization features including a GitHub-style activity heatmap and Chart.js analytics, providing users with actionable metrics.
- **Implemented** secure authentication flows employing BCrypt password hashing, session timeout management, and strict access control via Servlet Filters.
- **Integrated** iText 7 to dynamically generate and stream professional multi-page PDF performance reports directly to the client.
