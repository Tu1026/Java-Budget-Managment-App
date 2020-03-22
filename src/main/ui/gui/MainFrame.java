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
    private static final String BUDGET_FILE = "./data/budget.txt";
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings;
    private Goals goals;
    private static MainFrame instance;

    public MainFrame() {
        super("BudgetTracker");
        instance = this;
        MenuPanel uiButtons = new MenuPanel();
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(800, 800);
        this.setBounds(ss.width / 2 - frameSize.width / 2,
                ss.height / 2 - frameSize.height / 2,
                frameSize.width, frameSize.height);
        this.add(uiButtons);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadAccounts();
        initMenu();
    }

    // MODIFIES: this
    // EFFECTS: loads categories, savings, and goals from BUDGET_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() {
        try {
            needs = Reader.readNeeds(new File(BUDGET_FILE));
            regrets = Reader.readRegrets(new File(BUDGET_FILE));
            wants = Reader.readWants(new File(BUDGET_FILE));
            savings = Reader.readSavings(new File(BUDGET_FILE));
            goals = Reader.readGoals(new File(BUDGET_FILE));
        } catch (IOException e) {
            init();
        }
    }

    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu1 = new JMenu("Save to File");
        JMenu menu2 = new JMenu("Load a file");
        JMenuItem load = new JMenuItem("Load a file");
        JMenuItem menuItem1 = new JMenuItem("Save to file");
        menubar.add(menu1);
        menubar.add(menu2);
        menu1.add(menuItem1);
        menu2.add(load);
        setJMenuBar(menubar);
        menuItem1.setActionCommand("save");
        menuItem1.addActionListener(this);
        load.setActionCommand("load");
        load.addActionListener(this);

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
            Writer writer = new Writer(new File(BUDGET_FILE));
            writer.write(needs);
            writer.write(regrets);
            writer.write(wants);
            writer.write(savings);
            writer.write(goals);
            writer.close();
            System.out.println("Purchases, Savings and Goals are saved to " + BUDGET_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + BUDGET_FILE);
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
