package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingsPanel extends JPanel {
    private JTextField moneyField;
    private JTextField interestField;
    private GridBagConstraints gc = new GridBagConstraints();

    public SavingsPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Manage Savings"));

        setLayout(new GridBagLayout());
        makeLabels();
        makeTextFields();
        makeAddButton();
        makeBackToMenuButton();
    }

    private void makeBackToMenuButton() {
        JButton backToMenyButton = new JButton("Go back to main menu");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.weighty = 2;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        add(backToMenyButton, c);
        backToMenyButton.addActionListener(e -> MainFrame.getInstance().changePanel(new MenuPanel()));
    }

    public void makeLabels() {
        JLabel amountSaved = new JLabel("Money input: ");
        JLabel interestRate = new JLabel("Interest rate in decimal: ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(amountSaved, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(interestRate, gc);
    }

    public void makeTextFields() {
        moneyField = new JTextField(10);
        interestField = new JTextField(10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        add(moneyField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(interestField, gc);
    }

    public void makeAddButton() {
        JButton addButton = new JButton("Add to Savings");
        addButton.setToolTipText("Either input the amount put in (positive) or take out (negative) or a given"
                + "interest rate and hit this button");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.weighty = 2;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        add(addButton, c);
        addButton.addActionListener(this::addToSavingsAction);
    }

    public void addToSavingsAction(ActionEvent a) {
        if (!moneyField.getText().equals("")) {
            try {
                double money = Double.parseDouble(moneyField.getText());
                doSavingTransaction(money);
                moneyField.setText("");
                MainFrame.getInstance().savingsState();
            } catch (NumberFormatException e2) {
                String message = "Input money has to be a numeric number";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!interestField.getText().equals("")) {
            try {
                double interest = Double.parseDouble(interestField.getText());
                doInterestRate(interest);
                interestField.setText("");
                MainFrame.getInstance().savingsState();
            } catch (NumberFormatException e3) {
                String message = "Input interest rate has to be a numeric decimal";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void doSavingTransaction(double m) {
        if (!MainFrame.getInstance().getSavings().savingTransaction(m)) {
            String message = "Cannot have negative balance";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void doInterestRate(double i) {
        MainFrame.getInstance().getSavings().savingInterests(i);
    }

}

