package ui.testpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FrmChange extends JFrame {

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();

    public FrmChange() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initMenu();
        panel1.setBackground(Color.BLUE);
        panel2.setBackground(Color.RED);
        setLayout(new BorderLayout());
    }

    private class MenuAction implements ActionListener {

        private JPanel panel;

        private MenuAction(JPanel pnl) {
            this.panel = pnl;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(panel);

        }

    }

    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem1 = new JMenuItem("Panel1");
        JMenuItem menuItem2 = new JMenuItem("Panel2");
        menubar.add(menu);
        menu.add(menuItem1);
        menu.add(menuItem2);
        setJMenuBar(menubar);
        menuItem1.addActionListener(new MenuAction(panel1));
        menuItem2.addActionListener(new MenuAction(panel2));

    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
    }

    public static void main(String[] args) {
        FrmChange frame = new FrmChange();
        frame.setBounds(200, 200, 300, 200);
        frame.setVisible(true);

    }
}