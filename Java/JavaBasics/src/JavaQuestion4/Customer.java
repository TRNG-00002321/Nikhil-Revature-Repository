package JavaQuestion4;

public class Customer
{
    String name;
    static int age; //One static variable
    final String COUNTRY; //One final variable

    //One of the constructors of the class.
    public Customer(String name, int age, String country)
    {
        this.name = name;
        this.age = age;
        this.COUNTRY = country;
    }
    //Other constructor of the class.
    public Customer(int age, String country)
    {
        this.age = age;
        this.COUNTRY = country;
    }
    //Getter method that gets an object's age
    public static int getAge()
    {
        return age;
    }
    //Overloaded Method 1
    public static int add(int a, int b)
    {
        return a + b;
    }
    //Overloaded Method 2
    public static int add(int a, int b, int c)
    {
        return a + b + c;
    }

    public static void main(String[] args)
    {
        Customer a = new Customer("Nikhil", 30, "USA");
        Customer b = new Customer("Josh", 23, "Japana");
        System.out.println(a);
        System.out.print(add(3, 4));
        System.out.println(add(3, 4,5));
    }
}
