package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

        // Inform in log that the onCreate method is called
        Log.d("MainActivity", "onCreate() called");

        // Inform in a toast that the onCreate method is called
        Toast.makeText(this, "Welcome to the quiz!", Toast.LENGTH_SHORT).show();

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

    @Override
    protected void onStart() {
        super.onStart();

        // Inform in log that the onStart method is called
        Log.d("MainActivity", "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Inform in log that the onResume method is called
        Log.d("MainActivity", "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Inform in log that the onPause method is called
        Log.d("MainActivity", "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Inform in log that the onStop method is called
        Log.d("MainActivity", "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Inform in log that the onRestart method is called
        Log.d("MainActivity", "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Inform in log that the onDestroy method is called
        Log.d("MainActivity", "onDestroy() called");
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