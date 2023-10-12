package personal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    private int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class Quiz_application_with_timer {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static List<Question> questions = new ArrayList<>();
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    private static void initializeQuestions() {
        // Add questions and correct answers here
        questions.add(new Question("What is the capital of France?", List.of("A) London", "B) Madrid", "C) Paris", "D) Berlin"), 2));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("A) Mars", "B) Venus", "C) Earth", "D) Jupiter"), 0));
        // Add more questions
    }

    private static void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);

            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            int timeLimit = 20; // Time limit for each question in seconds
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    showResult();
                }
            };
            timer.schedule(timerTask, timeLimit * 1000);

            System.out.print("Your answer (enter the option letter): ");
            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine().trim().toUpperCase();

            int correctOption = currentQuestion.getCorrectOption();
            if (userAnswer.equals("A") || userAnswer.equals("B") || userAnswer.equals("C") || userAnswer.equals("D")) {
                if (userAnswer.charAt(0) - 'A' == correctOption) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect.");
                }
            } else {
                System.out.println("Invalid option.");
            }

            currentQuestionIndex++;
            timer.cancel();
            startQuiz();
        } else {
            showResult();
        }
    }

    private static void showResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your Score: " + score + " out of " + questions.size());

        // Display a summary of correct/incorrect answers

        System.exit(0);
    }
}

