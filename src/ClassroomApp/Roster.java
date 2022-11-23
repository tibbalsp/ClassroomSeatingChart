package ClassroomApp;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Roster extends JPanel{
    private JPanel rosterPanel = new JPanel();
    private DeskGenerator deskGenerator;
    private ArrayList<JCheckBox> checkBoxes;
    private ArrayList<JCheckBox> holdCheckBoxes = new ArrayList<>();
    private ArrayList<JLabel> labels = new ArrayList<>(100);
    private ArrayList<JTextField> jTextFields = new ArrayList<>(100);
    private PrintWriter writer;
    private String parentFolder = "";
    public Roster(){
        setVisible(true);
    }

    public void setDeskGenerator(DeskGenerator dg,String folder){
        parentFolder = folder;
        deskGenerator = dg;
        rosterPanel.setLayout(new BoxLayout(rosterPanel,BoxLayout.Y_AXIS));
        rosterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buildRoster();
    }

    public void addSeat(ArrayList<JCheckBox> jCheckBoxArrayList,int checkBoxCounter){
        checkBoxes = jCheckBoxArrayList;

        JTextField jTextField = new JTextField("",25);
        jTextFields.add(jTextField);

        JPanel seatAndCheckBox = new JPanel(new GridLayout(1,2));

        JLabel label = new JLabel("Seat: " + String.valueOf(checkBoxCounter), SwingConstants.LEFT);

        JCheckBox holdFromShuffle = new JCheckBox();
        holdFromShuffle.setText("Omit from shuffle: ");
        holdFromShuffle.setHorizontalTextPosition(SwingConstants.LEFT);
        holdFromShuffle.setName(String.valueOf(checkBoxes.size()+1));
        holdFromShuffle.setSelected(false);
        holdFromShuffle.setVisible(true);


        holdCheckBoxes.add(holdFromShuffle);

        labels.add(label);

        seatAndCheckBox.add(label);
        seatAndCheckBox.add(holdFromShuffle);

        rosterPanel.add(seatAndCheckBox);
        rosterPanel.add(jTextField);

    }
    public void removeSeat(){
        Component[] components =  rosterPanel.getComponents();
        rosterPanel.remove(components[components.length-1]);
        rosterPanel.remove(components[components.length-2]);
        validate();
        repaint();
    }

    void buildRoster(){
        generateView();
        for (JCheckBox checkBox: checkBoxes) {
            JPanel seatAndCheckBox = new JPanel(new GridLayout(1,2));
            JTextField jTextField = new JTextField("",25);
            jTextFields.add(jTextField);
            JLabel label = new JLabel("Seat: "+checkBox.getName(), SwingConstants.LEFT);


            JCheckBox holdFromShuffle = new JCheckBox();
            holdFromShuffle.setText("Omit from shuffle: ");
            holdFromShuffle.setHorizontalTextPosition(SwingConstants.LEFT);
            holdFromShuffle.setName(checkBox.getName());
            holdFromShuffle.setSelected(false);
            holdFromShuffle.setVisible(true);

            holdCheckBoxes.add(holdFromShuffle);

            labels.add(label);

            seatAndCheckBox.add(label);
            seatAndCheckBox.add(holdFromShuffle);

            rosterPanel.add(seatAndCheckBox);
           // rosterPanel.add(holdFromShuffle);
            rosterPanel.add(jTextField);
        }
        rosterPanel.repaint();
        add(rosterPanel);
        repaint();
        setVisible(true);
        validate();
    }

    void shuffleRoster() {
        Map<Integer,String> temp = new HashMap<>();
        int offset=1;
        if(holdCheckBoxes != null && holdCheckBoxes.size() != 0){
            for (int i = 0; i < holdCheckBoxes.size(); i++) {
                if(holdCheckBoxes.get(i).isSelected() == true && !jTextFields.get(Integer.parseInt(holdCheckBoxes.get(i).getName())-1).getText().equals("")) {
                    temp.put(Integer.parseInt(holdCheckBoxes.get(i).getName())-1, jTextFields.get(Integer.parseInt(holdCheckBoxes.get(i).getName())-1).getText());
                    offset++;
                }
            }
        }
        ArrayList<String> holdNames = new ArrayList<>(50);
        for (JTextField jtf :
                jTextFields) {
            System.out.print(jtf.getText());

        }
        System.out.println("");
        Collections.shuffle(jTextFields);

        for (JTextField jtf :
                jTextFields) {
            System.out.print(jtf.getText());

        }
        String[] strArr = new String[jTextFields.size()];
        int index = 0;
        int jTextFieldCount = 0;
        while(index < strArr.length){
            if(temp.containsKey(index)) {
                strArr[index] = temp.remove(index);
                holdNames.add(strArr[index]);
                index++;
            }else if(holdNames.contains(jTextFields.get(0).getText()) || temp.containsValue(jTextFields.get(0).getText())){
                jTextFields.remove(0);
            }else {
                if(temp.containsKey(jTextFields.get(0).getText())){
                    System.out.println("Detected");
                }else {
                    System.out.println("Not Detected");
                }
                strArr[index] = jTextFields.get(0).getText();
                jTextFields.remove(0);
                index++;
            }
        }

       /*

        int indexAdjustment = 0;
        for (int i = 0; i < jTextFields.size(); i++) {
            if(temp.containsKey(i)){
                System.out.println("I = " +i);
                strArr[i] = temp.remove(i);
                holdNames.add(strArr[i]);
                indexAdjustment = indexAdjustment+1;
                i--;
            }else{

                strArr[i+indexAdjustment] = jTextFields.get(i).getText();
            }

        }

        */
        generateView();
        jTextFields.clear();
        holdCheckBoxes.clear();
        int i=0;
        for (JCheckBox checkBox: checkBoxes) {
            JPanel seatAndCheckBox = new JPanel(new GridLayout(1,2));

            JTextField jTextField = new JTextField(strArr[i], 25);
            jTextFields.add(jTextField);

            JLabel label = new JLabel("Seat: "+checkBox.getName(), SwingConstants.LEFT);


            JCheckBox holdFromShuffle = new JCheckBox();
            holdFromShuffle.setText("Omit from shuffle: ");
            holdFromShuffle.setHorizontalTextPosition(SwingConstants.LEFT);
            holdFromShuffle.setName(checkBox.getName());
            holdFromShuffle.setSelected(false);
            holdFromShuffle.setVisible(true);

            for(int j=0 ; j < holdNames.size() ; j++){
                if(strArr[i].equals(holdNames.get(j))){
                    holdFromShuffle.setSelected(true);
                }
            }


            holdCheckBoxes.add(holdFromShuffle);

            labels.add(label);

            seatAndCheckBox.add(label);
            seatAndCheckBox.add(holdFromShuffle);

            rosterPanel.add(seatAndCheckBox);
            rosterPanel.add(jTextField);
            i++;
        }
        rosterPanel.repaint();
        add(rosterPanel);
        repaint();
        setVisible(true);
        validate();
    }

    private void generateView() {
        Component[] components = rosterPanel.getComponents();
        for (Component component:components){
            rosterPanel.remove(component);
        }
        rosterPanel.setLayout(new BoxLayout(rosterPanel,BoxLayout.Y_AXIS));
        checkBoxes = deskGenerator.getCheckBoxList();

        JButton shuffle = new JButton("Shuffle");
        JButton clearRoster = new JButton("Clear Roster");
        JButton saveRoster = new JButton("Save Roster");
        JButton loadRoster = new JButton("Load Roster");

        JPanel jPanel = new JPanel(new GridLayout(2,2));

        jPanel.add(clearRoster);
        jPanel.add(shuffle);
        jPanel.add(saveRoster);
        jPanel.add(loadRoster);
        rosterPanel.add(jPanel);

        shuffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleRoster();
            }
        });

        clearRoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyRoster();
            }
        });
        saveRoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveRoster();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        });
        loadRoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser(parentFolder + "/Roster Saves");
                    FileFilter filter = new FileNameExtensionFilter("Text File", "txt");
                    fileChooser.setFileFilter(filter);
                    fileChooser.showOpenDialog(loadRoster);
                    File fileName = new File(fileChooser.getSelectedFile().toString());
                    loadRoster(fileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });

    }

    void emptyRoster(){
        jTextFields.clear();
        buildRoster();
    }
    void saveRoster() throws FileNotFoundException, UnsupportedEncodingException {
        String temp;
        String fileName = JOptionPane.showInputDialog("Name this file");
        writer = new PrintWriter(parentFolder+"/Roster Saves/" + fileName + ".txt", String.valueOf(StandardCharsets.UTF_8));

        for (JTextField jTextField: jTextFields){
               temp = jTextField.getText();
               writer.println(temp);
        }
        writer.close();
    }
    private String fileName;
    void loadRoster(File file) throws IOException {
        fileName = file.getName();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = br.readLine();
        for (JTextField jTextField: jTextFields){
            if( temp != null) {
                jTextField.setText(temp);
                temp = br.readLine();
            }else {
                break;
            }
        }
    }
}
