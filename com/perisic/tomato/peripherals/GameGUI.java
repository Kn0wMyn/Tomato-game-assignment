package com.perisic.tomato.peripherals;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import com.perisic.tomato.engine.GameEngine;

public class GameGUI extends JFrame implements ActionListener {

    public static final long serialVersionUID = -107785653906635L;
    public static final int FRAME_WIDTH = 690;
    public static final int FRAME_HEIGHT = 500;
    public static final int BUTTON_FONT_SIZE = 20;

    public static final Color BUTTON_DEFAULT_COLOR = new Color(52, 152, 219);
    public static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185);
    public static final Color BUTTON_CORRECT_COLOR = Color.BLUE;

    private JLabel questArea = null;
    private GameEngine myGame = null;
    private BufferedImage currentGame = null;
    private JTextArea infoArea = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        int solution = Integer.parseInt(e.getActionCommand());
        boolean correct = myGame.checkSolution(solution);
        int score = myGame.getScore();                          

        if (correct) {
            handleCorrectSolution(score);
        } else {
            handleIncorrectSolution(score);
        }
    }

    private void handleCorrectSolution(int score) {
        System.out.println("Correct solution entered!");
        currentGame = myGame.nextGame();
        questArea.setIcon(new ImageIcon(currentGame));
        infoArea.setText("Good!  Score: " + score);
        flashButton(BUTTON_CORRECT_COLOR);
    }

    private void handleIncorrectSolution(int score) {
        System.out.println("Not Correct");
        infoArea.setText("Oops. Try again!  Score: " + score);
    }

    private void flashButton(Color color) {
        Color originalColor = BUTTON_DEFAULT_COLOR;
        Timer timer = new Timer(500, e -> {
            ((JButton) e.getSource()).setBackground(originalColor);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void initializeButtons(JPanel panel) {
        for (int i = 0; i < 10; i++) {
            JButton btn = createNumberButton(String.valueOf(i));
            panel.add(btn);
        }
    }

    private JButton createNumberButton(String label) {
        JButton btn = new JButton(label);
        btn.setBackground(BUTTON_DEFAULT_COLOR);
        btn.setForeground(Color.BLACK);
        btn.setFont(btn.getFont().deriveFont((float) BUTTON_FONT_SIZE));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BUTTON_DEFAULT_COLOR);
            }
        });

        btn.addActionListener(this::handleButtonClick);
        return btn;
    }

    private void handleButtonClick(ActionEvent e) {
        int solution = Integer.parseInt(e.getActionCommand());
        boolean correct = myGame.checkSolution(solution);
        int score = myGame.getScore();

        if (correct) {
            handleCorrectSolution(score);
        } else {
            handleIncorrectSolution(score);
        }
    }

    private void initGame(String player) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("What is the missing value?");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));

        myGame = new GameEngine(player);
        currentGame = myGame.nextGame();

        infoArea = new JTextArea(1, 25);
        infoArea.setEditable(false);
        infoArea.setText("What is the value of the tomato?   Score: 0");
        infoArea.setFont(new Font("Arial", Font.BOLD, 16));
        infoArea.setForeground(Color.WHITE);
        infoArea.setBackground(new Color(52, 73, 94));
        infoArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JScrollPane infoPane = new JScrollPane(infoArea);

        panel.add(infoPane);

        ImageIcon ii = new ImageIcon(currentGame);
        questArea = new JLabel(ii);
        questArea.setSize(330, 600);

        JScrollPane questPane = new JScrollPane(questArea);
        panel.add(questPane);

        initializeButtons(panel);

        getContentPane().add(panel);
        panel.repaint();
    }

    public GameGUI() {
        super();
        initGame(null);
    }

    public GameGUI(String player) {
        super();
        initGame(player);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            GameGUI myGUI = new GameGUI();
            myGUI.setVisible(true);
        });
    }
}
