package serialize;

import java.io.Serializable;

public class Employee implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int empId;
    private String empName;
    private transient String jobTitle;

    public Employee()
    {
    }

    public Employee(int empId, String empName, String jobTitle) {
        this.empId = empId;
        this.empName = empName;
        this.jobTitle = jobTitle;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
