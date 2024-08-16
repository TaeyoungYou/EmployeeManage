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

    public static void updateEmployee(Employee beforeEmp, Employee afterEmp) {
        employees.set(employees.indexOf(beforeEmp), afterEmp);
    }

    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public static void removeEmployee(int i){
        employees.remove(i);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
}
