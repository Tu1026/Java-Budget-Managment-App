package ui.gui;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goals;
import persistence.Reader;
import persistence.Writer;
import ui.gui.exception.CategoryInvalidException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class MainFrame extends JFrame implements ActionListener {
    private String budgetFile = "./data/budget.txt";
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings;
    private Goals goals;
    private static MainFrame instance;

    public MainFrame() {
        super("BudgetTracker");
        getContentPane().removeAll();
        instance = this;
        this.setLayout(new BorderLayout());
        MenuPanel uiButtons = new MenuPanel();
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(800, 800);
        this.setBounds(ss.width / 2 - frameSize.width / 2,
                ss.height / 2 - frameSize.height / 2,
                frameSize.width, frameSize.height);
        add(uiButtons, BorderLayout.CENTER);
        menuText();
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadAccounts();
        initMenu();
    }

    public void menuText() {
        JTextArea menuText = new JTextArea();
        menuText.setEditable(false);
        menuText.setWrapStyleWord(true);
        menuText.setLineWrap(true);
        String text = "This is an app that allows you to manage your savings, purchases and financial goals. \n"
                + "The app instantly loads the default file, however, there is also an option to load a new txt file \n"
                + "that is properly formatted and you can try loading the testBudgetTracker.txt in data folder to test."
                + "\n Most importantly the application does not auto save so SAVE before existing.";
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        menuText.setFont(font);
        menuText.append(text);
        add(menuText, BorderLayout.WEST);
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

    private void initMenu() {
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
        setJMenuBar(menubar);
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

    //This is the method that is called when the the JButton btn is clicked
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

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }

    public void changePanel(JPanel panel) {
        getContentPane().removeAll();
        instance.add(panel);
        instance.setVisible(true);
        getContentPane().doLayout();
        update(getGraphics());
    }

    public static MainFrame getInstance() {
        return instance;
    }

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

    public Savings getSavings() {
        return savings;
    }

    public void goalsState() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        String allGoals = goals.getAllGoals();
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

    public Goals getGoals() {
        return goals;
    }

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
