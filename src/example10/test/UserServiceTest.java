package example10.test;

import example10.main.PasswordEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class UserServiceTest {

    PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    @ParameterizedTest
    @CsvSource({"8b8Q*4", "z6$C06", "1z8T2$", "^f8C14", "409Nc#"})
    void validPasswords(String password) {
        passwordEncoder.encode(password);
        Mockito.when(passwordEncoder.encode(password)).thenReturn("true");
    }

    @ParameterizedTest
    @CsvSource({"nuzsqd", "HPEJHO", "273800", "$@&#$#", "zqWOnW", "abc"})
    void invalidPasswords(String password) {
        passwordEncoder.encode(password);
        Mockito.when(passwordEncoder.encode(password)).thenReturn("false");
    }

    @ParameterizedTest
    @CsvSource({"8b8Q*4", "z6$C06", "1z8T2$", "^f8C14", "409Nc#"})
    void verifyTimes(String password) {
        passwordEncoder.encode(password);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(password);
    }

    @ParameterizedTest
    @CsvSource({"8b8Q*4", "z6$C06", "1z8T2$", "^f8C14", "409Nc#"})
    void arguments(String password) {
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

        passwordEncoder.encode(password);

        Mockito.verify(passwordEncoder).encode(passwordCaptor.capture());

        Assertions.assertEquals(password, passwordCaptor.getValue());
    }
}
