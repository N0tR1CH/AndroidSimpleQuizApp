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
    private static final String QUIZ_TAG = "MainActivity";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private static final String KEY_RIGHT_ANSWERS = "rightAnswers";
    private static final String KEY_WRONG_ANSWERS = "wrongAnswers";
    private static final String KEY_USER_ANSWERS = "userAnswers";
    private static final String KEY_USER_ANSWERED_CURRENT_QUESTION = "userAnsweredCurrentQuestion";

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
        Log.d(QUIZ_TAG, "onCreate() called");

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
            rightAnswers = savedInstanceState.getInt(KEY_RIGHT_ANSWERS);
            wrongAnswers = savedInstanceState.getInt(KEY_WRONG_ANSWERS);
            userAnswers = savedInstanceState.getInt(KEY_USER_ANSWERS);
            userAnsweredCurrentQuestion = savedInstanceState.getBoolean(
                KEY_USER_ANSWERED_CURRENT_QUESTION);

            if (userAnswers == questions.length) {
                String rightAnswersMessage = getString(R.string.right_answers) + " " + rightAnswers;
                String wrongAnswersMessage = getString(R.string.wrong_answers) + " " + wrongAnswers;
                rightAnswersTextView.setText(rightAnswersMessage);
                wrongAnswersTextView.setText(wrongAnswersMessage);
            }
        }

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
        Log.d(QUIZ_TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Inform in log that the onResume method is called
        Log.d(QUIZ_TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Inform in log that the onPause method is called
        Log.d(QUIZ_TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Inform in log that the onStop method is called
        Log.d(QUIZ_TAG, "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Inform in log that the onRestart method is called
        Log.d(QUIZ_TAG, "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Inform in log that the onDestroy method is called
        Log.d(QUIZ_TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Inform in log that the onSaveInstanceState method is called
        Log.d(QUIZ_TAG, "onSaveInstanceState() called");

        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
        outState.putInt(KEY_RIGHT_ANSWERS, rightAnswers);
        outState.putInt(KEY_WRONG_ANSWERS, wrongAnswers);
        outState.putInt(KEY_USER_ANSWERS, userAnswers);
        outState.putBoolean(KEY_USER_ANSWERED_CURRENT_QUESTION, userAnsweredCurrentQuestion);
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