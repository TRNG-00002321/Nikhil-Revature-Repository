public class Student
{
    String name;
    static int countOfStudents=0;

    public Student(String name)
    {
        this.name = name;
        countOfStudents++;
    }
    public static void main(String[] args)
    {
        Student s1 = new Student("Sam");
        System.out.println(s1.countOfStudents);
        Student s2 = new Student("Sam");
        System.out.println(s2.countOfStudents);
        System.out.println("------------------------");
        System.out.println(s1.countOfStudents);
    }
}
