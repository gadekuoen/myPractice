package cn.wjqixige.Arrays.domain;

public class Student {

    private String name;
    private int score;

    public Student(){}

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString(){
        return String.format("Student(name: %s, score: %s)", name, score);
    }
}
