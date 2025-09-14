package util;

import java.time.LocalDate;
import java.time.Period;

public class Utils {
    public static boolean isValidStudentId(String id, String major) {
        if (id == null || id.length() != 10) return false;
        if (major.equals("CNTT") && id.startsWith("455105")) return true;
        if (major.equals("KTPM") && id.startsWith("455109")) return true;
        return false;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String normalizeName(String name) {
        name = name.trim().toLowerCase();
        String[] words = name.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public static boolean isValidDOB(LocalDate dob) {
        if (dob == null) return false;
        int age = Period.between(dob, LocalDate.now()).getYears();
        return age >= 15 && age <= 110;
    }

    public static boolean isValidGPA(double gpa) {
        return gpa >= 0.0 && gpa <= 10.0;
    }

    public static boolean isValidMajor(String major) {
        return "CNTT".equals(major) || "KTPM".equals(major);
    }
}
