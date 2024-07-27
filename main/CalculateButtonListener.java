package main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateButtonListener implements ActionListener {
    private JTextField incomeField, housingField, utilitiesField, transportationField, groceriesField, insuranceField, otherField;
    private JComboBox<String> frequencyBox;
    private JLabel resultLabel;

    public CalculateButtonListener(JTextField incomeField, JComboBox<String> frequencyBox, JTextField housingField, JTextField utilitiesField, JTextField transportationField, JTextField groceriesField, JTextField insuranceField, JTextField otherField, JLabel resultLabel, JPanel chartPanel) {
        this.incomeField = incomeField;
        this.frequencyBox = frequencyBox;
        this.housingField = housingField;
        this.utilitiesField = utilitiesField;
        this.transportationField = transportationField;
        this.groceriesField = groceriesField;
        this.insuranceField = insuranceField;
        this.otherField = otherField;
        this.resultLabel = resultLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double income = BudgetCalculator.getValidDoubleInput(incomeField.getText());
            int frequency = frequencyBox.getSelectedIndex() + 1;
            double monthlyIncome = BudgetCalculator.calculateMonthlyIncome(income, frequency);
            double housing = BudgetCalculator.getValidDoubleInput(housingField.getText());
            double utilities = BudgetCalculator.getValidDoubleInput(utilitiesField.getText());
            double transportation = BudgetCalculator.getValidDoubleInput(transportationField.getText());
            double groceries = BudgetCalculator.getValidDoubleInput(groceriesField.getText());
            double insurance = BudgetCalculator.getValidDoubleInput(insuranceField.getText());
            double other = BudgetCalculator.getValidDoubleInput(otherField.getText());

            BudgetCalculator.BudgetResult budgetResult = BudgetCalculator.calculateBudget(monthlyIncome, housing, utilities, transportation, groceries, insurance, other);
            String resultText = String.format("<html>Your needs consume %.2f%% of your income per month<br>" + "%.2f%% ($%.2f) should be allocated towards wants per month<br>" + "%.2f%% ($%.2f) should be allocated towards savings per month<br>", budgetResult.needsPercentage, budgetResult.wantsPercentage, budgetResult.wants, budgetResult.savingsPercentage, budgetResult.savings);
            switch (frequency) {
                case 1:
                    resultText += String.format("Since you get paid weekly, set aside $%.2f per paycheck for savings", budgetResult.savings / 4);
                    break;
                case 2:
                    resultText += String.format("Since you get paid biweekly, set aside $%.2f per paycheck for savings", budgetResult.savings / 2);
                    break;
            }
            resultLabel.setText(resultText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
