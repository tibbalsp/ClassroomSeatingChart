package ClassroomApp;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class StartUP {


    public String folderLocation = "";
    public String parentFolder = "";
    private boolean start = true;


    String ClassroomApp() throws IOException {

        try {
            File file = new File(StartUP.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            parentFolder = file.getParent();
            System.out.println(parentFolder);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File deskTop = new File(parentFolder);
        File[] parentFiles = deskTop.listFiles();
        for (File f : parentFiles) {
            if ((f.isDirectory() && f.getName().contains("Seat"))) {
                if (f.isDirectory()) {
                    start = false;
                } else {
                    //  Haven't Found dir yet
                }
            }
        }
        System.out.println(System.getProperty("user.dir"));
        if(parentFolder.contains("Classroom")){
            folderLocation = parentFolder+"\\SeatChart";
            System.out.println(folderLocation);


        }else{
            folderLocation = parentFolder;
            System.out.println(folderLocation);


        }



        while (start) {

            File fileViewer = new File(folderLocation);
            fileViewer.mkdir();

            File classroomSaves = new File(folderLocation+"\\Classroom Saves");
            classroomSaves.mkdir();

            File rosterSaves = new File(folderLocation + "\\Roster Saves");
            rosterSaves.mkdir();

            start = false;
        }

        return folderLocation;

    }



}
