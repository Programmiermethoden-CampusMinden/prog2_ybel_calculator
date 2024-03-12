package calculator;

/** A simple binary operation on two integer numbers. */
public interface Operation {
    /**
     * The binary operation for two integers.
     *
     * @param a left operand (integer)
     * @param b right operand (integer)
     * @return result of this operation
     */
    int doOperation(int a, int b);
}
