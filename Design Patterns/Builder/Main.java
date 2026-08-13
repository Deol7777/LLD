package builder;

import java.util.ArrayList;
import java.util.List;

class Email {
    private final String to;
    private final String subject;
    private final List<String> cc;
    private final List<String> bcc;
    private final String body;
    private final String priority;
    private final List<String> attachments;

    private Email(Builder builder) {
        this.to = builder.to;
        this.subject = builder.subject;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.body = builder.body;
        this.priority = builder.priority;
        this.attachments = builder.attachments;
    }

    @Override
    public String toString() {
        return "Email{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", cc=" + cc +
                ", bcc=" + bcc +
                ", body='" + body + '\'' +
                ", priority='" + priority + '\'' +
                ", attachments=" + attachments +
                '}';
    }

    public static class Builder {
        private final String to;
        private final String subject;
        private List<String> cc = new ArrayList<>();
        private List<String> bcc = new ArrayList<>();
        private String body;
        private String priority = "normal";
        private List<String> attachments = new ArrayList<>();

        public Builder(String to, String subject) {
            this.to = to;
            this.subject = subject;
        }

        public Builder cc(String cc) {
            // TODO: Add cc to the cc list
            this.cc.add(cc);
            return this;
        }

        public Builder bcc(String bcc) {
            // TODO: Add bcc to the bcc list
            this.bcc.add(bcc);
            return this;
        }

        public Builder body(String body) {
            // TODO: Set the body field
            this.body = body;
            return this;
        }

        public Builder priority(String priority) {
            // TODO: Set the priority field
            this.priority = priority;
            return this;
        }

        public Builder attachment(String attachment) {
            // TODO: Add attachment to the attachments list
            this.attachments.add(attachment);
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Email email1 = new Email.Builder("alice@example.com", "Meeting Tomorrow")
                .body("Let's meet at 10am in conference room B.")
                .build();

        Email email2 = new Email.Builder("bob@example.com", "Project Update")
                .cc("carol@example.com")
                .cc("dave@example.com")
                .bcc("manager@example.com")
                .body("Attached is the Q4 report.")
                .priority("high")
                .attachment("q4-report.pdf")
                .attachment("summary.xlsx")
                .build();

        System.out.println(email1);
        System.out.println();
        System.out.println(email2);
    }
}