package org.example;

public class Printing {

    /**
     * Prints a simple message to the console.
     *
     * @param message the message to be printed
     */
    public static void printSomething(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message to the console in a formatted box.
     *
     * @param message the message to be printed
     */
    public void printBoxed(String message) {
        String border = "+-" + "-".repeat(message.length()) + "-+";
        System.out.println(border);
        System.out.println("| " + message + " |");
        System.out.println(border);
    }

    /**
     * Prints a message in warning style.
     *
     * @param message the warning message
     */
    public void printWarning(String message) {
        System.out.println("⚠️ WARNING: " + message);
    }

    /**
     * Prints an error message.
     *
     * @param message the error message
     */
    public void printError(String message) {
        System.err.println("❌ ERROR: " + message);
    }

    /**
     * Prints a success message.
     *
     * @param message the success message
     */
    public void printSuccess(String message) {
        System.out.println("✅ SUCCESS: " + message);
    }
}