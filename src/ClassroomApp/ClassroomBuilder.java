package ClassroomApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
///import java.lang.reflect.GenericDeclaration;
//import java.nio.Buffer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

//import javafx.scene.layout.Border;


class ClassroomBuilder extends JFrame{

    private JFrame frame = new JFrame();

    private JPanel window = new JPanel();

    DeskGenerator deskGenerator;

    private String parentFolder = "";

    public ClassroomBuilder(String folder){
        parentFolder = folder;
        deskGenerator = new DeskGenerator(parentFolder);
        classroomBuilderWindow();
    }
    void classroomBuilderWindow(){
        //Main frame
        frame.setTitle("Building Classroom");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(window);
        //First JPanel put to work
        window.setSize(frame.getSize());
        window.setLayout(new BorderLayout());
        JMenuBar mainMenu = new JMenuBar();

        //Menu Items
        JMenu file = new JMenu("File");
        JMenuItem newRoom = new JMenuItem("New Classroom");
        file.add(newRoom);
        mainMenu.add(file);
        frame.setJMenuBar(mainMenu);



//Panel for the classroomSize and seatOptions panels
        JPanel sizeSeatPanel = new JPanel();
        sizeSeatPanel.setLayout(new BorderLayout());

//Split Pane for Buttons vs Map
        JSplitPane sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp2.setResizeWeight(0.1);
        sp2.setEnabled(false);
        sp2.setDividerSize(0);

//Classroom Size choice
        JPanel classroomSize = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        classroomSize.setSize(classroomSize.getSize());
        classroomSize.setLayout(new GridBagLayout());

//Custom Size Labels and Layout
        JLabel demensionsLabel = new JLabel("Enter Class Size in feet round to nearest whole number.  (Width x Height)");
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        classroomSize.add(demensionsLabel , gbc);

        JPanel sizeAndCreate = new JPanel();
        sizeAndCreate.setLayout(new GridLayout(2,1));
        JPanel sizeInput = new JPanel();
        JPanel createPanel = new JPanel();
        JTextField width = new JTextField("Enter Width");
        JTextField height = new JTextField("Enter Height");

        JLabel xlabel = new JLabel("x", SwingConstants.CENTER);
        JButton create = new JButton("Create");
        createPanel.add(create);

        sizeInput.setLayout(new GridLayout(0,3));
        sizeInput.add(width);
        sizeInput.add(xlabel);
        sizeInput.add(height);
        sizeAndCreate.add(sizeInput);
        sizeAndCreate.add(createPanel);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        classroomSize.add(sizeAndCreate,gbc);

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.drawRect(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()));
            }
        });


//Prebuild Sizes

        JPanel providedClassrooms = new JPanel();
        providedClassrooms.setLayout(new GridLayout(0,3));

//Jbuttons for prebuilt sizes
        JButton x20x20Button = new JButton("20 x 20");
        JButton x25x25Button = new JButton("25 x 25");
        JButton x30x30Button = new JButton("30 x 30");
        JButton x20x25Button = new JButton("20 x 25");
        JButton x20x30Button = new JButton("20 x 30");
        JButton x20x35Button = new JButton("20 x 35");

//Add prebuilt size buttons
        providedClassrooms.add(x20x20Button);
        providedClassrooms.add(x25x25Button);
        providedClassrooms.add(x30x30Button);
        providedClassrooms.add(x20x25Button);
        providedClassrooms.add(x20x30Button);
        providedClassrooms.add(x20x35Button);

// Action listeners for prebuilt sizes
        x20x20Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deskGenerator.drawRect(600,600); }
        });
        x25x25Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.drawRect(800,800);
            }
        });
        x30x30Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.drawRect(1000,1000);
            }
        });
        x20x25Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.drawRect(800,800);
            }
        });
        x20x30Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.drawRect(1000,800);
            }
        });
        x20x35Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.drawRect(1200,800);
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 40;
        gbc.ipady = 40;

        classroomSize.add(providedClassrooms , gbc);

//Seat options
        JPanel seatTypes = new JPanel();
        seatTypes.setLayout(new GridLayout(0,2,10,10));
        seatTypes.setBorder(BorderFactory.createEmptyBorder(10,10,10, 10));


//Seat buttons
        JButton singleButton = new JButton("Single Desk");
        JButton wideDoubleButton = new JButton("Wide Double Desk");
        JButton wideThreeButton = new JButton("Wide Triple Desk");
        JButton wideFourButton = new JButton("Wide Four Desk");
        JButton tallDoubleButton = new JButton("Tall Double Desk");
        JButton tallTripleDesk = new JButton("Tall Triple Desk");
        JButton tallFourDesk = new JButton("Tall Four Desk");
        JButton square4Button = new JButton("Square Four Desk");

//Action buttons
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton newWindow = new JButton("New");
        JButton undo = new JButton("Undo");

//Add Seat buttons
        seatTypes.add(singleButton);
        seatTypes.add(square4Button);
        seatTypes.add(wideDoubleButton);
        seatTypes.add(tallDoubleButton);
        seatTypes.add(wideThreeButton);
        seatTypes.add(tallTripleDesk);
        seatTypes.add(wideFourButton);
        seatTypes.add(tallFourDesk);


//Add Action buttons
        seatTypes.add(load);
        seatTypes.add(save);
        seatTypes.add(newWindow);
        seatTypes.add(undo);

// Button action listeners
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deskGenerator.addSingleDesk(10,10,true); }
        });
        wideDoubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.addWideDoubleDesk(10,10,true,true);
            }
        });
        tallDoubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deskGenerator.addTallDoubleDesk(10,10,true,true); }
        });

        wideThreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.addWideTripleDesk(10,10,true,true,true);
            }
        });

        tallTripleDesk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.addTallTripleDesk(10,10,true,true,true);
                }
        });

        wideFourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.addWideFourDesk(10,10,true,true,true,true);
            }
        });

        tallFourDesk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.addTallFourDesk(10,10,true,true,true,true);
            }
        });

        square4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.addFourSquareDesk(10,10,true,true,true,true);
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deskGenerator.save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser(parentFolder+"/Classroom Saves");
                    FileFilter filter = new FileNameExtensionFilter("Text File", "txt");
                    fileChooser.setFileFilter(filter);
                    fileChooser.showOpenDialog(load);
                    File fileName = new File(fileChooser.getSelectedFile().toString());
                    deskGenerator.load(fileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        newWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               deskGenerator.newWindow();
            }
        });
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskGenerator.undoSeat();

            }
        });

// Add to left side panels nested split panel
        sp2.add(classroomSize);
        sp2.add(seatTypes);
        sizeSeatPanel.add(sp2,BorderLayout.CENTER);



// Add 2 panels to main window panel

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,sizeSeatPanel, deskGenerator);
        sp.setEnabled(false);

//Add original split panel to main panel display
        window.add(sp, BorderLayout.CENTER);
        file.revalidate();
        frame.revalidate();
        frame.setVisible(true);




    }
}