package ui.gui;

import persistence.Reader;
import persistence.Writer;
import ui.gui.exception.CategoryInvalidException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MenuBar extends JMenuBar implements ActionListener {

    public MenuBar() {
        JMenu menu1 = new JMenu("Save to File");
        JMenu menu2 = new JMenu("Load a file");
        JMenuItem load = new JMenuItem("Load a file");
        JMenuItem save = new JMenuItem("Save to file");
        JMenuItem saveAs = new JMenuItem("Save as a new file");
        add(menu1);
        add(menu2);
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
        JFileChooser saveFile = properlySetUpFileChooser("Save this file as a txt file");
        int returnValue = saveFile.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = new File(saveFile.getSelectedFile() + ".txt");
            try {
                Writer writer = new Writer(file);
                try {
                    writer.write(GuiData.getCategory("needs"));
                    writer.write(GuiData.getCategory("regrets"));
                    writer.write(GuiData.getCategory("wants"));
                } catch (CategoryInvalidException e2) {
                    e2.printStackTrace();
                }
                writer.write(GuiData.getSavings());
                writer.write(GuiData.getGoals());
                writer.close();
            } catch (Exception e) {
                String message = "Unable to save to " + file.getAbsolutePath();
                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //EFFECTS: create a new JFileChooser that is properly set up to accept a file that matches the data type for this
    //         app
    private JFileChooser properlySetUpFileChooser(String s) {
        JFileChooser saveFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        saveFile.setDialogTitle(s);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        saveFile.setFileFilter(filter);
        return  saveFile;
    }

    //MODIFIES: this
    //EFFECTS: Load a new file and temporary set the default file to the given file
    private void loadAFile() {
        JFileChooser loadFile = properlySetUpFileChooser("Load this new txt file that matches the app");
        int returnValue = loadFile.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = loadFile.getSelectedFile();
            try {
                try {
                    GuiData.setCategory("needs", Reader.readNeeds(selectedFile));
                    GuiData.setCategory("regrets", Reader.readRegrets(selectedFile));
                    GuiData.setCategory("wants", Reader.readRegrets(selectedFile));
                } catch (CategoryInvalidException e2) {
                    e2.printStackTrace();
                }
                GuiData.setSavings(Reader.readSavings(selectedFile));
                GuiData.setGoals(Reader.readGoals(selectedFile));
                GuiData.setFilePath(selectedFile.getAbsolutePath());
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
            Writer writer = new Writer(new File(GuiData.getBudgetFile()));
            try {
                writer.write(GuiData.getCategory("needs"));
                writer.write(GuiData.getCategory("regrets"));
                writer.write(GuiData.getCategory("wants"));
            } catch (CategoryInvalidException e2) {
                e2.printStackTrace();
            }
            writer.write(GuiData.getSavings());
            writer.write(GuiData.getGoals());
            writer.close();
            System.out.println("Purchases, Savings and Goals are saved to " + GuiData.getBudgetFile());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + GuiData.getBudgetFile());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

}

