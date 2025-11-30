package serialize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class WriteEmpObj
{
    public static void main(String[] args) {
        Employee emp = new Employee(101, "Andrew", "QEA");
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream("employee.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(emp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
