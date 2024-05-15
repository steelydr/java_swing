import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton, equalsButton, clearButton, decimalButton, negateButton,
            percentButton, squareRootButton;
    private double operand1, operand2, result;
    private char operator;
    private boolean isOperandEntered;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        numberButtons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
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

        add(buttonPanel, BorderLayout.CENTER);

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

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.matches("\\d")) {
            if (isOperandEntered) {
                display.setText("");
                isOperandEntered = false;
            }
            display.setText(display.getText() + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            operand1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            isOperandEntered = true;
            display.setText("");
        } else if (command.equals("=")) {
            operand2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if (operand2 == 0) {
                        display.setText("Error: Division by Zero");
                    } else {
                        result = operand1 / operand2;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            isOperandEntered = true;
        } else if (command.equals("C")) {
            display.setText("");
            operand1 = 0;
            operand2 = 0;
            result = 0;
            operator = ' ';
            isOperandEntered = false;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}