package com.devops.devopsProject;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ProjectMain {
    static final Logger logger = Logger.getLogger(ProjectMain.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        if (args.length < 4) {
            logger.info("Please provide at least 4 arguments: <Principal> <Rate> <Time> <Choice>");
            return;
        }

        double P = Double.parseDouble(args[0]);
        double R = Double.parseDouble(args[1]);
        double T = Double.parseDouble(args[2]);
        int choice = Integer.parseInt(args[3]);

        logger.info("Principal (P): " + P);
        logger.info("Rate of Interest (R): " + R);
        logger.info("Time (T): " + T);

        logger.info("\n------ Financial Calculator Menu ------");
        logger.info("1. Simple Interest");
        logger.info("2. Compound Interest");
        logger.info("3. EMI Calculator");
        logger.info("4. Future Value");
        logger.info("5. Present Value");
        logger.info("6. Annuity Value");
        logger.info("7. Inflation Adjusted Value");
        logger.info("8. Doubling Time (Rule of 72)");
        logger.info("9. Net Interest Earned (CI)");
        logger.info("10. Investment Comparison (SI vs CI)");
        logger.info("Enter your choice (1-10):");

        switch (choice) {
            case 1:
                logger.info("Simple Interest: ₹" + String.format("%.2f", simpleInterest(P, R, T)));
                break;

            case 2:
                logger.info("Compound Interest: ₹" + String.format("%.2f", compoundInterest(P, R, T)));
                break;

            case 3:
                double[] emiDetails = emiCalculator(P, R, T);
                logger.info("EMI per month: ₹" + String.format("%.2f", emiDetails[0]));
                logger.info("Total Payment: ₹" + String.format("%.2f", emiDetails[1]));
                logger.info("Total Interest: ₹" + String.format("%.2f", emiDetails[2]));
                break;

            case 4:
                logger.info("Future Value: ₹" + String.format("%.2f", futureValue(P, R, T)));
                break;

            case 5:
                if (args.length < 5) {
                    logger.info("Please provide Future Value as 5th argument for Present Value calculation.");
                    return;
                }
                double FV = Double.parseDouble(args[4]);
                logger.info("Present Value: ₹" + String.format("%.2f", presentValue(FV, R, T)));
                break;

            case 6:
                logger.info("Annuity Value: ₹" + String.format("%.2f", annuityValue(P, R, T)));
                break;

            case 7:
                logger.info("Inflation Adjusted Value: ₹" + String.format("%.2f", inflationAdjustedValue(P, R, T)));
                break;

            case 8:
                logger.info("Doubling Time: " + String.format("%.2f", doublingTime(R)) + " years");
                break;

            case 9:
                logger.info("Net Interest Earned (CI): ₹" + String.format("%.2f", netInterestEarned(P, R, T)));
                break;

            case 10:
                double si = simpleInterest(P, R, T);
                double ci = compoundInterest(P, R, T);
                logger.info("Simple Interest: ₹" + String.format("%.2f", si));
                logger.info("Compound Interest: ₹" + String.format("%.2f", ci));
                logger.info("Difference: ₹" + String.format("%.2f", ci - si));
                break;

            default:
                logger.info("Invalid choice.");
        }
    }

    public static double simpleInterest(double P, double R, double T) {
        return (P * R * T) / 100;
    }

    public static double compoundInterest(double P, double R, double T) {
        return P * Math.pow((1 + R / 100), T) - P;
    }

    public static double[] emiCalculator(double P, double R, double T) {
        int N = (int) (T * 12);
        double r = R / (12 * 100);
        double emi = (P * r * Math.pow(1 + r, N)) / (Math.pow(1 + r, N) - 1);
        double totalPayment = emi * N;
        double interest = totalPayment - P;
        return new double[]{emi, totalPayment, interest};
    }

    public static double futureValue(double P, double R, double T) {
        return P * Math.pow((1 + R / 100), T);
    }

    public static double presentValue(double FV, double R, double T) {
        return FV / Math.pow((1 + R / 100), T);
    }

    public static double annuityValue(double P, double R, double T) {
        double r = R / 100;
        return P * ((Math.pow(1 + r, T) - 1) / r);
    }

    public static double inflationAdjustedValue(double P, double R, double T) {
        return P / Math.pow((1 + R / 100), T);
    }

    public static double doublingTime(double R) {
        return 72 / R;
    }

    public static double netInterestEarned(double P, double R, double T) {
        return compoundInterest(P, R, T);
    }
}
