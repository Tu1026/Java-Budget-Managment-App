package ui.gui;


import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
//The main menu of the application

public class MenuPanel extends JPanel {

    //EFFECTS: constructs the main menu for the app
    public MenuPanel() {
        setLayout(new GridBagLayout());
        categoryButton();
        savingsButton();
        goalsButton();
        popUpImageButton();
    }

    //MODIFIES: this
    //EFFECTS: Make a button that takes user to the category menu and plays money sound
    public void categoryButton() {
        GridBagConstraints gc = new GridBagConstraints();
        JButton b1 = new JButton("Manage Categories");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.weighty = 2;
        gc.weightx = 0;
        add(b1, gc);
        b1.setActionCommand("categories");
        b1.addActionListener(e -> {
            MainFrame.getInstance().categoryState();
            doMoneyMusic();
        });
    }

    //MODIFIES: this
    //EFFECTS: Make a button that takes user to the savings menu and plays coin sound
    public void savingsButton() {
        GridBagConstraints gc = new GridBagConstraints();
        JButton b2 = new JButton("Manage savings");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = 2;
        gc.weightx = 0;
        add(b2, gc);
        b2.addActionListener(e -> {
            MainFrame.getInstance().savingsState();
            doCoinNoise();
        });
    }

    //MODIFIES: this
    //EFFECTS: make a goals button that takes user to the goals menu and play a cheer noise
    public void goalsButton() {
        GridBagConstraints gc = new GridBagConstraints();
        JButton b3 = new JButton("Manage all goals");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weighty = 2;
        gc.weightx = 0;
        add(b3, gc);
        b3.addActionListener(e -> {
            MainFrame.getInstance().goalsState();
            doCheerNoise();
        });
    }

    //EFFECTS: make a button that when hit pop up a random cute dog photo
    public void popUpImageButton() {
        GridBagConstraints gc = new GridBagConstraints();
        JButton popUp = new JButton("Pop up a random cute photo");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weighty = 2;
        gc.weightx = 0;
        gc.gridwidth = 2;
        add(popUp, gc);
        popUp.addActionListener(e -> popUpImage());
    }

    //EFFECTS: plays a money sound
    //Reference code from https://stackoverflow.com/questions/20811728/adding-music-sound-to-java-programs
    public void doMoneyMusic() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(
                    "data/Money sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            String message = "Could not find this audio file";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: plays a sad noise
    //Reference code from https://stackoverflow.com/questions/20811728/adding-music-sound-to-java-programs
    public void doCoinNoise() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(
                    "data/coin.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            String message = "Could not find this audio file";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: plays a sad noise
    //Reference code from https://stackoverflow.com/questions/20811728/adding-music-sound-to-java-programs
    public void doCheerNoise() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(
                    "data/cheers.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            String message = "Could not find this audio file";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: pop up a random dog photo from the directory
    public static void popUpImage() {
        JDialog dialog = new JDialog();
        File dir = new File("data/photos");
        File[] photos = dir.listFiles();
        Random rando = new Random();
        assert photos != null;
        File photo = photos[rando.nextInt(photos.length)];
        JLabel label = new JLabel(new ImageIcon(photo.getAbsolutePath()));
        dialog.add(label);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }
}

