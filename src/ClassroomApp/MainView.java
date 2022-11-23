package ClassroomApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame {

        private JFrame frame = new JFrame();
        private JPanel rosterAndMap = new JPanel(new BorderLayout());
        private JPanel window = new JPanel();
        private String parentFolder = "";


        DeskGenerator deskGenerator;
        Roster roster = new Roster();
        GridBagConstraints gbc = new GridBagConstraints();

        public void mainViewBuilder(String folder) {
            //Main frame
            parentFolder = folder;
            deskGenerator = new DeskGenerator(parentFolder);
            frame.setTitle("Custom Seat");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            frame.setLayout(new BorderLayout());
            JPanel window = new JPanel();
            frame.getContentPane().add(window);
            //First JPanel put to work
            window.setSize(frame.getSize());
            window.setLayout(new BorderLayout());



            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            splitPane.setResizeWeight(0.1);
            splitPane.setEnabled(true);
            splitPane.setDividerSize(0);








            //Main Menu Bar
            JMenuBar mainMenu = new JMenuBar();

            //Menu Items
            JMenu file = new JMenu("File");
            JMenu edit = new JMenu("Edit");
            JMenu help = new JMenu("Help");

            //File menu options
            JMenuItem newRoom = new JMenuItem("New Classroom");
            JMenuItem openRoom = new JMenuItem("Open Previous Classroom");
            JMenuItem saveRoster = new JMenuItem("Save Roster");
            file.add(newRoom);
            file.add(openRoom);
            file.add(saveRoster);

            //Edit menu options
            JMenuItem shuffle = new JMenuItem("Shuffle Seats");
            edit.add(shuffle);

            //Help menu options
            JMenuItem guide = new JMenuItem("Guide");
            JMenuItem about = new JMenuItem("About");
            help.add(guide);
            help.add(about);

            //Add sub menus to menu bar
            mainMenu.add(file);
            mainMenu.add(edit);
            mainMenu.add(help);

            newRoom.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    new ClassroomBuilder(parentFolder);


                }
            });

            openRoom.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        deskGenerator.setRoster(roster);
                       // JFileChooser fileChooser = new JFileChooser("src/Classroom Saves");
                        JFileChooser fileChooser = new JFileChooser(parentFolder+"/Classroom Saves");
                        fileChooser.showOpenDialog(openRoom);

                        File fileName = new File(fileChooser.getSelectedFile().toString());


                        deskGenerator.load(fileName);
                        roster.setDeskGenerator(deskGenerator,parentFolder);


                        rosterAndMap.removeAll();
                        rosterAndMap.add(roster,BorderLayout.WEST);
                        rosterAndMap.add(deskGenerator, BorderLayout.CENTER);

                        rosterAndMap.repaint();


                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });

            //Add the menu bar to frame
            frame.setJMenuBar(mainMenu);


//Make panel for listing the students
//Make panel for displaying the classroom
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(roster,BorderLayout.CENTER);
           // splitPane.add(panel);
            //splitPane.add(deskGenerator);

            rosterAndMap.add(panel,BorderLayout.WEST);
            rosterAndMap.add(deskGenerator,BorderLayout.CENTER);
            window.add(rosterAndMap, BorderLayout.CENTER);

            frame.add(window, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
           // frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        }
}
