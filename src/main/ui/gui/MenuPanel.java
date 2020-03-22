package ui.gui;


import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(new GridBagLayout());
        categoryButton();
        savingsButton();
        goalsButton();
    }

    public void categoryButton() {
        GridBagConstraints gc = new GridBagConstraints();
        JButton b1 = new JButton("Manage Categories");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.weighty = 2;
        gc.weightx = 0;
        add(b1,gc);
        b1.setActionCommand("categories");
        b1.addActionListener(e -> MainFrame.getInstance().categoryState());
    }

    public void savingsButton() {
        GridBagConstraints gc = new GridBagConstraints();
        JButton b2 = new JButton("Manage savings");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = 2;
        gc.weightx = 0;
        add(b2, gc);
        b2.addActionListener(e -> MainFrame.getInstance().savingsState());
    }

    public void goalsButton() {
        GridBagConstraints gc = new GridBagConstraints();
        JButton b3 = new JButton("Manage all goals");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weighty = 2;
        gc.weightx = 0;
        add(b3,gc);
        b3.addActionListener(e -> MainFrame.getInstance().goalsState());
    }
}
