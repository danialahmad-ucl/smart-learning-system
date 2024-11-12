package model.features;

import java.io.*;

public class UserFeature implements Feature {
    private boolean active;
    private final String name = "UserFeatures";
    private User user;

    public UserFeature(boolean active) {
        this.active = active;
        this.user = readFromFile();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void activate() {
        active = true;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public static void writeToFile(User user){
        User temp = readFromFile();

        if (temp != null) {
            return;
        }

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/UserData"));
            output.writeObject(user);
            output.close();
        }
        catch (IOException e) {
            System.out.println("Error Write User");
        }
    }

    public static User readFromFile() {
        User user = null;
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/UserData"));
            user =(User)input.readObject();
            input.close();
        }
        catch (IOException e){
            System.out.println("Error Read User");
        }
        catch (ClassNotFoundException e1){
            System.out.println("User Class Not found");
        }
        return user;
    }

    public User getUser(String username) {

        if (user != null && user.getUsername().equals(username))
            return user;
        return null;
    }

    public User getUser() {
        return user;
    }

    public void addUser(User user) {
        writeToFile(user);
        this.user = user;
    }



}