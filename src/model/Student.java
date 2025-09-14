package model;

import java.time.LocalDate;

public class Student {
    private String studentId;
    private String fullName;
    private LocalDate dob;
    private String major;
    private double gpa;
    private String className;

    public Student() {}

    public Student(String studentId, String fullName, LocalDate dob, String major, double gpa, String className) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dob = dob;
        this.major = major;
        this.gpa = gpa;
        this.className = className;
    }

    public String getStudentId() { return studentId; }
    public String getFullName() { return fullName; }
    public LocalDate getDob() { return dob; }
    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
    public String getClassName() { return className; }

    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setMajor(String major) { this.major = major; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public void setClassName(String className) { this.className = className; }

    @Override
    public String toString() {
        return studentId + " | " + fullName + " | " + dob + " | " + major + " | " + gpa + " | " + className;
    }
}