package com.brainfluence.psychiatry.model;

public class ProblemModel {
    public String name;

    public ProblemModel() {
        this.name = null;
    }

    public ProblemModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
