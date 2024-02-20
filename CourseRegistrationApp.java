import java.util.ArrayList;
import java.util.List;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Course: " + title + " (" + code + ")\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\n";
    }
}

class Student {
    int studentId;
    String name;
    List<String> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name + "\nRegistered Courses: " + registeredCourses + "\n";
    }
}

class CourseRegistrationSystem {
    List<Course> courses;
    List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void registerStudentForCourse(int studentId, String courseCode) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (course.capacity > 0) {
                student.registeredCourses.add(course.title);
                course.capacity--;
                System.out
                        .println("Registration successful for student " + student.name + " in course " + course.title);
            } else {
                System.out.println("Course is full. Cannot register.");
            }
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public void dropStudentFromCourse(int studentId, String courseCode) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.registeredCourses.contains(course.title)) {
                student.registeredCourses.remove(course.title);
                course.capacity++;
                System.out.println("Student " + student.name + " dropped from course " + course.title);
            } else {
                System.out.println("Student is not registered in the specified course.");
            }
        } else {
            System.out.println("Student or course not found.");
        }
    }

    private Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.studentId == studentId) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.code.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

public class CourseRegistrationApp {
    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        Course course1 = new Course("CSC101", "Introduction to Programming", "Basic programming concepts", 30,
                "Mon/Wed 9:00 AM - 10:30 AM");
        Course course2 = new Course("MAT201", "Calculus I", "Limits, derivatives, integrals", 25,
                "Tue/Thu 11:00 AM - 12:30 PM");

        registrationSystem.addCourse(course1);
        registrationSystem.addCourse(course2);

        registrationSystem.displayCourses();

        // Simulate student registration
        Student student1 = new Student(1, "John Doe");
        registrationSystem.students.add(student1);

        registrationSystem.registerStudentForCourse(1, "CSC101");
        registrationSystem.registerStudentForCourse(1, "MAT201");

        System.out.println(student1);

        // Simulate dropping a course
        registrationSystem.dropStudentFromCourse(1, "CSC101");

        System.out.println(student1);
    }
}
