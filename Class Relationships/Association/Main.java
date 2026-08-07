package Association;

import java.util.ArrayList;
import java.util.List;

class Instructor {
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Instructor(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        // TODO: Add course to list and set this as the course's instructor
        course.setInstructor(this);
        courses.add(course);
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
}

class Course {
    private String title;
    private Instructor instructor;
    private List<Student> students = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }

    public void setInstructor(Instructor instructor) {
        // TODO: Set the instructor reference
        this.instructor = instructor;
    }

    public void enrollStudent(Student student) {
        // TODO: Add student to list and set this as the student's enrolled course
        student.setEnrolledCourse(this);
        students.add(student);

    }

    public String getTitle() {
        return title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Student> getStudents() {
        return students;
    }
}

class Student {
    private String name;
    private Course enrolledCourse;

    public Student(String name) {
        this.name = name;
    }

    public void setEnrolledCourse(Course course) {
        // TODO: Set the enrolled course reference
        this.enrolledCourse = course;
    }

    public String getInstructorName() {
        // TODO: Navigate through enrolledCourse to get the instructor's name
        // Return "No instructor" if course or instructor is null
        return enrolledCourse.getInstructor().getName();
    }

    public String getName() {
        return name;
    }

    public Course getEnrolledCourse() {
        return enrolledCourse;
    }
}

public class Main {
    public static void main(String[] args) {
        Instructor alice = new Instructor("Alice");
        Course dsa = new Course("Data Structures");
        Course sysDesign = new Course("System Design");

        alice.addCourse(dsa);
        alice.addCourse(sysDesign);

        Student bob = new Student("Bob");
        Student charlie = new Student("Charlie");

        dsa.enrollStudent(bob);
        dsa.enrollStudent(charlie);
        sysDesign.enrollStudent(charlie);

        System.out.println(alice.getName() + "'s courses:");
        for (Course c : alice.getCourses())
            System.out.println("  - " + c.getTitle());

        System.out.println("Students in " + dsa.getTitle() + ":");
        for (Student s : dsa.getStudents())
            System.out.println("  - " + s.getName());

        System.out.println(bob.getName() + "'s instructor: " + bob.getInstructorName());
    }
}