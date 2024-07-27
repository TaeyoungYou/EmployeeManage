package system.management.employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeList {
    private final static List<Employee> employees = new ArrayList<Employee>();

    public EmployeeList() { }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static void updateEmployee(int i, Employee employee) {
        employees.set(i, employee);
    }

    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
}
