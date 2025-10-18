package ua.opnu;

import org.ietf.jgss.GSSManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame implements ActionListener {

    public MainFrame(String title) throws HeadlessException {
        super(title);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));

        JButton rockButton = new JButton("Камінь");
        rockButton.addActionListener(this);
        rockButton.setActionCommand("rock");
        JButton paperButton = new JButton("Папір");
        paperButton.addActionListener(this);
        paperButton.setActionCommand("paper");
        JButton scissorsButton = new JButton("Ножиці");
        scissorsButton.addActionListener(this);
        scissorsButton.setActionCommand("scissors");

        this.add(rockButton);
        this.add(paperButton);
        this.add(scissorsButton);

        this.pack();
        this.setVisible(true);
    }

    private GameShape generateShape() {
        int random = new Random().nextInt(3);
        return switch (random) {
            case 0 -> new Rock();
            case 1 -> new Paper();
            default -> new Cutter();
        };
    }

    private int checkWinner(GameShape player, GameShape computer) {
        // Це моє рішення, але воно не підходить під завдання
//        Map<String, String> winnerTable = new HashMap<>();
//
//        winnerTable.put("Rock", "Cutter");
//        winnerTable.put("Cutter", "Paper");
//        winnerTable.put("Paper", "Rock");
//
//        String playerShape = player.toString();
//        String computerShape = computer.toString();
//
//        for (Map.Entry<String, String> entry : winnerTable.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            if (Objects.equals(playerShape, key) && Objects.equals(computerShape, value)) {
//                return 1;
//            } else if (Objects.equals(computerShape, key) && Objects.equals(playerShape, value)) {
//                return -1;
//            }
//        }
        if (player instanceof Rock && computer instanceof Cutter) return 1;
        if (player instanceof Cutter && computer instanceof Rock) return -1;

        if (player instanceof Cutter && computer instanceof Paper) return 1;
        if (player instanceof Paper && computer instanceof Cutter) return -1;

        if (player instanceof Paper && computer instanceof Rock) return 1;
        if (player instanceof Rock && computer instanceof Paper) return -1;

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameShape computerShape = generateShape();

        GameShape playerShape = null;
        switch (e.getActionCommand()) {
            case "rock":
                playerShape = new Rock();
                break;
            case "paper":
                playerShape = new Paper();
                break;
            case "scissors":
                playerShape = new Cutter();
                break;
        }

        int gameResult = checkWinner(playerShape, computerShape);

        String message = "Player shape: " + playerShape + ". Computer shape: " + computerShape + ". ";
        switch (gameResult) {
            case -1:
                message += "Computer has won!";
                break;
            case 0:
                message += "It's a tie!";
                break;
            case 1:
                message += "Player has won!";
        }

        JOptionPane.showMessageDialog(null, message);
    }
}
