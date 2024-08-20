package system.controller.locate;

public class LocateControllerManager {
    private static LocatePageController locatePageController;
    private static LocateDataController locateDataController;

    public static LocatePageController getLocatePageController() {
        return locatePageController;
    }
    public static void setLocatePageController(LocatePageController locatePageController) {
        LocateControllerManager.locatePageController = locatePageController;
    }

    public static LocateDataController getLocateDataController() {
        return locateDataController;
    }
    public static void setLocateDataController(LocateDataController locateDataController) {
        LocateControllerManager.locateDataController = locateDataController;
    }
}
