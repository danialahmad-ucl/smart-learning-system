package model.features;

import java.io.*;
import java.util.ArrayList;

public class PracticeFeature implements Feature, Serializable {
    private boolean active;
    private final String name = "PracticeMode";
    private ArrayList<FlashCard> flashCards;
    private ArrayList<MCQs> msqs;

    public PracticeFeature(boolean active)
    {
        this.active = active;
        flashCards = readFlashCardFromFile();
    }

    public static void writeMCQsToFile(MCQs mcqs){
        ArrayList<MCQs> list = readMCQsFromFile();
        list.add(mcqs);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/MCQsData"));
            output.writeObject(list);
            output.close();
        }
        catch (IOException e) {
            System.out.println("Error Write MCQs");
        }
    }

    public static ArrayList<MCQs> readMCQsFromFile() {
        ArrayList<MCQs> list = new ArrayList<MCQs>();
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/MCQsData"));
            list = (ArrayList<MCQs>) input.readObject();
            input.close();
        }
        catch (IOException e){
            System.out.println("Error Read MCQs");
        }
        catch (ClassNotFoundException e1){
            System.out.println("Class FlashCard Not found");
        }
        return list;
    }

    public static void writeFlashCardToFile(FlashCard flashCard){
        ArrayList<FlashCard> list = readFlashCardFromFile();
        list.add(flashCard);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/FlashCardData"));
            output.writeObject(list);
            output.close();
        }
        catch (IOException e) {
            System.out.println("Error Write FlashCard");
        }
    }

    public static ArrayList<FlashCard> readFlashCardFromFile() {
        ArrayList<FlashCard> list = new ArrayList<FlashCard>();
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/FlashCardData"));
            list = (ArrayList<FlashCard>) input.readObject();
            input.close();
        }
        catch (IOException e){
            System.out.println("Error Read FlashCard");
        }
        catch (ClassNotFoundException e1){
            System.out.println("Class FlashCard Not found");
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