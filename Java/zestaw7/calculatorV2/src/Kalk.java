import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Kalk implements ActionListener {
    JTextField t1;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot;
    JButton bplus, bminus, bmul, bdiv, bpow, bsqrt, bpercent, bmemclear, bmemread, bmemsave;
    JButton brow;

    double x, buf;
    boolean dotUsed = false;
    boolean memUsed = false;
    double memory = 0.0;

    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();

        if (target == b1 || target == b2 || target == b3 ||
                target == b4 || target == b5 || target == b6 ||
                target == b7 || target == b8 || target == b9 ||
                target == b0 || target == bdot) {
            String buttonText = ((JButton) target).getText();

            if (buttonText.equals(".") && dotUsed) {
                return; // Jeżeli kropka jest już używana, zignoruj
            }

            t1.setText(t1.getText() + buttonText);

            if (buttonText.equals(".")) {
                dotUsed = true;
            }

            t1.requestFocus();
        } else if (target == bplus || target == bminus || target == bmul || target == bdiv) {
            handleBinaryOperation((JButton) target);
        } else if (target == bpow || target == bsqrt || target == bpercent) {
            handleUnaryOperation((JButton) target);
        } else if (target == bmemclear) {
            memory = 0.0;
            memUsed = false;
        } else if (target == bmemsave) {
            memory = Double.parseDouble(t1.getText());
            memUsed = true;
        } else if (target == bmemread) {
            if (memUsed) {
                t1.setText(Double.toString(memory));
                t1.requestFocus();
            }
        } else if (target == brow || target == t1) {
            handleEqualsOperation();
        }
    }

    private void handleBinaryOperation(JButton operationButton) {
        if (!t1.getText().isEmpty()) {
            String buttonText = operationButton.getText();

            if (buttonText.matches("[+\\-*/]")) {
                x = Double.parseDouble(t1.getText());
                buf = x;
                t1.setText("");
                dotUsed = false;
                t1.requestFocus();
            } else {
                showError("Błędna operacja matematyczna: " + buttonText);
            }
        }
    }

    private void handleUnaryOperation(JButton operationButton) {
        if (!t1.getText().isEmpty()) {
            double operand = Double.parseDouble(t1.getText());
            double result = 0.0;

            switch (operationButton.getText()) {
                case "^":
                    result = Math.pow(operand, 2);
                    break;
                case "√":
                    if (operand >= 0) {
                        result = Math.sqrt(operand);
                    } else {
                        showError("Nie można policzyć pierwiastka kwadratowego z liczby ujemnej.");
                        return;
                    }
                    break;
                case "%":
                    result = operand / 100;
                    break;
            }

            t1.setText(Double.toString(result));
            dotUsed = false;
            t1.requestFocus();
        }
    }

    private void handleEqualsOperation() {
        if (!t1.getText().isEmpty()) {
            double y = Double.parseDouble(t1.getText());
            double result = 0.0;

            switch (t1.getText()) {
                case "+":
                    result = buf + y;
                    break;
                case "-":
                    result = buf - y;
                    break;
                case "*":
                    result = buf * y;
                    break;
                case "/":
                    if (y != 0) {
                        result = buf / y;
                    } else {
                        showError("Nie można dzielić przez zero.");
                        return;
                    }
                    break;
            }

            t1.setText(Double.toString(result));
            dotUsed = false;
            t1.requestFocus();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Błąd", JOptionPane.ERROR_MESSAGE);
    }

    void init() {
        JFrame f = new JFrame();
        Container c = f.getContentPane();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        c.setLayout(gbl);

        t1 = new JTextField(15);
        t1.addActionListener(this);
        t1.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.ipadx = 0;
        gbc.ipady = 5;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(t1, gbc);
        c.add(t1);

        // Dodaj przyciski z cyframi
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(this);
            button.setFocusable(false);
            gbc.gridx = (i - 1) % 3;
            gbc.gridy = (i - 1) / 3 + 1;
            gbc.gridwidth = 1;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            gbc.insets = new Insets(5, 5, 0, 0);
            gbl.setConstraints(button, gbc);
            c.add(button);
        }

        // Dodaj przycisk 0
        b0 = new JButton("0");
        b0.addActionListener(this);
        b0.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b0, gbc);
        c.add(b0);

        // Dodaj przycisk kropki
        bdot = new JButton(".");
        bdot.addActionListener(this);
        bdot.setFocusable(false);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(bdot, gbc);
        c.add(bdot);

        // Dodaj przyciski operacji matematycznych
        String[] operationButtons = {"+", "-", "*", "/", "^", "√", "%"};
        int row = 1;
        int col = 3;
        for (String op : operationButtons) {
            JButton button = new JButton(op);
            button.addActionListener(this);
            button.setFocusable(false);
            gbc.gridx = col;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            gbc.insets = new Insets(5, 5, 0, 0);
            gbl.setConstraints(button, gbc);
            c.add(button);
            row++;
        }

        // Dodaj przyciski pamięci
        bmemclear = new JButton("MC");
        bmemclear.addActionListener(this);
        bmemclear.setFocusable(false);
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(bmemclear, gbc);
        c.add(bmemclear);

        bmemread = new JButton("MR");
        bmemread.addActionListener(this);
        bmemread.setFocusable(false);
        gbc.gridx = col;
        gbc.gridy = row + 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(bmemread, gbc);
        c.add(bmemread);

        bmemsave = new JButton("MS");
        bmemsave.addActionListener(this);
        bmemsave.setFocusable(false);
        gbc.gridx = col;
        gbc.gridy = row + 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(bmemsave, gbc);
        c.add(bmemsave);

        bplus = new JButton("=");
        bplus.addActionListener(this);
        bplus.setFocusable(false);
        bplus.setToolTipText("dodawanie");
        gbc.gridx = col + 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(bplus, gbc);
        c.add(bplus);

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Kalk");
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Kalk().init();
            }
        });
    }
}