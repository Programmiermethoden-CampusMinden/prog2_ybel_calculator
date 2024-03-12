package calculator;

import javax.swing.*;

/** Starter for our Calculator. */
public class Main {

    /**
     * Create a new Calculator.
     *
     * @param args command line parameters, not used
     */
    public static void main(String... args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
