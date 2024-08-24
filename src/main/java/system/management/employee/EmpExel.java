package system.management.employee;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import system.management.path.ManageFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmpExel {
    private static final String pathKey = "ManagePath";

    public static void readEmpExel(){
        try{
            FileInputStream in = new FileInputStream(ManageFile.getPath(pathKey));
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row: sheet){
                if(row.getRowNum() == 0){
                    continue;
                }
                List<Cell> cells = new ArrayList<Cell>();
                for(Cell cell: row){
                    cells.add(cell);
                }
                EmployeeList.addEmployee(
                        Employee.createEmployee(
                                cells.get(0).toString(),
                                cells.get(1).toString(),
                                cells.get(2).toString(),
                                cells.get(3).toString(),
                                cells.get(4).toString(),
                                cells.get(5).toString(),
                                cells.get(6).toString()
                        )
                );
            }
        }catch(IOException e){}
    }
    public static void saveEmpExel(){
        try{
            FileInputStream in = new FileInputStream(ManageFile.getPath(pathKey));
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);

            int lastRow = sheet.getLastRowNum();
            for(int i = lastRow; i>0; i--){
                Row row = sheet.getRow(i);
                if(row != null){
                    sheet.removeRow(row);
                }
            }

            List<Employee> tempList = EmployeeList.getEmployees();
            for(int i = 0; i<tempList.size(); i++){
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(tempList.get(i).getName());
                row.createCell(1).setCellValue(tempList.get(i).getBirth());
                row.createCell(2).setCellValue(tempList.get(i).getPhone());
                row.createCell(3).setCellValue(tempList.get(i).getEmail());
                row.createCell(4).setCellValue(tempList.get(i).getExprience());
                row.createCell(5).setCellValue(tempList.get(i).getRole());
                row.createCell(6).setCellValue(tempList.get(i).getEtc());
            }

            FileOutputStream out = new FileOutputStream(ManageFile.getPath(pathKey));
            workbook.write(out);
        }catch(IOException e){}
    }
}
