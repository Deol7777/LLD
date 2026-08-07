
package Composition;

import java.util.ArrayList;
import java.util.List;

class Message {
    private String sender;
    private String text;
    private long timestamp;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
        this.timestamp = System.currentTimeMillis();
    }

    public void display() {
        // TODO: Print message in format "[sender]: text"
        System.out.println("[" + sender + "]" + ": " + text);
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }
}

class Conversation {
    private String title;
    private List<Message> messages;

    public Conversation(String title) {
        this.title = title;
        this.messages = new ArrayList<>();
    }

    public void sendMessage(String sender, String text) {
        // TODO: Create a Message internally and add it to the list
        messages.add(new Message(sender, text));
    }

    public void printHistory() {
        // TODO: Print conversation title and all messages
        System.out.println("--- " + title + " ---");
        for (Message message : messages) {
            message.display();
        }
    }

    public void delete() {
        // TODO: Clear all messages (they are destroyed with the conversation)
        messages.clear();
    }

    public int getMessageCount() {
        return messages.size();
    }

    public String getTitle() {
        return title;
    }

    public void forwardMessage(Conversation target, int messageIndex) {
        // TODO (Challenge): Copy message content into a NEW Message
        // in the target conversation. Don't move the original.
        Message oldMessage = messages.get(messageIndex);
        target.sendMessage(oldMessage.getSender(), oldMessage.getText());
    }
}

public class Main {
    public static void main(String[] args) {
        Conversation teamChat = new Conversation("Team Discussion");
        Conversation projectChat = new Conversation("Project Alpha");

        teamChat.sendMessage("Alice", "Hey team, standup in 5 minutes");
        teamChat.sendMessage("Bob", "Got it, joining now");
        teamChat.sendMessage("Alice", "Don't forget to update your tasks");

        projectChat.sendMessage("Charlie", "Deployment is scheduled for Friday");

        System.out.println("Before deletion:");
        teamChat.printHistory();
        System.out.println("Project chat has " + projectChat.getMessageCount() + " messages\n");

        // Challenge: forward a message
        teamChat.forwardMessage(projectChat, 2);
        System.out.println("After forwarding:");
        projectChat.printHistory();

        // Delete team chat - all its messages are destroyed
        teamChat.delete();
        System.out.println("\nAfter deleting team chat:");
        System.out.println("Team chat has " + teamChat.getMessageCount() + " messages");
        System.out.println("Project chat still has " + projectChat.getMessageCount() + " messages");
    }
}