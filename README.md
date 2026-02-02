# 🎬 BookMyMovies – Movie Ticket Booking System

A **console-based Movie Ticket Booking System** built using **Core Java, JDBC, and MySQL**.
This project simulates real-world backend logic for booking movie tickets, managing seat availability, and ensuring safe transactions — similar to platforms like BookMyShow.

---

## 🚀 Features

* 📍 City-wise theater selection
* 🎥 Movie and show listing by theater
* 🪑 Real-time seat availability tracking
* 🔐 Secure ticket booking with transaction management
* ❌ Prevention of double seat booking
* 🗂 CRUD operations for:

  * Movies
  * Theaters
  * Shows
  * Seats
  * Bookings
* ⚙️ Dynamic price calculation based on seat count
* 🧾 Clean console-based user interaction
* 🛡 Uses `PreparedStatement` to prevent SQL Injection

---

## 🛠 Tech Stack

### Backend

* **Java (Core Java)**
* **JDBC**

### Database

* **MySQL**

### Tools & IDE

* **IntelliJ IDEA**
* **MySQL Workbench**
* **Git & GitHub**

---

## 🗄 Database Design

The system follows a **relational database design** with proper foreign key relationships:

* `cities`
* `theaters`
* `movies`
* `shows`
* `seats`
* `bookings`

Each show is linked to a theater and movie, and seat bookings are handled using **transactions** to maintain data consistency.

---

## 🔄 How It Works

1. User selects a city
2. Available theaters are displayed
3. User selects a theater and movie
4. Available shows and seats are shown
5. User books tickets
6. System updates seat availability atomically

---

## 🧠 Key Learnings

* JDBC database connectivity
* SQL schema design with foreign keys
* Transaction management (`commit` / `rollback`)
* Exception handling in Java
* Backend logic for real-world systems
* Writing clean, modular Java code

---

## 📌 Future Enhancements

* User authentication & login
* Seat layout visualization
* Payment gateway simulation
* Conversion to Spring Boot REST APIs
* Frontend integration (React)

---

## 👩‍💻 Author

**Avani Joshi**
B.Tech Computer Science (2022–2026)
Java Full-Stack Developer

🔗 LinkedIn: [https://www.linkedin.com/in/avani-joshi-17a15b24b/](https://www.linkedin.com/in/avani-joshi-17a15b24b/)

---

⭐ *This project was built to strengthen backend development skills, database design, and real-world application logic.*
