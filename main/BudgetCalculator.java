package main;
public class BudgetCalculator {
    public static class BudgetResult {
        public double needsPercentage;
        public double wantsPercentage;
        public double savingsPercentage;
        public double wants;
        public double savings;
    }

    public static double getValidDoubleInput(String input) throws NumberFormatException {
        if (input.isEmpty()) {
            return 0;
        } else {
            double value = Double.parseDouble(input);
            if (value < 0) {
                throw new NumberFormatException("Negative value");
            }
            return value;
        }
    }

    public static double calculateMonthlyIncome(double income, int frequency) {
        switch (frequency) {
            case 1:
                return income * 4; 
            case 2:
                return income * 2; 
            case 3:
                return income;
            default:
                return 0;
        }
    }

    public static BudgetResult calculateBudget(double monthlyIncome, double housing, double utilities, double transportation, double groceries, double insurance, double other) {
        BudgetResult result = new BudgetResult();

        double totalExpenses = housing + utilities + transportation + groceries + insurance + other;
        result.needsPercentage = (totalExpenses / monthlyIncome) * 100;

        result.wantsPercentage = 30;
        result.savingsPercentage = 20;

        if (result.needsPercentage > 80) {
            double decreasePercent = (result.needsPercentage - 80);
            result.wantsPercentage = 0;
            result.savingsPercentage -= decreasePercent;
        } else if (result.needsPercentage >= 50 && result.needsPercentage <= 80) {
            double increasePercent = (result.needsPercentage - 50);
            result.wantsPercentage -= (increasePercent);
            result.savingsPercentage = 100 - result.wantsPercentage - result.needsPercentage;
        } else if (result.needsPercentage <= 50 && result.needsPercentage >= 30) {
            double decreasePercent = (50 - result.needsPercentage);
            result.savingsPercentage += (decreasePercent);
        } else {
            double decreasePercent = (30 - result.needsPercentage);
            result.wantsPercentage -= (decreasePercent * 0.33333);
            result.savingsPercentage = 100 - result.wantsPercentage - result.needsPercentage;
        }

        result.wants = result.wantsPercentage / 100 * monthlyIncome;
        result.savings = result.savingsPercentage / 100 * monthlyIncome;

        return result;
    }
}
