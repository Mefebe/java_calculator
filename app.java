import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {
    private JFrame frame;
    private JTextField display;
    private JButton[] numberButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton, clearButton;

    private double firstOperand;
    private String operator;
    private boolean isTypingNumber = false;

    public SimpleCalculator() {
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(Integer.toString(i));
            buttonPanel.add(numberButtons[i]);
            final int digit = i;
            numberButtons[i].addActionListener(e -> onNumberButtonClicked(digit));
        }

        addButton = createOperatorButton("+");
        subtractButton = createOperatorButton("-");
        multiplyButton = createOperatorButton("*");
        divideButton = createOperatorButton("/");
        equalsButton = createOperatorButton("=");
        clearButton = createOperatorButton("C");

        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JButton createOperatorButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(e -> onOperatorButtonClicked(label));
        return button;
    }

    private void onNumberButtonClicked(int digit) {
        if (!isTypingNumber) {
            display.setText("");
            isTypingNumber = true;
        }
        String currentText = display.getText();
        display.setText(currentText + digit);
    }

    private void onOperatorButtonClicked(String newOperator) {
        if (isTypingNumber) {
            double secondOperand = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    firstOperand += secondOperand;
                    break;
                case "-":
                    firstOperand -= secondOperand;
                    break;
                case "*":
                    firstOperand *= secondOperand;
                    break;
                case "/":
                    firstOperand /= secondOperand;
                    break;
                case "=":
                    firstOperand = secondOperand;
                    break;
                case "C":
                    firstOperand = 0;
                    break;
            }
            display.setText(String.valueOf(firstOperand));
        }
        operator = newOperator;
        isTypingNumber = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator());
    }
}
