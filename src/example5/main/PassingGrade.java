package example5.main;

public class PassingGrade {
    public boolean passed(float grade) {
        if (grade < 1 || grade > 10)
            throw new IllegalArgumentException();
        return grade >= 5;
    }
}
