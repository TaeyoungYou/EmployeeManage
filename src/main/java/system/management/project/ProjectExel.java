package system.management.project;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;
import system.management.location.Location;
import system.management.location.LocationList;
import system.management.path.ManageFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectExel {
    private static final String pathKey = "ManagePath";

    public static void readProjectExel(){
        try{
            FileInputStream in = new FileInputStream(ManageFile.getPath(pathKey));
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(2);

            for(Row row: sheet){
                if(row.getRowNum() == 0){
                    continue;
                }
                List<Cell> cells = new ArrayList<Cell>();
                for(Cell cell: row){
                    cells.add(cell);
                }

                Location location = new ProjectExel().getLocation(cells.get(3));
                List<Employee> employeeList = new ProjectExel().getEmpList(cells.subList(4, cells.size()));


                ProjectList.add(new Project(
                        cells.get(0).toString(),
                        cells.get(1).toString().isEmpty()?null:LocalDate.parse(cells.get(1).toString()),
                        cells.get(2).toString().isEmpty()?null:LocalDate.parse(cells.get(2).toString()),
                        location,
                        employeeList
                ));
            }
        }catch(Exception e){}

    }

    private Location getLocation(Cell cell){
        if(!cell.toString().isEmpty()) {
            for(Location l: LocationList.getLocations()){
                if(cell.toString().equals(l.getLocationName())){
                    return l;
                }
            }
        }
        return new Location("","");
    }

    private List<Employee> getEmpList(List<Cell> cells){
        List<Employee> tmpList = new ArrayList<>();
        for(Cell cell: cells){
            for(Employee e: EmployeeList.getEmployees()){
                if(cell.toString().equals(e.getName())){
                    tmpList.add(e);
                }
            }
        }
        return tmpList;
    }

    public static void saveProjectExel(){
        try{
            FileInputStream in = new FileInputStream(ManageFile.getPath(pathKey));
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(2);

            int lastRowNum = sheet.getLastRowNum();
            for(int i = lastRowNum; i>0; i--){
                Row row = sheet.getRow(i);
                if(row != null){
                    sheet.removeRow(row);
                }
            }

            List<Project> tmpList = ProjectList.getProjects();
            for(int i=0;i<tmpList.size();i++){
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(tmpList.get(i).getProjectName());
                row.createCell(1).setCellValue(tmpList.get(i).getProjectStartDate().toString());
                row.createCell(2).setCellValue(tmpList.get(i).getProjectEndDate().toString());
                row.createCell(3).setCellValue(tmpList.get(i).getProjectLocation().getLocationName());
                for(int j = 0;j<tmpList.get(i).getAttendedEmployees().size();j++){
                    row.createCell(j+4).setCellValue(tmpList.get(i).getAttendedEmployees().get(j).getName());
                }
            }
            FileOutputStream out = new FileOutputStream(ManageFile.getPath(pathKey));
            workbook.write(out);
        }catch(Exception e){}

    }
}
