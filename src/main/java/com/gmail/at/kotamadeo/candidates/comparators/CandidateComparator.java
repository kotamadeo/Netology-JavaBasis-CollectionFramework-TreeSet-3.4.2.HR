package com.gmail.at.kotamadeo.candidates.comparators;

import com.gmail.at.kotamadeo.candidates.Candidate;

import java.util.Comparator;

public class CandidateComparator implements Comparator<Candidate> {
    @Override
    public int compare(Candidate o1, Candidate o2) {
        return Comparator.comparing(Candidate::getRelevance)
                .thenComparingInt(Candidate::getRating).reversed() // реверс релевантности
                .thenComparingInt(Candidate::getAge)
                .thenComparing(Candidate::getSex, Comparator.reverseOrder()) // короткий набор символов,ex male
                .thenComparing(Candidate::getName, Comparator.reverseOrder()) // короткий набор символов, ex 1: а 2: аб
                .compare(o1, o2);
    }
}
