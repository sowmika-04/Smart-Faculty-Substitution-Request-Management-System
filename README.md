# Smart Faculty Substitution and Request Management System

## Overview

The **Smart Faculty Substitution and Request Management System** is a web-based platform designed to streamline the process of managing faculty leave and class substitutions. It eliminates manual coordination by allowing faculty members to request substitutes, accept or reject substitution requests, and automatically update timetables. The admin has full visibility and control over all substitution requests, leave records, and status updates.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation Guide](#installation-guide)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [User Workflow](#user-workflow)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [Contributors](#contributors)
- [License](#license)
- [Support](#support)
- [Acknowledgments](#acknowledgments)
- [Version History](#version-history)
- [Project Status](#project-status)

---

## Features

### For Faculty Members
- **Secure Login** using unique Faculty ID
- **View Personal Timetable** after login
- **Select a Period** they wish to substitute
- **View Available Faculty** who are free during the selected time slot
- **Send Substitution Request** with reason and class details to a chosen available faculty member
- **Accept or Reject** incoming substitution requests
- **Resend Request** to another faculty if the previous request is rejected

### For Admin
- **Upload Timetables** for all faculty members based on their Faculty ID
- **View All Substitution Requests** along with their current status (Pending, Accepted, Rejected)
- **Monitor Leave Requests and Substitutions** in a centralized dashboard
- **View Status Updates** in real-time

### Additional Functionality
- **Automatic Timetable Update** upon acceptance of a substitution request
- **Notification System** – The Class CR (Class Representative) is notified when a substitution is accepted
- **Status Tracking** – Real-time status updates for all substitution requests

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Frontend | HTML, CSS, JavaScript, Bootstrap 5 |
| Backend | Python Django / Flask (or Node.js/Express) |
| Database | MySQL / PostgreSQL |
| Authentication | Faculty ID-based login |
| Notifications | Email (SMTP) / In-app notifications |
| Version Control | Git & GitHub |

---

## Installation Guide

### Prerequisites

Before you begin, ensure you have the following installed:

- Python 3.8+ or Node.js (depending on backend choice)
- MySQL 8.0+ or PostgreSQL 12+
- Git
- pip (Python package manager) or npm (Node package manager)

### Step-by-Step Installation

#### 1. Clone the Repository

```bash
git clone https://github.com/your-username/smart-faculty-substitution.git
cd smart-faculty-substitution
```
#### 2. Backend Setup (Python Django Example)
```bash
# Create virtual environment
python -m venv venv

# Activate virtual environment
# On Windows:
venv\Scripts\activate
# On Mac/Linux:
source venv/bin/activate

# Install dependencies
pip install -r requirements.txt
```
#### 3. Database Setup
```bash
# Create database
mysql -u root -p
CREATE DATABASE faculty_subsystem;
EXIT;

# Run migrations
python manage.py migrate
```
#### 4. Environment Variables
Create a .env file in the root directory:

env
# Database Configuration
DB_HOST=localhost
DB_PORT=3306
DB_NAME=faculty_subsystem
DB_USER=root
DB_PASSWORD=yourpassword

# Application Configuration
SECRET_KEY=your_secret_key_here
DEBUG=True
ALLOWED_HOSTS=localhost,127.0.0.1

# Email Configuration (for notifications)
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USE_TLS=True
EMAIL_HOST_USER=your_email@gmail.com
EMAIL_HOST_PASSWORD=your_app_password

# Admin Configuration
ADMIN_USERNAME=admin
ADMIN_PASSWORD=admin123

#### 5. Load Sample Data (Optional)
```bash
python manage.py loaddata sample_data.json
```
#### 6. Run the Application
```bash
python manage.py runserver
The application will be available at: http://localhost:8000
```
## 7. Default Login Credentials

| Role | Faculty ID / Username | Password |
|------|----------------------|----------|
| Faculty | FAC001 | faculty123 |
| Faculty | FAC002 | faculty123 |
| Faculty | FAC003 | faculty123 |
| Admin | ADMIN001 | admin123 |

#### Project Structure
```bash
smart-faculty-substitution/
│
├── manage.py                 # Django management script
├── requirements.txt          # Python dependencies
├── .env                      # Environment variables
├── .gitignore               # Git ignore file
│
├── faculty_subsystem/        # Project configuration
│   ├── __init__.py
│   ├── settings.py
│   ├── urls.py
│   └── wsgi.py
│
├── apps/                     # Main application modules
│   ├── accounts/            # Authentication module
│   │   ├── models.py
│   │   ├── views.py
│   │   └── urls.py
│   │
│   ├── faculty/             # Faculty module
│   │   ├── models.py
│   │   ├── views.py
│   │   ├── urls.py
│   │   └── templates/
│   │
│   ├── admin_panel/         # Admin module
│   │   ├── models.py
│   │   ├── views.py
│   │   └── urls.py
│   │
│   ├── substitution/        # Substitution module
│   │   ├── models.py
│   │   ├── views.py
│   │   └── urls.py
│   │
│   └── notifications/       # Notification module
│       ├── models.py
│       ├── views.py
│       └── utils.py
│
├── static/                   # Static files (CSS, JS, images)
│   ├── css/
│   ├── js/
│   └── images/
│
├── media/                    # User uploaded files
│   └── timetables/
│
├── templates/                # Global templates
│   ├── base.html
│   ├── index.html
│   └── login.html
│
└── README.md                # Project documentation
```
#### Database Schema
Faculty Table
```sql
CREATE TABLE faculty (
    id INT PRIMARY KEY AUTO_INCREMENT,
    faculty_id VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    department VARCHAR(50),
    designation VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
Timetable Table
```sql
CREATE TABLE timetable (
    id INT PRIMARY KEY AUTO_INCREMENT,
    faculty_id VARCHAR(20),
    day_of_week VARCHAR(10),
    period_number INT,
    subject_code VARCHAR(20),
    class_name VARCHAR(20),
    room_number VARCHAR(10),
    FOREIGN KEY (faculty_id) REFERENCES faculty(faculty_id)
);
```
Substitution Request Table
```sql
CREATE TABLE substitution_request (
    id INT PRIMARY KEY AUTO_INCREMENT,
    requesting_faculty_id VARCHAR(20),
    substitute_faculty_id VARCHAR(20),
    original_date DATE,
    original_period INT,
    class_name VARCHAR(20),
    subject_code VARCHAR(20),
    reason TEXT,
    status VARCHAR(20) DEFAULT 'pending',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    response_date TIMESTAMP NULL,
    FOREIGN KEY (requesting_faculty_id) REFERENCES faculty(faculty_id),
    FOREIGN KEY (substitute_faculty_id) REFERENCES faculty(faculty_id)
);
```
Notification Table
```sql
CREATE TABLE notification (
    id INT PRIMARY KEY AUTO_INCREMENT,
    recipient_type VARCHAR(20), -- 'faculty', 'cr', 'admin'
    recipient_id VARCHAR(50),
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
# API Endpoints

## Faculty Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/api/faculty/login` | Faculty login | `{"faculty_id": "FAC001"}` | `{"success": true, "name": "Dr. Smith"}` |
| GET | `/api/faculty/timetable/{faculty_id}` | Get faculty timetable | None | `{"timetable": [...]}` |
| GET | `/api/faculty/available/{date}/{period}` | Get available faculty | None | `{"available_faculty": [...]}` |
| POST | `/api/substitution/request` | Send substitution request | `{"requesting_id": "FAC001", "substitute_id": "FAC002", "date": "2024-01-15", "period": 3, "reason": "Medical emergency"}` | `{"request_id": 101, "status": "pending"}` |

## Substitution Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/api/substitution/pending/{faculty_id}` | Get pending requests for a faculty | None | `{"requests": [...]}` |
| POST | `/api/substitution/respond/{request_id}` | Accept/reject request | `{"action": "accept"}` | `{"success": true, "message": "Request accepted"}` |
| GET | `/api/substitution/status/{request_id}` | Check request status | None | `{"status": "accepted", "updated_by": "FAC002"}` |

## Admin Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/api/admin/login` | Admin login | `{"username": "admin", "password": "admin123"}` | `{"token": "..."}` |
| POST | `/api/admin/upload-timetable` | Upload faculty timetable (CSV/Excel) | Form-data with file | `{"uploaded": 50, "failed": 2}` |
| GET | `/api/admin/all-requests` | View all substitution requests | None | `{"requests": [...]}` |
| GET | `/api/admin/statistics` | Get system statistics | None | `{"total_requests": 150, "pending": 5, "accepted": 140, "rejected": 5}` |

### User Workflow
# Faculty Workflow
```
1. Faculty Member Logs In
   ↓
2. Views Personal Timetable
   ↓
3. Selects Period to Substitute
   ↓
4. System Displays Available Faculty
   ↓
5. Faculty Selects Substitute & Sends Request
   ↓
6. Substitute Faculty Receives Notification
   ↓
7. Substitute Accepts OR Rejects Request
   ↓
   If Accepted:
   - Timetable Updated Automatically
   - Notification Sent to Class CR
   - Request Status Changed to "Accepted"
   ↓
   If Rejected:
   - Requesting Faculty Notified
   - Can Select Another Available Faculty
   - Process Repeats from Step 5
```
# Admin Workflow
```
1. Admin Logs In
   ↓
2. Uploads/Updates Faculty Timetables
   ↓
3. Views Dashboard with Statistics
   ↓
4. Monitors All Substitution Requests
   ↓
5. Tracks Status Updates in Real-time
   ↓
6. Generates Reports (Optional)
Screenshots
(Add screenshots of your application here)
```
Faculty Dashboard
https://screenshots/faculty-dashboard.png

Timetable View
https://screenshots/timetable-view.png

Substitution Request Form
https://screenshots/substitution-request.png

Admin Panel
https://screenshots/admin-panel.png

## Future Enhancements

- **Email/SMS Notifications** - Automatic alerts for faculty and CR via email/SMS
- **Workload Balancing** - AI-powered substitute suggestions based on faculty workload
- **Calendar View** - Interactive calendar showing all substitutions
- **Leave Application Module** - Integrated leave requests with automatic substitution
- **Role-Based Access** - Separate panels for HOD, Dean, and Principal
- **Mobile Application** - Android/iOS app for on-the-go access
- **Analytics Dashboard** - Advanced analytics with charts and graphs
- **Export Reports** - PDF/Excel export of substitution history
- **Bulk Operations** - Bulk upload and update of timetables
- **Real-time Chat** - In-app messaging between faculty members

## Troubleshooting

### Common Issues and Solutions

| Issue | Solution |
|-------|----------|
| Database connection error | Check .env file credentials and ensure database server is running |
| Login fails | Verify faculty ID exists in database |
| Timetable not loading | Check if timetable data is uploaded for the faculty ID |
| Email notifications not sending | Verify SMTP settings in .env file |
| CSS not loading | Run `python manage.py collectstatic` |

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Contribution Guidelines

- Write clear commit messages
- Follow PEP 8 style guide (for Python code)
- Add comments for complex logic
- Update documentation for new features
- Write unit tests for new functions

## Contributors

- Your Name - Initial work - YourGitHub
- Contributor Name - Documentation - GitHub Profile

## License

This project is licensed under the MIT License - see the LICENSE file for details.
