package ui.gui;

import categories.Category;
import model.Purchase;
import ui.gui.exception.CategoryInvalidException;
import exception.NameNotValidException;

import javax.swing.*;
import java.awt.*;

//the menu for managing purchases in category

public class CategoryPanel extends JPanel {

    private GridBagConstraints gc = new GridBagConstraints();
    private JTextField nameField = new JTextField(10);
    private JTextField costField = new JTextField(10);
    private JTextField categoryField = new JTextField(10);

    //EFFECTS: Construct the menu for managing category
    public CategoryPanel() {
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Manage Categories"));
        setLayout(new GridBagLayout());
        makeLabels();
        makeFields();
        makeAddToCategoryButton();
        makeBackToMenuButton();
        makeNewDeleteButton();
        makeNewDeleteAllButton();
    }

    //MODIFIES: this
    //EFFECTS: make a button that takes user back to the main menu
    private void makeBackToMenuButton() {
        JButton b2 = new JButton("Go back to main menu");
        b2.setToolTipText("Hit this button to go back to main menu");
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 5;
        add(b2, gc);
        b2.addActionListener(e -> MainFrame.getInstance().changePanel(new MenuPanel()));
    }

    //MODIFIES: this, MainFrame
    //EFFECTS: make a button that when hit, takes user inputs and add a given purchases, given cost, to the given
    //         category
    private void makeAddToCategoryButton() {
        JButton b1 = new JButton("Add to the category");
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        b1.setToolTipText("Hit this button after you have input a name, price, and category for a purchase");
        add(b1, gc);
        b1.addActionListener(e -> {
            if (!nameField.getText().equals("") && !costField.getText().equals("")
                    && !categoryField.getText().equals("")) {
                String name = nameField.getText();
                try {
                    Double cost = Double.parseDouble(costField.getText());
                    String category = categoryField.getText().toLowerCase();
                    addToCat(name, cost, category);
                    MainFrame.getInstance().categoryState();
                } catch (NumberFormatException e2) {
                    errorCostMessage();
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: make text fields that take user input
    private void makeFields() {
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1;
        gc.gridy = 0;
        add(nameField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(costField, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(categoryField, gc);
    }

    //MODIFIES: this
    //EFFECTS: make labels for the respective text fields
    public void makeLabels() {
        JLabel nameOfItem = new JLabel("Name of Purchase: ");
        JLabel costOfGoal = new JLabel("Cost of Purchase: ");
        JLabel desireOfGoal = new JLabel("Which category(needs, regrets, wants)?: ");
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
    }

    //MODIFIES: this, MainFrame
    //EFFECTS: add a given purchases to a category
    public void addToCat(String n, Double c, String cat) {
        Purchase p = new Purchase(n, c);
        try {
            Category placeholderCat = MainFrame.getInstance().getCategory(cat.toLowerCase());
            placeholderCat.addToCat(p);
            MainFrame.getInstance().categoryState();
        } catch (Exception e) {
            errorCategoryMessage();
        }
    }

    //EFFECTS: pop up a message that remind the user only enter one of the three categories
    public void errorCategoryMessage() {
        String message = "Only enter needs, regrets, or wants for the Category field!!!";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //EFFECTS: pop up a message that remind user cost has to be a numeric number
    public void errorCostMessage() {
        String message = "Only positive numeric number for cost!!!";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //MODIFIES: this, MainFrame
    //EFFECTS: make a button that deletes a purchase in a category that matches the user inputs
    public void makeNewDeleteButton() {
        JButton deleteButton = new JButton("Delete a Purchase");
        deleteButton.setToolTipText("Hit this button after putting the name and category of a purchase in the text"
                + "columns to delete the given purchase");
        gc.weighty = 0.5;
        gc.weightx = 0.5;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.BOTH;
        add(deleteButton, gc);
        deleteButton.addActionListener(e -> {
            String removerName = nameField.getText().toLowerCase();
            String removerCat = categoryField.getText().toLowerCase();
            try {
                Category cat = MainFrame.getInstance().getCategory(removerCat);
                cat.removeNamedPurchase(removerName);
                MainFrame.getInstance().categoryState();
            } catch (CategoryInvalidException e1) {
                errorCategoryMessage();
            } catch (NameNotValidException e2) {
                nameNotValidError();
            }
        });

    }

    //EFFECTS: Pop up a message telling the user there is no such purchase with the given name in the given category
    public void nameNotValidError() {
        String message = "Cannot find purchase with this name in this category";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //MODIFIES: this, MainFrame
    //EFFECTS: delete all purchases in the given category
    public void makeNewDeleteAllButton() {
        JButton deleteAllButton = new JButton("Delete all purchase in given category");
        deleteAllButton.setToolTipText("Put the name of the category in the category text field to delete"
                + "everything in the category");
        gc.weighty = 0.5;
        gc.weightx = 0.5;
        gc.gridx = 0;
        gc.gridy = 5;
        gc.fill = GridBagConstraints.BOTH;
        add(deleteAllButton, gc);
        deleteAllButton.addActionListener(e -> {
            try {
                Category cat = MainFrame.getInstance().getCategory(categoryField.getText().toLowerCase());
                cat.clearList();
                MainFrame.getInstance().categoryState();
            } catch (CategoryInvalidException e0) {
                errorCategoryMessage();
            }
        });
    }
}