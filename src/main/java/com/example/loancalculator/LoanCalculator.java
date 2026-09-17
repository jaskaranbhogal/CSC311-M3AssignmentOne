package com.example.loancalculator;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {

    @Override
    public void start(Stage stage) {

        // Create text fields
        TextField interestRateField = new TextField();
        TextField yearsField = new TextField();
        TextField loanAmountField = new TextField();
        TextField monthlyPaymentField = new TextField();
        TextField totalPaymentField = new TextField();

        // The result fields should not be editable
        monthlyPaymentField.setEditable(false);
        totalPaymentField.setEditable(false);

        // Align numbers to the right
        interestRateField.setAlignment(Pos.CENTER_RIGHT);
        yearsField.setAlignment(Pos.CENTER_RIGHT);
        loanAmountField.setAlignment(Pos.CENTER_RIGHT);
        monthlyPaymentField.setAlignment(Pos.CENTER_RIGHT);
        totalPaymentField.setAlignment(Pos.CENTER_RIGHT);

        // Create calculate button
        Button calculateButton = new Button("Calculate");

        // Create layout
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(15));
        pane.setHgap(10);
        pane.setVgap(10);

        // Add labels and fields
        pane.add(new Label("Annual Interest Rate:"), 0, 0);
        pane.add(interestRateField, 1, 0);

        pane.add(new Label("Number of Years:"), 0, 1);
        pane.add(yearsField, 1, 1);

        pane.add(new Label("Loan Amount:"), 0, 2);
        pane.add(loanAmountField, 1, 2);

        pane.add(new Label("Monthly Payment:"), 0, 3);
        pane.add(monthlyPaymentField, 1, 3);

        pane.add(new Label("Total Payment:"), 0, 4);
        pane.add(totalPaymentField, 1, 4);

        pane.add(calculateButton, 1, 5);
        GridPane.setHalignment(calculateButton, HPos.RIGHT);

        // Event-driven programming:
        // This code runs when the Calculate button is clicked
        calculateButton.setOnAction(e -> {

            double annualInterestRate =
                    Double.parseDouble(interestRateField.getText());

            int years =
                    Integer.parseInt(yearsField.getText());

            double loanAmount =
                    Double.parseDouble(loanAmountField.getText());

            // Convert annual interest rate to monthly interest rate
            double monthlyInterestRate = annualInterestRate / 1200;

            // Find the total number of monthly payments
            int numberOfPayments = years * 12;

            double monthlyPayment;

            // Calculate monthly payment
            if (monthlyInterestRate == 0) {
                monthlyPayment = loanAmount / numberOfPayments;
            } else {
                monthlyPayment =
                        loanAmount * monthlyInterestRate /
                                (1 - 1 / Math.pow(
                                        1 + monthlyInterestRate,
                                        numberOfPayments));
            }

            // Calculate total payment
            double totalPayment =
                    monthlyPayment * numberOfPayments;

            // Display results
            monthlyPaymentField.setText(
                    String.format("$%.2f", monthlyPayment));

            totalPaymentField.setText(
                    String.format("$%.2f", totalPayment));
        });

        // Create scene
        Scene scene = new Scene(pane, 400, 270);

        stage.setTitle("LoanCalculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}