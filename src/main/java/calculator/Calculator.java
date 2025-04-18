package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/** Our simple calculator. */
public class Calculator extends JFrame {
    private JTextField lhs;
    private JTextField rhs;
    private JComboBox<String> operationSelector;
    private Map<String, Operation> operations;
    private JTextField result;

    /** Create a new calculator window. */
    public Calculator() {
        super();

        setTitle("Calculator");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupOperationSelector();
        setupPanel();

        pack();
        setVisible(true);
    }

    /**
     * Prepare the operations for the calculator.
     *
     * <p>We collect all operations in a map using the name of the operation as key. This string is
     * displayed in the JComboBox and the operation object is used to execute the operation.
     */
    private void setupOperationSelector() {
        operations = new HashMap<>();
        operations.put("Add", new Add());

        // TODO
        // Add a new operation "Sub" for the subtraction of two integers as an (instance of a) Java
        // class (you have yet to write this class)

        // TODO
        // Add a new operation "Mul" for the multiplication of two integers as an anonymous class

        // TODO
        // Add a new operation "Div" for the division of two integers as a lambda expression

        operationSelector = new JComboBox<>();
        operations.forEach((key, value) -> operationSelector.addItem(key));

        // TODO
        // Replace the anonymous class with a lambda expression
        operationSelector.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            result.setText("" + calculate());
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input.");
                        }
                    }
                });
    }

    /**
     * Create a main panel for the calculator.
     *
     * <p>Our panel consists of two text fields for entering the operands (integers), a combo box
     * for selecting the operations, a button for executing the operations and a text field for
     * displaying the result.
     */
    private void setupPanel() {
        JPanel panel = new JPanel();

        // input left operand
        lhs = new JTextField("first operand", 8);
        lhs.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(final FocusEvent pE) {
                        lhs.selectAll();
                    }
                });
        lhs.setInputVerifier(new JTextFieldVerifier(lhs));
        panel.add(lhs);

        // select operation
        panel.add(operationSelector);

        // input right operand
        rhs = new JTextField("second operand", 8);
        rhs.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(final FocusEvent pE) {
                        rhs.selectAll();
                    }
                });
        rhs.setInputVerifier(new JTextFieldVerifier(rhs));
        panel.add(rhs);

        // button to trigger the calculation
        JButton solutionButton = new JButton("=");
        solutionButton.addActionListener(
                e -> {
                    try {
                        result.setText("" + calculate());
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid input.");
                    }
                });
        panel.add(solutionButton);

        // display result
        result = new JTextField("", 4);
        result.setEditable(false);
        panel.add(result);

        add(panel);
    }

    /**
     * Verify the input in our text fields.
     *
     * <p>Fetches the text from the two input fields, retrieves the selected operand and the
     * associated operation, applies this operation to the operands and returns the result.
     *
     * @return result of {@code operation(leftOperand, rightOperand)}
     * @throws NumberFormatException if either text input (operand) is not an integer
     */
    private int calculate() throws NumberFormatException {
        Operation operation = operations.get(operationSelector.getSelectedItem());
        int a = Integer.parseInt(lhs.getText());
        int b = Integer.parseInt(rhs.getText());
        return operation.doOperation(a, b);
    }

    /** Tests the text field to verify whether an integer has been entered. */
    private static class JTextFieldVerifier extends InputVerifier {
        private final JTextField tf;

        /**
         * Creates a new verifier and stores the text field to be used.
         *
         * @param tf text field to be used
         */
        public JTextFieldVerifier(JTextField tf) {
            super();
            this.tf = tf;
        }

        /**
         * Verifies whether the input is an integer.
         *
         * <p>The text field should contain strings that can be parsed to integers.
         *
         * <p>If the text field does not contain an integer, the current content is selected and
         * highlighted in a red colour. Additionally, the focus of the text field will not be
         * released.
         *
         * <p>If the text field does contain an integer, any highlighting will be reset and the
         * focus of the text field will be released.
         *
         * @param input the JComponent to verify
         * @return true if text field contains an integer, otherwise false
         */
        @Override
        public boolean verify(JComponent input) {
            try {
                Integer.parseInt(tf.getText());
                tf.setSelectedTextColor(Color.BLACK); // reset warning color
                return true; // release focus
            } catch (NumberFormatException e) {
                tf.selectAll(); // select text
                tf.setSelectedTextColor(Color.RED); // highlight it
                return false; // do not release focus
            }
        }
    }
}
