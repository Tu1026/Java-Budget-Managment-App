package ui.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private GridBagConstraints gbc = new GridBagConstraints();

    public MenuPanel() {
        setLayout(new GridBagLayout());

        b1 = new JButton("Manage Categories");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(b1,gbc);
        b1.setActionCommand("categories");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().categoryState();
            }
        });

        b2 = new JButton("Manage savings");
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(b2, gbc);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().savingsState();
            }
        });

        b3 = new JButton("Manage all goals");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(b3,gbc);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().goalsState();
            }
        });

    }
}
