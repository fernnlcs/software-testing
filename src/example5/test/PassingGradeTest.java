package example5.test;

import example5.main.PassingGrade;

import net.jqwik.api.*;
import net.jqwik.api.constraints.FloatRange;
import org.assertj.core.api.Assertions;

public class PassingGradeTest {

    public final PassingGrade pg = new PassingGrade();

    @Property
    void fail(@ForAll @FloatRange(min = 1f, max = 5.0f, maxIncluded = false) float grade) {
        Assertions.assertThat(pg.passed(grade)).isFalse();
    }

    @Property
    void pass(@ForAll @FloatRange(min = 5.0f, max = 10.0f, maxIncluded = true) float grade) {
        Assertions.assertThat(pg.passed(grade)).isTrue();
    }

    @Property
    void invalid(@ForAll("invalidGrades") float grade) {
        Assertions.assertThatThrownBy(() -> {
            pg.passed(grade);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Provide
    private Arbitrary<Float> invalidGrades() {
        return Arbitraries.oneOf(
                Arbitraries.floats().lessThan(1f),
                Arbitraries.floats().greaterThan(10f)
        );
    }
}
