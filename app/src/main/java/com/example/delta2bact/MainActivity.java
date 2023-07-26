package com.example.delta2bact;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000;
    private TextView statementTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        statementTextView = findViewById(R.id.statementTextView);

        List<String> randomStatements = RandomStatementGenerator.generateRandomStatements(5);
        User user = new User(randomStatements);

        String randomStatement = getRandomStatement(user);
        statementTextView.setText(randomStatement);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, CharMapSelect.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }

    private String getRandomStatement(User user) {
        List<String> randomStatements = user.getStatements();
        Random random = new Random();
        int randomIndex = random.nextInt(randomStatements.size());
        return randomStatements.get(randomIndex);
    }

}
