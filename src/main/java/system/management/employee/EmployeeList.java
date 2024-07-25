package system.management.employee;

import java.util.HashSet;
import java.util.Set;

public class EmployeeList {
    private final Set<Employee> employees = new HashSet<Employee>();

    public EmployeeList() { }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee getEmployee(String id) {
        for(Employee employee : employees) {
            if(employee.getEmployeeID().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public void updateEmployee(String id, Employee employee) {
        employees.removeIf(emp -> emp.getEmployeeID().equals(id));
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
}
