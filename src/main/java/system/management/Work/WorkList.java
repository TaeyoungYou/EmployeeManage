package system.management.Work;

import java.util.ArrayList;

public class WorkList {
    private final ArrayList<WorkShift> workShiftList;
    private int workingDays;

    public WorkList(){
        workShiftList = new ArrayList<>();
        setDays();
    }

    public void addWorkShift(WorkShift workShift){
        workShiftList.add(workShift);
        setDays();
    }

    public WorkShift getWorkShift(String date){
        for(WorkShift workShift : workShiftList){
            if(workShift.getDate().equals(date)){
                return workShift;
            }
        }
        return null;
    }

    public void updateWorkShift(String date, WorkShift workShift){
        for(WorkShift shift : workShiftList){
            if(shift.getDate().equals(date)){
                workShiftList.set(workShiftList.indexOf(shift), workShift);
                break;
            }
        }
    }

    public void deleteWorkShift(String date){
        workShiftList.removeIf(shift -> shift.getDate().equals(date));
        setDays();
    }

    private void setDays(){
        workingDays = workShiftList.size();
    }

    public int getWorkingDays(){
        return workingDays;
    }
}
