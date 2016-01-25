package rootPack.testGUI;

import javax.swing.*;
import java.awt.*;

public class TestGUI {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    createGUI();
                }
            });
        }
//TODO: Add buttons and path to excel file;
    private static void createGUI() {

        JButton updateListButton = new JButton("Send Bonuses");
        JButton updateLookAndFeelButton = new JButton("Edite Tamplate");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.add(updateListButton);
        buttonsPanel.add(Box.createHorizontalStrut(5));
        buttonsPanel.add(updateLookAndFeelButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(buttonsPanel);

        JPanel topPanel = new JPanel();

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout());
        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);


        JPanel btnPannel = new JPanel();
        bottomPanel = new JPanel();
        bottomPanel.add(btnPannel);
        JFrame frame = new JFrame("Look&Feel Switcher");
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
