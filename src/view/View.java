package view;

import controller.Controller;
import model.Model;
import model.features.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class View extends JFrame{

    int Page_Width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int Page_Height = Toolkit.getDefaultToolkit().getScreenSize().height;

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
        // Set up the main frame
        this.setSize((int) (Page_Width/3.2), (int) (Page_Height/(1.6)));
        this.setTitle("Learning System From Future");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Header_P = new JPanel();
        ShowHeader();

        Menu_P = new JPanel();
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
        if ( user != null)
            title = user.getUsername();

        if (User_B == null) {
            User_B = new JButton(title);
            User_B.setBackground(Color.BLUE);
            User_B.setForeground(Color.WHITE);
            User_B.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            User_B.setFont(new Font("Serif", Font.BOLD, 15));
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
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();

        Menu_P.setLayout(null);
        Menu_P.setBackground(Color.lightGray);

        Menu_L = new JLabel("MAIN MENU");
        Menu_L.setFont(new Font("Serif", Font.BOLD, 30));
        Menu_L.setBounds(145, 60, 300, 30);
        Menu_L.setForeground(new Color(51, 51, 51));
        Menu_P.add(Menu_L);

        Course_B = new JButton("COURSE");
        Course_B.setBackground(Color.ORANGE);
        Course_B.setBounds(100, 160, 120, 80);
        Course_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(Course_B);

        Help_B = new JButton("HELP");
        Help_B.setBackground(Color.ORANGE);
        Help_B.setBounds(240, 160, 120, 80);;
        Help_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(Help_B);

        PracticeMode_B = new JButton("PRACTICE MODE");
        PracticeMode_B.setBackground(Color.ORANGE);
        PracticeMode_B.setBounds(100, 280, 260, 80);
        PracticeMode_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(PracticeMode_B);

        Exit_B = new JButton("EXIT");
        Exit_B.setBounds(100, 400, 80, 25);
        Exit_B.setBackground(Color.RED);
        Exit_B.setForeground(Color.BLACK);
        Exit_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(Exit_B);
        add(Menu_P, BorderLayout.CENTER);

        MyListener listener = new MyListener();
        Course_B.addActionListener(listener);
        PracticeMode_B.addActionListener(listener);
        Help_B.addActionListener(listener);
        Exit_B.addActionListener(listener);
        model.getCurrentLogsList().add("[View] Main menu displayed");
    }

    public void  showCoursePanel()
    {
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();

        Menu_P.setLayout(null);
        Menu_P.setBackground(Color.lightGray);

        Menu_L = new JLabel("COURSE MENU");
        Menu_L.setFont(new Font("Serif", Font.BOLD, 30));
        Menu_L.setBounds(145, 60, 300, 30);
        Menu_L.setForeground(new Color(51, 51, 51));
        Menu_P.add(Menu_L);

        String[] difficultyLevels = {Difficulty.EASY.toString(), Difficulty.MEDIUM.toString(), Difficulty.HARD.toString() };
        difficultyDropdown = new JComboBox<>(difficultyLevels);
        difficultyDropdown.setBounds(100, 160, 260, 50);
        Menu_P.add(difficultyDropdown);

        DisplayCourse_B = new JButton("COURSES");
        DisplayCourse_B.setBackground(Color.ORANGE);
        DisplayCourse_B.setBounds(100, 280, 120, 80);
        DisplayCourse_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(DisplayCourse_B);

        PremiumCourse_B = new JButton("PREMIUM");
        PremiumCourse_B.setBackground(Color.ORANGE);
        PremiumCourse_B.setBounds(240, 280, 120, 80);
        PremiumCourse_B.setFont(new Font("Serif", Font.BOLD, 15));
        if (model.userFeature.getUser() != null) Menu_P.add(PremiumCourse_B);

        Course_Back_B = new JButton("BACK");
        Course_Back_B.setBounds(100, 400, 80, 25);
        Course_Back_B.setBackground(Color.BLUE);
        Course_Back_B.setForeground(Color.WHITE);
        Course_Back_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(Course_Back_B);

        add(Menu_P, BorderLayout.CENTER);

        MyListener listener = new MyListener();
        DisplayCourse_B.addActionListener(listener);
        PremiumCourse_B.addActionListener(listener);
        Course_Back_B.addActionListener(listener);
        model.getCurrentLogsList().add("[View] Course menu displayed");
    }

    public void ShowCoursesPanel(ArrayList<Course> coursesList, String difficulty, boolean isPremium) {
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();

        Menu_P.setLayout(new BorderLayout());

        String[] columnNames = { "Course Name", "Difficulty", "Premium", "View" };

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
        table.getColumn("View").setCellEditor(new ButtonEditor(new JCheckBox(), table, coursesList));

        JScrollPane scrollPane = new JScrollPane(table);

        Course_B = new JButton("BACK");
        Course_B.setBounds(100, 400, 80, 25);
        Course_B.setBackground(Color.BLUE);
        Course_B.setForeground(Color.WHITE);
        Course_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(Course_B);
        Menu_P.add(scrollPane, BorderLayout.CENTER);

        Menu_P.repaint();
        Menu_P.revalidate();

        MyListener listener = new MyListener();
        Course_B.addActionListener(listener);
        model.getCurrentLogsList().add("[View] Courses displayed");
    }

    public void ShowCourseTopicsPanel(Course course, String topic, int index) {
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();

        Menu_P.setLayout(new BorderLayout());

        model.getCurrentLogsList().add("[View] Course topics displayed");
        JLabel courseTitle = new JLabel("Topics for: " + course.getName());
        courseTitle.setFont(new Font("Serif", Font.BOLD, 24));
        Menu_P.add(courseTitle, BorderLayout.NORTH);

        JList<String> topicList = new JList<>(course.getTopics().toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(topicList);
        Menu_P.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 400, 80, 25);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Serif", Font.BOLD, 15));

        ArrayList<Course> coursesList = CourseFeature.readFromFile();
        backButton.addActionListener(e -> ShowCoursesPanel(coursesList, course.getDifficulty().toString(), course.isPremium()));
        buttonPanel.add(backButton);

        Menu_P.add(buttonPanel, BorderLayout.SOUTH);
        Menu_P.repaint();
        Menu_P.revalidate();

    }

    public void showPracticeModePanel() {
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();

        Menu_P.setLayout(null);
        Menu_P.setBackground(Color.lightGray);

        Menu_L = new JLabel("PRACTICE MODE");
        Menu_L.setFont(new Font("Serif", Font.BOLD, 30));
        Menu_L.setBounds(145, 60, 300, 30);
        Menu_L.setForeground(new Color(51, 51, 51));
        Menu_P.add(Menu_L);

        Flashcard_B = new JButton("Flash Cards");
        Flashcard_B.setBackground(Color.ORANGE);
        Flashcard_B.setBounds(100, 160, 120, 80);
        Flashcard_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(Flashcard_B);

        MCQs_B = new JButton("MCQs");
        MCQs_B.setBackground(Color.ORANGE);
        MCQs_B.setBounds(240, 160, 120, 80);;
        MCQs_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(MCQs_B);

        Course_Back_B = new JButton("BACK");
        Course_Back_B.setBounds(100, 400, 80, 25);
        Course_Back_B.setBackground(Color.BLUE);
        Course_Back_B.setForeground(Color.WHITE);
        Course_Back_B.setFont(new Font("Serif", Font.BOLD, 15));
        Menu_P.add(Course_Back_B);

        MyListener listener = new MyListener();
        Flashcard_B.addActionListener(listener);
        MCQs_B.addActionListener(listener);
        Course_Back_B.addActionListener(listener);

        Menu_P.revalidate();
        Menu_P.repaint();
        model.getCurrentLogsList().add("[View] Practice mode displayed");
    }

    private void displayFlashCards() {
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();

        Menu_P.setLayout(null);
        ArrayList<FlashCard> flashCards = PracticeFeature.readFlashCardFromFile();
        final int[] currentFlashCardIndex = {0};

        Runnable updateFlashCardPanel = new Runnable() {
            @Override
            public void run() {
                FlashCard currentCard = flashCards.get(currentFlashCardIndex[0]);

                JLabel questionLabel = new JLabel("Question: " + currentCard.getQuestion());
                questionLabel.setFont(new Font("Serif", Font.PLAIN, 16));
                questionLabel.setBounds(95, 30, 400, 20);
                JLabel answerLabel = new JLabel("Answer: " + currentCard.getAnswer());
                answerLabel.setBounds(95, 80, 400, 20);
                answerLabel.setFont(new Font("Serif", Font.PLAIN, 14));

                Menu_P.removeAll();

                Menu_P.add(questionLabel);
                Menu_P.add(answerLabel);

                JButton previousButton = new JButton("Previous");
                previousButton.setBackground(Color.BLACK);
                previousButton.setForeground(Color.WHITE);
                previousButton.setBounds(100, 280, 120, 80);
                previousButton.setFont(new Font("Serif", Font.BOLD, 15));

                JButton nextButton = new JButton("Next");
                nextButton.setBackground(Color.BLACK);
                nextButton.setForeground(Color.WHITE);
                nextButton.setBounds(240, 280, 120, 80);;
                nextButton.setFont(new Font("Serif", Font.BOLD, 15));

                previousButton.addActionListener(e -> {
                    if (currentFlashCardIndex[0] > 0) {
                        currentFlashCardIndex[0]--;
                        run();
                    }
                });

                // Next button action
                nextButton.addActionListener(e -> {
                    if (currentFlashCardIndex[0] < flashCards.size() - 1) {
                        currentFlashCardIndex[0]++;
                        run();
                    }
                });

                Menu_P.add(previousButton);
                Menu_P.add(nextButton);

                PracticeMode_Back_B = new JButton("Back");
                PracticeMode_Back_B.setBackground(Color.BLUE);
                PracticeMode_Back_B.setForeground(Color.WHITE);
                PracticeMode_Back_B.setFont(new Font("Serif", Font.BOLD, 15));
                PracticeMode_Back_B.setBounds(100, 400, 80, 25);
                PracticeMode_Back_B.addActionListener(e -> showPracticeModePanel());

                Menu_P.add(PracticeMode_Back_B);

                Menu_P.revalidate();
                Menu_P.repaint();
            }
        };

        updateFlashCardPanel.run();

        Menu_P.revalidate();
        Menu_P.repaint();
        model.getCurrentLogsList().add("[View] Flash cards displayed");
    }


    private void displayMCQs() {
        Menu_P.removeAll();
        Menu_P.repaint();
        Menu_P.revalidate();

        Menu_P.setLayout(null);

        PracticeMode_Back_B = new JButton("Back");
        PracticeMode_Back_B.setBounds(100, 400, 80, 25);
        PracticeMode_Back_B.setBackground(Color.BLUE);
        PracticeMode_Back_B.setForeground(Color.WHITE);
        PracticeMode_Back_B.setFont(new Font("Serif", Font.BOLD, 15));
        PracticeMode_Back_B.addActionListener(new MyListener());

        ArrayList<MCQs> mcqs = PracticeFeature.readMCQsFromFile();

        final int[] currentMCQIndex = {0};

        Runnable updateMCQPanel = new Runnable() {
            @Override
            public void run() {
                MCQs currentMCQ = mcqs.get(currentMCQIndex[0]);
                JLabel questionLabel = new JLabel("Question: " + currentMCQ.getQuestion());
                questionLabel.setFont(new Font("Serif", Font.PLAIN, 16));
                questionLabel.setBounds(95, 30, 400, 20);

                JPanel optionsPanel = new JPanel();
                optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
                optionsPanel.setBounds(95, 100, 400, 180);
                optionsPanel.setBackground(Color.lightGray);

                for (String option : currentMCQ.getOptions()) {
                    JButton optionButton = new JButton(option);
                    optionButton.setBackground(Color.YELLOW);
                    optionButton.setForeground(Color.BLACK);
                    optionButton.setFont(new Font("Serif", Font.BOLD, 15));
                    optionsPanel.add(optionButton);
                    optionButton.addActionListener(e -> {
                        if (currentMCQ.getAnswer() == option) {
                            optionButton.setBackground(Color.GREEN);
                        }
                    });
                }

                Menu_P.removeAll();

                Menu_P.add(questionLabel);
                Menu_P.add(optionsPanel);

                JButton previousButton = new JButton("Previous");
                previousButton.setBackground(Color.BLACK);
                previousButton.setForeground(Color.WHITE);
                previousButton.setBounds(100, 280, 120, 80);
                previousButton.setFont(new Font("Serif", Font.BOLD, 15));

                JButton nextButton = new JButton("Next");
                nextButton.setBackground(Color.BLACK);
                nextButton.setForeground(Color.WHITE);
                nextButton.setBounds(240, 280, 120, 80);;
                nextButton.setFont(new Font("Serif", Font.BOLD, 15));

                // Previous button action
                previousButton.addActionListener(e -> {
                    if (currentMCQIndex[0] > 0) {
                        currentMCQIndex[0]--;
                        run(); // Update with new MCQ
                    }
                });

                nextButton.addActionListener(e -> {
                    if (currentMCQIndex[0] < mcqs.size() - 1) {
                        currentMCQIndex[0]++;
                        run(); // Update with new MCQ
                    }
                });

                Menu_P.add(previousButton);
                Menu_P.add(nextButton);
                Menu_P.add(PracticeMode_Back_B);
                Menu_P.revalidate();
                Menu_P.repaint();
            }
        };
        updateMCQPanel.run();
        Menu_P.revalidate();
        Menu_P.repaint();

        model.getCurrentLogsList().add("[View] MCQs displayed");
    }


    public class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == User_B){
                String userName = JOptionPane.showInputDialog(null, "Enter userName to enter : ", "Sign In/Sign Up", JOptionPane.QUESTION_MESSAGE);

                User user = model.userFeature.getUser(userName);
                if (user == null) {
                    String password = JOptionPane.showInputDialog(null, "Enter password to enter : ", "Sign Up", JOptionPane.QUESTION_MESSAGE);
                    User newUser = new User(userName, password);
                    model.userFeature.addUser(newUser);
                    JOptionPane.showMessageDialog(null, "User SignUp Successful", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    model.getCurrentLogsList().add("[View] User SignUp Successful");
                }
                else {
                    JOptionPane.showMessageDialog(null, "User SignIn Successful", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    model.getCurrentLogsList().add("[View] User SignIn Successful");
                }
                ShowHeader();
            }
            else if (e.getSource() == Course_B) {
                showCoursePanel();
            } else if (e.getSource() == DisplayCourse_B) {
                ArrayList<Course> coursesList = CourseFeature.readFromFile();
                ShowCoursesPanel(coursesList, (String) difficultyDropdown.getSelectedItem(), false);
            } else if (e.getSource() == PremiumCourse_B) {
                ArrayList<Course> coursesList = CourseFeature.readFromFile();
                ShowCoursesPanel(coursesList, (String) difficultyDropdown.getSelectedItem(), true);
            } else if (e.getSource() == Course_Back_B) {
                ShowMainMenu();
            } else if (e.getSource() == PracticeMode_B) {
                showPracticeModePanel();
            } else if (e.getSource() == Flashcard_B) {
                displayFlashCards();
            } else if (e.getSource() == MCQs_B) {
                displayMCQs();
            } else if (e.getSource() == PracticeMode_Back_B) {
                showPracticeModePanel();
            } else if (e.getSource() == Help_B) {
                model.getCurrentLogsList().add("[View] Help");
            } else if (e.getSource() == Exit_B) {
                System.exit(0);
                model.getCurrentLogsList().add("[View] Exit");
            }
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setBackground(Color.ORANGE);
            setForeground(Color.BLACK);
            setFont(new Font("Serif", Font.BOLD, 15));
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table; // Reference to the table
        private ArrayList<Course> coursesList; // Reference to the courses list

        public ButtonEditor(JCheckBox checkBox, JTable table, ArrayList<Course> coursesList) {
            super(checkBox);
            this.table = table;
            this.coursesList = coursesList; // Store reference to courses list
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
            model.getCurrentLogsList().add("[View] ButtonEditor created");
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Handle "View" button click logic here
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Course selectedCourse = coursesList.get(selectedRow);
                    ShowCourseTopicsPanel(selectedCourse, selectedCourse.getTopics().get(0), 0);
                }
            }
            model.getCurrentLogsList().add("[View] ButtonEditor getCellEditorValue");
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}

