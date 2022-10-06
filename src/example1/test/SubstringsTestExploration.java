package example1.test;

import example1.main.Substrings;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubstringsTestExploration {

    @Test
    void simpleCase() {
        Assertions.assertThat(Substrings.substringsBetween("abcd", "a", "d"))
                .isEqualTo(new String[] {"bc"});
    }

    @Test
    void manySubstrings() {
        Assertions.assertThat(Substrings.substringsBetween("abcdabcdab", "a", "d"))
                .isEqualTo(new String[] {"bc", "bc"});
    }

    @Test
    void openAndCloseTagsTharAreLongerThan1Char() {
        Assertions.assertThat(Substrings.substringsBetween("aabcddaabfddaa", "aa", "dd"))
                .isEqualTo(new String[] {"bc", "bf"});
    }

    @Test
    void strIsNullOrEmpty() {
        // Test 1: str is null
        Assertions.assertThat(Substrings.substringsBetween(null, "a", "b"))
                .isEqualTo(null);

        // Test 2: str is empty
        Assertions.assertThat(Substrings.substringsBetween("", "a", "b"))
                .isEqualTo(new String[]{});
    }

    @Test
    void openIsNullOrEmpty() {
        // Test 1: open is null
        Assertions.assertThat(Substrings.substringsBetween("abc", null, "c"))
                .isEqualTo(null);

        // Test 2: open is empty
        Assertions.assertThat(Substrings.substringsBetween("abc", "", "c"))
                .isEqualTo(null);
    }

    @Test
    void closeIsNullOrEmpty() {
        // Test 1: close is null
        Assertions.assertThat(Substrings.substringsBetween("abc", "a", null))
                .isEqualTo(null);

        // Test 2: close is empty
        Assertions.assertThat(Substrings.substringsBetween("abc", "a", ""))
                .isEqualTo(null);
    }

    @Test
    void theSingleCharOfStrMatchesOpenOrClose() {
        // Test 1: str matches open
        Assertions.assertThat(Substrings.substringsBetween("a", "a", "c"))
                .isEqualTo(null);

        // Test 1: str matches close
        Assertions.assertThat(Substrings.substringsBetween("c", "a", "c"))
                .isEqualTo(null);
    }

    @Test
    void theSingleCharacterOfStrDoesNotMatchAnything() {
        Assertions.assertThat(Substrings.substringsBetween("b", "a", "c"))
                .isEqualTo(null);
    }

    @Test
    void theSingleCharacterOfStrMatchesOpenAndClose() {
        Assertions.assertThat(Substrings.substringsBetween("b", "b", "b"))
                .isEqualTo(null);
    }

    @Test
    void strContainsNeitherOpenNorClose() {
        // open and close are single characters
        Assertions.assertThat(Substrings.substringsBetween("abc", "d", "e"))
                .isEqualTo(null);

        // open and close are longer than 1 character
        Assertions.assertThat(Substrings.substringsBetween("abcde", "fg", "hi"))
                .isEqualTo(null);
    }

    @Test
    void strContainsOnlyOpen() {
        // open and close are single characters
        Assertions.assertThat(Substrings.substringsBetween("abc", "a", "d"))
                .isEqualTo(null);

        // open and close are longer than 1 character
        Assertions.assertThat(Substrings.substringsBetween("abcde", "ab", "fg"))
                .isEqualTo(null);
    }

    @Test
    void strContainsOnlyClose() {
        // open and close are single characters
        Assertions.assertThat(Substrings.substringsBetween("abc", "d", "c"))
                .isEqualTo(null);

        // open and close are longer than 1 character
        Assertions.assertThat(Substrings.substringsBetween("abcde", "fg", "de"))
                .isEqualTo(null);
    }

    @Test
    void strContainsOpenAndClose() {
        // open and close are single characters
        Assertions.assertThat(Substrings.substringsBetween("abc", "a", "c"))
                .isEqualTo(new String[]{"b"});

        // open and close are longer than 1 character
        Assertions.assertThat(Substrings.substringsBetween("abcde", "ab", "de"))
                .isEqualTo(new String[]{"c"});
    }

    @Test
    void strContainsOpenAndCloseMultipleTimes() {
        // open and close are single characters
        Assertions.assertThat(Substrings.substringsBetween("abcadc", "a", "c"))
                .isEqualTo(new String[]{"b", "d"});

        // open and close are longer than 1 character
        Assertions.assertThat(Substrings.substringsBetween("abcdeabfde", "ab", "de"))
                .isEqualTo(new String[]{"c", "f"});
    }

    @Test
    void strContainsOpenAndCloseWithNoCharacterBetweenThem() {
        // open and close are single characters
        Assertions.assertThat(Substrings.substringsBetween("ab", "a", "b"))
                .isEqualTo(new String[]{""});

        // open and close are longer than 1 character
        Assertions.assertThat(Substrings.substringsBetween("abcd", "ab", "cd"))
                .isEqualTo(new String[]{""});
    }

}
