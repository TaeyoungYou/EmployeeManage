package system.management.project;

import system.management.employee.Employee;
import system.management.location.Location;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String projectName;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private Location projectLocation;
    private List<Employee> attendedEmployees;

    public Project(String projectName, LocalDate projectStartDate, LocalDate projectEndDate, Location projectLocation, List<Employee> emps) {
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectLocation = projectLocation;
        attendedEmployees = new ArrayList<Employee>();
        attendedEmployees.addAll(emps);
    }
    public String getProjectName() {
        return projectName;
    }

    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    public Location getProjectLocation() {
        return projectLocation;
    }

    public List<Employee> getAttendedEmployees() {
        return attendedEmployees;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public void setProjectLocation(Location projectLocation) {
        this.projectLocation = projectLocation;
    }

    public void setAttendedEmployees(List<Employee> attendedEmployees) {
        this.attendedEmployees = attendedEmployees;
    }
}
