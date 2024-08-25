package system.management.report;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import system.management.employee.Employee;
import system.management.path.ManageFile;
import system.management.project.Project;
import system.management.project.ProjectList;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class ReportProject {
    private final static String reportKey = "ReportPath";
    private final static Workbook workbook = new XSSFWorkbook();

    public static void exportReport(){
        createSheet();
        createEmpBlock();
        setCenter();
        try{
            FileOutputStream out = new FileOutputStream(ManageFile.getPath(reportKey));
            workbook.write(out);
            out.close();
        }catch (Exception e){}
    }

    private static void createSheet(){
        for(Project project: ProjectList.getProjects()){
            Sheet sheet = workbook.createSheet(project.getProjectName());

            sheet.createFreezePane(1,0);

            Row headerRow = sheet.createRow(0);
            Cell headerCell = headerRow.createCell(1);
            headerCell.setCellValue(project.getProjectName());

            sheet.addMergedRegion(new CellRangeAddress(0,1,1,project.getProjectStartDate().with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth()+1));

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            headerStyle.setFont(headerFont);
            headerCell.setCellStyle(headerStyle);
        }

    }

    private static void createEmpBlock(){
        for(Project project: ProjectList.getProjects()){
            Sheet sheet = workbook.getSheet(project.getProjectName());

            int year = project.getProjectStartDate().getYear();
            int month = project.getProjectStartDate().getMonthValue();

            LocalDate lastDayOfMonth = project.getProjectStartDate().with(TemporalAdjusters.lastDayOfMonth());
            int lastDay = lastDayOfMonth.getDayOfMonth();

            int startRowIndex = 3;
            int endRowIndex = startRowIndex + 6;    // 임의로 6개 요소를 넣음
            int startColIndex = 0;
            int endColIndex = startColIndex + lastDay + 2;

            for(Employee employee: project.getAttendedEmployees()){
                List<Row> rows = new ArrayList<>();
                for(int i = 0; i < endRowIndex - startRowIndex; i++){   // 각 employee칸의 row 객체 생성
                    rows.add(sheet.createRow(i+startRowIndex));
                }

                String[] menu = new String[] {"날짜",employee.getName(),"출근","퇴근","금액","소계"};     // 임의로 설정
                for(int i = 0; i < rows.size(); i++){   // 각 직원 메뉴 작성
                    rows.get(i).createCell(startColIndex).setCellValue(menu[i]);
                }

                for(int i = 0; i < lastDay; i++){               // 각 달에 맞게 요일 작성
                    Cell cell = rows.get(0).getCell(i+1);
                    if(cell == null){
                        cell = rows.get(0).createCell(i+1);
                    }
                    cell.setCellValue(switch(LocalDate.of(year,month,i+1).getDayOfWeek().toString()){
                        case "MONDAY" -> "월";
                        case "TUESDAY" -> "화";
                        case "WEDNESDAY" -> "수";
                        case "THURSDAY" -> "목";
                        case "FRIDAY" -> "금";
                        case "SATURDAY" -> "토";
                        case "SUNDAY" -> "일";
                        default -> "X";
                    });
                }

                // 마지막 셀에 합계 넣기
                Cell lastCell = rows.get(0).getCell(lastDay+1);
                if(lastCell == null){
                    lastCell = rows.get(0).createCell(lastDay+1);
                }
                lastCell.setCellValue("합계");

                for(int i = 0; i < lastDay; i++){       // 각 employee의 row를 그 달의 일로 채우기
                    Cell cell = rows.get(1).getCell(i+1);
                    if(cell == null){
                        cell = rows.get(1).createCell(i+1);
                    }
                    cell.setCellValue(i+1);
                }



                empBlockDesign(rows, startRowIndex, endRowIndex, startColIndex, endColIndex);

                startRowIndex = endRowIndex + 1;
                endRowIndex = startRowIndex + 6;
            }


        }
    }

    private static void empBlockDesign(List<Row> rows, int startRow, int endRow, int startCol, int endCol){
        CellStyle topBorderStyle = ReportProject.workbook.createCellStyle();
        topBorderStyle.setBorderTop(BorderStyle.MEDIUM);

        CellStyle bottomBorderStyle = ReportProject.workbook.createCellStyle();
        bottomBorderStyle.setBorderBottom(BorderStyle.MEDIUM);

        CellStyle rightBorderStyle = ReportProject.workbook.createCellStyle();
        rightBorderStyle.setBorderRight(BorderStyle.MEDIUM);

        for(int i = 0; i < endCol; i++){        // 위쪽 테두리 스타일 설정
            Row row = rows.get(0);

            Cell cell = row.getCell(i);
            if(cell == null){
                cell = row.createCell(i);
            }
            cell.setCellStyle(topBorderStyle);
        }

        for(int i = 0; i < endCol; i++){        // 아래쪽 테두리 스타일 설정
            Row row = rows.get(endRow - startRow - 1);

            Cell cell = row.getCell(i);
            if(cell == null){
                cell = row.createCell(i);
            }
            cell.setCellStyle(bottomBorderStyle);
        }

        for(int i = 0; i < endRow - startRow; i++){
            Row row = rows.get(i);

            Cell cell = row.getCell(endCol - 1);
            if(cell == null){
                cell = row.createCell(endCol - 1);
            }

            if(i == 0){
                CellStyle cornerStyle = ReportProject.workbook.createCellStyle();
                cornerStyle.setBorderTop(BorderStyle.MEDIUM);
                cornerStyle.setBorderRight(BorderStyle.MEDIUM);
                cell.setCellStyle(cornerStyle);
            }else if(i == endRow - startRow - 1){
                CellStyle cornerStyle = ReportProject.workbook.createCellStyle();
                cornerStyle.setBorderBottom(BorderStyle.MEDIUM);
                cornerStyle.setBorderRight(BorderStyle.MEDIUM);
                cell.setCellStyle(cornerStyle);
            }else{
                cell.setCellStyle(rightBorderStyle);
            }
        }
    }

    private static void setCenter(){
        for(Project project: ProjectList.getProjects()){
            Sheet sheet = workbook.getSheet(project.getProjectName());


            for(int i = 0; i < sheet.getLastRowNum() + 1; i++){
                Row row = sheet.getRow(i);
                if(row != null){
                    for(int j = 0; j < row.getLastCellNum(); j++){
                        Cell cell = row.getCell(j);
                        if(cell != null){
                            CellStyle center = workbook.createCellStyle();
                            CellStyle existStyle = cell.getCellStyle();
                            center.cloneStyleFrom(existStyle);
                            center.setAlignment(HorizontalAlignment.CENTER);
                            cell.setCellStyle(center);
                        }
                    }
                }
            }
        }

    }
}
