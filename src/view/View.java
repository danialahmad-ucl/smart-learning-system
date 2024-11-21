package view;

import controller.Controller;
import model.Model;
import model.features.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View extends JFrame{

    int Page_Width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int Page_Height = Toolkit.getDefaultToolkit().getScreenSize().height;
    String[] difficultyLevels = {Difficulty.EASY.toString(), Difficulty.MEDIUM.toString(), Difficulty.HARD.toString() };
    String[] columnNames = { "Course Name", "Difficulty", "Premium", "View" };

    Model model;
    Controller controller;

    private JPanel Menu_P, Header_P;
    private JLabel Menu_L;
    private JButton Course_B, User_B, PracticeMode_B, Help_B, Exit_B, DisplayCourse_B, PremiumCourse_B, Course_Back_B, MCQs_B, Flashcard_B, PracticeMode_Back_B;
    private JComboBox<String> difficultyDropdown;

    public View(Controller controllerLayer, Model systemModel) {

        model = systemModel;
        controller = controllerLayer;
        model.getCurrentLogsList().add("[View] View object created");

        this.setSize((int) (Page_Width/3.2), (int) (Page_Height/(1.6)));
        this.setTitle("Learning System From Future");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Header_P = new JPanel();
        Menu_P = new JPanel();

        ShowHeader();
        ShowMainMenu();
        setVisible(true);
    }

    public boolean showView() {
        setVisible(true);
        model.getCurrentLogsList().add("[View] View displayed");
        return true;
    }

    public boolean hideView() {
        setVisible(false);
        model.getCurrentLogsList().add("[View] View hidden");
        return true;
    }

    public void ShowHeader() {
        Header_P.removeAll();
        Header_P.repaint();
        Header_P.revalidate();

        Header_P.setBackground(Color.white);
        Header_P.setPreferredSize(new Dimension(500, 50));
        Header_P.setLayout(new FlowLayout(FlowLayout.RIGHT));

        String title = "Sign In";
        User user = model.userFeature.getUser();
        if ( user != null) title = user.getUsername();

        if (User_B == null) {
            User_B = createButton(title, Color.WHITE, Color.BLUE, -1, -1, -1, -1);
            User_B.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        } else {
            User_B.setText(title);
        }

        Header_P.add(User_B);
        add(Header_P, BorderLayout.NORTH);

        MyListener listener = new MyListener();
        User_B.addActionListener(listener);

        model.getCurrentLogsList().add("[View] Header displayed");
    }

    public void ShowMainMenu() {
        removeAllAndRepaint();

        Menu_P.setLayout(null);
        Menu_P.setBackground(Color.lightGray);

        Menu_L = createLabel("MAIN MENU", new Color(51, 51, 51), new Font("Serif", Font.BOLD, 30),145, 60, 300, 30);
        Course_B = createButton("COURSE", Color.BLACK, Color.ORANGE, 100, 160, 120, 80);
        Help_B = createButton("HELP", Color.BLACK, Color.ORANGE, 240, 160, 120, 80);
        PracticeMode_B = createButton("PRACTICE MODE", Color.BLACK ,Color.ORANGE, 100, 280, 260, 80);
        Exit_B = createButton("EXIT", Color.BLACK, Color.RED, 100, 400, 80, 25);

        Menu_P.add(Menu_L);
        Menu_P.add(Course_B);
        Menu_P.add(Help_B);
        Menu_P.add(PracticeMode_B);
        Menu_P.add(Exit_B);

        MyListener listener = new MyListener();
        Course_B.addActionListener(listener);
        PracticeMode_B.addActionListener(listener);
        Help_B.addActionListener(listener);
        Exit_B.addActionListener(listener);

        add(Menu_P, BorderLayout.CENTER);
        model.getCurrentLogsList().add("[View] Main menu displayed");
    }

    public void ShowCoursePanel(){
        removeAllAndRepaint();

        Menu_P.setLayout(null);
        Menu_P.setBackground(Color.lightGray);

        difficultyDropdown = new JComboBox<>(difficultyLevels);
        difficultyDropdown.setBounds(100, 160, 260, 50);

        Menu_L = createLabel("COURSE MENU", new Color(51, 51, 51), new Font("Serif", Font.BOLD, 30),145, 60, 300, 30);
        DisplayCourse_B = createButton("COURSES", Color.BLACK, Color.ORANGE, 100, 280, 120, 80);
        PremiumCourse_B = createButton("PREMIUM", Color.BLACK, Color.ORANGE, 240, 280, 120, 80);
        Course_Back_B = createButton("BACK", Color.WHITE, Color.BLUE, 100, 400, 80, 25);

        Menu_P.add(Menu_L);
        Menu_P.add(difficultyDropdown);
        Menu_P.add(DisplayCourse_B);
        if (model.userFeature.getUser() != null)
            Menu_P.add(PremiumCourse_B);
        Menu_P.add(Course_Back_B);

        MyListener listener = new MyListener();
        DisplayCourse_B.addActionListener(listener);
        PremiumCourse_B.addActionListener(listener);
        Course_Back_B.addActionListener(listener);

        add(Menu_P, BorderLayout.CENTER);
        model.getCurrentLogsList().add("[View] Course menu displayed");
    }

    public void ShowCourseTopicsPanel(Course course, String topic, int index) {
        removeAllAndRepaint();

        JList<String> topicList = new JList<>(course.getTopics().toArray(new String[0]));
        ArrayList<Course> coursesList = CourseFeature.readFromFile();

        Menu_P.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JLabel courseTitle = createLabel("Topics for: " + course.getName(), Color.BLACK, new Font("Serif", Font.BOLD, 24), -1, -1,-1, -1);
        JScrollPane scrollPane = new JScrollPane(topicList);
        JButton backButton = createButton("Back", Color.WHITE, Color.BLUE, 100, 400, 80, 25);

        backButton.addActionListener(e -> ShowCoursesPanel(coursesList, course.getDifficulty().toString(), course.isPremium()));
        buttonPanel.add(backButton);

        Menu_P.add(courseTitle, BorderLayout.NORTH);
        Menu_P.add(scrollPane, BorderLayout.CENTER);
        Menu_P.add(buttonPanel, BorderLayout.SOUTH);
        model.getCurrentLogsList().add("[View] Course topics displayed");
    }

    public void ShowCoursesPanel(ArrayList<Course> coursesList, String difficulty, boolean isPremium) {
        removeAllAndRepaint();
        Menu_P.setLayout(new BorderLayout());

        ArrayList<Course> newCourseList = new ArrayList<>();
        for (Course course : coursesList) {
            if (course.getDifficulty().toString().equals(difficulty) && course.isPremium() == isPremium) {
                newCourseList.add(course);
            }
        }

        Object[][] data = new Object[newCourseList.size()][4];
        for (int i = 0; i < newCourseList.size(); i++) {
            Course course = newCourseList.get(i);
            if (isPremium == course.isPremium() && difficulty == course.getDifficulty().toString()) {
                data[i][0] = course.getName();
                data[i][1] = course.getDifficulty().toString();
                data[i][2] = course.isPremium() ? "Yes" : "No";
                data[i][3] = "View";
            }
        }

        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        table.getColumn("View").setCellRenderer(new ButtonRenderer());
        table.getColumn("View").setCellEditor(new ButtonEditor(this, new JCheckBox(), table, coursesList));

        JScrollPane scrollPane = new JScrollPane(table);
        JLabel noCoursesLabel = createLabel("No courses found", Color.BLACK, new Font("Serif", Font.BOLD, 20), -1, -1, -1, -1);
        Course_B = createButton("BACK", Color.WHITE, Color.BLUE, 100, 400, 80, 25);

        Menu_P.add(Course_B);
        if (newCourseList.size() == 0)
            Menu_P.add(noCoursesLabel, BorderLayout.NORTH);
        Menu_P.add(scrollPane, BorderLayout.CENTER);

        MyListener listener = new MyListener();
        Course_B.addActionListener(listener);

        model.getCurrentLogsList().add("[View] Courses displayed");
    }

    public void ShowPracticeModePanel() {
        removeAllAndRepaint();

        Menu_P.setLayout(null);
        Menu_P.setBackground(Color.lightGray);

        Menu_L = createLabel("PRACTICE MODE", new Color(51, 51, 51), new Font("Serif", Font.BOLD, 30),145, 60, 300, 30);
        Flashcard_B = createButton("Flash Cards", Color.BLACK, Color.ORANGE,100, 160, 120, 80);
        MCQs_B = createButton("MCQs", Color.BLACK, Color.ORANGE,240, 160, 120, 80);
        Course_Back_B = createButton("BACK", Color.WHITE, Color.BLUE,100, 400, 80, 25);

        Menu_P.add(Menu_L);
        Menu_P.add(Flashcard_B);
        Menu_P.add(MCQs_B);
        Menu_P.add(Course_Back_B);

        MyListener listener = new MyListener();
        Flashcard_B.addActionListener(listener);
        MCQs_B.addActionListener(listener);
        Course_Back_B.addActionListener(listener);

        model.getCurrentLogsList().add("[View] Practice mode displayed");
    }

    private void displayFlashCards() {
        removeAllAndRepaint();

        ArrayList<FlashCard> flashCards = PracticeFeature.readFlashCardFromFile();
        final int[] currentFlashCardIndex = {0};

        Menu_P.setLayout(null);

        Runnable updateFlashCardPanel = new Runnable() {
            @Override
            public void run() {
                removeAllAndRepaint();
                FlashCard currentCard = flashCards.get(currentFlashCardIndex[0]);

                JLabel questionLabel = createLabel("Question: " + currentCard.getQuestion(), Color.BLACK, new Font("Serif", Font.PLAIN, 16), 95, 30, 400, 20);
                JLabel answerLabel = createLabel("Answer: " + currentCard.getAnswer(), Color.BLACK, new Font("Serif", Font.PLAIN, 14), 95, 80, 400, 20);
                JButton previousButton = createButton("Previous", Color.WHITE, Color.BLACK, 100, 280, 120, 80);
                JButton nextButton = createButton("Next", Color.WHITE, Color.BLACK, 240, 280, 120, 80);
                PracticeMode_Back_B = createButton("Back", Color.WHITE, Color.BLUE, 100, 400, 80, 25);

                previousButton.addActionListener(e -> {
                    if (currentFlashCardIndex[0] > 0) {
                        currentFlashCardIndex[0]--;
                        run();
                    }
                });

                nextButton.addActionListener(e -> {
                    if (currentFlashCardIndex[0] < flashCards.size() - 1) {
                        currentFlashCardIndex[0]++;
                        run();
                    }
                });

                PracticeMode_Back_B.addActionListener(e -> {
                    ShowPracticeModePanel();
                });

                Menu_P.add(questionLabel);
                Menu_P.add(answerLabel);
                Menu_P.add(previousButton);
                Menu_P.add(nextButton);
                Menu_P.add(PracticeMode_Back_B);
            }
        };

        updateFlashCardPanel.run();
        model.getCurrentLogsList().add("[View] Flash cards displayed");
    }

    private void displayMCQs() {
        removeAllAndRepaint();

        Menu_P.setLayout(null);

        PracticeMode_Back_B = createButton("Back", Color.WHITE, Color.BLUE, 100, 400, 80, 25);
        PracticeMode_Back_B.addActionListener(new MyListener());

        ArrayList<MCQs> mcqs = PracticeFeature.readMCQsFromFile();
        final int[] currentMCQIndex = {0};

        Runnable updateMCQPanel = new Runnable() {
            @Override
            public void run() {
                removeAllAndRepaint();

                MCQs currentMCQ = mcqs.get(currentMCQIndex[0]);

                JPanel optionsPanel = new JPanel();
                optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
                optionsPanel.setBounds(95, 100, 400, 180);
                optionsPanel.setBackground(Color.lightGray);

                JLabel questionLabel = createLabel("Question: " + currentMCQ.getQuestion(), Color.BLACK, new Font("Serif", Font.PLAIN, 16), 95, 30, 400, 20);
                JButton reset_B = createButton("Retry", Color.WHITE, Color.BLUE, 380, 280, 80, 80);
                JButton previousButton = createButton("Previous", Color.WHITE, Color.BLACK, 100, 280, 120, 80);
                JButton nextButton = createButton("Next", Color.WHITE, Color.BLACK, 240, 280, 120, 80);

                for (String option : currentMCQ.getOptions()) {
                    JButton optionButton = createButton(option, Color.BLACK, Color.YELLOW, -1, -1, -1, -1);
                    optionsPanel.add(optionButton);
                    optionButton.addActionListener(e -> {
                        if (currentMCQ.getAnswer() == option) {
                            optionButton.setBackground(Color.GREEN);
                        }
                        else {
                            optionButton.setBackground(Color.RED);
                            previousButton.setEnabled(false);
                            nextButton.setEnabled(false);
                            optionsPanel.add(reset_B);
                            Menu_P.add(reset_B);
                        }
                    });
                }

                reset_B.addActionListener(e -> {
                    run();
                });

                previousButton.addActionListener(e -> {
                    if (currentMCQIndex[0] > 0) {
                        currentMCQIndex[0]--;
                        run();
                    }
                });

                nextButton.addActionListener(e -> {
                    if (currentMCQIndex[0] < mcqs.size() - 1) {
                        currentMCQIndex[0]++;
                        run();
                    }
                });

                Menu_P.add(questionLabel);
                Menu_P.add(optionsPanel);
                Menu_P.add(previousButton);
                Menu_P.add(nextButton);
                Menu_P.add(PracticeMode_Back_B);
            }
        };
        updateMCQPanel.run();
        model.getCurrentLogsList().add("[View] MCQs displayed");
    }

    // Method that creates button taking button name foreground and background color bounds as parameters and returns the button
    private JButton createButton(String buttonName, Color foregroundColor, Color backgroundColor, int x, int y, int width, int height) {
        JButton button = new JButton(buttonName);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Serif", Font.BOLD, 15));
        if (x != -1 && y != -1 && width != -1 && height != -1)
            button.setBounds(x, y, width, height);

        return button;
    }

    //Method to create label with text, font, bounds, color and return the label and if bounds are not provided then don't set bounds use default parameters for bounds
    private JLabel createLabel(String text, Color color, Font font,int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        if (x != -1 && y != -1 && width != -1 && height != -1)
            label.setBounds(x, y, width, height);

        return label;
    }

    //Method to removeall and repaint the panel
    private void removeAllAndRepaint() {
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();
    }

    public class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(User_B)){
                if (model.userFeature.getUser() == null) {
                    String userName = JOptionPane.showInputDialog(null, "Enter userName to enter : ", "Sign In/Sign Up", JOptionPane.QUESTION_MESSAGE);
                    String password = JOptionPane.showInputDialog(null, "Enter password to enter : ", "Sign Up", JOptionPane.QUESTION_MESSAGE);
                    User newUser = new User(userName, password);
                    model.userFeature.addUser(newUser);
                    JOptionPane.showMessageDialog(null, "User SignUp Successful", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    model.getCurrentLogsList().add("[View] User SignUp Successful");
                }
                else {
                    int view = JOptionPane.showConfirmDialog(null, "Do you want to SignOut and Delete Account?", "Sign Out", JOptionPane.YES_NO_OPTION);

                    if (view == 0) {
                        JOptionPane.showMessageDialog(null, "User SignOut Successful \nAccount Deleted", "Successful", JOptionPane.INFORMATION_MESSAGE);
                        model.getCurrentLogsList().add("[View] User SignOut Successful");
                        model.deleteUser();
                    }
                }

                ShowHeader();
            }
            else if (e.getSource() == Course_B) {
                ShowCoursePanel();
            } else if (e.getSource() == DisplayCourse_B) {
                ArrayList<Course> coursesList = CourseFeature.readFromFile();
                ShowCoursesPanel(coursesList, (String) difficultyDropdown.getSelectedItem(), false);
            } else if (e.getSource() == PremiumCourse_B) {
                ArrayList<Course> coursesList = CourseFeature.readFromFile();
                ShowCoursesPanel(coursesList, (String) difficultyDropdown.getSelectedItem(), true);
            } else if (e.getSource() == Course_Back_B) {
                ShowMainMenu();
            } else if (e.getSource() == PracticeMode_B) {
                ShowPracticeModePanel();
            } else if (e.getSource() == Flashcard_B) {
                displayFlashCards();
            } else if (e.getSource() == MCQs_B) {
                displayMCQs();
            } else if (e.getSource() == PracticeMode_Back_B) {
                ShowPracticeModePanel();
            } else if (e.getSource() == Help_B) {
                model.getCurrentLogsList().add("[View] Help");
            } else if (e.getSource() == Exit_B) {
                System.exit(0);
                model.getCurrentLogsList().add("[View] Exit");
            }
        }
    }
}