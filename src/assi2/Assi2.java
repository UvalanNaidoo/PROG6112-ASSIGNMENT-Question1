/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assi2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class Assi2 {

    /**
     * @param args the command line arguments
     */

    static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
        System.out.println("Student Management Application");
            System.out.println("*****************************************");
        System.out.println("\nEnter 1 to launch the menu or any other key to exit.");
        System.out.print("Your choice: ");

        String userInput = scanner.nextLine();

        if (!userInput.equals("1")) {
            System.out.println("Exiting the application. Goodbye!");
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Capture a new student.");
            System.out.println("2. Search for a student.");
            System.out.println("3. Delete a student.");
            System.out.println("4. Print student report.");
            System.out.println("5. Update Student");
            System.out.println("6. Exit application.");

            System.out.print("Enter your choice (1-6): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        saveStudent(scanner);
                        break;
                    case 2:
                        searchStudent(scanner);
                        break;
                    case 3:
                        deleteStudent(scanner);
                        break;
                    case 4:
                        studentReport();
                        break;
                    case 5:
                        updateStudent(scanner);
                        break;
                    case 6:
                        System.out.println("Exiting the application. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option (1-5).");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option (1-5).");
                scanner.nextLine();  // Consume the invalid input
            }
        }
        }
    }

    public static void saveStudent(Scanner scanner) {
    System.out.println("\nCapture a new student:");

    int id;
    do {
        System.out.print("Enter the student ID (numbers only): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Student ID must be a number.");
            System.out.print("Enter the student ID (numbers only): ");
            scanner.next();  // Consume the non-integer input
        }
        id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
    } while (id <= 0);  // Ensure ID is positive

    System.out.print("Enter the student name: ");
    String name = scanner.nextLine();

    int age;
    do {
        System.out.print("Enter the student age (must be 16 or older): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Age must be a number.");
            System.out.print("Enter the student age (must be 16 or older): ");
            scanner.next();  // Consume the non-integer input
        }
        age = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        if (age < 16) {
            System.out.println("Invalid input. Age must be 16 or older.");
        }
    } while (age < 16);

    String email;
    do {
        System.out.print("Enter the student email (must contain '@'): ");
        email = scanner.nextLine();

        if (!email.contains("@")) {
            System.out.println("Invalid input. Email must contain '@' symbol.");
        }
    } while (!email.contains("@"));

    System.out.print("Enter the student course: ");
    String course = scanner.nextLine();

    Student student = new Student(id, name, age, email, course);
    studentList.add(student);

    System.out.println("Student added successfully!");
}


   public static void searchStudent(Scanner scanner) {
    System.out.println("\nSearch for a student:");

    int searchId;
    do {
        System.out.print("Enter the student ID (numbers only): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Student ID must be a number.");
            System.out.print("Enter the student ID (numbers only): ");
            scanner.next();  // Consume the non-integer input
        }
        searchId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
    } while (searchId <= 0);  // Ensure ID is positive

    boolean studentFound = false;

    for (Student student : studentList) {
        if (student.getId() == searchId) {
            System.out.println("Student details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());
            studentFound = true;
            break;  // Exit the loop as the student is found
        }
    }

    if (!studentFound) {
        System.out.println("Student with ID " + searchId + " not found.");
    }
}


    public static void deleteStudent(Scanner scanner) {
    System.out.println("\nDelete a student:");

    int deleteId;
    do {
        System.out.print("Enter the student ID to delete (numbers only): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Student ID must be a number.");
            System.out.print("Enter the student ID to delete (numbers only): ");
            scanner.next();  // Consume the non-integer input
        }
        deleteId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
    } while (deleteId <= 0);  // Ensure ID is positive

    boolean studentDeleted = false;

    for (Student student : studentList) {
        if (student.getId() == deleteId) {
            System.out.println("Student found:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());

            System.out.print("Are you sure you want to delete this student? (y/n): ");
            String confirmation = scanner.nextLine().toLowerCase();

            if (confirmation.equals("y")) {
                studentList.remove(student);
                System.out.println("Student with ID " + deleteId + " has been deleted.");
                studentDeleted = true;
            } else {
                System.out.println("Deletion canceled. Student with ID " + deleteId + " has not been deleted.");
            }

            break;  // Exit the loop after processing
        }
    }

    if (!studentDeleted) {
        System.out.println("Student with ID " + deleteId + " not found.");
    }
}


    public static void studentReport() {
        System.out.println("\nStudent Report:");

        if (studentList.isEmpty()) {
            System.out.println("No students found in the database.");
        } else {
            for (Student student : studentList) {
                System.out.println("----------------------");
                System.out.println("ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Age: " + student.getAge());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Course: " + student.getCourse());
                System.out.println("----------------------");
            }
        }
    }
    
    public static void updateStudent(Scanner scanner) {
    System.out.println("\nUpdate a student:");

    int updateId;
    do {
        System.out.print("Enter the student ID to update (numbers only): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Student ID must be a number.");
            System.out.print("Enter the student ID to update (numbers only): ");
            scanner.next();  // Consume the non-integer input
        }
        updateId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
    } while (updateId <= 0);  // Ensure ID is positive

    boolean studentUpdated = false;

    for (Student student : studentList) {
        if (student.getId() == updateId) {
            System.out.println("Student found:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());

            System.out.println("\nUpdate student information:");

            System.out.print("Enter new ID (or press Enter to keep current ID): ");
            String newIdInput = scanner.nextLine();
            if (!newIdInput.isEmpty()) {
                int newId;
                try {
                    newId = Integer.parseInt(newIdInput);
                    if (newId <= 0) {
                        System.out.println("Invalid input. ID must be a positive number.");
                    } else {
                        student.setId(newId);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. ID must be a number.");
                }
            }

            System.out.print("Enter new name (or press Enter to keep current name): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                student.setName(newName);
            }

            int newAge;
            do {
                System.out.print("Enter new age (or press Enter to keep current age): ");
                String newAgeInput = scanner.nextLine();
                if (newAgeInput.isEmpty()) {
                    break;  // Keep the current age
                }
                try {
                    newAge = Integer.parseInt(newAgeInput);
                    if (newAge < 16) {
                        System.out.println("Invalid input. Age must be 16 or older.");
                    } else {
                        student.setAge(newAge);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Age must be a number.");
                }
            } while (true);

            System.out.print("Enter new email (or press Enter to keep current email): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.isEmpty()) {
                student.setEmail(newEmail);
            }

            System.out.print("Enter new course (or press Enter to keep current course): ");
            String newCourse = scanner.nextLine();
            if (!newCourse.isEmpty()) {
                student.setCourse(newCourse);
            }

            System.out.println("Student information updated successfully.");
            studentUpdated = true;
            break;  // Exit the loop after updating
        }
    }

    if (!studentUpdated) {
        System.out.println("Student with ID " + updateId + " not found.");
    }
}


}