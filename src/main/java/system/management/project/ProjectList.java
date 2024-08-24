package system.management.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectList {
    private static List<Project> projects = new ArrayList<Project>();

    public static void add(Project project) {
        projects.add(project);
    }

    public static List<Project> getProjects() {
        return projects;
    }
}
