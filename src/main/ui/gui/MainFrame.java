package ui.gui;

import account.Savings;
import categories.Category;
import model.Goals;
import ui.gui.exception.CategoryInvalidException;
import ui.gui.exception.MainMenu;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.*;

//The main frame that will contain different menus in different state
public class MainFrame extends JFrame implements ActionListener {
    private String budgetFile = "./data/budget.txt";
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings;
    private Goals goals;
    private static MainFrame instance;

    //MODIFIES: this
    //EFFECTS: Construct a frame with given title and centred in the middle of screen with main menu and load
    //         data from the default file
    public MainFrame() {
        super("BudgetTracker");
        getContentPane().removeAll();
        instance = this;
        this.setLayout(new BorderLayout());
        MainPanel uiButtons = new MainPanel();
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(800, 800);
        this.setBounds(ss.width / 2 - frameSize.width / 2,
                ss.height / 2 - frameSize.height / 2,
                frameSize.width, frameSize.height);
        add(uiButtons, BorderLayout.CENTER);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        loadAccounts();
//        initMenu();
        setMenuBar(new MainMenu());
        instance.addMouseListener(new MouseListener());
    }

    //EFFECTS: When mouse is pressed down make a click noise otherwise don't do anything
    private static class MouseListener implements java.awt.event.MouseListener {
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            try {
                AudioInputStream sound = AudioSystem.getAudioInputStream(new File(
                        "data/click noise.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(sound);
                FloatControl gainControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
                String message = "Could not find this audio file";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }




    // MODIFIES: this
    // EFFECTS: loads categories, savings, and goals from BUDGET_FILE, if that file exists;
    // otherwise initializes accounts with default values
//    private void loadAccounts() {
//        try {
//            needs = Reader.readNeeds(new File(budgetFile));
//            regrets = Reader.readRegrets(new File(budgetFile));
//            wants = Reader.readWants(new File(budgetFile));
//            savings = Reader.readSavings(new File(budgetFile));
//            goals = Reader.readGoals(new File(budgetFile));
//        } catch (IOException e) {
//            init();
//        }
//    }

    //MODIFIES: this
    //EFFECTS: Add you menu bars each with their own functions to the main frame
//    private void initMenu() {
//        JMenuBar menubar = new JMenuBar();
//        JMenu menu1 = new JMenu("Save to File");
//        JMenu menu2 = new JMenu("Load a file");
//        JMenuItem load = new JMenuItem("Load a file");
//        JMenuItem save = new JMenuItem("Save to file");
//        JMenuItem saveAs = new JMenuItem("Save as a new file");
//        menubar.add(menu1);
//        menubar.add(menu2);
//        menu1.add(save);
//        menu1.add(saveAs);
//        menu2.add(load);
//        setJMenuBar(menubar);
//        save.setActionCommand("save");
//        save.addActionListener(this);
//        load.setActionCommand("load");
//        load.addActionListener(this);
//        saveAs.setActionCommand("saveas");
//        saveAs.addActionListener(this);
//    }


//    // MODIFIES: this
//    // EFFECTS: initializes a empty budget tracker
//    private void init() {
//        needs = new Needs();
//        regrets = new Regrets();
//        wants = new Wants();
//        savings = new Savings();
//        goals = new Goals();
//    }

    //MODIFIES: this
    //EFFECTS: if the menu bar called has the command "save" save data to default file
    //         if the menu bar called has the command "load" load a file that is formatted to this app
    //         if the menu bar called has the command "saveas" save this file as a new txt file
//    public void actionPerformed(ActionEvent e) {
//        String actionEvent = e.getActionCommand();
//        switch (actionEvent) {
//            case "save":
//                saveAccounts();
//                break;
//            case "load":
//                loadAFile();
//            case "saveas":
//                saveAFile();
//        }
//    }


//    //MODIFIES: this
//    //EFFECTS; Save current file as a new txt file and temporary change default file to this new file
//    private void saveAFile() {
//        JFileChooser saveFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//        saveFile.setDialogTitle("Save this file as a txt file");
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
//        saveFile.setFileFilter(filter);
//        int returnValue = saveFile.showSaveDialog(this);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            File file = new File(saveFile.getSelectedFile() + ".txt");
//            try {
//                Writer writer = new Writer(file);
//                writer.write(needs);
//                writer.write(regrets);
//                writer.write(wants);
//                writer.write(savings);
//                writer.write(goals);
//                writer.close();
//            } catch (FileNotFoundException e) {
//                String message = "Unable to save to " + file.getAbsolutePath();
//                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    //MODIFIES: this
//    //EFFECTS: Load a new file and temporary set the default file to the given file
//    private void loadAFile() {
//        JFileChooser loadFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
//        loadFile.setFileFilter(filter);
//        int returnValue = loadFile.showOpenDialog(this);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = loadFile.getSelectedFile();
//            try {
//                needs = Reader.readNeeds(selectedFile);
//                regrets = Reader.readRegrets(selectedFile);
//                wants = Reader.readWants(selectedFile);
//                savings = Reader.readSavings(selectedFile);
//                goals = Reader.readGoals(selectedFile);
//                budgetFile = selectedFile.getAbsolutePath();
//            } catch (IOException e) {
//                String message = "Not a valid formatted file";
//                JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }

//    //Reference code from teller app
//    // Modifies: this
//    // EFFECTS: saves state of Categories, Savings, and Goals to BUDGET_FILE
//    public void saveAccounts() {
//        try {
//            Writer writer = new Writer(new File(budgetFile));
//            writer.write(needs);
//            writer.write(regrets);
//            writer.write(wants);
//            writer.write(savings);
//            writer.write(goals);
//            writer.close();
//            System.out.println("Purchases, Savings and Goals are saved to " + budgetFile);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save to " + budgetFile);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            // this is due to a programming error
//        }
//    }

    //EFFECTS: initiates the gui for the budgetTracker app
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }

    // MODIFIES: this
    // EFFECTS: reset the frame to contain only the given panel
    public void changePanel(JPanel panel) {
        getContentPane().removeAll();
        instance.add(panel);
        instance.setVisible(true);
        getContentPane().doLayout();
        update(getGraphics());
    }

    //EFFECTS: return this given instance of MainFrame
    public static MainFrame getInstance() {
        return instance;
    }

    //MODIFIES: this
    //EFFECTS: chang the state of the Frame to contain savings menu
    public void savingsState() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        final JPanel panel = new JPanel();
        String savingString = Double.toString(savings.getSavings());
        final JLabel savingsText = new JLabel("Money in Bank: $" + savingString, SwingConstants.CENTER);
        panel.add(savingsText);
        Font font = new Font("Times New Roman", Font.BOLD, 30);
        savingsText.setFont(font);

        SavingsPanel savingsPanel = new SavingsPanel();
        Container c = getContentPane();
        c.add(savingsPanel, BorderLayout.WEST);
        c.add(savingsText, BorderLayout.CENTER);
        setVisible(true);
        update(getGraphics());
    }

    //EFFECTS: return savings in the file
    public Savings getSavings() {
        return savings;
    }

    //MODIFIES: this
    //EFFECTS: Remove the current menu and change to goals menu
    public void goalsState() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        String allGoals = goals.getListOfGoals();
        final JTextArea goalText = new JTextArea();
        goalText.setEditable(false);
        goalText.append("All your goals are: \n" + allGoals);
        Font font = new Font("Times New Roman", Font.BOLD, 15);
        goalText.setFont(font);
        goalText.setLineWrap(true);
        goalText.setWrapStyleWord(true);
        GoalsPanel goalsPanel = new GoalsPanel();
        Container c = getContentPane();
        c.add(goalsPanel, BorderLayout.WEST);
        c.add(goalText, BorderLayout.CENTER);
        setVisible(true);
        update(getGraphics());
    }

    //EFFECTS: return the goals in this file
    public Goals getGoals() {
        return goals;
    }

    //EFFECTS: Return a given category in this file
    public Category getCategory(String s) throws CategoryInvalidException {
        switch (s) {
            case "needs":
                return needs;
            case "wants":
                return wants;
            case "regrets":
                return regrets;
            default:
                throw new CategoryInvalidException();
        }
    }

    //Modifies: this
    //EFFECTS: remove the current menu from the frame and change it to category menu
    public void categoryState() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        String needsList = needs.getListOfPurchases();
        String regretsList = regrets.getListOfPurchases();
        String wantsList = wants.getListOfPurchases();
        final JTextArea CategoryText = new JTextArea();
        CategoryText.setEditable(false);
        CategoryText.append("All your purchases are: \n\n Needs:\n " + needsList + "\n Regrets:\n " + regretsList
                + "\n Wants:\n " + wantsList);
        Font font = new Font("Times New Roman", Font.BOLD, 15);
        CategoryText.setFont(font);
        CategoryText.setLineWrap(true);
        CategoryText.setWrapStyleWord(true);


        CategoryPanel categoryPanel = new CategoryPanel();
        Container c = getContentPane();
        c.add(categoryPanel, BorderLayout.WEST);
        c.add(CategoryText, BorderLayout.CENTER);
        setVisible(true);
        update(getGraphics());
    }
}
