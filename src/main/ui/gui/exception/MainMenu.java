package ui.gui.exception;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goals;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainMenu extends JMenuBar implements ActionListener {
    private String budgetFile = "./data/budget.txt";
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings;
    private Goals goals;

    public MainMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu1 = new JMenu("Save to File");
        JMenu menu2 = new JMenu("Load a file");
        JMenuItem load = new JMenuItem("Load a file");
        JMenuItem save = new JMenuItem("Save to file");
        JMenuItem saveAs = new JMenuItem("Save as a new file");
        menubar.add(menu1);
        menubar.add(menu2);
        menu1.add(save);
        menu1.add(saveAs);
        menu2.add(load);
        save.setActionCommand("save");
        save.addActionListener(this);
        load.setActionCommand("load");
        load.addActionListener(this);
        saveAs.setActionCommand("saveas");
        saveAs.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes a empty budget tracker
    private void init() {
        needs = new Needs();
        regrets = new Regrets();
        wants = new Wants();
        savings = new Savings();
        goals = new Goals();
    }

    // MODIFIES: this
    // EFFECTS: loads categories, savings, and goals from BUDGET_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() {
        try {
            needs = Reader.readNeeds(new File(budgetFile));
            regrets = Reader.readRegrets(new File(budgetFile));
            wants = Reader.readWants(new File(budgetFile));
            savings = Reader.readSavings(new File(budgetFile));
            goals = Reader.readGoals(new File(budgetFile));
        } catch (IOException e) {
            init();
        }
    }

//    MODIFIES: this
//    EFFECTS: if the menu bar called has the command "save" save data to default file
//             if the menu bar called has the command "load" load a file that is formatted to this app
//             if the menu bar called has the command "saveas" save this file as a new txt file
    public void actionPerformed(ActionEvent e) {
        String actionEvent = e.getActionCommand();
        switch (actionEvent) {
            case "save":
                saveAccounts();
                break;
            case "load":
                loadAFile();
            case "saveas":
                saveAFile();
        }
    }

    //MODIFIES: this
    //EFFECTS; Save current file as a new txt file and temporary change default file to this new file
    private void saveAFile() {
        JFileChooser saveFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        saveFile.setDialogTitle("Save this file as a txt file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        saveFile.setFileFilter(filter);
        int returnValue = saveFile.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = new File(saveFile.getSelectedFile() + ".txt");
            try {
                Writer writer = new Writer(file);
                writer.write(needs);
                writer.write(regrets);
                writer.write(wants);
                writer.write(savings);
                writer.write(goals);
                writer.close();
            } catch (FileNotFoundException e) {
                String message = "Unable to save to " + file.getAbsolutePath();
                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: Load a new file and temporary set the default file to the given file
    private void loadAFile() {
        JFileChooser loadFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        loadFile.setFileFilter(filter);
        int returnValue = loadFile.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = loadFile.getSelectedFile();
            try {
                needs = Reader.readNeeds(selectedFile);
                regrets = Reader.readRegrets(selectedFile);
                wants = Reader.readWants(selectedFile);
                savings = Reader.readSavings(selectedFile);
                goals = Reader.readGoals(selectedFile);
                budgetFile = selectedFile.getAbsolutePath();
            } catch (IOException e) {
                String message = "Not a valid formatted file";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Reference code from teller app
    // Modifies: this
    // EFFECTS: saves state of Categories, Savings, and Goals to BUDGET_FILE
    public void saveAccounts() {
        try {
            Writer writer = new Writer(new File(budgetFile));
            writer.write(needs);
            writer.write(regrets);
            writer.write(wants);
            writer.write(savings);
            writer.write(goals);
            writer.close();
            System.out.println("Purchases, Savings and Goals are saved to " + budgetFile);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + budgetFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

}

