package serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadEmpObject
{
    public static void main(String[] args)
    {
        FileInputStream fis;
        ObjectInputStream ois;
        try
        {
            fis = new FileInputStream("Employee.dat");
            ois = new ObjectInputStream((fis));
            Employee emp = null;
            try
            {
                emp = (Employee) ois.readObject();
            } catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
            System.out.println(emp);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
