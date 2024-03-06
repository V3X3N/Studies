import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 * Klasa reprezentująca prostą kalkulator GUI.
 */
class Calculator implements ActionListener {

    // Komponenty interfejsu użytkownika
    JTextField t1, historyField;
    JButton[] digitButtons;
    JButton bDot, bClear, bAdd, bSubtract, bMultiply, bDivide, bPower, bSqrt, bPercent, bMemoryAdd, bMemorySubtract, bMemoryRecall, bBackspace, bEquals;

    // Flagi i zmienne pomocnicze
    boolean dotUsed = false;
    double memory = 0.0;
    Stack<Double> operandStack = new Stack<>();
    Stack<Character> operatorStack = new Stack<>();
    StringBuilder history = new StringBuilder();

    /**
     * Obsługuje zdarzenia akcji (np. naciśnięcia przycisku).
     */
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();

        // Sprawdza, który przycisk został naciśnięty i wywołuje odpowiednią funkcję obsługi
        if (target == bSqrt || target == bPower || target == bAdd || target == bSubtract ||
                target == bMultiply || target == bDivide || target == bPercent || target == bEquals) {
            handleMathOperation((JButton) target);
        } else if (target == bClear) {
            clearCalculator();
            history.setLength(0);
            updateHistoryField();
        } else if (target == bDot) {
            handleDot();
        } else if (target == bMemoryAdd) {
            handleMemoryOperation('+');
        } else if (target == bMemorySubtract) {
            handleMemoryOperation('-');
        } else if (target == bMemoryRecall) {
            recallMemory();
        } else if (target == bBackspace) {
            handleBackspace();
        } else {
            handleDigitInput((JButton) target);
        }
    }

    /**
     * Obsługuje operacje matematyczne, takie jak dodawanie, odejmowanie, itp.
     */
    void handleMathOperation(JButton button) {
        if (button == bEquals) {
            handleEquals();
        } else {
            if (!t1.getText().isEmpty()) {
                double currentNumber = Double.parseDouble(t1.getText());
                operandStack.push(currentNumber);

                if (!operatorStack.isEmpty() && hasPrecedence(button.getText(), operatorStack.peek())) {
                    evaluateStack();
                }

                char operator = button.getText().charAt(0);

                // Sprawdzenie dzielenia przez zero
                if (operator == '÷' && currentNumber == 0) {
                    clearCalculator();
                    t1.setText("Error: Division by zero");
                    return;
                }

                // Sprawdzenie pierwiastkowania liczby ujemnej
                if ((operator == '√' || operator == '^') && currentNumber < 0) {
                    clearCalculator();
                    t1.setText("Error: Invalid operation on negative number");
                    return;
                }

                operatorStack.push(operator);
                t1.setText("");  // Usunięcie tekstu po naciśnięciu operatora
                dotUsed = false;

                // Zapisanie operacji do historii
                history.append(currentNumber).append(" ").append(operator).append(" ");
                updateHistoryField();  // Usunięte z tej sekcji
            }
        }
    }

    /**
     * Obsługuje naciśnięcie przycisku "=".
     */
    void handleEquals() {
        if (!t1.getText().isEmpty()) {
            double currentNumber = Double.parseDouble(t1.getText());
            operandStack.push(currentNumber);

            while (!operatorStack.isEmpty()) {
                evaluateStack();
            }

            // Zapisanie wyniku do historii
            history.append("= ").append(operandStack.peek()).append("\n");
            updateHistoryField();

            // Aktualizacja pola tekstowego
            t1.setText(Double.toString(operandStack.pop()));
        }
    }

    /**
     * Ocenia stos operandów i operatorów, wykonując odpowiednie operacje matematyczne.
     */
    void evaluateStack() {
        double secondOperand = operandStack.pop();
        double firstOperand = operandStack.pop();
        char operator = operatorStack.pop();

        double result = 0;

        switch (operator) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '÷':
                // Sprawdzenie dzielenia przez zero
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    clearCalculator();
                    t1.setText("Error: Division by zero");
                    return;
                }
                break;
            case '^':
                // Sprawdzenie pierwiastkowania liczby ujemnej
                if (firstOperand >= 0) {
                    result = Math.pow(firstOperand, secondOperand);
                } else {
                    clearCalculator();
                    t1.setText("Error: Invalid operation on negative number");
                    return;
                }
                break;
            case '√':
                // Sprawdzenie pierwiastkowania liczby ujemnej
                if (firstOperand >= 0) {
                    result = Math.sqrt(firstOperand);
                } else {
                    clearCalculator();
                    t1.setText("Error: Invalid operation on negative number");
                    return;
                }
                break;
            case '%':
                result = firstOperand * (secondOperand / 100);
                break;
        }

        operandStack.push(result);

        // Dodanie wyniku do historii
        history.append(secondOperand).append(" = ").append(result).append("\n");
        updateHistoryField();

        // Aktualizacja pola tekstowego
        t1.setText(Double.toString(result));
    }

    /**
     * Aktualizuje pole historii w interfejsie użytkownika.
     */
    void updateHistoryField() {
        historyField.setText(history.toString());
    }

    /**
     * Obsługuje naciśnięcie przycisku ".".
     */
    void handleDot() {
        if (!dotUsed) {
            t1.setText(t1.getText() + ".");
            t1.requestFocus();
            dotUsed = true;
        }
    }

    /**
     * Obsługuje operacje na pamięci (M+, M-).
     */
    void handleMemoryOperation(char operation) {
        if (!t1.getText().isEmpty()) {
            double currentNumber = Double.parseDouble(t1.getText());
            switch (operation) {
                case '+' -> memory += currentNumber;
                case '-' -> memory -= currentNumber;
            }
            clearCalculator();
        }
    }

    /**
     * Przywraca wartość z pamięci na ekranie kalkulatora.
     */
    void recallMemory() {
        t1.setText(Double.toString(memory));
        t1.requestFocus();
    }

    /**
     * Obsługuje naciśnięcie przycisku "←" (backspace).
     */
    void handleBackspace() {
        String currentText = t1.getText();
        if (!currentText.isEmpty()) {
            t1.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    /**
     * Obsługuje wprowadzenie cyfry.
     */
    void handleDigitInput(JButton digitButton) {
        t1.setText(t1.getText() + digitButton.getText());
        t1.requestFocus();
    }

    /**
     * Sprawdza, czy operator1 ma wyższy priorytet niż operator2.
     */
    boolean hasPrecedence(String op1, char op2) {
        return (!op1.equals("+") && !op1.equals("-")) || (op2 != '*' && op2 != '÷');
    }

    /**
     * Czyści kalkulator.
     */
    void clearCalculator() {
        t1.setText("");
        dotUsed = false;
    }

    /**
     * Zapisuje historię operacji do pliku "calculator_history.txt".
     */
    void saveHistoryToFile() {
        try (FileWriter writer = new FileWriter("calculator_history.txt")) {
            writer.write(history.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicjalizuje interfejs użytkownika.
     */
    void init() {
        t1 = new JTextField(15);
        t1.setFont(new Font("Arial", Font.PLAIN, 34));
        t1.setEditable(false);

        historyField = new JTextField(15);
        historyField.setFont(new Font("Arial", Font.PLAIN, 20));
        historyField.setEditable(false);

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(Integer.toString(i));
        }

        // Inicjalizacja przycisków
        bDot = new JButton(".");
        bClear = new JButton("C");
        bAdd = new JButton("+");
        bSubtract = new JButton("-");
        bMultiply = new JButton("*");
        bDivide = new JButton("÷");
        bPower = new JButton("^");
        bSqrt = new JButton("√");
        bPercent = new JButton("%");
        bMemoryAdd = new JButton("M+");
        bMemorySubtract = new JButton("M-");
        bMemoryRecall = new JButton("MR");
        bBackspace = new JButton("←");
        bEquals = new JButton("=");

        // Utworzenie panelu przycisków
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 3));

        // Dodanie przycisków do panelu
        p.add(bMemoryAdd);
        p.add(bMemorySubtract);
        p.add(bMemoryRecall);
        p.add(bPower);

        p.add(bClear);
        p.add(bPercent);
        p.add(bBackspace);
        p.add(bDivide);

        p.add(digitButtons[7]);
        p.add(digitButtons[8]);
        p.add(digitButtons[9]);
        p.add(bMultiply);

        p.add(digitButtons[4]);
        p.add(digitButtons[5]);
        p.add(digitButtons[6]);
        p.add(bSubtract);

        p.add(digitButtons[1]);
        p.add(digitButtons[2]);
        p.add(digitButtons[3]);
        p.add(bAdd);

        p.add(bSqrt);
        p.add(digitButtons[0]);
        p.add(bDot);
        p.add(bEquals);

        // Dodanie obsługi zdarzeń dla przycisków cyfr
        for (int i = 0; i < 10; i++) {
            digitButtons[i].addActionListener(this);
        }

        // Dodanie obsługi zdarzeń dla pozostałych przycisków
        bDot.addActionListener(this);
        bClear.addActionListener(this);
        bAdd.addActionListener(this);
        bSubtract.addActionListener(this);
        bMultiply.addActionListener(this);
        bDivide.addActionListener(this);
        bPower.addActionListener(this);
        bSqrt.addActionListener(this);
        bPercent.addActionListener(this);
        bMemoryAdd.addActionListener(this);
        bMemorySubtract.addActionListener(this);
        bMemoryRecall.addActionListener(this);
        bBackspace.addActionListener(this);
        bEquals.addActionListener(this);

        // Utworzenie ramki i dodanie komponentów do niej
        JFrame f = new JFrame();
        Container c = f.getContentPane();
        c.add(historyField, BorderLayout.NORTH);
        c.add(t1, BorderLayout.CENTER);
        c.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        // Dodanie zdarzenia zamknięcia okna
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveHistoryToFile();
            }
        });
    }

    /**
     * Metoda uruchamiająca kalkulator.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().init());
    }
}