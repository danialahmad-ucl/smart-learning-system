package model.features;

import java.io.*;
import java.util.ArrayList;

public class CourseFeature implements Feature, Serializable {
    private boolean active;
    private final String name = "Courses";
    private ArrayList<Course> courses;

    public CourseFeature(boolean active)
    {
        this.active = active;
        courses = readFromFile();
    }

    public static void writeToFile(Course course){
        ArrayList<Course> list = readFromFile();
        list.add(course);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/CourseData"));
            output.writeObject(list);
            output.close();
        }
        catch (IOException e) {
            System.out.println("Error Write Course");
        }
    }

    public static ArrayList<Course> readFromFile() {
        ArrayList<Course> list = new ArrayList<Course>();
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/CourseData"));
            list = (ArrayList<Course>) input.readObject();
            input.close();
        }
        catch (IOException e){
            System.out.println("Error Read Course");
        }
        catch (ClassNotFoundException e1){
            System.out.println("Class Course Not found");
        }
        return list;
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
}
