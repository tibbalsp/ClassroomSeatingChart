package ClassroomApp;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        String parentFolder = new StartUP().ClassroomApp();
        new MainView().mainViewBuilder(parentFolder);
    }
}
