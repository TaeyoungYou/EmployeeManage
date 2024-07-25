package system.management.employee;

import system.management.util.Checker;

import java.util.Arrays;
import java.util.Objects;

public class Employee {
    private String employeeID;
    private String name;
    private String phone;

    private Employee(String employeeID, String name, String phone){
        this.employeeID = employeeID;
        this.name = name;
        this.phone = phone;
    }

    public static Employee createEmployee(String...data){
        String ID = data[0];
        String name = data[1];
        String phone = data[2];
        return new Employee(ID, name, phone);
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
