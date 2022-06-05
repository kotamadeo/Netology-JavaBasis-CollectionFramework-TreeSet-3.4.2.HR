package com.gmail.at.kotamadeo.candidates;

import java.util.Objects;

public class Candidate {
    private String name;
    private String sex;
    private int age;
    private int relevance;
    private int rating;

    public Candidate(String name, String sex, int age, int relevance, int rating) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        if (relevance >= 0 && relevance <= 5) {
            this.relevance = relevance;
        } else {
            if (relevance <= 0) {
                this.relevance = 1;
            } else {
                this.relevance = 5;
            }
        }
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            if (rating <= 0) {
                this.rating = 1;
            } else {
                this.rating = 5;
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public int getRelevance() {
        return relevance;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relevance, rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var candidate = (Candidate) o;
        return Objects.equals(relevance, candidate.relevance) && Objects.equals(rating, candidate.rating);
    }

    @Override
    public String toString() {
        return name + ", " + sex + ", " + age + "\nРелевантность: " + relevance + ".\nРейтинг: " + rating + ".";
    }
}
