

import dao.StudentDAO;
import model.Student;
import util.Utils;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Xem tất cả sinh viên");
            System.out.println("5. Xem theo lớp");
            System.out.println("6. Xem theo ngành");
            System.out.println("7. Sắp xếp theo GPA");
            System.out.println("8. Tìm theo tháng sinh");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: updateStudent(); break;
                case 3: deleteStudent(); break;
                case 4:
                    System.out.println("===== DANH SÁCH SINH VIÊN =====");
                    List<Student> list = dao.getAll();
                    if (list.isEmpty()) {
                        System.out.println("Không có sinh viên nào trong cơ sở dữ liệu.");
                    } else {
                        for (Student s : list) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 5:
                    System.out.print("Nhập tên lớp: "); String c = sc.nextLine();
                    printList(dao.getByClass(c)); break;
                case 6:
                    System.out.print("Nhập ngành (CNTT/KTPM): "); String m = sc.nextLine();
                    printList(dao.getByMajor(m)); break;
                case 7:
                    List<Student> sortedList = dao.getAll();  
                    sortedList.sort(Comparator.comparingDouble(Student::getGpa).reversed());
                    printList(sortedList);
                    break;

                case 8:
                    System.out.print("Nhập tháng: "); int month = sc.nextInt();
                    printList(dao.getByBirthMonth(month)); break;
                case 0: System.exit(0);
            }
        }
    }

    private static void addStudent() {
        System.out.print("Mã SV: "); String id = sc.nextLine();
        System.out.print("Họ tên: "); String name = sc.nextLine();
        System.out.print("Ngày sinh (yyyy-mm-dd): "); String dobStr = sc.nextLine();
        System.out.print("Ngành (CNTT/KTPM): ");
        String major = sc.nextLine().trim();  // thêm trim()
        System.out.print("Điểm TB: "); double gpa = sc.nextDouble(); sc.nextLine();
        System.out.print("Lớp: "); String cls = sc.nextLine();

        LocalDate dob = LocalDate.parse(dobStr);
        name = Utils.normalizeName(name);

        if (!Utils.isValidStudentId(id, major) ||
            !Utils.isValidName(name) ||
            !Utils.isValidDOB(dob) ||
            !Utils.isValidMajor(major) ||
            !Utils.isValidGPA(gpa)) {
            System.out.println("Dữ liệu không hợp lệ!");
            return;
        }

        Student s = new Student(id, name, dob, major, gpa, cls);
        if (dao.addStudent(s)) System.out.println("Thêm thành công!");
    }

    private static void updateStudent() {
        System.out.print("Nhập mã SV cần sửa: "); String id = sc.nextLine();
        System.out.print("Tên mới: "); String name = Utils.normalizeName(sc.nextLine());
        System.out.print("Ngày sinh mới (yyyy-mm-dd): "); LocalDate dob = LocalDate.parse(sc.nextLine());
        System.out.print("Ngành mới: "); String major = sc.nextLine();
        System.out.print("GPA mới: "); double gpa = sc.nextDouble(); sc.nextLine();
        System.out.print("Lớp mới: "); String cls = sc.nextLine();

        Student s = new Student(id, name, dob, major, gpa, cls);
        if (dao.updateStudent(s)) System.out.println("Sửa thành công!");
    }

    private static void deleteStudent() {
        System.out.print("Nhập mã SV cần xóa: "); String id = sc.nextLine();
        if (dao.deleteStudent(id)) System.out.println("Xóa thành công!");
    }

    private static void printList(List<Student> list) {
        for (Student s : list) System.out.println(s);
    }
}
