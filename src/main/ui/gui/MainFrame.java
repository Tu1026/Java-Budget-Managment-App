package ui.gui;



import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

//The main frame that will contain different menus in different state
public class MainFrame extends JFrame {
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
        setJMenuBar(new MenuBar());
        new GuiData();
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
        String savingString = Double.toString(GuiData.getSavings().getSavings());
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


    //MODIFIES: this
    //EFFECTS: Remove the current menu and change to goals menu
    public void goalsState() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        String allGoals = GuiData.getGoals().getListOfGoals();
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

    //Modifies: this
    //EFFECTS: remove the current menu from the frame and change it to category menu
    public void categoryState() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        String needsList = GuiData.getCategoryWithoutEx("needs").getListOfPurchases();
        String regretsList = GuiData.getCategoryWithoutEx("regrets").getListOfPurchases();
        String wantsList = GuiData.getCategoryWithoutEx("wants").getListOfPurchases();
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
