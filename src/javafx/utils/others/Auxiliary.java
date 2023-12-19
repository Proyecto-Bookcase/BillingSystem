package javafx.utils.others;

import org.jetbrains.annotations.NotNull;

/**
 * Contain all auxiliary functions that has no defined classification
 */
public abstract class Auxiliary {

    /**
     * @param callingOrder if passed, increment the order of the calling function.
     * @return Name of the calling function
     */
    public static @NotNull String getCallingFunctionName(int... callingOrder) {

        int callIncrement = callingOrder.length > 0 ? 3 + callingOrder[0] : 3;

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        return elements[callIncrement].getMethodName();
    }
}
