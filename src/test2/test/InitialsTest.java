package test2.test;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import org.apache.commons.lang3.text.WordUtils;
import org.assertj.core.api.Assertions;

import java.util.List;

public class InitialsTest {

    @Property
    void strMustNotBeEmpty(@ForAll @StringLength(value = 0) @Chars(value = {}) String str) {
        String processedWord = WordUtils.initials(str);
        Assertions.assertThat(processedWord).isEqualTo(str);
    }

    @Property
    void delimitersMustNotBeEmpty(@ForAll @NotEmpty String str, @ForAll @Size(value = 0) List<Character> delimiters) {
//        String processedWord = WordUtils.initials(str, delimiters.toArray());
//        Assertions.assertThat(processedWord).isEqualTo("");
    }

}
