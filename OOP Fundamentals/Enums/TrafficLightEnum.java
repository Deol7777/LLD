package Enums;

enum TrafficLight {

    RED(30),
    YELLOW(5),
    GREEN(25);

    private int duration;

    private TrafficLight(int duration) {
        this.duration = duration;
    }

    public TrafficLight next() {
        switch (this) {
            case RED:
                return GREEN;
            case YELLOW:
                return RED;

            case GREEN:
                return YELLOW;
            default:
                return RED;
        }
    }

    public String getColor() {
        switch (this) {
            case RED:
                return "RED";
            case YELLOW:
                return "YELLOW";

            case GREEN:
                return "GREEN";
            default:
                return "RED";
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public void display() {
        System.out.println("The color is " + this.getColor() + ". Duration left: " + this.duration);
    }
}

// Test your implementation
public class TrafficLightEnum {
    static int passed = 0;
    static int failed = 0;

    static void check(String name, Object expected, Object actual) {
        if (expected == null ? actual == null : expected.equals(actual)) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name + " -> expected " + expected + ", got " + actual);
        }
    }

    public static void main(String[] args) {
        // --- getDuration() ---
        check("RED duration", 30, TrafficLight.RED.getDuration());
        check("YELLOW duration", 5, TrafficLight.YELLOW.getDuration());
        check("GREEN duration", 25, TrafficLight.GREEN.getDuration());

        // --- next(): RED->GREEN, GREEN->YELLOW, YELLOW->RED ---
        check("RED next", TrafficLight.GREEN, TrafficLight.RED.next());
        check("GREEN next", TrafficLight.YELLOW, TrafficLight.GREEN.next());
        check("YELLOW next", TrafficLight.RED, TrafficLight.YELLOW.next());

        // --- next() is a full cycle back to start ---
        TrafficLight cycled = TrafficLight.RED.next().next().next();
        check("full cycle returns to RED", TrafficLight.RED, cycled);

        // --- next() does not mutate the source constant ---
        TrafficLight.RED.next();
        check("next() has no side effect on RED", 30, TrafficLight.RED.getDuration());

        // --- ordering / count sanity ---
        check("enum count", 3, TrafficLight.values().length);
        check("valueOf RED", TrafficLight.RED, TrafficLight.valueOf("RED"));

        // display() -- visual, no assert; verify prints "COLOR (Xs)"
        System.out.println("display() output (verify format manually):");
        for (TrafficLight t : TrafficLight.values()) {
            t.display();
        }

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }
}