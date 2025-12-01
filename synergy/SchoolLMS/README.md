# School Learning Management System (LMS)

## 🎓 Overview
A comprehensive School Learning Management System built with **Java**, **Java Servlets**, **HTML/CSS/JavaScript**, **SQL**, and **JDBC**. This system provides complete management of students, teachers, courses, assignments, grades, and attendance.

## ✨ Features

### User Management
- **Three user roles**: Admin, Teacher, Student
- Secure authentication and authorization
- Role-based dashboards
- Profile management

### Course Management
- Create and manage courses
- Assign teachers to courses
- Student enrollment system
- Course catalog with details

### Assignment Management
- Teachers can create assignments
- Students can submit assignments
- Assignment grading system
- Due date tracking

### Grades Management
- Grade tracking for all courses
- GPA calculation
- Grade reports
- Performance analytics

### Attendance Management
- Daily attendance marking
- Attendance reports
- Percentage calculation
- Date range filtering

### Announcements
- Course-specific and global announcements
- Chronological display
- Admin and teacher posting rights

## 🛠️ Technology Stack

- **Backend**: Java (JDK 8+)
- **Web Layer**: Java Servlets
- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **Database**: MySQL
- **Database Connectivity**: JDBC
- **Server**: Apache Tomcat 9.0+

## 📁 Project Structure

```
SchoolLMS/
├── src/main/
│   ├── java/com/schoollms/
│   │   ├── dao/              # Data Access Objects
│   │   ├── model/            # Entity classes
│   │   ├── servlet/          # Servlets (Controllers)
│   │   ├── util/             # Utility classes
│   │   └── filter/           # Filters
│   ├── webapp/
│   │   ├── WEB-INF/
│   │   │   └── web.xml
│   │   ├── css/
│   │   │   └── style.css
│   │   ├── js/
│   │   │   └── script.js
│   │   ├── admin/            # Admin pages
│   │   ├── teacher/          # Teacher pages
│   │   ├── student/          # Student pages
│   │   ├── index.html
│   │   ├── login.html
│   │   └── register.html
│   └── resources/
│       └── db.properties
└── database_schema.sql
```

## 🚀 Setup Instructions

### Prerequisites
1. **JDK 8 or higher**
   ```bash
   java -version
   ```

2. **Apache Tomcat 9.0+**
   - Download from: https://tomcat.apache.org/

3. **MySQL Database**
   ```bash
   mysql --version
   ```

4. **MySQL JDBC Driver**
   - Download from: https://dev.mysql.com/downloads/connector/j/

### Database Setup

1. **Create Database**
   ```bash
   mysql -u root -p
   ```

2. **Run the schema script**
   ```sql
   source /path/to/SchoolLMS/database_schema.sql
   ```

3. **Verify tables created**
   ```sql
   USE school_lms;
   SHOW TABLES;
   ```

### Application Configuration

1. **Update database credentials**
   Edit `src/main/resources/db.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/school_lms
   db.username=root
   db.password=your_password
   ```

2. **Add MySQL JDBC Driver**
   - Copy `mysql-connector-java-8.x.x.jar` to `src/main/webapp/WEB-INF/lib/` directory
   - Or add to your classpath

### Deployment

#### Option 1: Manual Deployment

1. **Compile Java files**
   ```bash
   # From project root
   javac -d compiled -cp "path/to/servlet-api.jar;path/to/mysql-connector.jar" src/main/java/com/schoollms/**/*.java
   ```

2. **Package as WAR**
   ```bash
   # Copy compiled classes to WEB-INF/classes
   # Copy all webapp files
   # Create SchoolLMS.war
   ```

3. **Deploy to Tomcat**
   ```bash
   # Copy WAR to Tomcat webapps directory
   cp SchoolLMS.war $TOMCAT_HOME/webapps/
   ```

#### Option 2: IDE Deployment (Eclipse/IntelliJ)

1. Import project as Dynamic Web Project
2. Configure Tomcat server in IDE
3. Right-click project → Run on Server

### Running the Application

1. **Start Tomcat**
   ```bash
   # Windows
   %TOMCAT_HOME%\bin\startup.bat
   
   # Linux/Mac
   $TOMCAT_HOME/bin/startup.sh
   ```

2. **Access Application**
   ```
   http://localhost:8080/SchoolLMS
   ```

## 👤 Default Login Credentials

### Admin
- Username: `admin`
- Password: `admin123`

### Teacher
- Username: `teacher1`
- Password: `teacher123`

### Student
- Username: `student1`
- Password: `student123`

## 📱 Application Screenshots

### Landing Page
Modern landing page with feature showcase and navigation

### Login Page
Secure login with role-based redirection

### Student Dashboard
- Enrolled courses
- Pending assignments
- Grades overview
- Attendance statistics

### Teacher Dashboard
- Assigned courses
- Student management
- Assignment creation
- Grade management

### Admin Dashboard
- System statistics
- User management
- Course management
- System configuration

## 🔒 Security Features

1. **Authentication Filter**
   - Protects all pages except public resources
   - Session-based authentication
   - Role-based access control

2. **Input Validation**
   - Server-side validation
   - Client-side validation
   - SQL injection prevention (PreparedStatements)
   - XSS prevention

3. **Password Security**
   - Password hashing (SHA-256)
   - Minimum length requirements

## 🎨 Design Features

- **Modern Dark Theme** with vibrant accents
- **Gradient Backgrounds** for visual appeal
- **Smooth Animations** and transitions
- **Responsive Design** for all devices
- **Card-based Layout** for content organization
- **Stats Cards** with gradient backgrounds
- **Interactive Elements** with hover effects

## 📊 Database Schema

### Tables
1. **users** - System users (admin, teachers, students)
2. **courses** - Course information
3. **enrollments** - Student-course relationships
4. **assignments** - Course assignments
5. **submissions** - Student submissions
6. **attendance** - Daily attendance records
7. **grades** - Student grades
8. **announcements** - System and course announcements

## 🔧 API Endpoints

### Authentication
- `POST /login` - User login
- `GET /logout` - User logout
- `POST /register` - New user registration

### Dashboard
- `GET /api/dashboard` - Get role-specific dashboard data

## 🐛 Troubleshooting

### Database Connection Issues
```
Error: Unable to connect to database
Solution: Check db.properties file and ensure MySQL is running
```

### Servlet Not Found
```
Error: HTTP 404
Solution: Verify WAR deployment and context path
```

### Class Not Found
```
Error: ClassNotFoundException
Solution: Ensure JDBC driver is in WEB-INF/lib/
```

## 📚 Future Enhancements

- [ ] File upload for assignments
- [ ] Email notifications
- [ ] Chat system
- [ ] Video lecture integration
- [ ] Mobile application
- [ ] REST API
- [ ] Report generation (PDF)
- [ ] Quiz module
- [ ] Discussion forums
- [ ] Calendar integration

## 📄 License

This project is created for educational purposes.

## 👨‍💻 Developer Notes

### Code Organization
- **DAO Pattern**: All database operations in DAO classes
- **MVC Architecture**: Separation of concerns
- **PreparedStatements**: For SQL injection prevention
- **Try-with-resources**: For automatic resource management

### Best Practices Implemented
- Input validation
- Error handling
- Session management
- Responsive design
- Code documentation
- Consistent naming conventions

## 🤝 Support

For issues or questions:
1. Check the troubleshooting section
2. Review the database schema
3. Verify configuration files
4. Check Tomcat logs

## 🎯 Getting Started Checklist

- [ ] Install JDK, Tomcat, and MySQL
- [ ] Create database using schema file
- [ ] Update db.properties with credentials
- [ ] Add MySQL JDBC driver to project
- [ ] Deploy application to Tomcat
- [ ] Access application in browser
- [ ] Login with default credentials
- [ ] Explore features

---

**Built with ❤️ using Java, Servlets, HTML, CSS, JavaScript, SQL, and JDBC**
