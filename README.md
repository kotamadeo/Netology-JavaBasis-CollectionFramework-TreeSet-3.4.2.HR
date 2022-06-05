# **Задача № 2 Система подбора кандидатов**

## **Цель**:
1. Создать систему подбора кандидатов по базам резюме. Необходимо хранить кандидатов таким образом, чтобы они сортировались в порядке убывания, по двум полям одновременно — по релевантности резюме и по оценке на собеседовании (т.е. среди двух кандидатов предпочтение отдаётся тому, у которого выше релевантность резюме, а среди равных по этому параметру тому, у кого выше оценка на собеседовании).

### *Пример*:
``` Пример 1
1. Класс, описывающий кандидата;

2. Создать множество, заполнить его кандидатами (общаться с пользователями не нужно);

3. Вывод кандидатов в отсортированном порядке.
```

### **Моя реализация**:
1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - класс, отвечающий за запуск программы, путем инициирования метода *start()* с инициированием внутри себя
  вспомогательных ```void``` методов: 
  * *printMenu()* - выводит меню команд программы на экран;
  * *addStudentInList()* - добавляет студента в список;
  * *printSortedCandidatesList()* - выводит список кандидатов на экран;
  * *removeCandidatesFromList()* - удаляет кандидата из списка;
  * *demonstration()* - демонстрация работы программы. 

#### Класс **Program**:
``` java
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
```

* **Candidate** - класс, описывающий кандидата. имеет переопреденный *toString()*, *hashCode()* и *equals()*, а также геттер-методы для получения доступа к полям.

#### Класс **Candidate**:
``` java   
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
```

* **CandidateComparator** - класс, реализующий логику сравнения путем реализации интерфейса ```Comparator```.

#### Класс **CandidateComparator**:
```java
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
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) {
        var program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Эта программа эмулирует работу системы сортировки резюме!
Возможные команды программы:
0 или выход: выход из программы.
1: добавить нового кандидата в список.
2: удалить кандидата из списка.
3: отсортировать список кандидатов.
4: демонстрация работоспособности.
```
* Демонстрация работы:
```
Отсортированный список кандидатов: релевантность -> рейтинг -> возраст -> пол -> длина имени
1. Эрик Темницкий, male, 25
Релевантность: 5.
Рейтинг: 5.

2. Гладушева Елена Викторовна, female, 30
Релевантность: 5.
Рейтинг: 5.

3. Иванов Иван Иванович, male, 30
Релевантность: 5.
Рейтинг: 3.

4. Джейн Фелисия Доун, female, 30
Релевантность: 4.
Рейтинг: 4.

5. Петрова Анна Сергеевна, female, 32
Релевантность: 4.
Рейтинг: 4.

6. Александрина Кира Андреевна, female, 32
Релевантность: 4.
Рейтинг: 4.

7. Владыкин Алексей Александрович, male, 40
Релевантность: 4.
Рейтинг: 2.

8. Котов Иван Васильевич, male, 30
Релевантность: 3.
Рейтинг: 3.

9. Базанова Жанна Викторовна, female, 30
Релевантность: 3.
Рейтинг: 3.

10. Сергеев Иван Иванович, male, 28
Релевантность: 2.
Рейтинг: 2.
```