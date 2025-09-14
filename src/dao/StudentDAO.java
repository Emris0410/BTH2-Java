package dao;

import model.Student;


import java.sql.*;
import java.util.*;
import java.sql.Date;
import dao.DBConnection;

public class StudentDAO {

    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students(student_id, full_name, dob, major, gpa, class_name) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getStudentId());
            ps.setString(2, s.getFullName());
            ps.setDate(3, Date.valueOf(s.getDob()));
            ps.setString(4, s.getMajor());
            ps.setDouble(5, s.getGpa());
            ps.setString(6, s.getClassName());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Add failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET full_name=?, dob=?, major=?, gpa=?, class_name=? WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getFullName());
            ps.setDate(2, Date.valueOf(s.getDob()));
            ps.setString(3, s.getMajor());
            ps.setDouble(4, s.getGpa());
            ps.setString(5, s.getClassName());
            ps.setString(6, s.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(String id) {
        String sql = "DELETE FROM students WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("student_id"),
                        rs.getString("full_name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("major"),
                        rs.getDouble("gpa"),
                        rs.getString("class_name")
                );
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("Get all failed: " + e.getMessage());
        }
        return list;
    }

    public List<Student> getByClass(String className) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE class_name=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, className);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getString("student_id"),
                            rs.getString("full_name"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("major"),
                            rs.getDouble("gpa"),
                            rs.getString("class_name")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Get by class failed: " + e.getMessage());
        }
        return list;
    }

    public List<Student> getByMajor(String major) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE major=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, major);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getString("student_id"),
                            rs.getString("full_name"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("major"),
                            rs.getDouble("gpa"),
                            rs.getString("class_name")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Get by major failed: " + e.getMessage());
        }
        return list;
    }

    public List<Student> getByBirthMonth(int month) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE MONTH(dob)=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, month);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getString("student_id"),
                            rs.getString("full_name"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("major"),
                            rs.getDouble("gpa"),
                            rs.getString("class_name")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Get by month failed: " + e.getMessage());
        }
        return list;
    }
}
