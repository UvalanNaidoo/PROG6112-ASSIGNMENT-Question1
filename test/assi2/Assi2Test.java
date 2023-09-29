/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assi2;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class Assi2Test {
    


    private ArrayList<Student> studentList;
    private InputStream originalSystemIn;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        originalSystemIn = System.in;
    }

    @Test
    
    public void testSaveStudent() {
    
        String input = "123\nJohn Doe\n20\njohndoe@example.com\nComputer Science\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        
        System.setIn(in);

        Assi2.saveStudent(new Scanner(System.in));

        assertEquals(1, studentList.size());
        Student savedStudent = studentList.get(0);
        assertEquals(123, savedStudent.getId());
        assertEquals("John Doe", savedStudent.getName());
        assertEquals(20, savedStudent.getAge());
        assertEquals("johndoe@example.com", savedStudent.getEmail());
        assertEquals("Computer Science", savedStudent.getCourse());
        
    }
    
    public void testSearchStudent() {
        
        // Create a test student and add it to the student list
        Student testStudent = new Student(123, "John Doe", 20, "johndoe@example.com", "Computer Science");
        studentList.add(testStudent);

        // Redirect standard input to provide student ID
        String input = "123\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture printed student details
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assi2.searchStudent(new Scanner(System.in));

        // Check if the correct student details have been printed
        String expectedOutput = "Student details:\n" +
                                "ID: 123\n" +
                                "Name: John Doe\n" +
                                "Age: 20\n" +
                                "Email: johndoe@example.com\n" +
                                "Course: Computer Science\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    
    public void testSearchStudent_StudentNotFound() {
        
        // Redirect standard input to provide an incorrect student ID
        String input = "999\n"; // Assuming 999 is an invalid student ID
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture printed result
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assi2.searchStudent(new Scanner(System.in));

        // Check if the method correctly reports that no student was found
        String expectedOutput = "Student with ID 999 not found.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    
    public void testDeleteStudent() {
       
        // Create a test student and add it to the student list
        Student testStudent = new Student(123, "John Doe", 20, "johndoe@example.com", "Computer Science");
        studentList.add(testStudent);

        // Redirect standard input to provide student ID for deletion
        String input = "123\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture printed result
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assi2.deleteStudent(new Scanner(System.in));

        // Check if the student has been successfully deleted
        assertEquals(0, studentList.size());
        String expectedOutput = "Student found:\n" +
                                "ID: 123\n" +
                                "Name: John Doe\n" +
                                "Age: 20\n" +
                                "Email: johndoe@example.com\n" +
                                "Course: Computer Science\n" +
                                "Are you sure you want to delete this student? (y/n): \n" +
                                "Student with ID 123 has been deleted.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testDeleteStudent_StudentNotFound() {
        
        // Redirect standard input to provide an incorrect student ID
        String input = "999\n"; // Assuming 999 is an invalid student ID
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture printed result
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assi2.deleteStudent(new Scanner(System.in));

        // Check if the method correctly reports that no student was found to delete
        assertEquals(0, studentList.size()); // Ensure no students were deleted
        String expectedOutput = "Student with ID 999 not found.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    
    public void testStudentAge_StudentAgeValid() {
        
        // Redirect standard input to provide a valid student age (e.g., 18)
        String input = "18\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture printed result
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assi2.saveStudent(new Scanner(System.in));

        // Check if the student's age is valid and the student has been saved
        assertEquals(1, studentList.size()); // Ensure one student is added
        Student savedStudent = studentList.get(0);
        assertEquals(18, savedStudent.getAge()); // Check the age

    }
    
    public void testStudentAge_StudentAgeInvalid() {
       
        // Redirect standard input to provide an invalid student age (e.g., 15)
        String input = "15\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture printed result
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assi2.saveStudent(new Scanner(System.in));

        // Check if the student's age is invalid and no student is saved
        assertEquals(0, studentList.size()); // Ensure no students are added

        // Check if the appropriate validation message is printed
        String expectedOutput = "Enter the student age (must be 16 or older): Invalid input. Age must be 16 or older.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    
     public void testStudentAge_StudentAgeInvalidCharacter() {
         
        // Redirect standard input to provide an invalid character as student age
        String input = "InvalidAge\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to capture printed result
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assi2.saveStudent(new Scanner(System.in));

        // Check if the student age is treated as invalid and no student is saved
        assertEquals(0, studentList.size()); // Ensure no students are added

        // Check if the appropriate validation message is printed
        String expectedOutput = "Enter the student age (must be 16 or older): Invalid input. Age must be a number.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
     
    

    

    
    
    
    
    

     

    
    
    
}



    

    

