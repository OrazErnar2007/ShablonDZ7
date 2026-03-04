import java.util.*;

interface IMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
    void removeUser(User user);
}

class ChatRoom implements IMediator {

    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " присоединился к чату");
    }

    public void removeUser(User user) {
        users.remove(user);
        System.out.println(user.getName() + " покинул чат");
    }

    public void sendMessage(String message, User sender) {
        if(!users.contains(sender)) {
            System.out.println("Вы не участник чата!");
            return;
        }

        for(User user : users) {
            if(user != sender) {
                user.receive(sender.getName() + ": " + message);
            }
        }
    }
}

class User {

    private String name;
    private IMediator mediator;

    public User(String name, IMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() { return name; }

    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println(name + " получил сообщение -> " + message);
    }
}

public class ChatMediator {
    public static void main(String[] args) {

        ChatRoom chat = new ChatRoom();

        User ernar = new User("Ernar", chat);
        User aigerim = new User("Aigerim", chat);
        User nurlan = new User("Nurlan", chat);

        chat.addUser(ernar);
        chat.addUser(aigerim);
        chat.addUser(nurlan);

        ernar.send("Всем привет!");
        nurlan.send("Привет!");
    }
}
