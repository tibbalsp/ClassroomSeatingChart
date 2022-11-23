package ClassroomApp;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.*;




public class DeskGenerator extends JPanel {

    private static PrintWriter writer;
    private String fileName = "";
    private int startingX = 10;
    private int startingY = 10;
    private boolean drag = false;
    private Point mousePt;
    private ArrayList<JPanel> panels = new ArrayList<>(25);
    private ArrayList<JCheckBox> checkBoxesList = new ArrayList<>(100);
    private int checkBoxCounter;

    public ArrayList<JCheckBox> getCheckBoxList() {
        return checkBoxesList;
    }

    Roster roster;
    private String parentFolder = "";

    public void setRoster(Roster temp) {
        roster = temp;
    }

    public DeskGenerator(String folder) {
        parentFolder = folder;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(null);
        setFocusable(true);
        validate();
        setVisible(true);

    }
    void makeCheckBox(JCheckBox checkBox,Boolean state){
        checkBox.setMnemonic(KeyEvent.VK_C);
        checkBox.setSelected(state);
        checkBox.setVisible(true);
        checkBox.setOpaque(false);
        checkBox.setName(String.valueOf(checkBoxCounter));
        if (state == true) {
            checkBoxesList.add(checkBox);
        }else {
            //Dont add is false
        }

        checkBoxListener(checkBox);
    }
    void checkBoxListener(JCheckBox checkBox) {
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected() == true) {
                    try {
                        checkBoxesList.add(checkBox);
                        renameCheckBoxes();
                        roster.addSeat(checkBoxesList,checkBoxesList.size());
                        update();

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //Do nothing already in list
                } else {
                    try {
                        removeNameFromCheckBoxList(checkBox);
                       // renameCheckBoxes();
                       // update();


                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
    }
// Manage checkbox list

    void renameCheckBoxes() {
        for (int i = 0; i < checkBoxesList.size(); i++) {
            if (checkBoxesList.get(i).isSelected()) {
                checkBoxesList.get(i).setText(String.valueOf(i + 1));
                checkBoxesList.get(i).setName(String.valueOf(i + 1));
            } else {
                //Do nothing
            }
        }
    }

    void removeNameFromCheckBoxList(JCheckBox checkBox) throws IOException {
        String name = checkBox.getName();
        int index = Integer.parseInt(name) - 1;
        checkBoxesList.get(index).setText("");
        checkBoxesList.remove(index);
        for (int i = index; i < checkBoxesList.size(); i++) {
            checkBoxesList.get(i).setText(String.valueOf(i + 1));
            checkBoxesList.get(i).setName(String.valueOf(i + 1));
        }
        if(roster!=null) {
            roster.removeSeat();
        }
        renameCheckBoxes();
        update();
    }


    // Function buttons
    public void save() throws IOException {

        fileName = JOptionPane.showInputDialog("Name this file");
        writer = new PrintWriter(parentFolder + "/Classroom Saves/" + fileName + ".txt", String.valueOf(StandardCharsets.UTF_8));

        writer.println(width + "," + height);

        for (JPanel panel : panels) {
            writeToFile(panel);
        }
        writer.close();
    }

    public void writeToFile(JPanel panel){
        Component[] components = panel.getComponents();

        if (panel.getName().equals("SingleDesk")) {
            JCheckBox temp = (JCheckBox) components[0];
            String s = "false";
            if (temp.isSelected() == true) {
                s = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s);
        } else if (panel.getName().equals("WideDoubleDesk")) {
            //Get Checkbox Panel
            JPanel tempPanel = (JPanel) components[0];

            Component[] components1 = tempPanel.getComponents();
            JCheckBox temp1 = (JCheckBox) components1[1];
            JCheckBox temp2 = (JCheckBox) components1[3];
            String s1 = "false";
            String s2 = "false";
            if (temp1.isSelected() == true) {
                s1 = "true";
            }
            if (temp2.isSelected() == true) {
                s2 = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s1 + "," + s2);
        } else if (panel.getName().equals("TallDoubleDesk")) {
            //Get Checkbox Panel
            JPanel tempPanel = (JPanel) components[0];

            Component[] components1 = tempPanel.getComponents();
            JCheckBox temp1 = (JCheckBox) components1[1];
            JCheckBox temp2 = (JCheckBox) components1[3];
            String s1 = "false";
            String s2 = "false";
            if (temp1.isSelected() == true) {
                s1 = "true";
            }
            if (temp2.isSelected() == true) {
                s2 = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s1 + "," + s2);


        } else if (panel.getName().equals("WideTripleDesk")) {
            JPanel tempPanel = (JPanel) components[0];

            Component[] components1 = tempPanel.getComponents();

            JCheckBox temp1 = (JCheckBox) components1[1];
            JCheckBox temp2 = (JCheckBox) components1[3];
            JCheckBox temp3 = (JCheckBox) components1[5];
            String s1 = "false";
            String s2 = "false";
            String s3 = "false";
            if (temp1.isSelected() == true) {
                s1 = "true";
            }
            if (temp2.isSelected() == true) {
                s2 = "true";
            }
            if (temp3.isSelected() == true) {
                s3 = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s1 + "," + s2 + "," + s3);

        } else if (panel.getName().equals("TallTripleDesk")) {
            JPanel tempPanel = (JPanel) components[0];

            Component[] components1 = tempPanel.getComponents();

            JCheckBox temp1 = (JCheckBox) components1[1];
            JCheckBox temp2 = (JCheckBox) components1[3];
            JCheckBox temp3 = (JCheckBox) components1[5];
            String s1 = "false";
            String s2 = "false";
            String s3 = "false";
            if (temp1.isSelected() == true) {
                s1 = "true";
            }
            if (temp2.isSelected() == true) {
                s2 = "true";
            }
            if (temp3.isSelected() == true) {
                s3 = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s1 + "," + s2 + "," + s3);

        } else if (panel.getName().equals("WideFourLongDesk")) {
            JPanel tempPanel = (JPanel) components[0];

            Component[] components1 = tempPanel.getComponents();

            JCheckBox temp1 = (JCheckBox) components1[1];
            JCheckBox temp2 = (JCheckBox) components1[3];
            JCheckBox temp3 = (JCheckBox) components1[5];
            JCheckBox temp4 = (JCheckBox) components1[7];

            String s1 = "false";
            String s2 = "false";
            String s3 = "false";
            String s4 = "false";
            if (temp1.isSelected() == true) {
                s1 = "true";
            }
            if (temp2.isSelected() == true) {
                s2 = "true";
            }
            if (temp3.isSelected() == true) {
                s3 = "true";
            }
            if (temp4.isSelected() == true) {
                s4 = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s1 + "," + s2 + "," + s3 + "," + s4);
        } else if (panel.getName().equals("TallFourLongDesk")) {
            JPanel tempPanel = (JPanel) components[0];

            Component[] components1 = tempPanel.getComponents();

            JCheckBox temp1 = (JCheckBox) components1[1];
            JCheckBox temp2 = (JCheckBox) components1[3];
            JCheckBox temp3 = (JCheckBox) components1[5];
            JCheckBox temp4 = (JCheckBox) components1[7];

            String s1 = "false";
            String s2 = "false";
            String s3 = "false";
            String s4 = "false";
            if (temp1.isSelected() == true) {
                s1 = "true";
            }
            if (temp2.isSelected() == true) {
                s2 = "true";
            }
            if (temp3.isSelected() == true) {
                s3 = "true";
            }
            if (temp4.isSelected() == true) {
                s4 = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s1 + "," + s2 + "," + s3 + "," + s4);

        } else if (panel.getName().equals("FourSquareDesk")) {

            Component[] components1 = panel.getComponents();

            JCheckBox temp1 = (JCheckBox) components1[0];
            JCheckBox temp2 = (JCheckBox) components1[1];
            JCheckBox temp3 = (JCheckBox) components1[2];
            JCheckBox temp4 = (JCheckBox) components1[3];

            String s1 = "false";
            String s2 = "false";
            String s3 = "false";
            String s4 = "false";
            if (temp1.isSelected() == true) {
                s1 = "true";
            }
            if (temp2.isSelected() == true) {
                s2 = "true";
            }
            if (temp3.isSelected() == true) {
                s3 = "true";
            }
            if (temp4.isSelected() == true) {
                s4 = "true";
            }
            writer.println(panel.getName() + "," + "(" + panel.getX() + "," + panel.getY() + ")," + s1 + "," + s2 + "," + s3 + "," + s4);
        }
    }
    public void update() throws IOException {

        if (fileName.equals("")) {
            fileName = parentFolder + "/Classroom Saves/temp.txt";
        } else {
            File file = new File(parentFolder + "/Classroom Saves/"+fileName);
            file.delete();
            System.out.println("File deleted");
        }


        writer = new PrintWriter(parentFolder + "/Classroom Saves/"+fileName);
        writer.write(width + "," + height + "\n");
        for (JPanel panel : panels) {
            writeToFile(panel);
        }
        writer.close();
    }

    public void load(File file) throws IOException {
        newWindow();
        fileName = file.getName();
        int x, y;
        Boolean state1 ,state2 ,state3 ,state4 ;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = br.readLine();
        //Draw Classroom Shape
        String[] classShapeArr = temp.split(",");
        if(classShapeArr[0].equals("0") && classShapeArr[1].equals("0")) {

        }else {
            drawRect(Integer.parseInt(classShapeArr[0]), Integer.parseInt(classShapeArr[1]));
        }
        temp = br.readLine();
        while (temp != null) {
            temp = temp.replaceAll("[()]", "");
            temp = temp.replaceAll("[,]", " ");
            // System.out.println(temp);
            String[] tempArr = temp.split(" ");
            // System.out.println(Arrays.toString(tempArr));
            if (tempArr.length >= 4 && tempArr[0] != null && tempArr[1] != null && tempArr[2] != null) {
                x = Integer.parseInt(tempArr[1].replaceAll(" ", ""));
                y = Integer.parseInt(tempArr[2].replaceAll(" ", ""));
//Establish States
                if (tempArr[3].equals("true")) {
                    state1 = true;
                }else{
                    state1 = false;
                }
                if (tempArr.length>=5 && tempArr[4].equals("true")){
                    state2 = true;
                }else{
                    state2 = false;
                }
                if (tempArr.length>=6 && tempArr[5].equals("true")){
                    state3 = true;
                }else{
                    state3 = false;
                }
                if (tempArr.length>=7 && tempArr[6].equals("true")){
                    state4 = true;
                }else{
                    state4 = false;
                }
//Create desks
                if (tempArr[0].equals("SingleDesk")) {
                    addSingleDesk(x, y, state1);
                }
                if (tempArr[0].equals("WideDoubleDesk")) {
                    addWideDoubleDesk(x, y, state1, state2);
                }
                if (tempArr[0].equals("TallDoubleDesk")) {
                    addTallDoubleDesk(x, y, state1, state2);
                }
                if (tempArr[0].equals("WideTripleDesk")) {
                    addWideTripleDesk(x, y, state1, state2, state3);
                }
                if (tempArr[0].equals("TallTripleDesk")) {
                    addTallTripleDesk(x, y, state1, state2, state3);
                }
                if (tempArr[0].equals("WideFourLongDesk")) {
                    addWideFourDesk(x, y, state1, state2, state3, state4);
                }
                if (tempArr[0].equals("TallFourLongDesk")) {
                    addTallFourDesk(x, y, state1, state2, state3, state4);
                }
                if (tempArr[0].equals("FourSquareDesk")) {
                    addFourSquareDesk(x, y, state1, state2, state3, state4);
                }

            }
            temp = br.readLine();
        }

    }

    public void newWindow() {
        checkBoxCounter = 0;
        checkBoxesList.clear();
        removeAll();
        panels.clear();
        repaint();

    }

    public void undoSeat() {
        if (panels.size() == 0) {
            //Do Nothing
        } else {

            String name = panels.get(panels.size() - 1).getName();

            if (checkBoxesList.size() > 0) {

                if (name.equals("SingleDesk")) {
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                } else if (name.equals("WideDoubleDesk")||name.equals("TallDoubleDesk")) {
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                } else if (name.equals("WideTripleDesk")||name.equals("TallTripleDesk")) {
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                } else if (name.equals("FourLongDesk") || name.equals("FourSquareDesk")) {
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                    checkBoxesList.remove(checkBoxesList.size() - 1);
                } else {
                }
            }
        }

        remove(panels.get(panels.size() - 1));
        panels.remove(panels.size() - 1);
        renameCheckBoxes();
        repaint();
    }

    String position = "West";

    // Desk Types
    public void addSingleDesk(int x, int y, boolean state) {

        startingX = x;
        startingY = y;
        JPanel singleDeskPanel = new JPanel();
        singleDeskPanel.setLayout(new BorderLayout());


        JCheckBox checkBox = new JCheckBox();
        makeCheckBox(checkBox,state);



//        ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
//        Image imageNew = iconNew.getImage();
//        Image newImgNew = imageNew.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);

        icon.setOpaque(true);

        icon.setBounds(0, 0, 100, 100);

        singleDeskPanel.add(checkBox, BorderLayout.WEST);
        singleDeskPanel.add(icon, BorderLayout.CENTER);
        singleDeskPanel.setVisible(true);
        singleDeskPanel.validate();
        checkBox.setOpaque(false);
        icon.setOpaque(false);
        singleDeskPanel.setOpaque(false);
        add(singleDeskPanel);
        singleDeskPanel.setBounds(startingX, startingY, icon.getWidth(), icon.getHeight());

        singleDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x, jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        singleDeskPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                singleDeskPanel.removeAll();
                if (position.equals("West")) {
                    singleDeskPanel.add(checkBox, BorderLayout.NORTH);
                    position = "North";
                } else if (position.equals("North")) {
                    singleDeskPanel.add(checkBox, BorderLayout.EAST);
                    position = "East";
                } else if (position.equals("East")) {
                    singleDeskPanel.add(checkBox, BorderLayout.SOUTH);
                    position = "South";
                } else {
                    singleDeskPanel.add(checkBox, BorderLayout.WEST);
                    position = "West";
                }
                singleDeskPanel.add(icon, BorderLayout.CENTER);
                repaint();
                validate();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                if (e.getSource() == singleDeskPanel) {
                    drag = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
                try {
                    update();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        renameCheckBoxes();
        singleDeskPanel.setName("SingleDesk");
        panels.add(singleDeskPanel);
        singleDeskPanel.validate();
        // panel.revalidate();
        singleDeskPanel.setVisible(true);

        setVisible(true);

    }

    String positionWideDoubleDesk = "South";


    public void addWideDoubleDesk(int x, int y, boolean state1, boolean state2) {
        startingX = x;
        startingY = y;
        JPanel doubleDeskPanel = new JPanel();
        doubleDeskPanel.setLayout(new BorderLayout());


        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.LINE_AXIS));


        JCheckBox checkBox1 = new JCheckBox();
        makeCheckBox(checkBox1,state1);

        JCheckBox checkBox2 = new JCheckBox();
        makeCheckBox(checkBox2,state2);

        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox1);
        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox2);
        checkBoxes.add(Box.createHorizontalGlue());



   //     ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
   //     Image imageNew = iconNew.getImage();
   //     Image newImgNew = imageNew.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);

        icon.setOpaque(true);

        icon.setBounds(0, 0, 100, 75);

        doubleDeskPanel.add(checkBoxes, BorderLayout.SOUTH);

        doubleDeskPanel.add(icon, BorderLayout.CENTER);
        doubleDeskPanel.setVisible(true);
        doubleDeskPanel.validate();

        checkBoxes.setOpaque(false);
        icon.setOpaque(false);
        doubleDeskPanel.setOpaque(false);
        add(doubleDeskPanel);
        doubleDeskPanel.setBounds(startingX, startingY, icon.getWidth(), icon.getHeight());

        doubleDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x, jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        doubleDeskPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doubleDeskPanel.removeAll();
                if (positionWideDoubleDesk.equals("South")) {
                    doubleDeskPanel.add(checkBoxes, BorderLayout.NORTH);
                    positionWideDoubleDesk = "North";
                } else {
                    doubleDeskPanel.add(checkBoxes, BorderLayout.SOUTH);
                    positionWideDoubleDesk = "South";
                }
                doubleDeskPanel.add(icon, BorderLayout.CENTER);
                repaint();
                validate();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();

                if (e.getSource() == doubleDeskPanel) {
                    drag = true;
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        renameCheckBoxes();
        doubleDeskPanel.setName("WideDoubleDesk");
        panels.add(doubleDeskPanel);
        doubleDeskPanel.validate();
        doubleDeskPanel.setVisible(true);

        setVisible(true);

    }

    String positionTallDoubleDesk = "West";
    public void addTallDoubleDesk(int x, int y, boolean state1, boolean state2) {
        startingX = x;
        startingY = y;

        JPanel doubleDeskPanel = new JPanel();
        doubleDeskPanel.setLayout(new BorderLayout());


        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));


        JCheckBox checkBox1 = new JCheckBox();
        makeCheckBox(checkBox1,state1);

        JCheckBox checkBox2 = new JCheckBox();
        makeCheckBox(checkBox2,state2);

        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox1);
        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox2);
        checkBoxes.add(Box.createVerticalGlue());



        //     ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
        //     Image imageNew = iconNew.getImage();
        //     Image newImgNew = imageNew.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);

        icon.setOpaque(true);

        icon.setBounds(0, 0, 90, 100);

        doubleDeskPanel.add(checkBoxes, BorderLayout.WEST);

        doubleDeskPanel.add(icon, BorderLayout.EAST);
        doubleDeskPanel.setVisible(true);
        doubleDeskPanel.validate();

        checkBoxes.setOpaque(false);
        icon.setOpaque(false);
        doubleDeskPanel.setOpaque(false);
        add(doubleDeskPanel);
        doubleDeskPanel.setBounds(startingX, startingY, icon.getWidth(), icon.getHeight());

        doubleDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x, jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        doubleDeskPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doubleDeskPanel.removeAll();
                if (positionTallDoubleDesk.equals("West")) {
                    doubleDeskPanel.add(checkBoxes, BorderLayout.EAST);
                    doubleDeskPanel.add(icon, BorderLayout.WEST);
                    positionTallDoubleDesk = "East";
                } else {
                    doubleDeskPanel.add(checkBoxes, BorderLayout.WEST);
                    doubleDeskPanel.add(icon, BorderLayout.EAST);
                    positionTallDoubleDesk = "West";
                }
                repaint();
                validate();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();

                if (e.getSource() == doubleDeskPanel) {
                    drag = true;
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        renameCheckBoxes();
        doubleDeskPanel.setName("TallDoubleDesk");
        panels.add(doubleDeskPanel);
        doubleDeskPanel.validate();
        doubleDeskPanel.setVisible(true);

        setVisible(true);

    }
    String wideTripleDesk = "South";
    public void addWideTripleDesk(int x, int y, boolean state1, boolean state2, boolean state3) {
        startingX = x;
        startingY = y;
        JPanel wideTripleDeskPanel = new JPanel();
        wideTripleDeskPanel.setLayout(new BorderLayout());


        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.LINE_AXIS));


        JCheckBox checkBox1 = new JCheckBox();
        makeCheckBox(checkBox1,state1);

        JCheckBox checkBox2 = new JCheckBox();
        makeCheckBox(checkBox2,state2);

        JCheckBox checkBox3 = new JCheckBox();
        makeCheckBox(checkBox3,state3);

        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox1);
        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox2);
        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox3);
        checkBoxes.add(Box.createHorizontalGlue());

        //ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
        //Image imageNew = iconNew.getImage();
        //Image newImgNew = imageNew.getScaledInstance(150, 50, java.awt.Image.SCALE_SMOOTH);
       // iconNew = new ImageIcon(newImgNew);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(150, 50, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);

        icon.setOpaque(true);

        icon.setBounds(0, 0, 150, 75);

        wideTripleDeskPanel.add(checkBoxes, BorderLayout.SOUTH);

        wideTripleDeskPanel.add(icon, BorderLayout.CENTER);
        wideTripleDeskPanel.setVisible(true);
        wideTripleDeskPanel.validate();

        checkBoxes.setOpaque(false);
        icon.setOpaque(false);
        wideTripleDeskPanel.setOpaque(false);
        add(wideTripleDeskPanel);
        wideTripleDeskPanel.setBounds(startingX, startingY, icon.getWidth(), icon.getHeight());

        wideTripleDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x, jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        wideTripleDeskPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wideTripleDeskPanel.removeAll();
                if (wideTripleDesk.equals("South")) {
                    wideTripleDeskPanel.add(checkBoxes, BorderLayout.NORTH);
                    wideTripleDesk = "North";
                } else {
                    wideTripleDeskPanel.add(checkBoxes, BorderLayout.SOUTH);
                    wideTripleDesk = "South";
                }
                wideTripleDeskPanel.add(icon, BorderLayout.CENTER);
                repaint();
                validate();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                if (e.getSource() == wideTripleDeskPanel) {
                    drag = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        renameCheckBoxes();
        wideTripleDeskPanel.setName("WideTripleDesk");
        panels.add(wideTripleDeskPanel);
        wideTripleDeskPanel.validate();
        // panel.revalidate();
        wideTripleDeskPanel.setVisible(true);

        setVisible(true);

    }

    String TallTripleDesk = "West";
    public void addTallTripleDesk(int x, int y, boolean state1, boolean state2, boolean state3) {
        startingX = x;
        startingY = y;
        JPanel tripleTallDeskPanel = new JPanel();
        tripleTallDeskPanel.setLayout(new BorderLayout());


        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));


        JCheckBox checkBox1 = new JCheckBox();
        makeCheckBox(checkBox1,state1);

        JCheckBox checkBox2 = new JCheckBox();
        makeCheckBox(checkBox2,state2);

        JCheckBox checkBox3 = new JCheckBox();
        makeCheckBox(checkBox3,state3);

        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox1);
        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox2);
        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox3);
        checkBoxes.add(Box.createVerticalGlue());

        //ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
        //Image imageNew = iconNew.getImage();
        //Image newImgNew = imageNew.getScaledInstance(150, 50, java.awt.Image.SCALE_SMOOTH);
        // iconNew = new ImageIcon(newImgNew);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(50, 150, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);

        icon.setOpaque(true);

        icon.setBounds(0, 0, 90, 150);

        tripleTallDeskPanel.add(checkBoxes, BorderLayout.WEST);

        tripleTallDeskPanel.add(icon, BorderLayout.EAST);
        tripleTallDeskPanel.setVisible(true);
        tripleTallDeskPanel.validate();

        checkBoxes.setOpaque(false);
        icon.setOpaque(false);
        tripleTallDeskPanel.setOpaque(false);
        add(tripleTallDeskPanel);
        tripleTallDeskPanel.setBounds(startingX, startingY, icon.getWidth(), icon.getHeight());

        tripleTallDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x, jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        tripleTallDeskPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tripleTallDeskPanel.removeAll();
                if (TallTripleDesk.equals("West")) {
                    tripleTallDeskPanel.add(checkBoxes, BorderLayout.EAST);
                    tripleTallDeskPanel.add(icon, BorderLayout.WEST);
                    TallTripleDesk = "East";
                } else {
                    tripleTallDeskPanel.add(checkBoxes, BorderLayout.WEST);
                    tripleTallDeskPanel.add(icon, BorderLayout.EAST);
                    TallTripleDesk = "West";
                }
                repaint();
                validate();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                if (e.getSource() == tripleTallDeskPanel) {
                    drag = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        renameCheckBoxes();
        tripleTallDeskPanel.setName("TallTripleDesk");
        panels.add(tripleTallDeskPanel);
        tripleTallDeskPanel.validate();
        // panel.revalidate();
        tripleTallDeskPanel.setVisible(true);

        setVisible(true);

    }

    String wideFourDesk = "South";
    public void addWideFourDesk(int x, int y, boolean state1, boolean state2, boolean state3, boolean state4) {
        startingX = x;
        startingY = y;
        JPanel fourDeskPanel = new JPanel();
        fourDeskPanel.setLayout(new BorderLayout());


        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.LINE_AXIS));

        JCheckBox checkBox5 = new JCheckBox();
        checkBox5.setVisible(false);
        JCheckBox checkBox6 = new JCheckBox();
        checkBox6.setVisible(false);

        JCheckBox checkBox1 = new JCheckBox();
        makeCheckBox(checkBox1,state1);

        JCheckBox checkBox2 = new JCheckBox();
        makeCheckBox(checkBox2,state2);

        JCheckBox checkBox3 = new JCheckBox();
        makeCheckBox(checkBox3,state3);

        JCheckBox checkBox4 = new JCheckBox();
        makeCheckBox(checkBox4,state4);


        checkBoxes.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox1);
        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox2);
        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox3);
        checkBoxes.add(Box.createHorizontalGlue());
        checkBoxes.add(checkBox4);
        checkBoxes.add(Box.createHorizontalGlue());

        //ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
       // Image imageNew = iconNew.getImage();
       // Image newImgNew = imageNew.getScaledInstance(200, 50, java.awt.Image.SCALE_SMOOTH);
       // iconNew = new ImageIcon(newImgNew);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(200, 50, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);


        icon.setOpaque(true);

        icon.setBounds(0, 0, 200, 75);


        checkBox2.setHorizontalAlignment(JCheckBox.CENTER);
        fourDeskPanel.add(checkBoxes, BorderLayout.SOUTH);
        icon.setHorizontalAlignment(JLabel.CENTER);
        icon.setVerticalAlignment(JLabel.CENTER);
        fourDeskPanel.add(icon, BorderLayout.CENTER);

        fourDeskPanel.setVisible(true);
        fourDeskPanel.validate();
        checkBox1.setOpaque(false);
        checkBox2.setOpaque(false);
        checkBox3.setOpaque(false);
        checkBox4.setOpaque(false);
        checkBoxes.setOpaque(false);
        icon.setOpaque(false);
        fourDeskPanel.setOpaque(false);
        add(fourDeskPanel);
        fourDeskPanel.setBounds(startingX, startingY, icon.getWidth() + 100, icon.getHeight());

        fourDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x, jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        fourDeskPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fourDeskPanel.removeAll();
                if (wideFourDesk.equals("South")) {
                    fourDeskPanel.add(checkBoxes, BorderLayout.NORTH);
                    wideFourDesk = "North";
                } else {
                    fourDeskPanel.add(checkBoxes, BorderLayout.SOUTH);
                    wideFourDesk = "South";
                }
                fourDeskPanel.add(icon, BorderLayout.CENTER);
                repaint();
                validate();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();

                if (e.getSource() == fourDeskPanel) {
                    drag = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        renameCheckBoxes();
        fourDeskPanel.setName("WideFourLongDesk");
        panels.add(fourDeskPanel);
        fourDeskPanel.validate();
        // panel.revalidate();
        fourDeskPanel.setVisible(true);

        setVisible(true);

    }

    String TallFourDesk = "West";
    public void addTallFourDesk(int x, int y, boolean state1, boolean state2, boolean state3, boolean state4) {
        startingX = x;
        startingY = y;
        JPanel fourDeskPanel = new JPanel();
        fourDeskPanel.setLayout(new BorderLayout());


        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));

        JCheckBox checkBox5 = new JCheckBox();
        checkBox5.setVisible(false);
        JCheckBox checkBox6 = new JCheckBox();
        checkBox6.setVisible(false);

        JCheckBox checkBox1 = new JCheckBox();
        makeCheckBox(checkBox1,state1);

        JCheckBox checkBox2 = new JCheckBox();
        makeCheckBox(checkBox2,state2);

        JCheckBox checkBox3 = new JCheckBox();
        makeCheckBox(checkBox3,state3);

        JCheckBox checkBox4 = new JCheckBox();
        makeCheckBox(checkBox4,state4);


        checkBoxes.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox1);
        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox2);
        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox3);
        checkBoxes.add(Box.createVerticalGlue());
        checkBoxes.add(checkBox4);
        checkBoxes.add(Box.createVerticalGlue());

        //ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
        // Image imageNew = iconNew.getImage();
        // Image newImgNew = imageNew.getScaledInstance(200, 50, java.awt.Image.SCALE_SMOOTH);
        // iconNew = new ImageIcon(newImgNew);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(50, 200, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);


        icon.setOpaque(true);

        icon.setBounds(0, 0, 50, 200);


        checkBox2.setHorizontalAlignment(JCheckBox.CENTER);
        fourDeskPanel.add(checkBoxes, BorderLayout.WEST);
        icon.setHorizontalAlignment(JLabel.CENTER);
        icon.setVerticalAlignment(JLabel.CENTER);
        fourDeskPanel.add(icon, BorderLayout.EAST);

        fourDeskPanel.setVisible(true);
        fourDeskPanel.validate();
        checkBox1.setOpaque(false);
        checkBox2.setOpaque(false);
        checkBox3.setOpaque(false);
        checkBox4.setOpaque(false);
        checkBoxes.setOpaque(false);
        icon.setOpaque(false);
        fourDeskPanel.setOpaque(false);
        add(fourDeskPanel);
        fourDeskPanel.setBounds(startingX, startingY, icon.getWidth() + 90, icon.getHeight());

        fourDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x, jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        fourDeskPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fourDeskPanel.removeAll();
                if (TallFourDesk.equals("West")) {
                    fourDeskPanel.add(checkBoxes, BorderLayout.EAST);
                    fourDeskPanel.add(icon, BorderLayout.WEST);
                    TallFourDesk = "East";
                } else {
                    fourDeskPanel.add(checkBoxes, BorderLayout.WEST);
                    fourDeskPanel.add(icon, BorderLayout.EAST);
                    TallFourDesk = "West";
                }

                repaint();
                validate();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();

                if (e.getSource() == fourDeskPanel) {
                    drag = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        renameCheckBoxes();
        fourDeskPanel.setName("TallFourLongDesk");
        panels.add(fourDeskPanel);
        fourDeskPanel.validate();
        // panel.revalidate();
        fourDeskPanel.setVisible(true);

        setVisible(true);

    }

    public void addFourSquareDesk(int x, int y, boolean state1, boolean state2, boolean state3, boolean state4) {
        startingX = x;
        startingY = y;

        JCheckBox checkBox1 = new JCheckBox();
        makeCheckBox(checkBox1,state1);

        JCheckBox checkBox2 = new JCheckBox();
        makeCheckBox(checkBox2,state2);

        JCheckBox checkBox3 = new JCheckBox();
        makeCheckBox(checkBox3,state3);

        JCheckBox checkBox4 = new JCheckBox();
        makeCheckBox(checkBox4,state4);


        //ImageIcon iconNew = new ImageIcon("src/ClassroomApp/square.png");
        //Image imageNew = iconNew.getImage();
        //Image newImgNew = imageNew.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
       // iconNew = new ImageIcon(newImgNew);

        URL url = ClassLoader.getSystemClassLoader().getResource("square.png");
        ImageIcon iconI = new ImageIcon(url);
        Image imageNew = iconI.getImage();
        Image newImgNew = imageNew.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        iconI = new ImageIcon(newImgNew);

        JLabel icon = new JLabel();
        icon.setIcon(iconI);
        icon.setOpaque(true);
        icon.setBounds(0, 0, 100, 100);

        JPanel fourSquareDeskPanel = new JPanel();
        fourSquareDeskPanel.setLayout(new BorderLayout());


        fourSquareDeskPanel.add(checkBox1, BorderLayout.WEST);
        fourSquareDeskPanel.add(checkBox2, BorderLayout.NORTH);
        fourSquareDeskPanel.add(checkBox3, BorderLayout.EAST);
        fourSquareDeskPanel.add(checkBox4, BorderLayout.SOUTH);
        checkBox2.setHorizontalAlignment(JCheckBox.CENTER);
        checkBox4.setHorizontalAlignment(JCheckBox.CENTER);

        fourSquareDeskPanel.add(icon, BorderLayout.CENTER);
        icon.setHorizontalAlignment(JLabel.CENTER);
        icon.setVerticalAlignment(JLabel.CENTER);


        fourSquareDeskPanel.setVisible(true);
        fourSquareDeskPanel.validate();
        checkBox1.setOpaque(false);
        checkBox2.setOpaque(false);
        checkBox3.setOpaque(false);
        checkBox4.setOpaque(false);
        icon.setOpaque(false);
        fourSquareDeskPanel.setOpaque(false);
        add(fourSquareDeskPanel);

        fourSquareDeskPanel.setBounds(startingX, startingY, icon.getWidth() + 50, icon.getHeight() + 50);
        fourSquareDeskPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == true) {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX() - mousePt.x , jc.getY() + e.getY() - mousePt.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        fourSquareDeskPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();

                if (e.getSource() == fourSquareDeskPanel) {
                    drag = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        fourSquareDeskPanel.setSize(160,150);
        fourSquareDeskPanel.setFocusable(false);
        renameCheckBoxes();
        fourSquareDeskPanel.setName("FourSquareDesk");
        panels.add(fourSquareDeskPanel);
        fourSquareDeskPanel.validate();
        fourSquareDeskPanel.setVisible(true);
        setVisible(true);

    }

    // Class Shape
    private int width = 0;
    private int height = 0;
    private int widthPadding = 0;
    private int heightPadding = 0;

    void drawRect(int width, int height) {
        this.width = width;
        this.height = height;
        widthPadding = (getWidth() - width) / 2;
        heightPadding = (getHeight() - height) / 2;
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.black);
        float thickness = 8;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect(widthPadding, heightPadding, width, height);
    }
}