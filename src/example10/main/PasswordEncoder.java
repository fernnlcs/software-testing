package example10.main;

public interface PasswordEncoder {
    String encode(String password); //an encoder to turn a clear-text password into a password hash
}
