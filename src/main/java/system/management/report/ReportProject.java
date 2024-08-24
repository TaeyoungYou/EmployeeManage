package system.management.report;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import system.management.project.Project;
import system.management.project.ProjectList;

public class ReportProject {
    private final String reportKey = "ReportPath";
    public void createTemplate(){
        Workbook workbook = new XSSFWorkbook();
        for(Project project: ProjectList.getProjects()){
            Sheet sheet = workbook.createSheet(project.getProjectName());



        }

    }
}
