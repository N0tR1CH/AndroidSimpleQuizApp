package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int currentIndex = 0;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView rightAnswersTextView;
    private TextView wrongAnswersTextView;
    private TextView questionTextView;
    private int rightAnswers;
    private int wrongAnswers;
    private int userAnswers;
    private boolean userAnsweredCurrentQuestion;

    private Question[] questions = new Question[] {
        new Question(R.string.q_python, false),
        new Question(R.string.q_oop, true),
        new Question(R.string.q_cpp, true),
        new Question(R.string.q_mth, false),
        new Question(R.string.q_onotation, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        rightAnswersTextView = findViewById(R.id.right_answers);
        wrongAnswersTextView = findViewById(R.id.wrong_answers);
        rightAnswers = 0;
        wrongAnswers = 0;
        userAnswers = 0;
        userAnsweredCurrentQuestion = false;

        rightAnswersTextView.setText("");
        wrongAnswersTextView.setText("");

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAnsweredCurrentQuestion) {
                    return;
                }
                checkAnswerCorrectness(true);
                currentIndex = (currentIndex + 1) % questions.length;
                userAnsweredCurrentQuestion= true;
                userAnswers++;
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAnsweredCurrentQuestion) {
                    return;
                }
                checkAnswerCorrectness(false);
                currentIndex = (currentIndex + 1) % questions.length;
                userAnsweredCurrentQuestion = true;
                userAnswers++;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
                userAnsweredCurrentQuestion = false;

                if (userAnswers == questions.length) {
                    String rightAnswersMessage = getString(R.string.right_answers) + " " + rightAnswers;
                    String wrongAnswersMessage = getString(R.string.wrong_answers) + " " + wrongAnswers;
                    rightAnswersTextView.setText(rightAnswersMessage);
                    wrongAnswersTextView.setText(wrongAnswersMessage);
                    userAnswers = 0;
                    rightAnswers = 0;
                    wrongAnswers = 0;
                }

                if (userAnswers == 1) {
                    rightAnswersTextView.setText("");
                    wrongAnswersTextView.setText("");
                }
            }
        });
        setNextQuestion();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId;

        if (userAnswer == correctAnswer) {
            rightAnswers++;
            resultMessageId = R.string.correct_answer;
        } else {
            wrongAnswers++;
            resultMessageId = R.string.incorrect_answer;
        }

        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }
}