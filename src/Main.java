import gameplay.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends JFrame implements WindowListener, ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Stage GamePlay;

    public Main() throws IOException {
        super("nFactorial tsk");
        addWindowListener(this);
        setSize(WIDTH, HEIGHT);
        GamePlay = new Stage();
        ((Component) GamePlay).setFocusable(true);
        getContentPane().add(GamePlay);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) throws IOException {
        Main t = new Main();
    }
}