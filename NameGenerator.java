import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class NameGenerator {

    static JFrame frame = new JFrame();
    static JPanel mainPanel = new JPanel();
    // Declare Text fields
    static JTextField genderText = new JTextField(20),
            yearText = new JTextField(20),
            rankText = new JTextField(20),
            firstText = new JTextField(20),
            numText = new JTextField(20);
    static GridBagConstraints constr;
    static JPanel namePanel = new JPanel();
    static JLabel nameLabel = new JLabel("");

    public static void main(String[] args) {
        frame.setTitle("Name Generator");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel headingPanel = new JPanel();
        JLabel headingLabel = new JLabel("Random Name Generator");
        headingPanel.add(headingLabel);

        JPanel panel = new JPanel(new GridBagLayout());
        constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);
        constr.anchor = GridBagConstraints.WEST;

        constr.gridx = 1;
        constr.gridy = 0;
        namePanel.add(nameLabel, constr);

        // Declare the required Labels
        JLabel genderLabel = new JLabel("Gender (boy/girl) :");
        JLabel yearLabel = new JLabel("Year (1974-2019) :");
        JLabel rankLabel = new JLabel("Rank (1-100) :");
        JLabel firstLabel = new JLabel("First Letter (e.g. A) :");
        JLabel numLabel = new JLabel("Number of Letters (e.g. 7) :");

        constr.gridy = 1;
        constr.gridx = 0;
        panel.add(genderLabel, constr);
        constr.gridx = 2;
        panel.add(genderText, constr);

        constr.gridy = 2;
        constr.gridx = 0;
        panel.add(yearLabel, constr);
        constr.gridx = 2;
        panel.add(yearText, constr);

        constr.gridy = 3;
        constr.gridx = 0;
        panel.add(rankLabel, constr);
        constr.gridx = 2;
        panel.add(rankText, constr);

        constr.gridy = 4;
        constr.gridx = 0;
        panel.add(firstLabel, constr);
        constr.gridx = 2;
        panel.add(firstText, constr);

        constr.gridy = 5;
        constr.gridx = 0;
        panel.add(numLabel, constr);
        constr.gridx = 2;
        panel.add(numText, constr);

        // Button with text "Randomize"
        JButton randomButton = new JButton("Randomize");
        // add a listener to button
        randomButton.addActionListener(new RandomAction());

        // Button with text "Exit"
        JButton exitButton = new JButton("Exit");
        // add a listener to button
        exitButton.addActionListener(new ExitAction());

        constr.gridy = 6;
        constr.gridwidth = 1;
        constr.anchor = GridBagConstraints.CENTER;
        // Add label and button to panel
        panel.add(randomButton, constr);
        constr.gridy = 7;
        panel.add(exitButton, constr);

        mainPanel.add(headingPanel);
        mainPanel.add(panel);

        // Add panel to frame
        frame.add(mainPanel);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class RandomAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String gender = genderText.getText();
            String year = yearText.getText();
            String rank = rankText.getText();
            String first = firstText.getText();
            String num = numText.getText();

            String fileName = "";
            boolean error = false;
            if (!year.isEmpty()) {
                int yearInt = Integer.parseInt(year);
                if (yearInt >= 2010 && yearInt <= 2019)
                    fileName = "NamesList\\babies-first-names-2010-2019.csv";
                else if (yearInt >= 2000 && yearInt <= 2009)
                    fileName = "NamesList\\babies-first-names-2000-2009.csv";
                else if (yearInt >= 1990 && yearInt <= 1999)
                    fileName = "NamesList\\babies-first-names-1990-1999.csv";
                else if (yearInt >= 1980 && yearInt <= 1989)
                    fileName = "NamesList\\babies-first-names-1980-1989.csv";
                else if (yearInt >= 1974 && yearInt <= 1979)
                    fileName = "NamesList\\babies-first-names-1974-1979.csv";
                else error = true;
            } else fileName = "NamesList\\babies-first-names-all-names-all-years.csv";

            List<List<String>> records = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    records.add(Arrays.asList(values));
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            records.remove(0);

            if (!error) {
                if (!year.isEmpty() && !records.isEmpty()) {
                    int yearInt = Integer.parseInt(year);
                    records.removeIf(r -> (Integer.parseInt(r.get(0)) != yearInt));
                }

                if (!gender.isEmpty() && !records.isEmpty()) {
                    if (gender.equalsIgnoreCase("boy"))
                        records.removeIf(r -> (r.get(1).equals("G")));
                    else records.removeIf(r -> (r.get(1).equals("B")));
                }

                if (!rank.isEmpty() && !records.isEmpty()) {
                    int rankInt = Integer.parseInt(rank);
                    records.removeIf(r -> (Integer.parseInt(r.get(4)) != rankInt));
                }

                if (!first.isEmpty() && !records.isEmpty()) {
                    char firstChar = first.charAt(0);
                    records.removeIf(r -> (r.get(2).charAt(0) != Character.toUpperCase(firstChar)));
                }

                if (!num.isEmpty() && !records.isEmpty()) {
                    int numInt = Integer.parseInt(num);
                    records.removeIf(r -> (r.get(2).length() != numInt));
                }
            }

            if (error == true) {
                nameLabel.setText("An error occurred! Please check your input.");
                nameLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            } else if (records.isEmpty()) {
                nameLabel.setText("The name with your preference doesn't seem to exist.");
                nameLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            } else {
                Random rand = new Random();
                int index = rand.nextInt(records.size());
                String name = records.get(index).get(2);
                nameLabel.setText(name);
                nameLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
            }

            mainPanel.add(namePanel);
            //frame.revalidate();  // For JDK 1.7 or above.
            frame.getContentPane().revalidate(); // For JDK 1.6 or below.
            frame.repaint();
        }
    }

    static class ExitAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}