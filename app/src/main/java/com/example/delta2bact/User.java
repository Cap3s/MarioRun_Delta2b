package com.example.delta2bact;

import java.util.List;

public class User {
    private List<String> statements;

    public User(List<String> statements) {
        this.statements = statements;
    }
    public List<String> getStatements() {
        return statements;
    }
}
