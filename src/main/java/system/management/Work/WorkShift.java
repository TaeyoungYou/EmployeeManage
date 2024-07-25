package system.management.Work;

import system.management.employee.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WorkShift {
    private LocalDate date;         // 일한 날짜
    private LocalTime startTime;    // 출근
    private LocalTime endTime;      // 퇴근

    private ArrayList<Integer> amount;  // 액수
    private int wage;                   // 일당

    private final WorkShiftOption options;

    private Employee employee;


    public WorkShift(Employee employee) {
        this.employee = employee;
        options = new WorkShiftOption("overtime",0);
    }

    public String getDate() {
        return date.toString();
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public String getEndTime() {
        return endTime.toString();
    }

    public int getTotalAmount() {
        return amount.stream().mapToInt(Integer::valueOf).sum();
    }

    public int getWage() {
        return wage;
    }

}
