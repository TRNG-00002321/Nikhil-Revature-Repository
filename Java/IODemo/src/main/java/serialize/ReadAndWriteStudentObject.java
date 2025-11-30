package serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Write programs that allows you to write a student or
// employee object into a JSON file and then read it and display the data.

public class ReadAndWriteStudentObject
{

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException
    {
        handleStudent();
    }

    private static void handleStudent() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        Student s = new Student(name, age, address);

        // Write to JSON
        mapper.writeValue(new File("student.json"), s);
        System.out.println("Student data saved to student.json");

        // Read back
        Student readStudent = mapper.readValue(new File("student.json"), Student.class);
        System.out.println("Read from file: " + readStudent);
    }
}
