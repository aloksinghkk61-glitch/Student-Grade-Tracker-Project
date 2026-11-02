import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Student Grade Manager (console-based).
 * Compile: javac StudentGradeManager.java
 * Run:     java StudentGradeManager
 */
public class StudentGradeManager {

    static class Student {
        private final String name;
        private final double score;

        Student(String name, double score) {
            this.name = name;
            this.score = score;
        }

        String getName() { return name; }
        double getScore() { return score; }

        String getGrade() {
            if (score >= 90) return "A";
            if (score >= 80) return "B";
            if (score >= 70) return "C";
            if (score >= 60) return "D";
            return "F";
        }
    }

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> showStatistics();
                case 3 -> showReport();
                case 4 -> removeStudent();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n===== Student Grade Manager =====");
        System.out.println("1. Add student");
        System.out.println("2. Show average / highest / lowest");
        System.out.println("3. Summary report");
        System.out.println("4. Remove student");
        System.out.println("0. Exit");
    }

    private static void addStudent() {
        System.out.print("Student name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        double score = readDouble("Score (0-100): ");
        if (score < 0 || score > 100) {
            System.out.println("Score must be between 0 and 100.");
            return;
        }
        students.add(new Student(name, score));
        System.out.println("Added " + name + ".");
    }

    private static void removeStudent() {
        if (students.isEmpty()) {
            System.out.println("No students to remove.");
            return;
        }
        System.out.print("Name to remove: ");
        String name = sc.nextLine().trim();
        boolean removed = students.removeIf(s -> s.getName().equalsIgnoreCase(name));
        System.out.println(removed ? "Removed." : "No student found with that name.");
    }

    private static double average() {
        double sum = 0;
        for (Student s : students) sum += s.getScore();
        return sum / students.size();
    }

    private static Student highest() {
        Student best = students.get(0);
        for (Student s : students) if (s.getScore() > best.getScore()) best = s;
        return best;
    }

    private static Student lowest() {
        Student worst = students.get(0);
        for (Student s : students) if (s.getScore() < worst.getScore()) worst = s;
        return worst;
    }

    private static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No data yet. Add a student first.");
            return;
        }
        Student hi = highest(), lo = lowest();
        System.out.printf("Average: %.2f%n", average());
        System.out.printf("Highest: %.2f (%s)%n", hi.getScore(), hi.getName());
        System.out.printf("Lowest : %.2f (%s)%n", lo.getScore(), lo.getName());
    }

    private static void showReport() {
        if (students.isEmpty()) {
            System.out.println("No data yet. Add a student first.");
            return;
        }
        System.out.println("\n---------- SUMMARY REPORT ----------");
        System.out.printf("%-4s %-20s %-8s %-5s%n", "#", "Name", "Score", "Grade");
        System.out.println("------------------------------------");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%-4d %-20s %-8.2f %-5s%n", i + 1, s.getName(), s.getScore(), s.getGrade());
        }
        System.out.println("------------------------------------");
        System.out.println("Total students: " + students.size());
        showStatistics();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = sc.nextInt();
                sc.nextLine();
                return v;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double v = sc.nextDouble();
                sc.nextLine();
                return v;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please enter a number.");
            }
        }
    }
}