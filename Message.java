import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private int messageId;
    private String sender;
    private String receiver;
    private String content;
    private boolean isRead;
    private boolean isSeen;
    //Default Constructor
    public Message() {
        this.messageId = idCounter.incrementAndGet();
        this.sender = "Unknown";
        this.receiver = "Unknown";
        this.content = "";
        this.isRead = false;
        this.isSeen = false;
    }
    //Parameterized Constructor
    public Message(String sender, String receiver, String content) {
        this.messageId = idCounter.incrementAndGet();
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.isRead = false;
        this.isSeen = false;
    }
    //Copy Constructor(Deep Copy)
    public Message(Message other) {
        this.messageId = other.messageId;
        this.sender = other.sender;
        this.receiver = other.receiver;
        this.content = new String(other.content);
        this.isRead = other.isRead;
        this.isSeen = other.isSeen;
    }

    //Encapsulation
    public int getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return isRead;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    @Override
    public String toString() {
        return "Message ID: " + messageId +
               "\nFrom: " + sender +
               "\nTo: " + receiver +
               "\nContent: " + content +
               "\nRead: " + isRead +
               "\nSeen: " + isSeen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        return messageId == other.messageId;
    }
}
