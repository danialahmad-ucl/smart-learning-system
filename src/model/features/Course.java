package model.features;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    private String courseName;
    private Difficulty courseDifficulty;
    private boolean isPremium;
    private ArrayList<String> courseTopics;

    public Course(String courseName, Difficulty courseDifficulty, boolean isPremium, ArrayList<String> courseTopics) {
        this.courseName = courseName;
        this.courseDifficulty = courseDifficulty;
        this.isPremium = isPremium;
        this.courseTopics = courseTopics;
    }

    public String getName() {
        return courseName;
    }

    public Difficulty getDifficulty() {
        return courseDifficulty;
    }

    public ArrayList<String> getTopics() {
        return courseTopics;
    }

    public boolean isPremium() {
        return isPremium;
    }
}
