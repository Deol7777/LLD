package Enums;

enum HttpStatus {

    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private int code;
    private String message;

    private HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSucess() {
        return this.code < 400 ? true : false;
    }

    public void display() {
        System.out.print(code + " " + message);
    }

    public static HttpStatus fromCode(int c) {
        switch (c) {
            case 200:
                return OK;
            case 400:
                return BAD_REQUEST;
            case 404:
                return NOT_FOUND;
            case 500:
                return INTERNAL_SERVER_ERROR;
            default:
                return null;

        }
    }

    public static void main(String[] args) {
        HttpStatus.OK.display();
        System.out.println();
        HttpStatus.NOT_FOUND.display();
        System.out.println();

        System.out.println("Is 200 success? " + HttpStatus.OK.isSucess());
        System.out.println("Is 404 success? " + HttpStatus.NOT_FOUND.isSucess());

        HttpStatus found = HttpStatus.fromCode(500);
        if (found != null) {
            System.out.print("Found by code 500: ");
            found.display();
            System.out.println();
        }

        // --- extra tests ---
        System.out.println("\n--- extra tests ---");
        check("fromCode(400) == BAD_REQUEST", HttpStatus.fromCode(400) == HttpStatus.BAD_REQUEST);
        check("fromCode(999) == null", HttpStatus.fromCode(999) == null);

        boolean roundTrip = true;
        for (HttpStatus s : HttpStatus.values()) {
            if (HttpStatus.fromCode(s.getCode()) != s) {
                roundTrip = false;
                break;
            }
        }
        check("all values round-trip via fromCode", roundTrip);
    }

    private static void check(String name, boolean pass) {
        System.out.println((pass ? "PASS" : "FAIL") + " - " + name);
    }

}