package ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingsPanel extends JPanel {
    private JButton b1;
    private JButton b2;

    public SavingsPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Manage Savings"));

        JLabel amountSaved = new JLabel("Money input: ");
        JLabel interestRate = new JLabel("Interest in decimal: ");

        JTextField moneyField = new JTextField(10);
        JTextField interestField = new JTextField(10);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        add(amountSaved, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(interestRate,gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        add(moneyField,gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(interestField,gc);

        b1 = new JButton("Add to Savings");
        gc.fill = GridBagConstraints.VERTICAL;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(b1, gc);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!moneyField.getText().equals("")) {
                    Double money = Double.parseDouble(moneyField.getText());
                    doSavingTransaction(money);
                    moneyField.setText("");
                }
                if (!interestField.getText().equals("")) {
                    Double interest = Double.parseDouble(interestField.getText());
                    doInterestRate(interest);
                    interestField.setText("");
                }
                MainFrame.getInstance().savingsState();
            }
        });

        b2 = new JButton("Go back to main menu");
        gc.fill = GridBagConstraints.VERTICAL;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.gridy = 3;
        add(b2, gc);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changePanel(new MenuPanel());
            }
        });
    }

    public void doSavingTransaction(double m) {
        MainFrame.getInstance().getSavings().savingTransaction(m);
    }

    public void doInterestRate(double i) {
        MainFrame.getInstance().getSavings().savingInterests(i);
    }

}

