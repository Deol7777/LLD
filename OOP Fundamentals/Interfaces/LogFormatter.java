package Interfaces;

interface Formatter {
    String format(String message);
}

class PlainFormatter implements Formatter {

    public String format(String message) {
        return message;
    }
}

class JsonFormatter implements Formatter {
    public String format(String message) {
        return "{\"log\": \"" + message + "\"}";
    }
}

class Logger {

    private Formatter formatter;

    public Logger(Formatter formatter) {
        this.formatter = formatter;
    }

    public void log(String message) {
        String modified = formatter.format(message);
        System.out.println(modified);
    }

}

class LogFormatterDemo {
    public static void main(String[] args) {
        Logger plainLogger = new Logger(new PlainFormatter());
        plainLogger.log("Server started on port 8080");

        Logger jsonLogger = new Logger(new JsonFormatter());
        jsonLogger.log("Server started on port 8080");

        // --- extra tests ---
        System.out.println("\n--- extra tests ---");

        Formatter plain = new PlainFormatter();
        Formatter json = new JsonFormatter();

        check("plain echoes message",
                plain.format("hello").equals("hello"));
        check("json wraps message",
                json.format("hello").equals("{\"log\": \"hello\"}"));

        // input now respected: different input -> different output
        check("plain reflects input",
                !plain.format("A").equals(plain.format("B")));
        check("json reflects input",
                json.format("A").equals("{\"log\": \"A\"}")
                        && json.format("B").equals("{\"log\": \"B\"}"));

        // Logger uses injected formatter (strategy pattern)
        check("Logger logs plain output + newline",
                capture(new Logger(plain), "x").equals("x" + System.lineSeparator()));
        check("Logger logs json output + newline",
                capture(new Logger(json), "x").equals("{\"log\": \"x\"}" + System.lineSeparator()));

        // edge cases
        check("plain empty string", plain.format("").equals(""));
        check("json empty string", json.format("").equals("{\"log\": \"\"}"));

        check("null formatter throws on log", nullFormatterThrows());
    }

    private static void check(String name, boolean pass) {
        System.out.println((pass ? "PASS" : "FAIL") + " - " + name);
    }

    private static String capture(Logger logger, String msg) {
        java.io.PrintStream original = System.out;
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(buf));
        logger.log(msg);
        System.setOut(original);
        return buf.toString();
    }

    private static boolean nullFormatterThrows() {
        try {
            new Logger(null).log("x");
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }
}