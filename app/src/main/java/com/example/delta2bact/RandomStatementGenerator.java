package com.example.delta2bact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStatementGenerator {
    private static final String[] RANDOM_STATEMENTS = {
            "Statement 1",
            "Statement 2",
            "Statement 3",
            "Statement 4",
            "Statement 5"
    };

    public static List<String> generateRandomStatements(int count) {
        List<String> statements = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(RANDOM_STATEMENTS.length);
            String statement = RANDOM_STATEMENTS[randomIndex];
            statements.add(statement);
        }

        return statements;
    }
}
