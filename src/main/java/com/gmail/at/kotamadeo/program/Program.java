package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.candidates.Candidate;
import com.gmail.at.kotamadeo.candidates.comparators.CandidateComparator;
import com.gmail.at.kotamadeo.utils.Utils;

import java.util.*;

public class Program {
    private final Scanner scanner = new Scanner(System.in);
    private final Set<Candidate> candidates = new TreeSet<>(new CandidateComparator());

    public void start() {
        String input;
        String[] allInput;
        while (true) {
            try {
                printMenu();
                input = scanner.nextLine();
                if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                    scanner.close();
                    break;
                } else {
                    var operationNumber = Integer.parseInt(input);
                    switch (operationNumber) {
                        case 1:
                            System.out.println(Utils.ANSI_BLUE + "Введите данные кандидата: " +
                                    "ФИО, пол, возраст, релевантность и рейтинг через запятую." + Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(",");
                            candidates.add(new Candidate(allInput[0], allInput[1], Integer.parseInt(allInput[2]),
                                    Integer.parseInt(allInput[3]), Integer.parseInt(allInput[4])));
                            break;
                        case 2:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы удалить кандидата из списка введите его " +
                                    "номер" + Utils.ANSI_RESET);
                            printSortedCandidatesList(candidates);
                            input = scanner.nextLine();
                            removeCandidatesFromList(Integer.parseInt(input));
                            break;
                        case 3:
                            printSortedCandidatesList(candidates);
                            break;
                        case 4:
                            demonstration();
                            break;
                    }
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(Utils.ANSI_RED + "Ошибка ввода!" + Utils.ANSI_RESET);
            }
        }
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Эта программа эмулирует работу системы сортировки резюме!" +
                Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: выход из программы.");
        System.out.println("1: добавить нового кандидата в список.");
        System.out.println("2: удалить кандидата из списка.");
        System.out.println("3: отсортировать список кандидатов.");
        System.out.println("4: демонстрация работоспособности.");
    }

    private void demonstration() {
        candidates.add(new Candidate("Джейн Фелисия Доун", "female", 30, 4, 4));
        candidates.add(new Candidate("Эрик Темницкий", "male", 25, 5, 5));
        candidates.add(new Candidate("Петрова Анна Сергеевна", "female", 32, 4, 4));
        candidates.add(new Candidate("Александрина Кира Андреевна", "female", 32, 4, 4));
        candidates.add(new Candidate("Сергеев Иван Иванович", "male", 28, 2, 2));
        candidates.add(new Candidate("Иванов Иван Иванович", "male", 30, 5, 3));
        candidates.add(new Candidate("Гладушева Елена Викторовна", "female", 30, 5, 5));
        candidates.add(new Candidate("Владыкин Алексей Александрович", "male", 40, 4, 2));
        candidates.add(new Candidate("Базанова Жанна Викторовна", "female", 30, 3, 3));
        candidates.add(new Candidate("Котов Иван Васильевич", "male", 30, 3, 3));
        printSortedCandidatesList(candidates);
    }

    private static void printSortedCandidatesList(Set<Candidate> candidates) {
        if (!candidates.isEmpty()) {
            System.out.println(Utils.ANSI_BLUE + "Отсортированный список кандидатов: " +
                    "релевантность -> рейтинг -> возраст -> пол -> длина имени" + Utils.ANSI_RESET);
            var counter = 0;
            for (Candidate candidate : candidates) {
                counter++;
                System.out.printf("%s%s. %s%s%n%n", Utils.ANSI_CYAN, counter, candidate, Utils.ANSI_RESET);
            }
        } else {
            System.out.println(Utils.ANSI_RED + "Список кандидатов пуст!" + Utils.ANSI_RESET);
        }

    }

    private void removeCandidatesFromList(int index) {
        List<Candidate> candidateList = new LinkedList<>();
        if (!candidates.isEmpty()) {
            var candidateForRemove = candidateList.get(index - 1);
            candidateList.addAll(candidates);
            candidates.clear();
            candidateList.remove(index - 1);
            System.out.println(Utils.ANSI_GREEN + "Кандидат " + candidateForRemove + " успешно удален из списка!" +
                    Utils.ANSI_RESET);
            candidates.addAll(candidateList);
        } else {
            System.out.println(Utils.ANSI_RED + "Список кандидатов пуст!" + Utils.ANSI_RESET);
        }
    }
}
