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
            double totalExpenses = housing + utilities + transportation + groceries + insurance + other;
            if (totalExpenses > monthlyIncome) {
                resultLabel.setText("<html>Total expenses exceed your income.<br>Please review your finances.</html>");
                return;
            }

            BudgetCalculator.BudgetResult budgetResult = BudgetCalculator.calculateBudget(monthlyIncome, housing, utilities, transportation, groceries, insurance, other);
            String resultText = "";
            switch (frequency) {
                case 1:
                    resultText = String.format("<html>Your needs consume %.2f%% of your income per month<br>" +
                            "%.2f%% ($%.2f) should be allocated towards wants per month<br>" +
                            "%.2f%% ($%.2f) should be allocated towards savings per month<br><br>" +
                            "Since you get paid weekly, set aside $%.2f per paycheck for expenses<br>" +
                            "Since you get paid weekly, set aside $%.2f per paycheck for wants<br>" +
                            "Since you get paid weekly, set aside $%.2f per paycheck for savings</html>",
                            budgetResult.needsPercentage,
                            budgetResult.wantsPercentage, budgetResult.wants,
                            budgetResult.savingsPercentage, budgetResult.savings,
                            totalExpenses / 4,
                            budgetResult.wants / 4,
                            budgetResult.savings / 4);
                    break;
                case 2:
                    resultText = String.format("<html>Your needs consume %.2f%% of your income per month<br>" +
                            "%.2f%% ($%.2f) should be allocated towards wants per month<br>" +
                            "%.2f%% ($%.2f) should be allocated towards savings per month<br><br>" +
                            "Since you get paid biweekly, set aside $%.2f per paycheck for expenses<br>" +
                            "Since you get paid biweekly, set aside $%.2f per paycheck for wants<br>" +
                            "Since you get paid biweekly, set aside $%.2f per paycheck for savings</html>",
                            budgetResult.needsPercentage,
                            budgetResult.wantsPercentage, budgetResult.wants,
                            budgetResult.savingsPercentage, budgetResult.savings,
                            totalExpenses / 2,
                            budgetResult.wants / 2,
                            budgetResult.savings / 2);
                    break;
                case 3:
                    resultText = String.format("<html>Your needs consume %.2f%% of your income per month<br>" +
                            "%.2f%% ($%.2f) should be allocated towards wants per month<br>" +
                            "%.2f%% ($%.2f) should be allocated towards savings per month<br><br>" +
                            "Set aside $%.2f per paycheck for expenses<br>" +
                            "Set aside $%.2f per paycheck for wants<br>" +
                            "Set aside $%.2f per paycheck for savings</html>",
                            budgetResult.needsPercentage,
                            budgetResult.wantsPercentage, budgetResult.wants,
                            budgetResult.savingsPercentage, budgetResult.savings,
                            totalExpenses,
                            budgetResult.wants,
                            budgetResult.savings);
                    break;
            }
            resultLabel.setText(resultText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
