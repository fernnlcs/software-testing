package example4.test;

import example4.main.LeftPadUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LeftPadUtilsTest {

    @ParameterizedTest
    @MethodSource("generator")
    void test(String originalStr, int size, String padStr, String expectedStr) {
        Assertions.assertThat(LeftPadUtils.leftPad(originalStr, size, padStr))
                .isEqualTo(expectedStr);
    }

    static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(null, 10, "-", null), // t1
                Arguments.of("", 5, "-", "-----"), // t2
                Arguments.of("abc", -1, "-", "abc"), // t3
                Arguments.of("abc", 5, null, "  abc"), // t4
                Arguments.of("abc", 5, "", "  abc"), // t5
                Arguments.of("abc", 5, "-", "--abc"), // t6
                Arguments.of("abc", 3, "-", "abc"), // t7
                Arguments.of("abc", 0, "-", "abc"), // t8
                Arguments.of("abc", 2, "-", "abc"), // t9
                Arguments.of("abc", 5, "--", "--abc"), // t10
                Arguments.of("abc", 5, "---", "--abc"), // t11
                Arguments.of("abc", 5, "-", "--abc") // t12
        );
    }
}
