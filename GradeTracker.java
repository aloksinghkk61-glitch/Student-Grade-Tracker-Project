import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
	private static class Student {
		private final String name;
		private final double score;

		private Student(String name, double score) {
			this.name = name;
			this.score = score;
		}
	}

	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nStudent Grade Tracker");
			System.out.println("1. Add student grade");
			System.out.println("2. Display summary report");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");

			switch (scanner.nextLine().trim()) {
				case "1" -> addStudent(scanner, students);
				case "2" -> displayReport(students);
				case "3" -> {
                                    scanner.close();
                                    return;
                        }
				default -> System.out.println("Invalid option. Choose 1, 2, or 3.");
			}
		}
	}

	private static void addStudent(Scanner scanner, ArrayList<Student> students) {
		System.out.print("Enter student name: ");
		String name = scanner.nextLine().trim();
		if (name.isEmpty()) {
			System.out.println("Name cannot be empty.");
			return;
		}

		System.out.print("Enter score (0-100): ");
		try {
			double score = Double.parseDouble(scanner.nextLine().trim());
			if (!Double.isFinite(score) || score < 0 || score > 100) {
				System.out.println("Score must be a number from 0 to 100.");
				return;
			}
			students.add(new Student(name, score));
			System.out.println("Grade added.");
		} catch (NumberFormatException exception) {
			System.out.println("Please enter a valid numeric score.");
		}
	}

	private static void displayReport(ArrayList<Student> students) {
		if (students.isEmpty()) {
			System.out.println("No grades have been entered yet.");
			return;
		}

		double total = 0;
		double highest = students.get(0).score;
		double lowest = students.get(0).score;

		System.out.println("\nStudent Summary Report");
		System.out.printf("%-25s %s%n", "Student", "Score");
		System.out.println("------------------------------------");
		for (Student student : students) {
			System.out.printf("%-25s %.2f%n", student.name, student.score);
			total += student.score;
			highest = Math.max(highest, student.score);
			lowest = Math.min(lowest, student.score);
		}

		System.out.println("------------------------------------");
		System.out.printf("Number of students: %d%n", students.size());
		System.out.printf("Average score: %.2f%n", total / students.size());
		System.out.printf("Highest score: %.2f%n", highest);
		System.out.printf("Lowest score: %.2f%n", lowest);
	}
}
