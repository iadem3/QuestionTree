import java.io.*;
import java.util.*;

public class QuestionMain {
    public static final String QUESTION_FILE = "question.txt";

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the cse143 question program.");
        System.out.println();

        QuestionTree questions = new QuestionTree();
        if (questions.yesTo("Do you want to read in the previous tree?")) {
            questions.read(new Scanner(new File(QUESTION_FILE)));
        }
        System.out.println();

        do {
            System.out.println("Please think of an object for me to guess.");
            questions.askQuestions();
            System.out.println();
        } while (questions.yesTo("Do you want to go again?"));
        questions.write(new PrintStream(new File(QUESTION_FILE)));
    }
}