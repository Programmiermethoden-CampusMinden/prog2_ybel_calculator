package calculator;

/** Simple addition of two integers. */
public class Add implements Operation {
    @Override
    public int doOperation(int a, int b) {
        return a + b;
    }
}
