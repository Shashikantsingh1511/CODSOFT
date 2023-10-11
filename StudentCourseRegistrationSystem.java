package personal;

import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int availableSlots;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isFull() {
        return availableSlots == 0;
    }

    public void registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
        }
    }

    public void dropStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        }
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent();
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.dropStudent();
    }
}

class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    public void registerStudentForCourse(int studentID, String courseCode, CourseDatabase courseDatabase) {
        Student student = getStudentByID(studentID);
        if (student != null) {
            Course course = courseDatabase.getCourseByCode(courseCode);
            if (course != null && !course.isFull()) {
                student.registerForCourse(course);
            }
        }
    }

    public void dropStudentFromCourse(int studentID, String courseCode, CourseDatabase courseDatabase) {
        Student student = getStudentByID(studentID);
        if (student != null) {
            Course course = courseDatabase.getCourseByCode(courseCode);
            if (course != null) {
                student.dropCourse(course);
            }
        }
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        CourseDatabase courseDatabase = new CourseDatabase();
        StudentDatabase studentDatabase = new StudentDatabase();

        Course course1 = new Course("CSCI101", "Introduction to Computer Science", "Fundamentals of programming", 30, "MWF 9:00 AM - 10:30 AM");
        Course course2 = new Course("MATH202", "Calculus II", "Advanced calculus topics", 25, "TTH 2:00 PM - 3:30 PM");

        courseDatabase.addCourse(course1);
        courseDatabase.addCourse(course2);

        Student student1 = new Student(1, "John Doe");
        Student student2 = new Student(2, "Jane Smith");

        studentDatabase.addStudent(student1);
        studentDatabase.addStudent(student2);

        
        studentDatabase.registerStudentForCourse(1, "CSCI101", courseDatabase);
        studentDatabase.registerStudentForCourse(2, "MATH202", courseDatabase);
        studentDatabase.dropStudentFromCourse(1, "CSCI101", courseDatabase);

        
        System.out.println("Available Courses:");
        List<Course> allCourses = courseDatabase.getAllCourses();
        for (Course course : allCourses) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle() + " (Available Slots: " + course.getAvailableSlots() + ")");
        }

        System.out.println("\nStudent Information:");
        Student student = studentDatabase.getStudentByID(2);
        if (student != null) {
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Name: " + student.getName());
            System.out.println("Registered Courses: " + student.getRegisteredCourses().size());
        }
    }
}
