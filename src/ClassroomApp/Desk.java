package ClassroomApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Desk {
    private int startingX;
    private int startingY;
    private JPanel deskPanel;
    private int checkBoxCount = 0;
    private ArrayList<JCheckBox> checkBoxesList = new ArrayList<>(100);
    private int checkBoxCounter;
    private boolean state1,state2,state3,state4;
    private Roster roster;

    void setRoster(Roster roster){
        this.roster = roster;
    }

    public Desk( int x,int y,boolean s1){

        this.startingX = x;
        this.startingY = y;
        this.state1 = s1;
    }
    public Desk(int x,int y,boolean s1,boolean s2){
        this.startingX = x;
        this.startingY = y;
        this.state1 = s1;
        this.state2 = s2;
    }
    public Desk(int x,int y,boolean s1,boolean s2,boolean s3){
        this.startingX = x;
        this.startingY = y;
        this.state1 = s1;
        this.state2 = s2;
        this.state3 = s3;
    }
    public Desk(int x,int y,boolean s1,boolean s2,boolean s3,boolean s4){
        this.startingX = x;
        this.startingY = y;
        this.state1 = s1;
        this.state2 = s2;
        this.state3 = s3;
        this.state4 = s4;
    }
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
            checkBoxCounter++;
        }


        roster.addSeat(checkBoxesList,checkBoxCounter);
     //   update();
    }

    void makeCheckBox(JCheckBox checkBox,Boolean state){
        checkBox.setMnemonic(KeyEvent.VK_C);
        checkBox.setSelected(state);
        checkBox.setVisible(true);
        checkBox.setOpaque(false);
        checkBox.setName(String.valueOf(checkBoxCounter));
        checkBoxesList.add(checkBox);
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
                        roster.addSeat(checkBoxesList,checkBoxCounter);
                        //update();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    //Do nothing already in list
                } else {
                    try {
                        removeNameFromCheckBoxList(checkBox);
                        renameCheckBoxes();
                      //  update();


                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
    }

}
