package system.controller.project;

import system.controller.project.add_scene.ProjectAddController;
import system.controller.project.add_scene.ProjectEmpDataController;
import system.controller.project.update_scene.ProjectDataController;

public class ProjectControllerManager {
    private static ProjectPageController projectPageController;
    private static ProjectAddController projectAddController;
    private static ProjectEmpDataController projectEmpDataController;
    private static ProjectDataController projectDataController;

    public static ProjectPageController getProjectPageController() {
        return projectPageController;
    }

    public static void setProjectPageController(ProjectPageController projectPageController) {
        ProjectControllerManager.projectPageController = projectPageController;
    }

    public static ProjectAddController getProjectAddController() {
        return projectAddController;
    }

    public static void setProjectAddController(ProjectAddController projectAddController) {
        ProjectControllerManager.projectAddController = projectAddController;
    }

    public static ProjectEmpDataController getProjectEmpDataController() {
        return projectEmpDataController;
    }

    public static void setProjectEmpDataController(ProjectEmpDataController projectEmpDataController) {
        ProjectControllerManager.projectEmpDataController = projectEmpDataController;
    }

    public static ProjectDataController getProjectDataController() {
        return projectDataController;
    }

    public static void setProjectDataController(ProjectDataController projectDataController) {
        ProjectControllerManager.projectDataController = projectDataController;
    }
}
