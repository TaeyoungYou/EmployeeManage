package system.management.location;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;
import system.management.path.ManageFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationExel {

    public static void readLocationExel(){
        try{
            FileInputStream in = new FileInputStream(ManageFile.getPath());
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(1);

            for(Row row: sheet){
                if(row.getRowNum() == 0){
                    continue;
                }
                List<Cell> cells = new ArrayList<Cell>();
                for(Cell cell: row){
                    cells.add(cell);
                }
                LocationList.add(new Location(cells.get(0).toString(), cells.get(1).toString()));
            }
        }catch(IOException e){}
    }
    public static void saveLocationExel(){
        try{
            FileInputStream in = new FileInputStream(ManageFile.getPath());
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(1);

            int lastRow = sheet.getLastRowNum();
            for(int i = lastRow; i>0; i--){
                Row row = sheet.getRow(i);
                if(row != null){
                    sheet.removeRow(row);
                }
            }

            List<Location> tempList = LocationList.getLocations();
            for(int i = 0; i<tempList.size(); i++){
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(tempList.get(i).getLocationName());
                row.createCell(1).setCellValue(tempList.get(i).getAddress());
            }

            FileOutputStream out = new FileOutputStream(ManageFile.getPath());
            workbook.write(out);
        }catch(IOException e){}
    }
}
