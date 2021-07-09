import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NameGenerator {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Window");
        frame.setLayout(null);

        JLabel labelGender = new JLabel("labelGender");
        labelGender.setText("Gender");
        labelGender.setBounds(20, 80, 200, 30);
        JLabel labelYear = new JLabel("labelYear");
        labelYear.setText("Year");
        labelYear.setBounds(20, 120, 200, 30);
        JLabel labelRank = new JLabel("labelRank");
        labelRank.setText("Rank");
        labelRank.setBounds(20, 160, 200, 30);
        JLabel labelFirst = new JLabel("labelFirst");
        labelFirst.setText("First Letter");
        labelFirst.setBounds(20, 200, 200, 30);
        JLabel labelNum = new JLabel("labelNum");
        labelNum.setText("Number of Letters");
        labelNum.setBounds(20, 240, 200, 30);

        JTextField gender, year, rank, firstLetter, numOfLetters;
        gender = new JTextField("Input preferred gender (boy/girl)");
        gender.setBounds(50, 80, 200, 30);
        year = new JTextField("Input preferred year (1974-2019)");
        year.setBounds(50, 120, 200, 30);
        rank = new JTextField("Input preferred rank (1-100)");
        rank.setBounds(50, 160, 200, 30);
        firstLetter = new JTextField("Input preferred first letter e.g. 'A'");
        firstLetter.setBounds(50, 200, 200, 30);
        numOfLetters = new JTextField("Input preferred number of letters e.g. '14'");
        numOfLetters.setBounds(50, 240, 200, 30);
        f.add(gender);
        f.add(year);
        f.add(rank);
        f.add(firstLetter);
        f.add(numOfLetters);

        frame.setVisible(true);
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        JButton randomButton = new JButton("Randomize");
        panel.add(randomButton);
        randomButton.addActionListener(new RandomAction());

        JButton exitButton = new JButton("Exit");
        panel.add(exitButton);
        exitButton.addActionListener(new ActionListener {
            public void actionPerformed (ActionEvent e){
                System.exit(0);
            }
        });
    }

    static class RandomAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame2 = new JFrame("Random");
            frame2.setVisible(true);
            frame2.setSize(500, 200);
            JLabel label = new JLabel("you clicked me");
            JPanel panel = new JPanel();
            frame2.add(panel);
            panel.add(label);
        }
    }
}