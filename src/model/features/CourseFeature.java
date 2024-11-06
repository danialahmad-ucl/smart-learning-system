package model.features;

public class CourseFeature implements Feature {
    private boolean active;
    private final String name = "Courses";

    public CourseFeature(boolean active) {
        this.active = active;
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
    public String[] getLogEntries() {
        return new String[]{
                "buttonMedium viewCourses mainWindow-Center",
                "window courseList",
                "list basicCourses courseList-Center"
        };
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
