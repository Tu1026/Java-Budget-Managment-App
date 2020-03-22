package ui.GUI;

import model.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalsPanel extends JPanel {
    private JButton b1;
    private JButton b2;

    public GoalsPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Manage Goals"));

        JLabel nameOfItem = new JLabel("Name of Goal: ");
        JLabel costOfGoal = new JLabel("Cost of Goal: ");
        JLabel desireOfGoal = new JLabel("Desire for Goal (1-10): ");

        JTextField nameField = new JTextField(10);
        JTextField costField = new JTextField(10);
        JTextField desireField = new JTextField(10);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(nameOfItem, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(costOfGoal, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(desireOfGoal, gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        add(nameField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(costField, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(desireField, gc);

        b1 = new JButton("Add to Goals");
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(b1, gc);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().equals("") && !costField.getText().equals("")
                        && !desireField.getText().equals("")) {
                    String nameOfGoal = nameField.getText();
                    Double costOfGoal = Double.parseDouble(costField.getText());
                    Integer desireOfGoal = Integer.parseInt(desireField.getText());
                    makeNewGoals(nameOfGoal,costOfGoal,desireOfGoal);
                }
                MainFrame.getInstance().goalsState();
            }
        });

        final JTextArea notice = new JTextArea();
        notice.append("You need to fill in all three text columns before using"
                + "the add button" + "\n" + "Desire point has to be a whole number between 1-10");
        Font font = new Font("Times new Roman", Font.BOLD, 20);
        notice.setFont(font);
        notice.setLineWrap(true);
        notice.setWrapStyleWord(true);
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.gridy = 4;
        add(notice, gc);


        b2 = new JButton("Go back to main menu");
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 5;
        add(b2, gc);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changePanel(new MenuPanel());
            }
        });
    }

    public void makeNewGoals(String n, Double c, Integer d) {
        Goal newGoal = new Goal(n, c, d);
        MainFrame.getInstance().getGoals().addToGoals(newGoal);
    }
}
