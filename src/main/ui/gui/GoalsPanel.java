package ui.gui;

import exception.NameNotValidException;
import model.Goal;

import javax.swing.*;
import java.awt.*;

public class GoalsPanel extends JPanel {
    private GridBagConstraints gc = new GridBagConstraints();
    private JTextField nameField = new JTextField(10);
    private JTextField costField = new JTextField(10);
    private JTextField desireField = new JTextField(10);

    public GoalsPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Manage Goals"));
        setLayout(new GridBagLayout());
        makeLabels();
        makeFields();
        makeAddToGoalsButton();
        makeTextArea();
        makeBackToMenuButton();
        makeNewDeleteButton();
        makeNewDeleteFirstButton();
    }

    private void makeBackToMenuButton() {
        JButton b2 = new JButton("Go back to main menu");
        b2.setToolTipText("Hit this button to go back to menu");
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 5;
        add(b2, gc);
        b2.addActionListener(e -> MainFrame.getInstance().changePanel(new MenuPanel()));
    }

    private void makeTextArea() {
        final JTextArea notice = new JTextArea();
        notice.append("You need to fill in all three text columns before using"
                + "the add button" + "\n" + "Desire point has to be a whole number between 1-10 \n"
                + "To delete just input the name of goal you want to delete and hit the delete button \n"
                + "***Most Importantly don't forget to SAVE your progress with the upper left menu");
        Font font = new Font("Times new Roman", Font.BOLD, 20);
        notice.setFont(font);
        notice.setLineWrap(true);
        notice.setWrapStyleWord(true);
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.gridy = 4;
        add(notice, gc);

    }

    private void makeAddToGoalsButton() {
        JButton b1 = new JButton("Add to Goals");
        b1.setToolTipText("Hit this button after inputting a name, cost, and desire point to add a new goal");
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(b1, gc);
        b1.addActionListener(e -> {
            if (!nameField.getText().equals("") && !costField.getText().equals("")
                    && !desireField.getText().equals("")) {
                String nameOfGoal = nameField.getText();
                try {
                    Double costOfGoal = Double.parseDouble(costField.getText());
                    Integer desireOfGoal = Integer.parseInt(desireField.getText());
                    makeNewGoals(nameOfGoal, costOfGoal, desireOfGoal);
                    MainFrame.getInstance().goalsState();
                } catch (NumberFormatException e2) {
                    errorCostMessage();
                }
            }
        });
    }

    public void errorCostMessage() {
        String message = "Only positive numeric number for cost!!!";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void makeFields() {
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weighty = 2;
        gc.weightx = 2;
        gc.gridx = 1;
        gc.gridy = 0;
        add(nameField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(costField, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(desireField, gc);
    }

    public void makeLabels() {
        JLabel nameOfItem = new JLabel("Name of Goal: ");
        JLabel costOfGoal = new JLabel("Cost of Goal: ");
        JLabel desireOfGoal = new JLabel("Desire for Goal (1-10): ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 2;
        gc.weighty = 2;
        gc.gridx = 0;
        gc.gridy = 0;
        add(nameOfItem, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(costOfGoal, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(desireOfGoal, gc);
    }

    public void makeNewGoals(String n, Double c, Integer d) {
        Goal newGoal = new Goal(n, c, d);
        MainFrame.getInstance().getGoals().addToGoals(newGoal);
    }

    public void makeNewDeleteButton() {
        JButton deleteButton = new JButton("Delete a Goal");
        deleteButton.setToolTipText("Hit this button after inputting the name of the goal that you want to delete"
                + "in the name field");
        gc.weighty = 0.5;
        gc.weightx = 0.5;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.BOTH;
        gc.gridheight = 2;
        add(deleteButton, gc);
        deleteButton.addActionListener(e -> {
            if (!nameField.getText().equals("")) {
                String remover = nameField.getText();
                try {
                    MainFrame.getInstance().getGoals().removeGivenGoal(remover);
                    MainFrame.getInstance().goalsState();
                } catch (NameNotValidException e2) {
                    noSuchGoalError();
                }
            }
        });

    }

    public void noSuchGoalError() {
        String message = "No goal with that name was found";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void makeNewDeleteFirstButton() {
        JButton deleteFirstButton = new JButton("Delete the first Goal");
        deleteFirstButton.setToolTipText("Delete the first goal in the goals");
        gc.weighty = 0.5;
        gc.weightx = 0.5;
        gc.gridx = 0;
        gc.gridy = 5;
        gc.fill = GridBagConstraints.BOTH;
        add(deleteFirstButton, gc);
        deleteFirstButton.addActionListener(e -> {
            try {
                MainFrame.getInstance().getGoals().removeIthGoal(1);
                MainFrame.getInstance().goalsState();
            } catch (IndexOutOfBoundsException ex) {
                //do nothing
            }
        });
    }
}
