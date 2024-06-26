import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display, history;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton, equalsButton, clearButton, decimalButton, negateButton,
            percentButton, squareRootButton;
    private double result, operand;
    private char operator;
    private boolean isOperandEntered;
    private boolean isOperationPerformed;
    private StringBuilder historyBuilder;
    private DecimalFormat decimalFormat;

    public Calculator() {
        setTitle("Calculator");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        history = new JTextField();
        history.setEditable(false);
        history.setFont(new Font("Arial", Font.PLAIN, 16));
        history.setHorizontalAlignment(JTextField.RIGHT);
        history.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(history, BorderLayout.NORTH);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 15, 15));
        buttonPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        numberButtons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setForeground(Color.BLACK);
            buttonPanel.add(numberButtons[i]);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        decimalButton = new JButton(".");
        negateButton = new JButton("+/-");
        percentButton = new JButton("%");
        squareRootButton = new JButton("√");

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);
        decimalButton.addActionListener(this);
        negateButton.addActionListener(this);
        percentButton.addActionListener(this);
        squareRootButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(decimalButton);
        buttonPanel.add(negateButton);
        buttonPanel.add(percentButton);
        buttonPanel.add(squareRootButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.setBackground(new Color(0x4285F4));
        subButton.setBackground(new Color(0x4285F4));
        mulButton.setBackground(new Color(0x4285F4));
        divButton.setBackground(new Color(0x4285F4));
        equalsButton.setBackground(new Color(0x34A853));
        clearButton.setBackground(new Color(0xEA4335));
        decimalButton.setBackground(Color.LIGHT_GRAY);
        negateButton.setBackground(Color.LIGHT_GRAY);
        percentButton.setBackground(Color.LIGHT_GRAY);
        squareRootButton.setBackground(Color.LIGHT_GRAY);

        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        addButton.setFont(buttonFont);
        subButton.setFont(buttonFont);
        mulButton.setFont(buttonFont);
        divButton.setFont(buttonFont);
        equalsButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);
        decimalButton.setFont(buttonFont);
        negateButton.setFont(buttonFont);
        percentButton.setFont(buttonFont);
        squareRootButton.setFont(buttonFont);

        addButton.setForeground(Color.WHITE);
        subButton.setForeground(Color.WHITE);
        mulButton.setForeground(Color.WHITE);
        divButton.setForeground(Color.WHITE);
        equalsButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);
        decimalButton.setForeground(Color.BLACK);
        negateButton.setForeground(Color.BLACK);
        percentButton.setForeground(Color.BLACK);
        squareRootButton.setForeground(Color.BLACK);

        addButton.setBorderPainted(false);
        subButton.setBorderPainted(false);
        mulButton.setBorderPainted(false);
        divButton.setBorderPainted(false);
        equalsButton.setBorderPainted(false);
        clearButton.setBorderPainted(false);
        decimalButton.setBorderPainted(false);
        negateButton.setBorderPainted(false);
        percentButton.setBorderPainted(false);
        squareRootButton.setBorderPainted(false);

        historyBuilder = new StringBuilder();
        decimalFormat = new DecimalFormat("#.###########");

        result = 0;
        operand = 0;
        operator = ' ';
        isOperandEntered = false;
        isOperationPerformed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.matches("\\d")) {
            if (isOperandEntered || display.getText().equals("0")) {
                display.setText("");
                isOperandEntered = false;
            }
            display.setText(display.getText() + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            long startTime = System.nanoTime();
            if (!isOperationPerformed) {
                result = Double.parseDouble(display.getText());
                historyBuilder.append(decimalFormat.format(result)).append(" ").append(command).append(" ");
            } else {
                operand = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+':
                        result = addTwoNumbers((int) result, (int) operand);
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        if (operand == 0) {
                            display.setText("Error: Division by Zero");
                            return;
                        } else {
                            result /= operand;
                        }
                        break;
                }
                historyBuilder.append(decimalFormat.format(operand)).append(" ").append(command).append(" ");
            }
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1000000.0;
            String formattedDuration = String.format("%.6f", duration);
            history.setText(historyBuilder.toString() + " [" + formattedDuration + " ms]");
            operator = command.charAt(0);
            isOperandEntered = true;
            isOperationPerformed = true;
            display.setText(decimalFormat.format(result));
        } else if (command.equals("=")) {
            long startTime = System.nanoTime();
            if (isOperationPerformed) {
                operand = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+':
                        result = addTwoNumbers((int) result, (int) operand);
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        if (operand == 0) {
                            display.setText("Error: Division by Zero");
                            return;
                        } else {
                            result /= operand;
                        }
                        break;
                }
                display.setText(decimalFormat.format(result));
                historyBuilder.append(decimalFormat.format(operand)).append(" = ").append(decimalFormat.format(result));
                long endTime = System.nanoTime();
                double duration = (endTime - startTime) / 1000000.0;
                String formattedDuration = String.format("%.6f", duration);
                history.setText(historyBuilder.toString() + " [" + formattedDuration + " ms]");
                isOperandEntered = true;
                isOperationPerformed = false;
            }
        } else if (command.equals("C")) {
            display.setText("");
            history.setText("");
            result = 0;
            operator = ' ';
            isOperandEntered = false;
            isOperationPerformed = false;
            historyBuilder = new StringBuilder();
        } else if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (command.equals("+/-")) {
            if (!display.getText().isEmpty()) {
                double value = Double.parseDouble(display.getText());
                value = -value;
                display.setText(String.valueOf(value));
            }
        } else if (command.equals("%")) {
            if (!display.getText().isEmpty()) {
                double value = Double.parseDouble(display.getText());
                value /= 100;
                display.setText(String.valueOf(value));
            }
        } else if (command.equals("√")) {
            if (!display.getText().isEmpty()) {
                double value = Double.parseDouble(display.getText());
                if (value >= 0) {
                    value = Math.sqrt(value);
                    display.setText(String.valueOf(value));
                } else {
                    display.setText("Error: Invalid input for square root");
                }
            }
        }
    }

    public static int addTwoNumbers(int num1, int num2) {
        String strNum1 = Integer.toString(num1);
        String strNum2 = Integer.toString(num2);
        if (strNum1.length() < strNum2.length()) {
            String temp = strNum1;
            strNum1 = strNum2;
            strNum2 = temp;
        }

        StringBuilder result = new StringBuilder();
        strNum1 = new StringBuilder(strNum1).reverse().toString();
        strNum2 = new StringBuilder(strNum2).reverse().toString();

        int carry = 0;
        int length = strNum1.length();
        for (int i = 0; i < length; i++) {
            int digit1 = Character.getNumericValue(strNum1.charAt(i));
            int digit2 = i < strNum2.length() ? Character.getNumericValue(strNum2.charAt(i)) : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.append(sum % 10);
        }
        if (carry > 0) {
            result.append(carry);
        }

        String finalResult = result.reverse().toString();

        try {
            return Integer.parseInt(finalResult);
        } catch (NumberFormatException e) {
            System.err.println("Result exceeds the range of int type.");
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}