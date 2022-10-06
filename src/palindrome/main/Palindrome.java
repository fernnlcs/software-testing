package palindrome.main;

import java.text.Normalizer;

public class Palindrome {
    public static boolean test(String word) {
        if (word == null) {
            return false;
        }

        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.toLowerCase().replaceAll("[^a-z0-9]", "");

        for (int i = 0; i < word.length() / 2f; i++) {
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
