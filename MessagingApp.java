import java.util.Scanner;

public class MessagingApp {
    private final Conversation[][] conversations; // 2D Array[][]
    private final User[] users;
    private int userCount;
    private static final int MAX_USERS = 10;
    public MessagingApp() {
        users = new User[MAX_USERS];
        conversations = new Conversation[MAX_USERS][MAX_USERS];
        userCount = 0;
    }
    //Add User
    public void addUser(String username) {
        if (userCount >= MAX_USERS) {
            System.out.println("Maximum user limit reached.");
            return;
        }
        if (findUserIndex(username) != -1) {
            System.out.println("Username already exists. Choose a different username.");
            return;
        }
        users[userCount] = new User(username);
        userCount++;
        System.out.println("User '" + username + "' added.");
    }
    private int findUserIndex(String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equalsIgnoreCase(username)) {
                return i;
            }
        }
        return -1;
    }
    //Get Conversation
    private Conversation getConversation(String user1, String user2) {
        int index1 = findUserIndex(user1);
        int index2 = findUserIndex(user2);

        if (index1 == -1 || index2 == -1) {
            System.out.println("One or both users not found.");
            return null;
        }

        if (index1 == index2) {
            System.out.println("Cannot create a conversation with the same user.");
            return null;
        }
        int lowerIndex = Math.min(index1, index2);
        int higherIndex = Math.max(index1, index2);

        if (conversations[lowerIndex][higherIndex] == null) {
            conversations[lowerIndex][higherIndex] = new Conversation(users[lowerIndex], users[higherIndex]);
            System.out.println("New conversation created between " + users[lowerIndex].getUsername() + " and " + users[higherIndex].getUsername() + ".");
        }

        return conversations[lowerIndex][higherIndex];
    }
    //Main Menu
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Messaging App ===");
            System.out.println("1. Add User");
            System.out.println("2. Send Message");
            System.out.println("3. Read All Messages");
            System.out.println("4. Read Specific Message");
            System.out.println("5. Delete Message");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine().trim();
                    if (username.isEmpty()) {
                        System.out.println("Username cannot be empty.");
                    } else {
                        addUser(username);
                    }
                    break;
                case 2:
                    System.out.print("Enter sender username: ");
                    String sender = scanner.nextLine().trim();
                    System.out.print("Enter receiver username: ");
                    String receiver = scanner.nextLine().trim();
                    System.out.print("Enter message content: ");
                    String content = scanner.nextLine().trim();

                    if (sender.isEmpty() || receiver.isEmpty() || content.isEmpty()) {
                        System.out.println("Sender, receiver, and content cannot be empty.");
                        break;
                    }
                    Conversation convo = getConversation(sender, receiver);
                    if (convo != null) {
                        convo.sendMessage(getUserByUsername(sender), getUserByUsername(receiver), content);
                        System.out.println("Message sent.");
                    }
                    break;
                case 3:
                    System.out.print("Enter sender username: ");
                    String sUser = scanner.nextLine().trim();
                    System.out.print("Enter receiver username: ");
                    String rUser = scanner.nextLine().trim();
                    Conversation readConvo = getConversation(sUser, rUser);
                    if (readConvo != null) {
                        readConvo.readAllMessages();
                    }
                    break;
                case 4:
                    System.out.print("Enter sender username: ");
                    String rsUser = scanner.nextLine().trim();
                    System.out.print("Enter receiver username: ");
                    String rrUser = scanner.nextLine().trim();
                    System.out.print("Enter Message ID to read: ");
                    if (scanner.hasNextInt()) {
                        int msgId = scanner.nextInt();
                        scanner.nextLine(); 
                        Conversation readMsgConvo = getConversation(rsUser, rrUser);
                        if (readMsgConvo != null) {
                            readMsgConvo.readMessageById(msgId);
                        }
                    } else {
                        System.out.println("Invalid Message ID. It should be a number.");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    System.out.print("Enter sender username: ");
                    String dsUser = scanner.nextLine().trim();
                    System.out.print("Enter receiver username: ");
                    String drUser = scanner.nextLine().trim();
                    System.out.print("Enter Message ID to delete: ");
                    if (scanner.hasNextInt()) {
                        int delId = scanner.nextInt();
                        scanner.nextLine();
                        Conversation delConvo = getConversation(dsUser, drUser);
                        if (delConvo != null) {
                            delConvo.deleteMessageById(delId);
                        }
                    } else {
                        System.out.println("Invalid Message ID. It should be a number.");
                        scanner.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Exiting application.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 6.");
            }
        }
    }
    private User getUserByUsername(String username) {
        int index = findUserIndex(username);
        if (index != -1) {
            return users[index];
        }
        return null;
    }
    // Main Method
    public static void main(String[] args) {
        MessagingApp app = new MessagingApp();
        app.start();
    }
}
