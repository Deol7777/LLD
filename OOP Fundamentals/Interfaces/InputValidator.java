package Interfaces;

import java.util.List;

interface Validator {
    boolean validate(String input);
}

class EmailValidator implements Validator {

    public boolean validate(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '@')
                return true;
        }
        return false;
    }
}

class PasswordValidator implements Validator {
    public boolean validate(String input) {
        return input.length() >= 8 ? true : false;
    }
}

class RegistrationService {
    private List<Validator> list;

    public RegistrationService(List<Validator> list) {
        this.list = list;
    }

    public void register(String input) {
        for (Validator validator : list) {
            if (validator.validate(input) == false) {
                System.out.println("Validation Failed");
                return;
            }
        }
        System.out.println("Validation Passed");
    }
}

class InputValidatorDemo {
    public static void main(String[] args) {
        List<Validator> emailValidators = List.of(new EmailValidator());
        RegistrationService emailReg = new RegistrationService(emailValidators);
        emailReg.register("user@example.com"); // Should pass
        emailReg.register("invalid-email");     // Should fail

        List<Validator> passwordValidators = List.of(new PasswordValidator());
        RegistrationService passReg = new RegistrationService(passwordValidators);
        passReg.register("strongpassword"); // Should pass
        passReg.register("short");           // Should fail

        // --- extra tests ---
        System.out.println("\n--- extra tests ---");

        Validator email = new EmailValidator();
        Validator pass = new PasswordValidator();

        // EmailValidator
        check("email valid has @", email.validate("a@b.com"));
        check("email invalid no @", !email.validate("invalid-email"));
        check("email empty fails", !email.validate(""));

        // PasswordValidator (>= 8 chars)
        check("password 8 chars passes", pass.validate("12345678"));
        check("password 7 chars fails", !pass.validate("1234567"));
        check("password long passes", pass.validate("strongpassword"));

        // RegistrationService single validator
        check("reg email pass -> Passed",
                reg(new EmailValidator(), "user@example.com").equals("Validation Passed"));
        check("reg email fail -> Failed",
                reg(new EmailValidator(), "invalid-email").equals("Validation Failed"));

        // RegistrationService multiple validators (AND: all must pass)
        List<Validator> both = List.of(new EmailValidator(), new PasswordValidator());
        check("reg both pass (email+8chars)",
                reg(both, "user@example.com").equals("Validation Passed"));
        check("reg both fail: has @ but too short",
                reg(both, "a@b.co").equals("Validation Failed"));
        check("reg both fail: long but no @",
                reg(both, "strongpassword").equals("Validation Failed"));

        // empty validator list -> passes (nothing to fail)
        check("reg empty list -> Passed",
                reg(List.of(), "anything").equals("Validation Passed"));
    }

    private static void check(String name, boolean pass) {
        System.out.println((pass ? "PASS" : "FAIL") + " - " + name);
    }

    private static String reg(Validator v, String input) {
        return reg(List.of(v), input);
    }

    private static String reg(List<Validator> validators, String input) {
        java.io.PrintStream original = System.out;
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(buf));
        new RegistrationService(validators).register(input);
        System.setOut(original);
        return buf.toString().trim();
    }
}