package main;
import javax.swing.*;
import java.awt.*;

public class BudgetTool extends JFrame {
    private JTextField incomeField, housingField, utilitiesField, transportationField, groceriesField, insuranceField, otherField;
    private JComboBox<String> frequencyBox;
    private JLabel resultLabel;
    private JPanel chartPanel;

    public BudgetTool() {
        setTitle("Budget Tool");
        setSize(600, 475);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Enter your last paycheck: "), gbc);
        incomeField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(incomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Paycheck frequency: "), gbc);
        String[] frequencies = {"Weekly", "Biweekly", "Monthly"};
        frequencyBox = new JComboBox<>(frequencies);
        gbc.gridx = 1;
        inputPanel.add(frequencyBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Enter Costs of Needs (Monthly):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(new JLabel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Housing: "), gbc);
        housingField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(housingField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Utilities: "), gbc);
        utilitiesField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(utilitiesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Transportation: "), gbc);
        transportationField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(transportationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Groceries: "), gbc);
        groceriesField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(groceriesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(new JLabel("Insurance: "), gbc);
        insuranceField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(insuranceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(new JLabel("Other: "), gbc);
        otherField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(otherField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        resultLabel = new JLabel();
        inputPanel.add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener(incomeField, frequencyBox, housingField, utilitiesField, transportationField, groceriesField, insuranceField, otherField, resultLabel, chartPanel));
        inputPanel.add(calculateButton, gbc);

        add(inputPanel, BorderLayout.NORTH);
        chartPanel = new JPanel();
        add(chartPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BudgetTool::new);
    }
}
