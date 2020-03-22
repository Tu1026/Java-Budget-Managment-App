package ui.GUI;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goals;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class MainFrame extends JFrame implements ActionListener {
    private static final String BUDGET_FILE = "./data/budget.txt";
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings;
    private Goals goals;
    private Scanner input;
    private static MainFrame instance;

    public MainFrame() {
        super("BudgetTracker");
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
        JMenu menu = new JMenu("Save to File");
        JMenuItem menuItem1 = new JMenuItem("Save to file");
        menubar.add(menu);
        menu.add(menuItem1);
        setJMenuBar(menubar);
        menuItem1.setActionCommand("save");
        menuItem1.addActionListener(this);

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
        }
    }

    //Reference code from teller app
    // EFFECTS: saves state of Categories, Savings, and Goals to BUDGET_FILE
    private void saveAccounts() {
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
        instance = frame;
        MenuPanel uiButtons = new MenuPanel();
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(800, 800);
        frame.setBounds(ss.width / 2 - frameSize.width / 2,
                ss.height / 2 - frameSize.height / 2,
                frameSize.width, frameSize.height);
        frame.add(uiButtons);
        frame.setVisible(true);
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
}
