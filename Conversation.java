public class Conversation {
    private final User user1;
    private final User user2;
    private final Message[] messages; // 1D Array to store messages
    private int messageCount;
    private static final int MAX_MESSAGES = 100;
    //Default Constructor
    public Conversation() {
        this.user1 = new User();
        this.user2 = new User();
        this.messages = new Message[MAX_MESSAGES];
        this.messageCount = 0;
    }
    //Parameterized Constructor
    public Conversation(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new Message[MAX_MESSAGES];
        this.messageCount = 0;
    }
    //Copy constructor(Deep Copy)
    public Conversation(Conversation other) {
        this.user1 = new User(other.user1);
        this.user2 = new User(other.user2);
        this.messages = new Message[MAX_MESSAGES];
        for (int i = 0; i < other.messageCount; i++) {
            this.messages[i] = new Message(other.messages[i]);
        }
        this.messageCount = other.messageCount;
    }
    //Method Overloading
    public void sendMessage(String content) {
        sendMessage(this.user1, this.user2, content);
    }
    //Method Overloading
    public void sendMessage(User sender, User receiver, String content) {
        if (messageCount < MAX_MESSAGES) {
            if ((sender.equals(user1) && receiver.equals(user2)) ||
                (sender.equals(user2) && receiver.equals(user1))) {
                messages[messageCount] = new Message(sender.getUsername(), receiver.getUsername(), content);
                messageCount++;
            } else {
                System.out.println("Sender and receiver do not match the conversation participants.");
            }
        } else {
            System.out.println("Conversation has reached maximum message capacity.");
        }
    }
    //Read all messages
    public void readAllMessages() {
        if (messageCount == 0) {
            System.out.println("No messages in this conversation.");
            return;
        }
        for (int i = 0; i < messageCount; i++) {
            System.out.println(messages[i]);
            messages[i].setRead(true);
            messages[i].setSeen(true);
            System.out.println("---------------------------");
        }
    }
    public void readMessageById(int id) {
        for (int i = 0; i < messageCount; i++) {
            if (messages[i].getMessageId() == id) {
                System.out.println(messages[i]);
                messages[i].setRead(true);
                messages[i].setSeen(true);
                return;
            }
        }
        System.out.println("Message with ID " + id + " not found.");
    }
    //Delete message by id
    public void deleteMessageById(int id) {
        for (int i = 0; i < messageCount; i++) {
            if (messages[i].getMessageId() == id) {
                // Shift messages left to fill the gap
                for (int j = i; j < messageCount - 1; j++) {
                    messages[j] = messages[j + 1];
                }
                messages[messageCount - 1] = null;
                messageCount--;
                System.out.println("Message with ID " + id + " deleted.");
                return;
            }
        }
        System.out.println("Message with ID " + id + " not found.");
    }
    //Mark all As Seen
    public void markAllAsSeen() {
        for (int i = 0; i < messageCount; i++) {
            messages[i].setSeen(true);
        }
    }
    //Getters
     public User getUser1() {
        return user1;
    }
    public User getUser2() {
        return user2;
    }
    //Tostring Method
    @Override
    public String toString() {
        return "Conversation between " + user1.getUsername() + " and " + user2.getUsername();
    }
}
