package palindrome.test;

import palindrome.main.Palindrome;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeTest {
    @Test
    void nullString() {
        Assertions.assertThat(Palindrome.test(null)).isFalse();
    }

    @Test
    void emptyString() {
        Assertions.assertThat(Palindrome.test("")).isTrue();
    }

    @Test
    void oneLetterString() {
        Assertions.assertThat(Palindrome.test("f")).isTrue();
    }

    @Test
    void twoIdenticalLettersString() {
        Assertions.assertThat(Palindrome.test("kk")).isTrue();
    }

    @Test
    void twoDifferentLettersString() {
        Assertions.assertThat(Palindrome.test("rs")).isFalse();
    }

    @Test
    void oddLengthValidWordLowercase() {
        Assertions.assertThat(Palindrome.test("arara")).isTrue();
    }

    @Test
    void evenLengthValidWordLowercase() {
        Assertions.assertThat(Palindrome.test("osso")).isTrue();
    }

    @Test
    void oddLengthInvalidWordLowercase() {
        Assertions.assertThat(Palindrome.test("coisa")).isFalse();
    }

    @Test
    void evenLengthInvalidWordLowercase() {
        Assertions.assertThat(Palindrome.test("teclas")).isFalse();
    }

    @Test
    void validWordUppercase() {
        Assertions.assertThat(Palindrome.test("RADAR")).isTrue();
    }

    @Test
    void validWordCaseInsensitive() {
        Assertions.assertThat(Palindrome.test("Rever")).isTrue();
    }

    @Test
    void validPhrase() {
        Assertions.assertThat(Palindrome.test("Irene ri.")).isTrue();
    }

    @Test
    void validLongPhrase() {
        Assertions.assertThat(Palindrome.test("Anotaram a data da maratona.")).isTrue();
    }

    @Test
    void validAccentedPhrase() {
        Assertions.assertThat(Palindrome.test("Luza Rocelina, a namorada do Manuel, leu na moda da romana: \"anil é cor azul\".")).isTrue();
    }

    @Test
    void validNumber() {
        Assertions.assertThat(Palindrome.test("2002")).isTrue();
    }

    @Test
    void invalidNumber() {
        Assertions.assertThat(Palindrome.test("2022")).isFalse();
    }

    @Test
    void validDate() {
        Assertions.assertThat(Palindrome.test("22/02/2022")).isTrue();
    }

    @Test
    void invalidDate() {
        Assertions.assertThat(Palindrome.test("21/09/2022")).isFalse();
    }
}
